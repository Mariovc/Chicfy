# Random User Code Test

## What was asked
You are working for a company showing random users information (RandomUser Inc.). As a good company based on random users data they want to show random information about random users. Your task for this code test is to design an Android/iOS application (the one you prefer) with these requirements:

* Download a list of random users from http://randomuser.me/ API.
* Be careful, some users can be duplicated. You should take this into account and do not show duplicated users. If Random User API returns the same user more than once you have to store just one user inside your system.
* Show a list of random users with this information sorted by name:
  * User name and surname.
  * User email.
  * User picture.
  * User phone.
* Add one button or any similar infinite scroll mechanism to retrieve more users and add them to your current users list.
* Add one button to each cell or a similar interaction to delete users. If you press that button your user will not be shown anymore in your user list. Even if the user is part of a new server side response.
* Your user interface should contain a textbox to filter users by name, surname or email. Once the user stop typing, your list will be updated with users that matches with the search term.
* If you press the user picture you have to show another view with the user detailed information:
  * User gender.
  * User name and surname.
  * User location: street, city and state.
  * Registered date.
  * User email.
  * User picture.

* The user information should be persistent.
* Test your code, think in the most important parts of your application and write tests.


## What was done

I used Travis-CI for CI:
* https://travis-ci.org/stanete/Chicfy

### The UI
The Application is based on two Activities:

* MainActivity shows a list of users.
* UserDetailsActivity showing detailed information about a user.

### The architecture:

I based this app on Clean Architecture. On a conceptual level it can be viewed as divided in three layers:

* _Presentation_ where I used a simple Model-View-Presenter.
* _Domain_ where I used two UseCases (or Interactor if you prefer).
* _Data_ where I created a simple Repository and an ApiClient.

### For testing:
* I used Espresso to interact with the Application UI.
* I used Dagger2 to replace production code with Test Doubles.
* I used MockWebServer to simulate a HTTP server.
* I used JUnit to perform assertions.