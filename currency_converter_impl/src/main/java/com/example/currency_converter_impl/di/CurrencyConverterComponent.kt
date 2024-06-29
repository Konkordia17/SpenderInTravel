package com.example.currency_converter_impl.di

import com.example.core_api.mediator.ProvidersFacade
import com.example.currency_converter_impl.presentation.CurrencyConverterFragment
import com.example.currency_converter_impl.presentation.CurrencyConverterViewModelFactory
import dagger.Component

@Component(
    dependencies = [ProvidersFacade::class],
    modules =
        [CurrencyConverterModule::class, CurrencyConverterBindsModule::class])
interface CurrencyConverterComponent {

  fun inject(fragment: CurrencyConverterFragment)

  fun getViewModelFactory(): CurrencyConverterViewModelFactory

  companion object {

    fun create(
        providersFacade: ProvidersFacade,
    ): CurrencyConverterComponent {
      return DaggerCurrencyConverterComponent.builder().providersFacade(providersFacade).build()
    }
  }
}
