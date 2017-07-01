package com.stanete.chicfy.matchers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * Created by @stanete
 */
public class RecyclerViewItemsCountMatcher extends BaseMatcher<View> {

  private final int expectedItemCount;

  public RecyclerViewItemsCountMatcher(int expectedItemCount) {
    this.expectedItemCount = expectedItemCount;
  }

  @Override public boolean matches(Object item) {
    RecyclerView recyclerView = (RecyclerView) item;
    return recyclerView.getAdapter().getItemCount() == expectedItemCount;
  }

  @Override public void describeTo(Description description) {
    description.appendText("recycler view does not contain " + expectedItemCount + " items");
  }

  public static Matcher<View> recyclerViewHasItemCount(int itemCount) {
    return new RecyclerViewItemsCountMatcher(itemCount);
  }
}