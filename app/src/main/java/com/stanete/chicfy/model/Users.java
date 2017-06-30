package com.stanete.chicfy.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by @stanete
 */
public class Users {

  @SerializedName("results") private List<User> users;
  @SerializedName("info") private Info info;

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public Info getInfo() {
    return info;
  }

  public void setInfo(Info info) {
    this.info = info;
  }
}
