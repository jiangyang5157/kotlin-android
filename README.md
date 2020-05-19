# FCF - Router

Source: https://github.com/jiangyang5157/kotlin-android/tree/master/router

## Hightlight
- Easy to use API's
- Highly configurable by *Builder* or *DSL*
- Built-in solution to support passing data to fragments
- Built-in solution to avoid fragment transaction exception
- No XML configuration compared to *androidx.navigation*
- AndroidX support

## What It Can Do
- Flexible routing with fragments in one region of the screen within one Activity
- Easy solution configuration for transitions/animations and particular scenarios
- Survives configuration changes
- Can restore the "routing stack" after process death

## What It Can't Do
- Similarly to *androidx.navigation*, this router is scope-in one Activity - WNF for following reasons:
  - Activity is designed to take full screen, on the contrary fragment is fit in a region of the screen and it requires to be hosted by a specific Activity which has it's own fragment stack
  - Activity launch mode is complicated are varied. Meaning routing from FragmentA to another route belongs to ActivityB involves to provide ability to config a launch mode whereas FragmentA is not aware the route is a fragment or Activity.
  - Android native provides differernt implementations to "show" Activity(`Intent`) and fragment(`FragmentManager`). 
- Routing to Dialog - WNF for following reasons:
  - Dialog is not present in the same region of the screen controlled by the router, it floating on top of fragment/Activity.
  - Show/dismiss dialog should be handle by "current" fragment.

## Example

