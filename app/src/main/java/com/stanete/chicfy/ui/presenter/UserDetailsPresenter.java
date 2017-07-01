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

  public void initialize(String username) {
    super.initialize();
    loadUser(username);
  }

  public void onRetryClicked(String username) {
    loadUser(username);
  }

  private void loadUser(String username) {

    getView().showLoading();
    getView().hideError();

    getUserByUsername.execute(new GetUserByUsername.Callback() {
      @Override public void onUserLoaded(User user) {
        getView().hideLoading();
        getView().showUser(user);
      }

      @Override public void onItemNotFoundError() {
        getView().hideLoading();
        getView().showError();
      }

      @Override public void onUnknownError() {
        getView().hideLoading();
        getView().showError();
      }
    }, username);
  }

  public interface View extends Presenter.View {

    void showUser(User user);
  }
}
