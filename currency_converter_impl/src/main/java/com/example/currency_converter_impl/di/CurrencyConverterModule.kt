package com.example.currency_converter_impl.di

import com.example.currency_converter_impl.data.CurrencyApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object CurrencyConverterModule {

  @Provides
  fun provideApiService(retrofit: Retrofit): CurrencyApi = retrofit.create(CurrencyApi::class.java)
}
