package com.stanete.chicfy.model;

import com.stanete.chicfy.data.UserPreferences;
import com.stanete.chicfy.data.UsersApiClient;
import com.stanete.chicfy.data.UsersRemover;
import com.stanete.chicfy.exception.NetworkErrorException;
import com.stanete.chicfy.exception.UnknownErrorException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stanete
 */
public class UsersRepository {

  private static final int RESULTS = 40;

  private final UsersApiClient usersApiClient;
  private final UserPreferences userPreferences;

  private final List<User> users;
  private final List<User> deletedUsers;
  private final UsersRemover usersRemover;

  public UsersRepository(UsersApiClient usersApiClient, UserPreferences userPreferences) {
    this.usersApiClient = usersApiClient;
    this.userPreferences = userPreferences;
    this.usersRemover = new UsersRemover();
    this.users = new ArrayList<>();
    this.deletedUsers = userPreferences.getDeletedUsers();
  }

  public Result<List<User>, ResultError> getUsers(int page) {

    if (page == 0) {
      users.clear();
    }

    try {
      List<User> pagedUsers = usersApiClient.getUsers(RESULTS, page, null);

      // Remove deleted users.
      usersRemover.removeDeletedUsers(deletedUsers, pagedUsers);

      // Remove duplicated users.
      usersRemover.removeDuplicatedUsers(users, pagedUsers);

      users.addAll(pagedUsers);

      return Result.success(pagedUsers);
    } catch (UnknownErrorException e) {
      e.printStackTrace();
      return Result.error(ResultError.UNKNOWN_ERROR);
    } catch (NetworkErrorException e) {
      e.printStackTrace();
      return Result.error(ResultError.NO_INTERNET_CONNECTION);
    }
  }

  public Result<User, ResultError> getUserByUsername(String username) {
    for (User user : users) {
      if (user.getLogin().getUsername().equals(username)) {
        return Result.success(user);
      }
    }
    return Result.error(ResultError.ITEM_NOT_FOUND);
  }

  public Result<User, ResultError> deleteUser(User user) {
    users.remove(user);
    deletedUsers.add(user);
    userPreferences.addDeletedUser(user);
    return Result.success(user);
  }

  public Result<List<User>, ResultError> getUsersByFilter(String filter) {
    List<User> filteredUsers = new ArrayList<>();

    if (filter.isEmpty()) {
      return Result.success(users);
    } else {
      for (User user : users) {
        if (user.getLogin().getUsername().toLowerCase().contains(filter) || user.getName()
            .getFirst()
            .toLowerCase()
            .contains(filter) || user.getName().getLast().toLowerCase().contains(filter)) {
          filteredUsers.add(user);
        }
      }

      return Result.success(filteredUsers);
    }
  }
}
