package com.example.main.di

import com.example.cost_accounting_api.CostAccountingMediator
import com.example.currency_converter.CurrencyConverterMediator
import com.example.history_api.di.HistoryMediator
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
object MenuModule {

  @Provides
  fun provideCurrencyConverterMediator(
      map: Map<Class<*>, @JvmSuppressWildcards Provider<Any>>
  ): CurrencyConverterMediator {
    return map[CurrencyConverterMediator::class.java]!!.get() as CurrencyConverterMediator
  }

  @Provides
  fun provideCostAccountingMediator(
      map: Map<Class<*>, @JvmSuppressWildcards Provider<Any>>
  ): CostAccountingMediator {
    return map[CostAccountingMediator::class.java]!!.get() as CostAccountingMediator
  }

  @Provides
  fun provideHistoryMediator(
      map: Map<Class<*>, @JvmSuppressWildcards Provider<Any>>
  ): HistoryMediator {
    return map[HistoryMediator::class.java]!!.get() as HistoryMediator
  }
}
