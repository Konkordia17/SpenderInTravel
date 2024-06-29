package com.example.history_impl.di

import com.example.core_api.mediator.ProvidersFacade
import com.example.history_impl.presentation.costs_per_day.CostsPerDayFragment
import com.example.history_impl.presentation.costs_per_day.CostsPerDayViewModelFactory
import com.example.history_impl.presentation.history_list.HistoryFragment
import com.example.history_impl.presentation.history_list.HistoryViewModelFactory
import dagger.Component

@Component(
    dependencies = [ProvidersFacade::class],
    modules = [HistoryBindsModule::class, HistoryModule::class])
interface HistoryComponent {

  fun inject(fragment: HistoryFragment)

  fun inject(fragment: CostsPerDayFragment)

  fun getViewModelFactory(): HistoryViewModelFactory

  fun getCostsViewModelFactory(): CostsPerDayViewModelFactory

  companion object {

    fun create(
        providersFacade: ProvidersFacade,
    ): HistoryComponent {
      return DaggerHistoryComponent.builder().providersFacade(providersFacade).build()
    }
  }
}
