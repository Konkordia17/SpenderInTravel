package com.example.currency_converter_impl.di

import com.example.currency_converter.CurrencyConverterMediator
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface CurrencyConverterExternalModule {

    @Binds
    @IntoMap
    @ClassKey(CurrencyConverterMediator::class)
    fun bindMediator(mediator: CurrencyConverterMediatorImpl): Any
}