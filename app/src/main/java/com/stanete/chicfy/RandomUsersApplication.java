package com.stanete.chicfy;

import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by @stanete
 */
public class RandomUsersApplication extends Application {

  @Override public void onCreate() {
    super.onCreate();
    Fresco.initialize(this);
  }
}
