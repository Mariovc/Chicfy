package com.stanete.chicfy;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import com.stanete.chicfy.data.UsersApiClient;
import com.stanete.chicfy.exception.UnknownErrorException;
import com.stanete.chicfy.model.User;
import java.io.IOException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by @stanete
 */
public class UsersApiClientTest {

  private UsersApiClient usersApiClient;

  private MockWebServer server;

  @Before public void setUp() throws Exception {
    this.server = new MockWebServer();
    this.server.start();
    String mockWebServerEndpoint = server.getUrl("/").toString();
    usersApiClient = new UsersApiClient(mockWebServerEndpoint);
  }

  @Test public void sendsGetAllRequestToTheCorrectEndpoint() throws Exception {
    enqueueMockResponse(200, MockResponseBodies.NO_USERS);

    usersApiClient.getUsers(40, 0);

    RecordedRequest request = server.takeRequest();
    assertEquals("/?results=40&page=0", request.getPath());
    assertEquals("GET", request.getMethod());
  }

  @Test(expected = UnknownErrorException.class)
  public void throwsUnknownErrorExceptionIfThereIsNotHandledErrorGettingAllSuperHeroes()
      throws Exception {
    enqueueMockResponse(418);

    usersApiClient.getUsers(40, 0);
  }

  @Test public void parsesUsersProperlyGettingTenUsers() throws Exception {
    enqueueMockResponse(200, MockResponseBodies.TEN_USERS);

    List<User> users = usersApiClient.getUsers(10, 0);

    assertEquals(users.size(), 10);
    assertUserContainsExpectedValues(users.get(0));
  }

  private void enqueueMockResponse(int code) throws IOException {
    MockResponse mockResponse = new MockResponse();
    mockResponse.setResponseCode(code);
    server.enqueue(mockResponse);
  }

  private void enqueueMockResponse(int code, String body) throws IOException {
    MockResponse mockResponse = new MockResponse();
    mockResponse.setResponseCode(code);
    mockResponse.setBody(body);
    server.enqueue(mockResponse);
  }

  private void assertUserContainsExpectedValues(User user) {
    assertEquals(user.getName().getFirst(), "liana");
    assertEquals(user.getName().getLast(), "wiese");
    assertEquals(user.getEmail(), "liana.wiese@example.com");
    assertEquals(user.getLogin().getUsername(), "goldenbird143");
    assertEquals(user.getPhone(), "0138-1418286");
    assertEquals(user.getPicture().getThumbnail(), "https://randomuser.me/api/portraits/thumb/women/49.jpg");
    assertEquals(user.getPicture().getLarge(), "https://randomuser.me/api/portraits/women/49.jpg");
    assertEquals(user.getGender(), "female");
    assertEquals(user.getLocation().getCity(), "cochem-zell");
    assertEquals(user.getLocation().getState(), "brandenburg");
  }
}
