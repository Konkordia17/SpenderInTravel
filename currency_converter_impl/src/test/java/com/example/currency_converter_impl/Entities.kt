package com.example.currency_converter_impl

import com.example.core_api.api_models.Currencies
import com.example.core_api.api_models.CurrenciesDataModel
import com.example.core_api.api_models.CurrencyItemDataModel
import com.example.currency_converter_impl.presentation.CurrenciesConverterUiModel

fun getCurrenciesConverterUiModel() =
    CurrenciesConverterUiModel(
        currencyFrom = Currencies.AUD,
        currencyTo = Currencies.CAD,
        currencyFromResult = 1.0,
        currencyToResult = 2.0)

fun getCurrenciesDataModel() =
    CurrenciesDataModel(
        currencies =
            listOf(
                CurrencyItemDataModel(
                    Currencies.AUD,
                    85.0,
                ),
                CurrencyItemDataModel(
                    Currencies.BGN,
                    90.5,
                ),
                CurrencyItemDataModel(
                    Currencies.BRL,
                    78.3,
                )))
