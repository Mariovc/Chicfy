package com.stanete.chicfy.usecase;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by @stanete
 */
public class UseCase {

  protected void runOnOtherThread(Runnable runnable){
    new Thread(runnable).start();
  }

  protected void runOnMainThread(Runnable runnable) {
    new Handler(Looper.getMainLooper()).post(runnable);
  }

}
