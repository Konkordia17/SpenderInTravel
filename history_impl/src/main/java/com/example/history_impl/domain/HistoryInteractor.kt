package com.example.history_impl.domain

import com.example.core_api.api_models.CurrenciesDataModel
import com.example.core_api.database.models.CostDbItem
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HistoryInteractor @Inject constructor(private val repository: HistoryRepository) {

  suspend fun getCosts(): List<CostDbItem> {
    return withContext(Dispatchers.IO) { repository.getCosts() }
  }

  suspend fun getHistoricalCurrency(date: Long): CurrenciesDataModel {
    return withContext(Dispatchers.IO) { repository.getHistoricalCurrencies(date) }
  }
}
