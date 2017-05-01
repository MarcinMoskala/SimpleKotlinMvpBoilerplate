# SimpleKotlinMvpBoilerplate
Presentation of simple Kotlin Android MVP

This project is presentation and boilerplaye of simple MVP for Kotlin. Simplest presentation Activity:

```kotlin
class MainActivity : PresenterBaseActivity(), MainView {

    override val presenter: Presenter by lazy { MainPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
```

It is implementing view interface that is empty in this case. PresenterBaseActivity is taking care of calling Presenter onStart, onResume, onStop and onDestroy. Here is simplest presenter:

```kotlin
class MainPresenter(val view: MainView): Presenter() {
}
```

Now, let's show some more complex example. [Here](https://github.com/MarcinMoskala/SimpleKotlinMvpBoilerplate/tree/master/app/src/main/java/com/marcinmoskala/simplekotlinmvpboilerplate/presentation/main) is really simple Activity where parcelable is proveded through argument. It is later presented on screen [Presenter](https://github.com/MarcinMoskala/SimpleKotlinMvpBoilerplate/blob/master/app/src/main/java/com/marcinmoskala/simplekotlinmvpboilerplate/presentation/main/MainPresenter.kt). This behavior is really easy to [unit test](https://github.com/MarcinMoskala/SimpleKotlinMvpBoilerplate/blob/master/app/src/test/java/com/marcinmoskala/simplekotlinmvpboilerplate/MainPresenterTest.kt).

Also full Login with error checks [is presented](https://github.com/MarcinMoskala/SimpleKotlinMvpBoilerplate/tree/master/app/src/main/java/com/marcinmoskala/simplekotlinmvpboilerplate/presentation/login). Node that even this complex behavior is easy to [unit test](https://github.com/MarcinMoskala/SimpleKotlinMvpBoilerplate/blob/master/app/src/test/java/com/marcinmoskala/simplekotlinmvpboilerplate/LoginPresenterTest.kt). Both preference and network can be easly mocked. 
