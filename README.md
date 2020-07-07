# Punk-APP
Android App displays data from ​Punk API,​ an open-source API that displays detailed information on 
delicious craft-beer. 

## Description
I worked on this app to implement androids recommended architecture for building android mobile apps.
This project makes use of the **MVVM** architecture model with androids **JETPACK** libraries such as
**ACTIVITY**, **APPCOMPACT**, **DATABINDING**, **FRAGMENT**, **LIFECYCLE**, **NAVIGATION**, **ROOM**,
 and **PAGING**. There are alot more *JETPACK* libraries which are not in use in this project
 mostly because of the project requirements but you can find all here <https://developer.android.com/jetpack>
 
 ## Punk Api Documentation
[Punk Api V2 Documentation](https://punkapi.com/documentation/v2)
 
### Requirements
1. [Android Studio 3.0](https://developer.android.com/studio) and above
2. [Android buildToolsVersion 29.0.3](https://developer.android.com/studio/releases/build-tools) and above 
3. Android minSdkVersion 19
4. Kotlin version 1.3.*

### Features
* Implementation of android [MVVM Architecture Pattern](https://developer.android.com/jetpack/guide).
* Data single source of truth implementation and google suggested practice for code separation using
 [Repository Architecture](https://codelabs.developers.google.com/codelabs/android-training-livedata-viewmodel/index.html#7).
* Functional programming implementation by observing to data changes with [LiveData](https://codelabs.developers.google.com/codelabs/android-training-livedata-viewmodel/index.html#5).
* Using [ViewModel](https://codelabs.developers.google.com/codelabs/android-training-livedata-viewmodel/index.html#8) to serve as the bridge between datasource and UI.
    ViewModel implementation also makes sure data is not lost during configurations changes as its lifecycle aware.
* Data persistance with [Room](https://codelabs.developers.google.com/codelabs/android-training-livedata-viewmodel/index.html#6).
* Managing of asynchronous calls using [Kotlin Coroutines](https://codelabs.developers.google.com/codelabs/kotlin-coroutines/#0).
* Display list with [RecycleView](https://codelabs.developers.google.com/codelabs/android-training-create-recycler-view/index.html#0) and data binding with [Paging Library](https://codelabs.developers.google.com/codelabs/android-paging/#0).

### Testing
* Android UI tests with [Espresso](https://developer.android.com/training/testing/espresso)
* [Testing Room DAO classes](https://medium.com/exploring-android/android-architecture-components-testing-your-room-dao-classes-e06e1c9a1535)
* Local Unit tests with [Mokito](https://developer.android.com/training/testing/unit-testing/local-unit-tests) and [Robolectric](http://robolectric.org)

### Screenshots
<table>
  <tr>
    <td>Splash Screen</td>
     <td>List Screen with paging</td>
     <td>Details Screen</td>
  </tr>
  <tr>
    <td><img src="https://github.com/samdanTetteh/PunkApp/blob/master/Screenshots/Screenshot_1594032244.png" width=250 height=480></td>
    <td><img src="https://github.com/samdanTetteh/PunkApp/blob/master/Screenshots/Screenshot_1594032022.png" width=250 height=480></td>
    <td><img src="https://github.com/samdanTetteh/PunkApp/blob/master/Screenshots/Screenshot_1594032232.png" width=250 height=480></td>
  </tr>
 
 </table>















