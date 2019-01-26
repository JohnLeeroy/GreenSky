# README #

### About ### 

The assignment was to create a basic weather app with the following requirements

* Show weekly forecast

* Able to refresh

* Clicking on a forecast will bring up a new view with additional information

* Includes Unit Tests

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

![Architecture Outline](https://imgur.com/PC4HDvx.jpg)

### Thoughts ###
Modularity- I like the current boundaries and layers.  With respect to Uncle Bob's CLEAN architecture, each module's dependencies are exclusively in a more inner layer fulfilling the idea of detail depending on policy and not the other way around.  It would be fairly trivial to replace DarkSky with another weather service thanks to this architecture.

CLEAN Architecture Anti-Pattern -  One thing I didn't adhere to with CLEAN is creating a clearer boundary between the use cases and the application.  Normally the core models would be transformed into other request/result POJOs to be consumed by the app.  I find that this creates unecessary duplication when it comes to Android development.    

RxJava - This is a great library that was made Android development easier.  One of the greatest boons is the simplification of threading.  Another is removing the concept of time when it comes to doing work.  It also lets Android developers get into more functional approaches of working with data.

Maintaining Flexibility - Business change over time.  Classes in the inner layers of CLEAN architecture should rarely change while classes in the outer layers may change often.  There's the concept of code hardening over time.  It is resistant to change and that is generally a good quality.  With this in mind, I introduced the idea of weather snapshots having a set of undefined weather components.  Composition through this helps us add new components without modifying existing code.  When these new components become core to the business, we can then decide to create a new snapshot that upgrades the component from living in the set to being a property.  
