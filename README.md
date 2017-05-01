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

It is implementing view interface that is empty in this case. PresenterBaseActivity is taking care of calling Presenter onStart, onResume
