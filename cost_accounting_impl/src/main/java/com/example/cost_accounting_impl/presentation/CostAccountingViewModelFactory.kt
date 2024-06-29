package com.example.cost_accounting_impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cost_accounting_impl.domain.CostAccountingInteractor
import javax.inject.Inject

class CostAccountingViewModelFactory
@Inject
constructor(private val costAccountingInteractor: CostAccountingInteractor) :
    ViewModelProvider.Factory {

  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    return when (modelClass) {
      CostAccountingViewModel::class.java -> CostAccountingViewModel(costAccountingInteractor)
      else -> throw IllegalArgumentException("Unknown ViewModel class")
    }
        as T
  }
}
