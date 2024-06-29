package com.example.cost_accounting_impl.di

import com.example.core_api.mediator.ProvidersFacade
import com.example.cost_accounting_impl.presentation.CostAccountingFragment
import com.example.cost_accounting_impl.presentation.CostAccountingViewModelFactory
import dagger.Component

@Component(dependencies = [ProvidersFacade::class], modules = [CostAccountingBinsModule::class])
interface CostAccountingComponent {

  fun inject(fragment: CostAccountingFragment)

  fun getViewModelFactory(): CostAccountingViewModelFactory

  companion object {

    fun create(
        providersFacade: ProvidersFacade,
    ): CostAccountingComponent {
      return DaggerCostAccountingComponent.builder().providersFacade(providersFacade).build()
    }
  }
}
