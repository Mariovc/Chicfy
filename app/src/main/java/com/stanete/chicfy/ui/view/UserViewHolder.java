package com.stanete.chicfy.ui.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stanete.chicfy.R;
import com.stanete.chicfy.model.User;
import com.stanete.chicfy.ui.presenter.UsersPresenter;

/**
 * Created by @stanete
 */
public class UserViewHolder extends RecyclerView.ViewHolder {

  @Bind(R.id.sdv_picture) SimpleDraweeView sdvPicture;
  @Bind(R.id.tv_full_name) TextView tvFullName;
  @Bind(R.id.tv_email) TextView tvEmail;
  @Bind(R.id.tv_phone) TextView tvPhone;
  @Bind(R.id.iv_delete) ImageView ivDelete;

  private final UsersPresenter presenter;

  public UserViewHolder(View itemView, UsersPresenter presenter) {
    super(itemView);
    this.presenter = presenter;
    ButterKnife.bind(this, itemView);
  }

  public void render(User user) {
    hookListeners(user);
    renderUserPicture(user.getPicture().getThumbnail());
    renderUserEmail(user.getEmail());
    renderUserFullName(user.getName().getFirst() + " " + user.getName().getLast());
    renderUserPhone(user.getPhone());
  }

  private void hookListeners(final User user) {

    sdvPicture.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        presenter.onUserClicked(user);
      }
    });

    ivDelete.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        presenter.onDeleteUserClicked(user, getAdapterPosition());
      }
    });
  }

  private void renderUserPicture(String picture) {
    sdvPicture.setImageURI(picture);
  }

  private void renderUserFullName(String fullname) {
    tvFullName.setText(fullname);
  }

  private void renderUserEmail(String email) {
    tvEmail.setText(email);
  }

  private void renderUserPhone(String phone) {
    tvPhone.setText(phone);
  }
}
