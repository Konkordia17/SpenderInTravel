package com.example.history_impl.di

import com.example.history_api.di.HistoryMediator
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface HistoryExternalModule {

    @Binds
    @IntoMap
    @ClassKey(HistoryMediator::class)
    fun bindMediator(mediator: HistoryMediatorImpl): Any
}