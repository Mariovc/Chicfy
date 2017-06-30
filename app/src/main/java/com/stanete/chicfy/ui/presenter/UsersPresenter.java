package com.stanete.chicfy.ui.presenter;

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

  public interface View extends Presenter.View {

    void showEmptyCase();

    void hideEmptyCase();

    void showNetworkError();

    void hideNetworkError();

    void showUsers(List<User> users);

    void openUserDetailsScreen(User user);
  }
}
