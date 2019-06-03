# Andriod App

Android App is tested on Huaweie mate 9 and Huaweie mate 10.

The min supported version is lollipop (level 21).

# App Configurations

App is Tested on Local network as well as on an online server running app backend.

You can modify Base URL in "AppConfig.java" located in the root of source directory.

You need to define the enviornment for which you are going to provide url. Default enviornment is prodution.

You can provide base URl for prodution but you can also provide for Local but you need to change current enviornment

by updating CURRENT_ENV to ENV_LOCAL in order to test on local. 

You can also change User by providing UNIQUE_USER_ID by setting "CURRENT_LOGGED_IN_USER_ID" to test app for different users as I didn't implemented login scenario.


# App architecture

Application is built on MVP pattern. I have used Dagger for depdency injections so I can provide dedepdency for testing. Retrofit with rxandroid for Http communication.
Picasso is used for image loading but we can interface any image library with changing app architecture as App is using inteface for Image Loading.
Mockito is used for unit testing. For integration tests I have implemented a MockApiService which will retun me the provided input for integration tests using dedepdency injections.
Robolectric and espresso is used for instrumented unit/integration testing of android components.
I have ovveride equals and hashcode methods of Model classes because they are being used for Assertion (object comparision).


# Src directory structure

main: Contains Application source code

sharedTest: Contains test classes that are shared among both test and androidTest

test: Contains Local unit and integration tests.

androidTest: Contains instrumented tests for unit and end to end.


# Libraries used

App is using following main libraries.
-rxandroid
-retrofit
-dagger
-picasso
-mockito
-espresso
-robolectric
