package com.example.currency_converter_impl.presentation

import com.example.core_api.api_models.Currencies

data class CurrenciesConverterUiModel(
    val currencyFrom: Currencies,
    val currencyFromResult: Double,
    val currencyTo: Currencies,
    val currencyToResult: Double
)
