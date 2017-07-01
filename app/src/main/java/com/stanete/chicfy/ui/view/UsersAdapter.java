package com.stanete.chicfy.ui.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.stanete.chicfy.R;
import com.stanete.chicfy.model.LoadMoreUsers;
import com.stanete.chicfy.model.User;
import com.stanete.chicfy.ui.presenter.UsersPresenter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stanete
 */
public class UsersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private final static int VIEW_TYPE_USER = 0;
  private final static int VIEW_TYPE_LOAD_MORE_USERS = 1;

  private final UsersPresenter presenter;
  private final List<User> users;
  private final LoadMoreUsers loadMoreUsers;
  private boolean filtered = false;

  public UsersAdapter(UsersPresenter presenter) {
    this.presenter = presenter;
    this.users = new ArrayList<>();
    this.loadMoreUsers = new LoadMoreUsers();
  }

  public void addUsers(List<User> users, boolean filtered) {
    this.users.clear();
    this.users.addAll(users);
    this.filtered = filtered;
  }

  public void addMoreUsers(List<User> users) {
    this.users.addAll(users);
    this.loadMoreUsers.setLoading(false);
  }

  public void deleteUser(int position) {
    users.remove(position);
  }

  @Override public int getItemViewType(int position) {
    if (filtered || position != users.size()) {
      return VIEW_TYPE_USER;
    } else {
      return VIEW_TYPE_LOAD_MORE_USERS;
    }
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == VIEW_TYPE_USER) {
      View view =
          LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row, parent, false);
      return new UserViewHolder(view, presenter);
    } else if (viewType == VIEW_TYPE_LOAD_MORE_USERS) {
      View view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.load_more_users_row, parent, false);
      return new LoadMoreUsersViewHolder(view, presenter);
    }

    return null;
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    if (holder.getItemViewType() == VIEW_TYPE_USER) {
      UserViewHolder userViewHolder = (UserViewHolder) holder;
      User user = users.get(position);
      userViewHolder.render(user);
    } else if (holder.getItemViewType() == VIEW_TYPE_LOAD_MORE_USERS) {
      LoadMoreUsersViewHolder loadMoreUsersViewHolder = (LoadMoreUsersViewHolder) holder;
      loadMoreUsersViewHolder.render(loadMoreUsers);
    }
  }

  @Override public int getItemCount() {
    if (filtered) {
      return users.size();
    } else {
      return users.size() + 1;
    }
  }
}
