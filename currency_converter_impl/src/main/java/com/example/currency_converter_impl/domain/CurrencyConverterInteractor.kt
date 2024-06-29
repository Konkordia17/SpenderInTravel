package com.example.currency_converter_impl.domain

import com.example.core_api.api_models.Currencies
import com.example.core_api.api_models.CurrenciesDataModel
import com.example.core_api.api_models.CurrencyItemDataModel
import com.example.currency_converter_impl.presentation.CurrenciesConverterUiModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CurrencyConverterInteractor
@Inject
constructor(private val currencyConverterRepository: CurrencyConverterRepository) {

  suspend fun getCurrencies(): CurrenciesDataModel {
    return withContext(Dispatchers.IO) { currencyConverterRepository.getCurrencies() }
  }

  fun changeCurrencyValue(
      model: CurrenciesConverterUiModel?,
      currencies: List<CurrencyItemDataModel>,
      isCurrencyFrom: Boolean,
      value: String?,
      currency: Currencies?
  ): CurrenciesConverterUiModel? {

    return model?.let { it ->
      val data = it
      val currencyFrom = if (isCurrencyFrom && currency != null) currency else data.currencyFrom
      val currencyTo = if (!isCurrencyFrom && currency != null) currency else data.currencyTo
      val updatedValue =
          if (value.isNullOrEmpty()) {
            if (isCurrencyFrom) data.currencyFromResult else data.currencyToResult
          } else {
            value.toDoubleOrNull()
          }

      val currentValueToDollar = currencies.firstOrNull { it.currency == currencyFrom }?.value
      val updatedValueToDollar = currencies.firstOrNull { it.currency == currencyTo }?.value

      return if (currentValueToDollar != null &&
          updatedValueToDollar != null &&
          updatedValue != null) {
        val result =
            if (isCurrencyFrom) {
              updatedValueToDollar / currentValueToDollar * updatedValue
            } else {
              currentValueToDollar / updatedValueToDollar * updatedValue
            }

        CurrenciesConverterUiModel(
            currencyFrom = currencyFrom,
            currencyFromResult = if (isCurrencyFrom) updatedValue else result,
            currencyTo = currencyTo,
            currencyToResult = if (isCurrencyFrom) result else updatedValue)
      } else null
    }
  }
}
