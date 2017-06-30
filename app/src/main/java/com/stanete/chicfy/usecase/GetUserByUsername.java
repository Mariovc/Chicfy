package com.stanete.chicfy.usecase;

import com.stanete.chicfy.model.UsersRepository;
import javax.inject.Inject;

/**
 * Created by @stanete
 */
public class GetUserByUsername {

  private final UsersRepository usersRepository;

  @Inject
  public GetUserByUsername(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

}
