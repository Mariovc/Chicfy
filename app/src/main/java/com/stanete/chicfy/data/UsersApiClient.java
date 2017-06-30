package com.stanete.chicfy.data;

import javax.inject.Inject;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by @stanete
 */
public class UsersApiClient {

  private UsersService usersService;

  @Inject public UsersApiClient() {
    this(UsersApiClientConfig.BASE_ENDPOINT);
  }

  public UsersApiClient(String baseEndpoint) {
    Retrofit retrofit = new Retrofit.Builder().baseUrl(baseEndpoint)
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    usersService = retrofit.create(UsersService.class);
  }
}
