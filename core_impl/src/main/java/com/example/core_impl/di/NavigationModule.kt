package com.example.core_impl.di

import com.example.core_api.di.ApplicationScope
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

@Module
object NavigationModule {

  private val cicerone: Cicerone<Router> = Cicerone.create()

  @Provides
  @ApplicationScope
  fun provideRouter(): Router {
    return cicerone.router
  }

  @Provides
  @ApplicationScope
  fun provideNavigatorHolder(): NavigatorHolder {
    return cicerone.getNavigatorHolder()
  }
}
