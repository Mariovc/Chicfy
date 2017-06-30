package com.stanete.chicfy.data;

import com.stanete.chicfy.exception.NetworkErrorException;
import com.stanete.chicfy.exception.UnknownErrorException;
import com.stanete.chicfy.model.User;
import com.stanete.chicfy.model.Users;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Response;
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

  public List<User> getUsers(int page, int results)
      throws UnknownErrorException, NetworkErrorException {
    try {
      Response<Users> response = usersService.getUsers(page, results).execute();
      inspectResponseForErrors(response);
      return response.body().getUsers();
    } catch (IOException e) {
      throw new NetworkErrorException();
    }
  }

  private void inspectResponseForErrors(Response response) throws UnknownErrorException {
    int code = response.code();
    if (code >= 400) {
      throw new UnknownErrorException();
    }
  }
}
