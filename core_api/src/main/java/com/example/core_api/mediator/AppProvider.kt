package com.example.core_api.mediator

import android.content.Context
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import retrofit2.Retrofit

interface AppProvider {

  fun provideContext(): Context

  fun navigatorHolder(): NavigatorHolder

  fun router(): Router

  fun retrofit(): Retrofit
}
