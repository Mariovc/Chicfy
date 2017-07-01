package com.stanete.chicfy.model;

/**
 * Created by @stanete
 */
public class LoadMoreUsers {

  private boolean loading;
  private int currentPage;

  public boolean isLoading() {
    return loading;
  }

  public void setLoading(boolean loading) {
    this.loading = loading;
  }

  public int getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }
}
