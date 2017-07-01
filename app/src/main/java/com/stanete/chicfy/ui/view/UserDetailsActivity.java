package com.stanete.chicfy.ui.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stanete.chicfy.R;
import com.stanete.chicfy.UsersApplication;
import com.stanete.chicfy.model.User;
import com.stanete.chicfy.ui.presenter.UserDetailsPresenter;
import javax.inject.Inject;

public class UserDetailsActivity extends AppCompatActivity implements UserDetailsPresenter.View {

  private static final String EXTRA_FULL_NAME = "full_name";
  private static final String EXTRA_USERNAME = "username";

  @Inject UserDetailsPresenter presenter;

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.sdv_picture) SimpleDraweeView sdvPicture;
  @Bind(R.id.tv_first_name) TextView tvFirstName;
  @Bind(R.id.tv_last_name) TextView tvLastName;
  @Bind(R.id.tv_gender) TextView tvGender;
  @Bind(R.id.tv_email) TextView tvEmail;
  @Bind(R.id.tv_location) TextView tvLocation;
  @Bind(R.id.tv_registered_on) TextView tvRegisteredOn;
  @Bind(R.id.btn_retry) Button btnRetry;
  @Bind(R.id.fl_error) LinearLayout flError;
  @Bind(R.id.fl_loading) FrameLayout flLoading;

  public static Intent getCallingIntent(Context origin, String fullName, String username) {
    Intent callingIntent = new Intent(origin, UserDetailsActivity.class);
    callingIntent.putExtra(EXTRA_FULL_NAME, fullName);
    callingIntent.putExtra(EXTRA_USERNAME, username);
    return callingIntent;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_details);
    ButterKnife.bind(this);
    toolbar.setTitle(getIntent().getStringExtra(EXTRA_FULL_NAME));
    setSupportActionBar(toolbar);

    initializeDagger();
    initializePresenter();
    presenter.initialize(getIntent().getStringExtra(EXTRA_USERNAME));
  }

  private void initializeDagger() {
    UsersApplication app = (UsersApplication) getApplication();
    app.getMainComponent().inject(this);
  }

  private void initializePresenter() {
    presenter.setView(this);
  }

  @OnClick(R.id.btn_retry) public void onRetryClicked(){
    presenter.onRetryClicked(getIntent().getStringExtra(EXTRA_USERNAME));
  }

  // region UserDetailsPresenter.View
  @Override public void showLoading() {
    flLoading.setVisibility(View.VISIBLE);
  }

  @Override public void hideLoading() {
    flLoading.setVisibility(View.INVISIBLE);
  }

  @Override public void showError() {
    flError.setVisibility(View.VISIBLE);
  }

  @Override public void hideError() {
    flError.setVisibility(View.INVISIBLE);
  }

  @Override public void showUser(User user) {

    sdvPicture.setImageURI(user.getPicture().getLarge());

    tvEmail.setText(user.getEmail());
    tvFirstName.setText(user.getName().getFirst());
    tvLastName.setText(user.getName().getLast());
    tvGender.setText(user.getGender());

    tvRegisteredOn.setText(user.getRegistered());

    tvLocation.setText(user.getLocation().getCity()
        + ", "
        + user.getLocation().getState()
        + ", "
        + user.getLocation().getStreet());
  }
  // endregion
}
