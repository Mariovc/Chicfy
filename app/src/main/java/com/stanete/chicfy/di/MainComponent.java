package com.stanete.chicfy.di;

import com.stanete.chicfy.ui.view.MainActivity;
import com.stanete.chicfy.ui.view.UserDetailsActivity;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by @stanete
 */
@Singleton @Component(modules = MainModule.class) public interface MainComponent {

  void inject(MainActivity activity);

  void inject(UserDetailsActivity userDetailsActivity);
}
