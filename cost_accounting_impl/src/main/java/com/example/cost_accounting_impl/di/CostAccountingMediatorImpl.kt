package com.example.cost_accounting_impl.di

import com.example.cost_accounting_api.CostAccountingMediator
import com.example.cost_accounting_impl.presentation.CostAccountingFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class CostAccountingMediatorImpl @Inject constructor() : CostAccountingMediator {
  override fun getCostAccountingMediator(): Screen {
    return FragmentScreen { CostAccountingFragment() }
  }
}
