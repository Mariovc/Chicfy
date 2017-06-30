package com.stanete.chicfy.data;

import android.content.Context;
import android.content.SharedPreferences;
import javax.inject.Inject;

/**
 * Created by @stanete
 */
public class UserPreferences {

  private static final String USER_PREFERENCES_NAME = "RandomUsersPreferences";

  private SharedPreferences sharedPreferences;

  @Inject public UserPreferences(Context context) {
    if (context == null) {
      throw new IllegalArgumentException("Invalid null parameter!");
    }
    this.sharedPreferences = context.getSharedPreferences(USER_PREFERENCES_NAME, 0);
  }
}
