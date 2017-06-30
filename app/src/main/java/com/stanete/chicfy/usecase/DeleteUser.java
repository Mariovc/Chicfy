package com.stanete.chicfy.usecase;

import com.stanete.chicfy.model.UsersRepository;
import javax.inject.Inject;

/**
 * Created by @stanete
 */
public class DeleteUser {

  private final UsersRepository usersRepository;

  @Inject public DeleteUser(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }
}
