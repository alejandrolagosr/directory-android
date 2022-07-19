<h1 align="center">Employee Directory</h1>

<p align="center">  
Employee Directory is a sample app demostrating the use of Hilt, Coroutines, Flow, Jetpack (ViewModel), Material design and MVVM architecture with a very easy to use API and an easy to understand code
</br></br>
<img src="https://user-images.githubusercontent.com/7938140/179643164-2fddbb91-b087-4a55-ba3d-75eca45d49ef.png"/>
</p>

## Build tools & versions used

- Minimum SDK level 24
- Jetpack
  - Lifecycle - Observe Android lifecycles and handle UI states upon the lifecycle changes.
  - ViewModel - Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
- Architecture
  - MVVM Architecture (Model- View - ViewModel)
  - Repository Pattern
- [Kotlin 1.4](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Hilt 2.42](https://dagger.dev/hilt/) for dependency injection.
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - Construct the REST APIs.
- [Sandwich 1.2.6](https://github.com/skydoves/Sandwich) - Construct a lightweight and modern response interface to handle network payload for Android.
- [Moshi 1.13.0](https://github.com/square/moshi/) - A modern JSON library for Kotlin and Java.
- [Picasso 2.71828](https://square.github.io/picasso/) - Loading images from network.
- [Material-Components 1.6.1](https://github.com/material-components/material-components-android) - Material design components.
- [Turbine 0.8.0](https://github.com/cashapp/turbine) - A small testing library for kotlinx.coroutines Flow.

## Steps to run the app

1. Clone the [repository](https://github.com/alejandrolagosr/directory-android) in your computer.
2. Run the app as usual.

## What areas of the app did you focus on?

1. Networking
2. Repository <> ViewModel
3. UI

## What was the reason for your focus? What problems were you trying to solve?

I focused on making the networking part as good as possible, I decided to use Moshi instead of Gson (normally I use more Gson), since it was the first thing I did, I was excited to implement something that I had not used and read a little more of the Most recommended ways to implement Retrofit (I think that in a job you don't have much opportunity to get to implement the network from scratch, that is, the configuration of the client and service, etc).

Later I spent time using the repository pattern with the ViewModel, I was thinking for a while if I would also use Use Cases, but I considered that the app was small and it was better to keep it simple and understandable.

The UI part is something that I really enjoy, however I didn't know if it would give me time to do something better, I tried to make it look good in a short time using CardView

## How long did you spend on this project?

I was working intermittently for a total of approximately 5 hours on this project.

## Did you make any trade-offs for this project? What would you have done differently with more time?

There are a number of things I would have liked to do if I had more time:

1. Use of DataBinding: I think it would have been quite nice to use it in this project to make the logic in the ViewModel and the smaller view, but I was afraid of having some problem in development and I preferred to only use ViewBinding this time, maybe I could update it later.
2. Better in ViewModel: Although I was satisfied, maybe I could make some improvements in how I handle flows.
3. Improve unit test: Add more tests with some not so common cases, although it adds the examples of empty and malformed list of employees, perhaps with more time it could improve the coverage of the project.
4. Instrumentation Test: It was not required, however, it is something that I have been learning and I would have liked to add some.
5. Use Cases: I was very tempted to use Use Cases and Repositories, but I thought it would add more complexity at the beginning and it was not very necessary, but without a doubt with more time I would have done it to separate the project even better.
6. Clean Architecture: Even though I separated into different packages, I would have liked to create separate modules for all the elements, however, this would take more time.

## What do you think is the weakest part of your project?

Probably my approach with LiveData and UI, although I was happy creating a Sealed class to handle states (on loading, on error, on list retrieved, on completion), part of me wanted to do it with DataBinding and thus avoid handling some UI situations in the view (MainActivity).

## Did you copy any code or dependencies? Please make sure to attribute them here!

At the time of doing the unit tests, I was looking for an idea to be able to use some local json and be able to build my tests using them as a response and I saw a pretty good and understandable example where an abstract class is used to read the files and use them as mock responses in the tests, this example, apart from the creator of one of the libraries that I use (Sandwich) for the correct handling of the answers, it helped me a lot to advance very quickly in the other parts of the app

[Jaewoong Eum](https://github.com/skydoves) is a Google Developer Expert with many great projects and quite good libraries used by the Android community.

Of course, use many of the tools built by [Square](https://github.com/square) for the community like Picasso, Retrofit, Turbine (CashApp), and more.

## Is there any other information you’d like us to know?

I ran the MAD (Modern Android Development) score plugin and got the following:

<p align="center">  
<img src="https://user-images.githubusercontent.com/7938140/179646421-ab76107b-2562-47be-94ce-090e31ccd8ab.png"/>
</p>
