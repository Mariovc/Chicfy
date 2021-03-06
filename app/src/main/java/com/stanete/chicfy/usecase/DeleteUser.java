package com.stanete.chicfy.usecase;

import com.stanete.chicfy.model.Result;
import com.stanete.chicfy.model.User;
import com.stanete.chicfy.model.UsersRepository;
import javax.inject.Inject;

/**
 * Created by @stanete
 */
public class DeleteUser extends UseCase {

  private final UsersRepository usersRepository;

  @Inject public DeleteUser(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  public void execute(final Callback callback, final User user) {
    runOnOtherThread(new Runnable() {
      @Override public void run() {

        final Result result = usersRepository.deleteUser(user);

        runOnMainThread(new Runnable() {
          @Override public void run() {
            if (result.getError() == null) {
              callback.onUserDeleted(user);
            } else {
              callback.onUnknownError();
            }
          }
        });
      }
    });
  }

  public interface Callback {

    void onUserDeleted(User user);

    void onUnknownError();
  }
}
