package com.example.currency_converter_impl.di

import com.example.currency_converter_api.CurrencyConverterApi
import com.example.currency_converter_impl.data.CurrencyConverterRepositoryImpl
import com.example.currency_converter_impl.domain.CurrencyConverterRepository
import com.example.currency_converter_impl.navigation.CurrencyConverterImpl
import dagger.Binds
import dagger.Module

@Module
interface CurrencyConverterModule {

  @Binds
  fun provideCurrencyConverterApi(
      currencyConverterImpl: CurrencyConverterImpl
  ): CurrencyConverterApi

  @Binds
  fun provideCurrencyConverterRepository(
      currencyConverterRepository: CurrencyConverterRepositoryImpl
  ): CurrencyConverterRepository

//    @Binds
//    @IntoMap
//    abstract fun bindCurrencyConverterViewModel(viewModel: CurrencyConverterViewModel): ViewModel
}
