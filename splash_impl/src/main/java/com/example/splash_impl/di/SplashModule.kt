package com.example.splash_impl.di

import com.example.main_api.MenuMediator
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
interface SplashModule {

    companion object {

        @Provides
        fun provideMediator(map: Map<Class<*>, @JvmSuppressWildcards Provider<Any>>): MenuMediator {
            return map[MenuMediator::class.java]!!.get() as MenuMediator
        }
    }
}