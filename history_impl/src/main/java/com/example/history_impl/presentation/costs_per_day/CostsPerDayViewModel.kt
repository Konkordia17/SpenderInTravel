package com.example.history_impl.presentation.costs_per_day

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_api.api_models.Currencies
import com.example.core_api.api_models.CurrencyItemDataModel
import com.example.history_impl.domain.HistoryInteractor
import com.example.history_impl.presentation.models.CostItemUiModel
import com.example.history_impl.presentation.models.CostsPerDayScreenState
import com.example.history_impl.presentation.models.GroupedCostsUiModel
import com.example.history_impl.presentation.models.GroupedValueByCurrency
import com.github.terrakok.cicerone.Router
import javax.inject.Inject
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CostsPerDayViewModel
@Inject
constructor(private val historyInteractor: HistoryInteractor, private val router: Router) :
    ViewModel() {

  private val currencies = MutableStateFlow<List<CurrencyItemDataModel>>(emptyList())

  private val _screenState =
      MutableStateFlow<CostsPerDayScreenState>(CostsPerDayScreenState.Loading)
  val screenState: StateFlow<CostsPerDayScreenState> = _screenState

  fun getListCurrencies(): ImmutableList<CurrencyItemDataModel> {
    return currencies.value.toImmutableList()
  }

  fun back() {
    router.exit()
  }

  fun getHistoricalCurrencies(date: Long, groupedCostsUiModel: GroupedCostsUiModel) {
    viewModelScope.launch {
      try {
        val result = historyInteractor.getHistoricalCurrency(date)
        currencies.value = result.currencies
        updateCurrency(Currencies.USD, groupedCostsUiModel)
      } catch (t: Throwable) {
        _screenState.value =
            CostsPerDayScreenState.Error { getHistoricalCurrencies(date, groupedCostsUiModel) }
      }
    }
  }

  fun updateCurrency(currency: Currencies, groupedCostsUiModel: GroupedCostsUiModel) {
    _screenState.value =
        CostsPerDayScreenState.Currency(
            currency = currency,
            sum =
                calculateResultSum(
                    targetCurrency = currency,
                    currencyRates = currencies.value,
                    groupedCostsUiModel = groupedCostsUiModel))
  }

  private fun calculateResultSum(
      targetCurrency: Currencies,
      currencyRates: List<CurrencyItemDataModel>,
      groupedCostsUiModel: GroupedCostsUiModel
  ): Double {
    val groupedCostsByCurrency = aggregateCostsByCurrency(groupedCostsUiModel.costs)
    return groupedCostsByCurrency.sumOf {
      convertCurrency(
          amount = it.value,
          fromCurrency = it.currency,
          toCurrency = targetCurrency,
          rates = currencyRates)
    }
  }

  private fun convertCurrency(
      amount: Double,
      fromCurrency: Currencies,
      toCurrency: Currencies,
      rates: List<CurrencyItemDataModel>
  ): Double {
    val fromRate = rates.find { it.currency == fromCurrency }?.value
    val toRate = rates.find { it.currency == toCurrency }?.value

    return if (fromRate != null && toRate != null) {
      val amountInUsd = amount / fromRate
      amountInUsd * toRate
    } else {
      0.0
    }
  }

  private fun aggregateCostsByCurrency(costs: List<CostItemUiModel>): List<GroupedValueByCurrency> {
    return costs
        .groupBy { it.currency }
        .map { (currency, items) ->
          val totalSum = items.sumOf { it.sum }
          GroupedValueByCurrency(currency = Currencies.valueOf(currency), value = totalSum)
        }
  }
}
