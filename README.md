# NYTIMES API demo

This is a demo Android application to download and show articles from the NYTimes API.

<img src="img/device-2017-06-30-103532.png" width="480">

If you want to try it out, you need to have a `NYTimes API key`. You can get one from here:
https://developer.nytimes.com/signup (you have to choose the `Most popular` API)

When you got the key, insert it in the project's root folder! Just create a file,
called `gradle.properties` and put this single line into it:
`APIKey="YOUR_API_KEY"`

If you want to build the application, just run this gradle task from the project's directory:
`./gradlew assembleDevDebug`. You can find the assembled APK in the `app/build/outputs/apk` folder.

Or you can simply import it into Android Studio, and press the `Run` button :) 

## Architecture

The application has separated components. These components are managed by the DI framework (Dagger2).
The UI has been implemented with MVP design pattern. These two solutions providing us the ability
to test the application without real networking. Every part of the structure is mockable.

## Dependencies

The application uses these 3rd party libraries:

-   Dagger2 for dependency injection
-   OkHTTP and Retrofit for networking with GSON
-   Stetho to monitor network requests
-   ButterKnife for View binding
-   MaterialDialogs for the unified dialog presentation (on every Android API level)
-   Picasso for image downloading
-   ChromeCustomTabs to show the article in a browser


 