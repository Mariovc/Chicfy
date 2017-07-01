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
public class GetUsers extends UseCase {

  private final UsersRepository usersRepository;

  @Inject public GetUsers(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  public void execute(final Callback callback, final int page) {
    runOnOtherThread(new Runnable() {
      @Override public void run() {

        final Result<List<User>, ResultError> result = usersRepository.getUsers(page);

        runOnMainThread(new Runnable() {
          @Override public void run() {
            if (result.getError() == null) {
              callback.onUsersLoaded(result.getValue());
            } else if (result.getError().equals(ResultError.NO_INTERNET_CONNECTION)) {
              callback.onNetworkError();
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

    void onNetworkError();

    void onUnknownError();
  }
}
