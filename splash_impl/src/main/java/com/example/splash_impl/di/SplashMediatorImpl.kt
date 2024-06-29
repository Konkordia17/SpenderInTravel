package com.example.splash_impl.di

import com.example.splash_api.SplashMediator
import com.example.splash_impl.SplashFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class SplashMediatorImpl @Inject constructor() : SplashMediator {

    override fun getSplashScreen(): Screen {
        return FragmentScreen { SplashFragment() }
    }
}
