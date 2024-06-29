package com.example.currency_converter_impl.di

import com.example.currency_converter_impl.data.CurrencyRepositoryImpl
import com.example.currency_converter_impl.domain.CurrencyConverterRepository
import dagger.Binds
import dagger.Module

@Module
interface CurrencyConverterBindsModule {

  @Binds
  fun provideCurrencyConverterRepository(
      currencyConverterRepository: CurrencyRepositoryImpl
  ): CurrencyConverterRepository

}
