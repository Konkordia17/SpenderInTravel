package com.example.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.activity.di.MainActivityComponent
import com.example.core_api.mediator.AppWithFacade
import com.example.splash_api.SplashMediator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {


    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var splashMediator: SplashMediator

    private val navigator = AppNavigator(this, R.id.fragment_container)

    private val mainActivityComponent: MainActivityComponent by lazy {
        MainActivityComponent.create((application as AppWithFacade).getFacade())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityComponent.inject(this)
        navigatorHolder.setNavigator(navigator)
        router.replaceScreen(splashMediator.getSplashScreen())
    }
}
