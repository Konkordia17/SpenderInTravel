package com.example.cost_accounting_impl.presentation.models

sealed interface CostAccountingScreenState {

  object Initial: CostAccountingScreenState

  object Loading : CostAccountingScreenState

  object Error : CostAccountingScreenState
}
