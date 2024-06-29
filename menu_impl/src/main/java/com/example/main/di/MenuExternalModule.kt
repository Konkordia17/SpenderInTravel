package com.example.main.di

import com.example.main_api.MenuMediator
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface MenuExternalModule {

    @Binds
    @IntoMap
    @ClassKey(MenuMediator::class)
    fun bindMediator(mediator: MenuMediatorImpl): Any
}
