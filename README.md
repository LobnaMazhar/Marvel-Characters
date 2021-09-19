# Marvel-Characters

# Overview
Marvel Characters is an app to display marvel characters using marvel api -> https://developer.marvel.com/
You can view the details of any characters and view its comics, series, stories and events, you can also search for any character.

# Requirements
To be able to run the code on your machine you need to register for the marvel api, generate public and private keys then add them to gradle.properties file under these names MARVEL_PUBLIC_KEY, MARVEL_PRIVATE_KEY

# Coding Architecture
The Project is coded in Kotlin language following MVVM architecture design pattern, using Kotlin Coroutines & LiveData. The project is divided into View (UI), Viewmodel, Model (data) & Repository Layers.

# Algorithms
While searching for characters, Debounce method was used to limit useless API requests.

# App Design

Splash Screen

<img src="https://user-images.githubusercontent.com/12811578/133921784-dc4b86ef-fab5-430c-b93f-da17bed87e3a.png" width="300" height="533">

Home Screen

<img src="https://user-images.githubusercontent.com/12811578/133921930-46d84b03-31f1-41de-b0fc-8a00c3c3df42.png" width="300" height="533">

Search Screen

<img src="https://user-images.githubusercontent.com/12811578/133921938-9285f9b2-4bc3-46cf-a66a-97ed83efbc67.png" width="300" height="533">

Details Screen

<img src="https://user-images.githubusercontent.com/12811578/133921942-8450c951-da90-4642-9906-d8163bd3be98.png" width="300" height="533">

Character Extras Screen

<img src="https://user-images.githubusercontent.com/12811578/133921948-4fe70dfe-a586-47fe-a145-a4586956a4b6.png" width="300" height="533">
