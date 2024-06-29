package com.example.history_impl.domain

import com.example.core_api.database.models.CostDbItem
import com.example.core_api.api_models.CurrenciesDataModel

interface HistoryRepository  {
    suspend fun getCosts(): List<CostDbItem>

    suspend fun getHistoricalCurrencies(date: Long): CurrenciesDataModel
}