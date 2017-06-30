package com.stanete.chicfy.ui.presenter;

import com.stanete.chicfy.model.User;
import com.stanete.chicfy.usecase.GetUserByUsername;
import javax.inject.Inject;

/**
 * Created by @stanete
 */
public class UserDetailsPresenter extends Presenter<UserDetailsPresenter.View> {

  private final GetUserByUsername getUserByUsername;

  @Inject public UserDetailsPresenter(GetUserByUsername getUserByUsername) {
    this.getUserByUsername = getUserByUsername;
  }

  public interface View extends Presenter.View {

    void showUser(User user);
  }
}
