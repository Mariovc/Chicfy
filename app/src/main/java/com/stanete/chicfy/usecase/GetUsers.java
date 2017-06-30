package com.stanete.chicfy.usecase;

import com.stanete.chicfy.model.UsersRepository;
import javax.inject.Inject;

/**
 * Created by @stanete
 */
public class GetUsers {

  private final UsersRepository usersRepository;

  @Inject
  public GetUsers(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

}
