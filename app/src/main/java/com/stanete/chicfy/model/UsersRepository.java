package com.stanete.chicfy.model;

import com.stanete.chicfy.data.UserPreferences;
import com.stanete.chicfy.data.UsersApiClient;

/**
 * Created by @stanete
 */
public class UsersRepository {

  private final UsersApiClient usersApiClient;
  private final UserPreferences userPreferences;

  public UsersRepository(UsersApiClient usersApiClient, UserPreferences userPreferences) {
    this.usersApiClient = usersApiClient;
    this.userPreferences = userPreferences;

  }

}
