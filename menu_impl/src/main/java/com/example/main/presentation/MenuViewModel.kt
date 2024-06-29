package com.example.main.presentation

import androidx.lifecycle.ViewModel
import com.example.cost_accounting_api.CostAccountingMediator
import com.example.currency_converter.CurrencyConverterMediator
import com.example.history_api.di.HistoryMediator
import com.github.terrakok.cicerone.Router
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class MenuViewModel
@AssistedInject
constructor(
    private val router: Router,
    private val currencyConverterMediator: CurrencyConverterMediator,
    private val costAccountingMediator: CostAccountingMediator,
    private val historyMediator: HistoryMediator
) : ViewModel() {

  fun onMenuClick(screen: MenuScreens) {
    when (screen) {
      MenuScreens.CURRENCY_CONVERTER ->
          router.navigateTo(currencyConverterMediator.getCurrencyConverterScreen())
      MenuScreens.COST_ACCOUNTING ->
          router.navigateTo(costAccountingMediator.getCostAccountingMediator())
      MenuScreens.HISTORY -> router.navigateTo(historyMediator.getHistoryMediatorScreen())
    }
  }

  @AssistedFactory
  interface Factory {
    fun create(): MenuViewModel
  }
}
