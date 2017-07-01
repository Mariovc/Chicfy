package com.stanete.chicfy;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;
import org.hamcrest.Matcher;

/**
 * Created by @stanete
 */
public class ClickChildViewWithIdViewAction implements ViewAction {

  private final int id;

  public ClickChildViewWithIdViewAction(int id) {
    this.id = id;
  }

  public static ViewAction clickChildViewWithId(final int id) {
    return new ViewAction() {
      @Override
      public Matcher<View> getConstraints() {
        return null;
      }

      @Override
      public String getDescription() {
        return "Click on a child view with specified id.";
      }

      @Override
      public void perform(UiController uiController, View view) {
        View v = view.findViewById(id);
        v.performClick();
      }
    };
  }

  @Override public Matcher<View> getConstraints() {
    return null;
  }

  @Override public String getDescription() {
    return "Click on a child view with specified id.";
  }

  @Override public void perform(UiController uiController, View view) {
    View v = view.findViewById(id);
    v.performClick();
  }
}
