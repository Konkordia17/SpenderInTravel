package com.example.splash_impl.di

import com.example.splash_api.SplashMediator
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface SplashExternalModule {

    @Binds
    @IntoMap
    @ClassKey(SplashMediator::class)
    fun bindMediator(mediator: SplashMediatorImpl): Any
}
