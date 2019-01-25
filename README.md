# README #

### How do I get set up? ###
* In the project's local.properties, add in the DarkSky Api key in the format of  `apiKey="DARK_SKY_API_KEY"`

### General Info ###
This module is divided up into 5 modules.

* Applicaton

* Core Models

* Domain

* Api Abstraction

* Dark Sky Api

Depedencies include:

* RxJava - Reactive & functional support for streams of data

* Timber - Easy to use logger

* Retrofit - Easy to setup api wrapper

* Kodein - Kotlin Dependency Injection


### Thoughts ###
Modularity- I like the current boundaries and layers.  With respect to Uncle Bob's CLEAN architecture, each module's dependencies are exclusively in a more inner layer fulfilling the idea of detail depending on policy and not the other way around.  It would be fairly trivial to replace DarkSky with another weather service thanks to this architecture.

CLEAN Architecture Anti-Pattern -  One thing I didn't adhere to with CLEAN is creating a clearer boundary between the use cases and the application.  Normally the core models would be transformed into other request/result POJOs to be consumed by the app.  I find that this creates unecessary duplication when it comes to Android development.    

RxJava - This is a great library that was made Android development easier.  One of the greatest boons is the simplification of threading.  Another is removing the concept of time when it comes to doing work.  It also lets Android developers get into more functional approaches of working with data.

Testing - I'm struggling with this part because I haven't had the opportunity to do more in this area on the current projects I am working on.  It's the common scenario of small team and not enough resource to dedicate to writing tests.  I am aware of a lot of the concepts and have experience writing unit tests for a state-machine-driven protocol library that paired with BLE communication.  I wrote a [blog](https://orlandodevs.com/blog/unit-testing-with-mockito/) about unit testing with Mockito back in 2015.  I have done acceptance testing with Cucumber while working Ruby on Rails developers on iOS apps.

Back to the project on hand.  This is one of my areas of weakness because I let my thoughts/theories of testing stagnate and disappear from lack of use over time.  I think I did a great job of breaking up responsibility and creating sensible seams in the codebase but there's one area in the implementation that acts as a testing blindside for me: the presenters.  My presenter implementations have a great reactive relationship with the view and usecases but I laregly kept the class method's private.  I'm going to think about why and look into what changes make sense to make the code more testable.

Maintaining Flexibility - Business change over time.  Classes in the inner layers of CLEAN architecture should rarely change while classes in the outer layers may change often.  There's the concept of code hardening over time.  It is resistant to change and that is generally a good quality.  With this in mind, I introduced the idea of weather snapshots having a set of undefined weather components.  Composition through this helps us add new components without modifying existing code.  When these new components become core to the business, we can then decide to create a new snapshot that upgrades the component from living in the set to being a property.  