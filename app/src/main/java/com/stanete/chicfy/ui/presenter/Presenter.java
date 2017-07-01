package com.stanete.chicfy.ui.presenter;

/**
 * Created by @stanete
 */
public class Presenter<T extends Presenter.View> {

  private T view;

  public void setView(T view) {
    this.view = view;
  }

  public T getView() {
    return view;
  }

  public void initialize() {

  }

  public void update() {

  }

  public interface View {

    void showLoading();

    void hideLoading();

    void showError();

    void hideError();
  }
}
