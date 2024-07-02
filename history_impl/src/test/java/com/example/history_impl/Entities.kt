package com.example.history_impl

import com.example.core_api.api_models.Currencies
import com.example.core_api.api_models.CurrenciesDataModel
import com.example.core_api.api_models.CurrencyItemDataModel
import com.example.history_impl.presentation.models.GroupedCostsUiModel

fun getGroupedCostsUiModel() =
    GroupedCostsUiModel(title = "title", costs = emptyList(), totalSum = 0.0)

fun getCurrenciesDataModel() =
    CurrenciesDataModel(
        currencies =
            listOf(
                CurrencyItemDataModel(
                    Currencies.AUD,
                    1.0,
                ),
                CurrencyItemDataModel(
                    Currencies.BGN,
                    2.0,
                ),
                CurrencyItemDataModel(
                    Currencies.BRL,
                    3.0,
                ),
                CurrencyItemDataModel(
                    Currencies.CAD,
                    0.0,
                ),
                CurrencyItemDataModel(
                    Currencies.CHF,
                    0.0,
                ),
                CurrencyItemDataModel(
                    Currencies.CNY,
                    0.0,
                ),
                CurrencyItemDataModel(
                    Currencies.CZK,
                    0.0,
                ),
                CurrencyItemDataModel(
                    Currencies.DKK,
                    0.0,
                ),
                CurrencyItemDataModel(
                    Currencies.EUR,
                    0.0,
                ),
                CurrencyItemDataModel(
                    Currencies.GBP,
                    0.0,
                ),
                CurrencyItemDataModel(
                    Currencies.HKD,
                    0.0,
                ),
                CurrencyItemDataModel(
                    Currencies.HRK,
                    0.0,
                ),
                CurrencyItemDataModel(
                    Currencies.HUF,
                    0.0,
                ),
                CurrencyItemDataModel(
                    Currencies.IDR,
                    0.0,
                ),
                CurrencyItemDataModel(
                    Currencies.ILS,
                    0.0,
                ),
                CurrencyItemDataModel(
                    Currencies.INR,
                    0.0,
                ),
                CurrencyItemDataModel(
                    Currencies.ISK,
                    0.0,
                ),
                CurrencyItemDataModel(
                    Currencies.JPY,
                    0.0,
                ),
                CurrencyItemDataModel(
                    Currencies.KRW,
                    0.0,
                ),
                CurrencyItemDataModel(
                    Currencies.MXN,
                    0.0,
                ),
                CurrencyItemDataModel(
                    Currencies.MYR,
                    0.0,
                ),
                CurrencyItemDataModel(
                    Currencies.NOK,
                    0.0,
                ),
                CurrencyItemDataModel(
                    Currencies.NZD,
                    0.0,
                ),
                CurrencyItemDataModel(
                    Currencies.PHP,
                    0.0,
                ),
                CurrencyItemDataModel(
                    Currencies.PLN,
                    0.0,
                ),
                CurrencyItemDataModel(
                    Currencies.RON,
                    0.0,
                ),
                CurrencyItemDataModel(
                    Currencies.RUB,
                    0.0,
                ),
                CurrencyItemDataModel(
                    Currencies.SEK,
                    0.0,
                ),
                CurrencyItemDataModel(
                    Currencies.SGD,
                    0.0,
                ),
                CurrencyItemDataModel(
                    Currencies.THB,
                    0.0,
                ),
                CurrencyItemDataModel(
                    Currencies.TRY,
                    0.0,
                ),
                CurrencyItemDataModel(
                    Currencies.USD,
                    0.0,
                ),
                CurrencyItemDataModel(
                    Currencies.ZAR,
                    0.0,
                ),
            ))