package com.example.currency_converter_impl.domain

import com.example.core_api.api_models.CurrenciesDataModel

interface CurrencyConverterRepository {

  suspend fun getCurrencies(): CurrenciesDataModel
}
