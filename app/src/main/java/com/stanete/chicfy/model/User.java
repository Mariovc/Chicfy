package com.stanete.chicfy.model;

/**
 * Created by @stanete
 */
public class User {

  private String gender;
  private Name name;
  private Location location;
  private String email;
  private Login login;
  private String registered;
  private Picture picture;

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Name getName() {
    return name;
  }

  public void setName(Name name) {
    this.name = name;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Login getLogin() {
    return login;
  }

  public void setLogin(Login login) {
    this.login = login;
  }

  public String getRegistered() {
    return registered;
  }

  public void setRegistered(String registered) {
    this.registered = registered;
  }

  public Picture getPicture() {
    return picture;
  }

  public void setPicture(Picture picture) {
    this.picture = picture;
  }
}
