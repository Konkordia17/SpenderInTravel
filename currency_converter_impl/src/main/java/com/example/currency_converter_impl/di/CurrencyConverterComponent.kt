package com.example.currency_converter_impl.di

import com.example.currency_converter_api.CurrencyConverterProvider
import com.example.currency_converter_impl.presentation.CurrencyConverterViewModel
import com.example.network.NetworkComponentProvider
import dagger.Component

@Component(
    modules = [CurrencyConverterModule::class], dependencies = [NetworkComponentProvider::class])
interface CurrencyConverterComponent : CurrencyConverterProvider {

    fun injectViewModel(): CurrencyConverterViewModel

  @Component.Factory
  interface Factory {
    fun create(networkComponentProvider: NetworkComponentProvider): CurrencyConverterComponent
  }
}
