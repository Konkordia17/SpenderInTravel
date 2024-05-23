package com.example.currency_converter_impl.domain

import com.example.network_api.models.CurrencyResponse
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CurrencyConverterInteractor
@Inject
constructor(private val currencyConverterRepository: CurrencyConverterRepository) {

  suspend fun getCurrencies(): CurrencyResponse {
    return withContext(Dispatchers.IO) { currencyConverterRepository.getCurrencies() }
  }
}
