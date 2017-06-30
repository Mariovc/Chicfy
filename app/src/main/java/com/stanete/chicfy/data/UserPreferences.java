package com.stanete.chicfy.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stanete.chicfy.model.User;
import java.lang.reflect.Type;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by @stanete
 */
public class UserPreferences {

  private static final String USER_PREFERENCES_NAME = "RandomUsersPreferences";
  private static final String KEY_DELETED_USERS = "key_deleted_users";

  private SharedPreferences sharedPreferences;

  @Inject public UserPreferences(Context context) {
    if (context == null) {
      throw new IllegalArgumentException("Invalid null parameter!");
    }
    this.sharedPreferences = context.getSharedPreferences(USER_PREFERENCES_NAME, 0);
  }

  public List<User> getDeletedUsers() {
    String json = sharedPreferences.getString(KEY_DELETED_USERS, "[]");
    Type type = new TypeToken<List<User>>() {
    }.getType();
    return new Gson().fromJson(json, type);
  }

  @SuppressLint("ApplySharedPref") public void addDeletedUser(User user) {

    List<User> deletedUsers = getDeletedUsers();
    deletedUsers.add(user);

    Type type = new TypeToken<List<User>>() {
    }.getType();
    String json = new Gson().toJson(deletedUsers, type);

    sharedPreferences.edit().putString(KEY_DELETED_USERS, json).commit();
  }
}
