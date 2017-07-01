package com.stanete.chicfy.ui.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.stanete.chicfy.R;
import com.stanete.chicfy.model.LoadMoreUsers;
import com.stanete.chicfy.ui.presenter.UsersPresenter;

/**
 * Created by @stanete
 */
public class LoadMoreUsersViewHolder extends RecyclerView.ViewHolder {

  private final UsersPresenter presenter;

  @Bind(R.id.tv_load_more_users) TextView tvLoadMoreUsers;
  @Bind(R.id.progress_bar) ProgressBar progressBar;

  public LoadMoreUsersViewHolder(View itemView, UsersPresenter presenter) {
    super(itemView);
    this.presenter = presenter;
    ButterKnife.bind(this, itemView);
  }

  public void render(LoadMoreUsers loadMoreUsers) {
    hookListeners(loadMoreUsers);
    renderLoading(loadMoreUsers);
  }

  private void hookListeners(final LoadMoreUsers loadMoreUsers) {
    tvLoadMoreUsers.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        presenter.onLoadMoreUsersClicked(loadMoreUsers);
      }
    });
  }

  private void renderLoading(LoadMoreUsers loadMoreUsers) {
    if (loadMoreUsers.isLoading()) {
      tvLoadMoreUsers.setVisibility(View.INVISIBLE);
      progressBar.setVisibility(View.VISIBLE);
    } else {
      tvLoadMoreUsers.setVisibility(View.VISIBLE);
      progressBar.setVisibility(View.INVISIBLE);
    }
  }
}
