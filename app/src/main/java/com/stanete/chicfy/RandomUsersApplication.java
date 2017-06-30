package com.stanete.chicfy;

import android.app.Application;
import android.support.annotation.VisibleForTesting;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.stanete.chicfy.di.DaggerMainComponent;
import com.stanete.chicfy.di.MainComponent;
import com.stanete.chicfy.di.MainModule;

/**
 * Created by @stanete
 */
public class RandomUsersApplication extends Application {

  private MainComponent mainComponent;

  @Override public void onCreate() {
    super.onCreate();
    Fresco.initialize(this);
    mainComponent = DaggerMainComponent.builder().mainModule(new MainModule(this)).build();
  }

  public MainComponent getMainComponent() {
    return mainComponent;
  }

  @VisibleForTesting public void setComponent(MainComponent mainComponent) {
    this.mainComponent = mainComponent;
  }
}
