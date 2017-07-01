package com.stanete.chicfy.di;

import android.content.Context;
import com.stanete.chicfy.UsersApplication;
import com.stanete.chicfy.data.UserPreferences;
import com.stanete.chicfy.data.UsersApiClient;
import com.stanete.chicfy.model.UsersRepository;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by @stanete
 */
@Module public class MainModule {

  private final UsersApplication application;

  public MainModule(UsersApplication application) {
    this.application = application;
  }

  @Provides @Singleton Context provideApplicationContext() {
    return application;
  }

  @Provides @Singleton public UsersApiClient provideUsersApiClient() {
    return new UsersApiClient();
  }

  @Provides @Singleton public UserPreferences provideUserPreferences(Context context) {
    return new UserPreferences(context);
  }

  @Provides @Singleton public UsersRepository provideUsersRepository(UsersApiClient usersApiClient,
      UserPreferences userPreferences) {
    return new UsersRepository(usersApiClient, userPreferences);
  }
}
