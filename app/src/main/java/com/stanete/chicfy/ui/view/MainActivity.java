package com.stanete.chicfy.ui.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.stanete.chicfy.R;

public class MainActivity extends AppCompatActivity {

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
  }
}
