package com.stanete.chicfy.ui.view;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.stanete.chicfy.R;
import com.stanete.chicfy.UsersApplication;
import com.stanete.chicfy.model.User;
import com.stanete.chicfy.ui.presenter.UsersPresenter;
import java.util.List;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements UsersPresenter.View {

  @Inject UsersPresenter presenter;
  private UsersAdapter adapter;

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.recycler_view) RecyclerView recyclerView;
  @Bind(R.id.tv_error) TextView tvError;
  @Bind(R.id.btn_retry) Button btnRetry;
  @Bind(R.id.fl_error) LinearLayout flError;
  @Bind(R.id.fl_empty_case) LinearLayout flEmptyCase;
  @Bind(R.id.fl_loading) FrameLayout flLoading;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    toolbar.setTitle(getResources().getString(R.string.app_name));
    setSupportActionBar(toolbar);

    initializeDagger();
    initializePresenter();
    initializeAdapter();
    initializeRecyclerView();
    presenter.initialize();
  }

  private void initializeDagger() {
    UsersApplication app = (UsersApplication) getApplication();
    app.getMainComponent().inject(this);
  }

  private void initializePresenter() {
    presenter.setView(this);
  }

  private void initializeAdapter() {
    adapter = new UsersAdapter(presenter);
  }

  private void initializeRecyclerView() {
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setHasFixedSize(true);

    DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
    
    divider.setDrawable(getResources().getDrawable(R.drawable.divider));
    recyclerView.addItemDecoration(divider);

    recyclerView.setAdapter(adapter);
  }

  // region UsersPresenter.View
  @Override public void showLoading() {
    flLoading.setVisibility(View.VISIBLE);
  }

  @Override public void hideLoading() {
    flLoading.setVisibility(View.INVISIBLE);
  }

  @Override public void showError() {
    tvError.setText(getResources().getString(R.string.error_loading_users));
    flError.setVisibility(View.VISIBLE);
  }

  @Override public void hideError() {
    flError.setVisibility(View.INVISIBLE);
  }

  @Override public void showEmptyCase() {
    flEmptyCase.setVisibility(View.VISIBLE);
  }

  @Override public void hideEmptyCase() {
    flEmptyCase.setVisibility(View.INVISIBLE);
  }

  @Override public void showNetworkError() {
    tvError.setText(getResources().getString(R.string.error_no_internet));
    flError.setVisibility(View.VISIBLE);
  }

  @Override public void hideNetworkError() {
    flError.setVisibility(View.INVISIBLE);
  }

  @Override public void showErrorLoadingMoreUsers() {
    Toast.makeText(this, getResources().getString(R.string.error_loading_more_users),
        Toast.LENGTH_LONG).show();
  }

  @Override public void showNetworkErrorLoadingMoreUsers() {
    Toast.makeText(this, getResources().getString(R.string.error_no_internet_loading_more_users),
        Toast.LENGTH_LONG).show();
  }

  @Override public void showUserDeleted(User user, int position) {
    Toast.makeText(this, getResources().getString(R.string.user_deleted), Toast.LENGTH_SHORT)
        .show();

    adapter.deleteUser(position);
    adapter.notifyItemRemoved(position);
  }

  @Override public void showUnknownErrorDeletingUser(User user) {
    Toast.makeText(this, getResources().getString(R.string.error_deleting_user), Toast.LENGTH_SHORT)
        .show();
  }

  @Override public void showUsers(List<User> users) {
    adapter.addUsers(users);
    adapter.notifyDataSetChanged();
  }

  @Override public void showMoreUsers(List<User> users) {
    adapter.addMoreUsers(users);
    adapter.notifyDataSetChanged();
  }

  @Override public void showLoadingMoreUsers() {
    adapter.notifyDataSetChanged();
  }

  @Override public void hideLoadingMoreUsers() {
    adapter.notifyDataSetChanged();
  }

  @Override public void openUserDetailsScreen(User user) {
    // TODO Open users details activity.
  }
  // endregion
}
