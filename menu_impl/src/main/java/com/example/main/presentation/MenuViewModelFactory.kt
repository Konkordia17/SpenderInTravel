package com.example.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cost_accounting_api.CostAccountingMediator
import com.example.currency_converter.CurrencyConverterMediator
import com.example.history_api.di.HistoryMediator
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class MenuViewModelFactory
@Inject
constructor(
    private val router: Router,
    private val currencyConverterMediator: CurrencyConverterMediator,
    private val costAccountingMediator: CostAccountingMediator,
    private val historyMediator: HistoryMediator
) : ViewModelProvider.Factory {

  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    return when (modelClass) {
      MenuViewModel::class.java ->
          MenuViewModel(router, currencyConverterMediator, costAccountingMediator, historyMediator)
      else -> throw IllegalArgumentException("Unknown ViewModel class")
    }
        as T
  }
}
