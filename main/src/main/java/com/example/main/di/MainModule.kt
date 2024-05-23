package com.example.main.di

import com.example.currency_converter_api.CurrencyConverterApi
import com.example.main.navigation.DependencyProvider
import com.example.main.navigation.MainFeatureImpl
import com.example.main_api.MainFeatureApi
import dagger.Module
import dagger.Provides

@Module
object MainModule {

  @Provides
  fun providesMainFeatureApi(): MainFeatureApi {
    return MainFeatureImpl()
  }

  @Provides
  fun provideDependencyProvider(
      mainFeatureApi: MainFeatureApi,
      currencyConverterApi: CurrencyConverterApi
  ): DependencyProvider {
    return DependencyProvider(mainFeatureApi, currencyConverterApi)
  }
}
