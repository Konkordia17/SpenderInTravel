package com.example.network_impl.data.mapper

import com.example.core_api.api_models.Currencies
import com.example.core_api.api_models.CurrenciesDataModel
import com.example.core_api.api_models.CurrencyItemDataModel
import com.example.currency_converter_impl.data.models.CurrencyResponse
import javax.inject.Inject

class CurrenciesMapper @Inject constructor() {

  fun mapCurrenciesResponse(entity: CurrencyResponse): CurrenciesDataModel {
    return CurrenciesDataModel(
        currencies =
            entity.data.map { (currency, value) ->
              CurrencyItemDataModel(currency = Currencies.valueOf(currency), value = value)
            })
  }
}
