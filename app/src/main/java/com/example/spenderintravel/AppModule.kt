package com.example.spenderintravel

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Module
    companion object {

        @Provides
        @Singleton
        fun provideCicerone(): Cicerone<Router> = Cicerone.create()

        @Provides
        @Singleton
        fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder = cicerone.getNavigatorHolder()

        @Provides
        @Singleton
        fun provideRouter(cicerone: Cicerone<Router>): Router = cicerone.router
    }
}