There are working example at:
- [example-router](https://github.com/jiangyang5157/kotlin-android/tree/master/example-router) has simple router tests.
- [example-router-app](https://github.com/jiangyang5157/kotlin-android/tree/master/example-router-app) is a dummy chat app built with Router.

###### [PNG](https://github.com/jiangyang5157/kotlin-android/blob/master/example-router-app/assets/demo.png)

<img src="https://github.com/jiangyang5157/kotlin-android/blob/master/example-router-app/assets/demo.png"/>

###### [GIF](https://github.com/jiangyang5157/kotlin-android/blob/master/example-router-app/assets/demo.gif)

<img src="https://github.com/jiangyang5157/kotlin-android/blob/master/example-router-app/assets/demo.gif" width=320/>

## Define Route
A Route that marks classes to be suitable for routing. It can easily be represented by data class, also it should have information that indicates associated fragment. There are Built-in interfaces for different usages.

Built-in interface *ParcelableRoute* is for route data management involved parcelization. Such as the default solution is attach/get route data through *Bundle* as a parcelable value.
- `interface ParcelableRoute : Route, Parcelable` 

Built-in interface *DataRoute* is for routing with any data.
- `interface DataRoute<T> : Route`

Built-in interface *KeyRoute* holds an unique *Key*.
- `interface KeyRoute : Route`

Built-in interface *FragmentRoute* holds a fragmentClass.
- `interface FragmentRoute : Route`


###### USAGE
For instance, below 2 different implementations achieve the same propose:

- e.g. 1

```java
interface AppRoute : ParcelableRoute, FragmentRoute

@Parcelize
class LoginRoute() : AppRoute {
    override val fragment = LoginFragment::class
}

@Parcelize  
class ContactListRoute(override val data: List<Contact>) : AppRoute, DataRoute<List<Contact>> {
    override val fragment = ContactListFragment::class
}

...
router push LoginRoute()
router push LoginProcessingRoute(email, password)
router push ContactListRoute(contacts)
```

- e.g. 2

```java
@Parcelize
class UriRoute(override val data: String) : ParcelableRoute, DataRoute<String>, KeyRoute,  {

    override val key: Key
        get() {
            val uri = Uri.parse(data)
            return Key("${uri.scheme}://${uri.authority}${uri.path}")
        }

    fun parameter(name: String): String? = Uri.parse(data).getQueryParameter(name)
}

...
router push UriRoute("myscheme://mydomain/login")
router push UriRoute("myscheme://mydomain/login_processing?email=$email&password=$password")
router push UriRoute("myscheme://mydomain/contact_list?contacts=${Gson().toJson(contacts)}")
```

Disadvantage of "e.g. 1" is the awareness of concrete route class.

Disadvantage of "e.g. 2" is a configuration of fragment mapping is required.

i.e.:

```java
FragmentRouter {
    fragment {
        map(Key("myscheme://mydomain/login")) { LoginFragment::class }
        map(Key("myscheme://mydomain/login_processing")) { LoginProcessingFragment::class }
        map(Key("myscheme://mydomain/contact_list")) { ContactListFragment::class }
    }
}
```

## Initialize Router
A *FragmentRouter* targets fragments. It has both `FragmentRouterBuilder` and `DSL` support.

i.e.

```java
val router: Fragment<UriRoute> = 
    FragmentRouter {
        transition { ... }
        fragment { ... }
        stackInitialization { ... }
        containerLifecycle { ... }
        stackStorage(...)
        routeStorage(...)
        stackPatcher(...)
    }
```

##### USAGE
- `transition`
  - Define default transitions and any particular transitions
  - Optional
- `fragment`
  - Define fragment mapping
  - Optional for *FragmentRoute* solution
  - Only required for *KeyRoute* solution
- `stackInitialization`
  - Setup router actions/instructions that will be executed once *router.setup* is ready. e.g. "`router push MainRoute`"
  - Optional
- `containerLifecycle`
  - Define when to attach/detach to/from fragment container. Accept *androidx.lifecycle.Lifecycle.Event*
  - Optional, default is *ON_RESUME/ON_PAUSE*
- `stackStorage`
  - Provide custom solution to restore routing stack in configuration changes or process death scenario. e.g. save/restore routing stack to prefs/xml/database
  - Optional, when *ParcelableRoute* being used. Default is save/restore routing stack through *Bundle*
  - Only required when *ParcelableRoute* is not being used, since the default solution is only applicable to work with parcelable data.
- `routeStorage`
  - Provide custom solution for route data management. e.g. attach/get route data to/from prefs/viewmodel
  - Optional, when *ParcelableRoute* being used. Default is attach/get router through *Bundle*
  - Only required when *ParcelableRoute* is not being used, since the default solution is only applicable to work with parcelable data.
- `stackPatcher`
  - Provide custom solution for how our routing stack work with *androidx.fragment.app.FragmentManager*
  - Optional

## Fragment Transitions

In order to support animations (fragment transitions) when routing you just need to implement `FragmentTransition` or `GenericFragmentTransition` as many as you want, then register them into router builder.

```java
FragmentRouter {
    transition {
        register(DefaultTransition())
        register(LoginToLoginProcessingTransition())
        register(ContactListToChatTransition())
    }
}
```

###### USAGE
e.g. Define default animations for fragment transaction

```java
class DefaultTransition : FragmentTransition {

    override fun setup(
        transaction: FragmentTransaction,
        exitFragment: Fragment, exitRoute: Route,
        enterFragment: Fragment, enterRoute: Route
    ) {
        exitFragment.exitTransition = Fade(Fade.MODE_OUT)
        enterFragment.enterTransition = Fade(Fade.MODE_IN)
    }
}
```

e.g. Define particular transitions

```java
class ContactListToChatTransition : FragmentTransition {

    override fun setup(
        transaction: FragmentTransaction,
        exitFragment: Fragment, exitRoute: Route,
        enterFragment: Fragment, enterRoute: Route
    ) {
        if (exitFragment is ContactListFragment && enterFragment is ChatFragment) {
            exitFragment.exitTransition = Slide(Gravity.START)
            enterFragment.enterTransition = Slide(Gravity.END)
        } else if (exitFragment is ChatFragment && enterFragment is ContactListFragment) {
            exitFragment.exitTransition = Slide(Gravity.END)
            enterFragment.enterTransition = Slide(Gravity.START)
        }
    }
}
```

e.g. Define particular transitions with concrete scenario instead of *if-check*

```java
class LoginToLoginProcessingTransition :
    GenericFragmentTransition<LoginFragment, Route, LoginProcessingFragment, Route> {

    override fun setup(
        transaction: FragmentTransaction,
        exitFragment: LoginFragment, exitRoute: Route,
        enterFragment: LoginProcessingFragment, enterRoute: Route
    ) {
        exitFragment.exitTransition = Slide(Gravity.BOTTOM)
        enterFragment.enterTransition = Slide(Gravity.TOP)
    }
}
```

## Setup Router

A *FragmentRouter* needs a *ViewGroup* to place the fragments in. A router can be setup from either `RouterFragmentActivity` or `RouterFragment`. It is common to use Activity.

```java
class MainActivity : AppCompatActivity(), RouterFragmentActivity {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        router.setup(savedInstanceState, R.id.content_router)
    }

    override fun onBackPressed() {
        router.popRetainRootImmediateOrFinish()
    }
}
```

*Note: any stackInitialization { ... } from the builder will be executed after the router.setup*

## Receive Route

Accessing the current route from within the any fragment is easily done by implementated *RouterFragment*.

e.g. UriRoute solution

```java
class LoginProcessingFragment : Fragment(), RouterFragment {
    private val route: UriRoute by route()
    private val email by lazy { route.parameter("email") }
    private val password by lazy { route.parameter("password") }
}

class LoginProcessingFragment : Fragment(), RouterFragment {
    private val route: UriRoute by route()
    private val contacts by lazy {
        route.parameter("contacts")?.let {
            Gson().fromJson<List<Contact>>(it)
        } ?: emptyList()
    }
}
```

## Routing DSL Api

```java
clear()
clear(key)
clear(element)
clear(route)
push(element)
push(route)
pushDistinct(route)
pop()
popUntilPredicate(predicate)
popUntil(key)
popUntil(element)
popUntil(route)
replaceTopWith(element)
replaceTopWith(route)

// More flexiable actions, as we are dealling with an `Iterable` here
// Such as `router.routingStackElementsInstruction { filter { ... } }` will remove all routes that doesn't meet the predicate
router.routingStackElementsInstruction { ... }
```

Also, you can find 31 routing stack tests at [unit test](https://github.com/jiangyang5157/kotlin-android/blob/master/router/src/test/java/com/gmail/jiangyang5157/android/router/core/RoutingStackElementsInstructionExecutorTest.kt).
