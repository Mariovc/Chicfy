package com.stanete.chicfy.ui.presenter;

import com.stanete.chicfy.model.LoadMoreUsers;
import com.stanete.chicfy.model.User;
import com.stanete.chicfy.usecase.DeleteUser;
import com.stanete.chicfy.usecase.GetUsers;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by @stanete
 */
public class UsersPresenter extends Presenter<UsersPresenter.View> {

  private final GetUsers getUsers;
  private final DeleteUser deleteUser;

  @Inject public UsersPresenter(GetUsers getUsers, DeleteUser deleteUser) {
    this.getUsers = getUsers;
    this.deleteUser = deleteUser;
  }

  @Override public void initialize() {
    super.initialize();

    loadUsers();
  }

  private void loadUsers() {
    getView().showLoading();
    getView().hideError();
    getView().hideEmptyCase();
    getView().hideNetworkError();

    getUsers.execute(new GetUsers.Callback() {
      @Override public void onUsersLoaded(List<User> users) {

        getView().hideLoading();

        if (users.isEmpty()) {
          getView().showEmptyCase();
        } else {
          getView().showUsers(users);
        }
      }

      @Override public void onNetworkError() {
        getView().hideLoading();
        getView().showNetworkError();
      }

      @Override public void onUnknownError() {
        getView().hideLoading();
        getView().showError();
      }
    }, 0);
  }

  public void onRetryClicked() {
    loadUsers();
  }

  public void onLoadMoreUsersClicked(final LoadMoreUsers loadMoreUsers) {

    loadMoreUsers.setCurrentPage(loadMoreUsers.getCurrentPage() + 1);
    loadMoreUsers.setLoading(true);
    getView().showLoadingMoreUsers();

    getUsers.execute(new GetUsers.Callback() {
      @Override public void onUsersLoaded(List<User> users) {

        loadMoreUsers.setLoading(false);
        getView().hideLoadingMoreUsers();

        if (!users.isEmpty()) {
          getView().showMoreUsers(users);
        }
      }

      @Override public void onNetworkError() {
        loadMoreUsers.setLoading(false);
        getView().hideLoadingMoreUsers();
        getView().showNetworkErrorLoadingMoreUsers();
      }

      @Override public void onUnknownError() {
        loadMoreUsers.setLoading(false);
        getView().hideLoadingMoreUsers();
        getView().showErrorLoadingMoreUsers();
      }
    }, loadMoreUsers.getCurrentPage());
  }

  public void onUserClicked(User user) {
    getView().openUserDetailsScreen(user);
  }

  public void onDeleteUserClicked(final User user, final int position) {
    deleteUser.execute(new DeleteUser.Callback() {
      @Override public void onUserDeleted(User user) {
        getView().showUserDeleted(user, position);
      }

      @Override public void onUnknownError() {
        getView().showUnknownErrorDeletingUser(user);
      }
    }, user);
  }

  public interface View extends Presenter.View {

    void showEmptyCase();

    void hideEmptyCase();

    void showNetworkError();

    void hideNetworkError();

    void showErrorLoadingMoreUsers();

    void showNetworkErrorLoadingMoreUsers();

    void showUserDeleted(User user, int position);

    void showUnknownErrorDeletingUser(User user);

    void showUsers(List<User> users);

    void showMoreUsers(List<User> users);

    void openUserDetailsScreen(User user);

    void showLoadingMoreUsers();

    void hideLoadingMoreUsers();
  }
}
