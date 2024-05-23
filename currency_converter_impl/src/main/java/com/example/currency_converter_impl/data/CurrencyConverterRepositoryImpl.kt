package com.example.currency_converter_impl.data

import com.example.currency_converter_impl.domain.CurrencyConverterRepository
import com.example.network_api.domain.CurrencyRepository
import com.example.network_api.models.CurrencyResponse
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CurrencyConverterRepositoryImpl
@Inject
constructor(private val converterRepository: CurrencyRepository) : CurrencyConverterRepository {
  override suspend fun getCurrencies(): CurrencyResponse {
    return withContext(Dispatchers.IO) { converterRepository.getCurrencies() }
  }
}
