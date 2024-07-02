package com.example.currency_converter_impl.data

import com.example.core_api.api_models.CurrenciesDataModel
import com.example.currency_converter_impl.domain.CurrencyConverterRepository
import com.example.currency_converter_impl.data.mapper.CurrenciesMapper
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CurrencyRepositoryImpl
@Inject
constructor(private val api: CurrencyApi, private val currenciesMapper: CurrenciesMapper) :
    CurrencyConverterRepository {
  override suspend fun getCurrencies(): CurrenciesDataModel {
    return withContext(Dispatchers.IO) {
      currenciesMapper.mapCurrenciesResponse(api.getLatestRates())
    }
  }
}
