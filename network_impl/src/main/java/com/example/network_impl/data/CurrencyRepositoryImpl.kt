package com.example.network_impl.data

import com.example.network_api.domain.CurrencyRepository
import com.example.network_api.models.CurrencyResponse
import com.example.network_api.provides.CurrencyApi
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CurrencyRepositoryImpl @Inject constructor(private val api: CurrencyApi) :
    CurrencyRepository {
  override suspend fun getCurrencies(): CurrencyResponse {
    return withContext(Dispatchers.IO) { api.getLatestRates() }
  }
}
