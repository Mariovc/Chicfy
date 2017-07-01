package com.stanete.chicfy.data;

import com.stanete.chicfy.model.User;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by @stanete
 */
public class UsersRemover {

  @Inject public UsersRemover() {
  }

  /**
   * Makes sure that a new list of users does not contain users present in the original list..
   */
  public List<User> removeDuplicatedUsers(List<User> allUsers, List<User> newUsers) {
    for (User user : allUsers) {
      for (User newUser : newUsers) {
        if (newUser.getLogin().getUsername().equals(user.getLogin().getUsername())) {
          newUsers.remove(newUser);
          break;
        }
      }
    }
    return newUsers;
  }

  /**
   * Makes sure that a new list of users does not contain users present in the deleted users list..
   */
  public List<User> removeDeletedUsers(List<User> deletedUsers, List<User> newUsers) {
    for (User user : deletedUsers) {
      for (User newUser : newUsers) {
        if (newUser.getLogin().getUsername().equals(user.getLogin().getUsername())) {
          newUsers.remove(newUser);
          break;
        }
      }
    }
    return newUsers;
  }
}
