# sample_repo
MVVM Architecture
Kotlin

Implementations

1) Libraries used in this project list below

* Hilt Dagger for providing dependencies  (jetpack)
* View model lifecycles
* By view models for activity instead use of factory 
* Retrofit for using API call
* Ihsanbal interceptor for request and response view in clean manner
* Room compiler for storing local data
* Swipe layout

2) Steps

* List stories using showstories api in recyclerview
* After that check database any data stored if not after fetch remote data stored locally.
* If Application close and again opens load from database
* If user pull using refresh cleared local db storage again fetch from remote then store locally.
* Filter implementation in adapter using filterable.
* Filter apply only title letters based search working

