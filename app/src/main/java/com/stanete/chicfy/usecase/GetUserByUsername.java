package com.stanete.chicfy.usecase;

import com.stanete.chicfy.model.Result;
import com.stanete.chicfy.model.ResultError;
import com.stanete.chicfy.model.User;
import com.stanete.chicfy.model.UsersRepository;
import javax.inject.Inject;

/**
 * Created by @stanete
 */
public class GetUserByUsername extends UseCase {

  private final UsersRepository usersRepository;

  @Inject public GetUserByUsername(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  public void execute(final Callback callback, final String username) {
    runOnOtherThread(new Runnable() {
      @Override public void run() {

        final Result<User, ResultError> result = usersRepository.getUserByUsername(username);

        runOnMainThread(new Runnable() {
          @Override public void run() {
            if (result.getError() == null) {
              callback.onUserLoaded(result.getValue());
            } else if (result.getError().equals(ResultError.ITEM_NOT_FOUND)) {
              callback.onItemNotFoundError();
            } else {
              callback.onUnknownError();
            }
          }
        });
      }
    });
  }

  public interface Callback {

    void onUserLoaded(User user);

    void onItemNotFoundError();

    void onUnknownError();
  }
}
