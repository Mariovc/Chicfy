package com.stanete.chicfy.data;

import com.stanete.chicfy.model.Users;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by @stanete
 */
public interface UsersService {

  @GET(UsersApiClientConfig.BASE_ENDPOINT) Call<Users> getUsers(
      @Query(UsersApiClientConfig.RESULTS) int results, @Query(UsersApiClientConfig.PAGE) int page);
}
