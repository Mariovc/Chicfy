package com.stanete.chicfy;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import com.stanete.chicfy.di.MainComponent;
import com.stanete.chicfy.di.MainModule;
import com.stanete.chicfy.model.Location;
import com.stanete.chicfy.model.Login;
import com.stanete.chicfy.model.Name;
import com.stanete.chicfy.model.Picture;
import com.stanete.chicfy.model.Result;
import com.stanete.chicfy.model.ResultError;
import com.stanete.chicfy.model.User;
import com.stanete.chicfy.model.UsersRepository;
import com.stanete.chicfy.recyclerview.RecyclerViewInteraction;
import com.stanete.chicfy.ui.view.MainActivity;
import com.stanete.chicfy.ui.view.UserDetailsActivity;
import it.cosenonjaviste.daggermock.DaggerMockRule;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.stanete.chicfy.matchers.RecyclerViewItemsCountMatcher.recyclerViewHasItemCount;
import static org.hamcrest.CoreMatchers.not;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Created by @stanete
 */
@RunWith(AndroidJUnit4.class) @LargeTest public class MainActivityTest {

  private static final int RANDOM_NUMBER_OF_USERS = 10;

  @Rule public DaggerMockRule<MainComponent> daggerRule = new DaggerMockRule<>(MainComponent.class,
      new MainModule((UsersApplication) InstrumentationRegistry.getInstrumentation()
          .getTargetContext()
          .getApplicationContext())).set(new DaggerMockRule.ComponentSetter<MainComponent>() {
    @Override public void setComponent(MainComponent component) {
      UsersApplication app = (UsersApplication) InstrumentationRegistry.getInstrumentation()
          .getTargetContext()
          .getApplicationContext();
      app.setComponent(component);
    }
  });

  @Rule public IntentsTestRule<MainActivity> activityRule =
      new IntentsTestRule<>(MainActivity.class, true, false);

  @Mock UsersRepository repository;

  @Test public void showsEmptyCaseIfThereAreNoUsers() {
    givenThereAreNoUsers();

    startActivity();

    onView(withText("¯\\_(ツ)_/¯")).check(matches(isDisplayed()));
  }

  @Test public void doesNotShowEmptyCaseIfThereAreUsers() {
    givenThereAreSomeUsers(RANDOM_NUMBER_OF_USERS);

    startActivity();

    onView(withId(R.id.fl_empty_case)).check(matches(not(isDisplayed())));
  }

  @Test public void showsUserEmailIfThereAreUsers() {
    List<User> users = givenThereAreSomeUsers(RANDOM_NUMBER_OF_USERS);

    startActivity();

    RecyclerViewInteraction.<User>onRecyclerView(withId(R.id.recycler_view)).withItems(users)
        .check(new RecyclerViewInteraction.ItemViewAssertion<User>() {
          @Override public void check(User user, View view, NoMatchingViewException e) {
            matches(hasDescendant(withText(user.getEmail()))).check(view, e);
          }
        });
  }

  @Test public void showsTheExactNumberOfUsersAndTheLoadMoreUsersFooter() {
    givenThereAreSomeUsers(RANDOM_NUMBER_OF_USERS);

    startActivity();

    onView(withId(R.id.recycler_view)).check(
        matches(recyclerViewHasItemCount(RANDOM_NUMBER_OF_USERS + 1)));
  }

  @Test public void opensUserDetailsActivityOnRecyclerViewItemPictureTapped() {
    List<User> users = givenThereAreSomeUsers(RANDOM_NUMBER_OF_USERS);
    startActivity();

    onView(withId(R.id.recycler_view)).
        perform(RecyclerViewActions.actionOnItemAtPosition(0,
            new ClickChildViewWithIdViewAction(R.id.sdv_picture)));

    User userSelected = users.get(0);
    intended(hasComponent(UserDetailsActivity.class.getCanonicalName()));
    intended(hasExtra("username", userSelected.getLogin().getUsername()));
    intended(hasExtra("full_name",
        userSelected.getName().getFirst() + " " + userSelected.getName().getLast()));
  }

  private void givenThereAreNoUsers() {
    when(repository.getUsers(0)).thenReturn(
        Result.<List<User>, ResultError>success(Collections.<User>emptyList()));
  }

  private List<User> givenThereAreSomeUsers(int numberOfUsers) {
    List<User> users = new ArrayList<>();

    for (int i = 0; i < numberOfUsers; i++) {
      String gender;

      if (i % 2 == 0) {
        gender = "female";
      } else {
        gender = "male";
      }

      String first = "first - " + i;
      String last = "last - " + i;
      Name name = new Name(first, last);

      String street = "street -" + i;
      String city = "city - " + i;
      String state = "state - " + i;
      Location location = new Location(street, city, state);

      String email = "email" + i + "@example.com";
      String username = "username - " + i;
      Login login = new Login(username);

      String registered;

      if (numberOfUsers < 10) {
        registered = "2013-02-0" + i + " 03:18:08";
      } else {
        registered = "2013-02-" + i + " 03:18:08";
      }

      Picture picture = new Picture("https://randomuser.me/api/portraits/thumb/women/49.jpg",
          "https://randomuser.me/api/portraits/thumb/women/49.jpg",
          "https://randomuser.me/api/portraits/thumb/women/49.jpg");

      String phone = "0138-14182" + i;

      User user = new User(gender, name, location, email, login, registered, picture, phone);
      users.add(user);
      when(repository.getUserByUsername(username)).thenReturn(
          Result.<User, ResultError>success(user));
    }

    when(repository.getUsers(anyInt())).thenReturn(Result.<List<User>, ResultError>success(users));
    return users;
  }

  private MainActivity startActivity() {
    return activityRule.launchActivity(null);
  }
}
