package com.stanete.chicfy.usecase;

import com.stanete.chicfy.model.Result;
import com.stanete.chicfy.model.ResultError;
import com.stanete.chicfy.model.User;
import com.stanete.chicfy.model.UsersRepository;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by @stanete
 */
public class GetUsersByFilter extends UseCase {

  private final UsersRepository usersRepository;

  @Inject public GetUsersByFilter(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  public void execute(final Callback callback, final String filter) {
    runOnOtherThread(new Runnable() {
      @Override public void run() {

        final Result<List<User>, ResultError> result = usersRepository.getUsersByFilter(filter);

        runOnMainThread(new Runnable() {
          @Override public void run() {
            if (result.getError() == null) {
              callback.onUsersLoaded(result.getValue());
            } else {
              callback.onUnknownError();
            }
          }
        });
      }
    });
  }

  public interface Callback {

    void onUsersLoaded(List<User> users);

    void onUnknownError();
  }
}
