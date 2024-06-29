package com.example.currency_converter.di

import com.example.currency_converter.CurrencyConverterProvider
import com.example.currency_converter_impl.di.DaggerCurrencyConverterComponent
import com.example.network_api.provides.NetworkComponentProvider

object CurrencyConverterProvidersFactory {

  private var currencyConverterComponent: CurrencyConverterProvider? = null

  fun getCurrencyConverterComponent(
      networkComponentProvider: NetworkComponentProvider
  ): CurrencyConverterProvider =
      currencyConverterComponent
          ?: DaggerCurrencyConverterComponent.factory().create(networkComponentProvider).also {
            currencyConverterComponent = it
          }
}
