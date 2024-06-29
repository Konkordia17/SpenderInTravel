package com.example.history_impl.data.mappers

import com.example.core_api.api_models.Currencies
import com.example.core_api.api_models.CurrenciesDataModel
import com.example.core_api.api_models.CurrencyItemDataModel
import com.example.history_impl.data.models.CurrencyRates
import com.example.history_impl.data.models.CurrencyResponse
import javax.inject.Inject
import kotlin.reflect.full.memberProperties

class CurrenciesMapper @Inject constructor() {

  fun mapCurrenciesResponse(entity: CurrencyResponse): CurrenciesDataModel {
    return CurrenciesDataModel(
        currencies = convertCurrencyRatesToList(entity.data.entries.firstOrNull()?.value)
            .map { (currency, value) ->
              CurrencyItemDataModel(currency = currency, value = value)
            })
  }

  private fun convertCurrencyRatesToList(currencyRates: CurrencyRates?): List<CurrencyItemDataModel> {
    val currencyList = mutableListOf<CurrencyItemDataModel>()

    CurrencyRates::class.memberProperties.forEach { property ->
      val currencyCode = property.name
      val amount = property.getter.call(currencyRates) as? Double
      if (amount != null) {
        currencyList.add(CurrencyItemDataModel(Currencies.valueOf(currencyCode), amount))
      }
    }

    return currencyList
  }
}
