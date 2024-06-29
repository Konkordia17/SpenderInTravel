package com.example.activity.di

import com.example.splash_api.SplashMediator
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
interface MainActivityModule {

    companion object {

        @Provides
        fun provideMediator(map: Map<Class<*>, @JvmSuppressWildcards Provider<Any>>): SplashMediator {
            return map[SplashMediator::class.java]!!.get() as SplashMediator
        }
    }
}