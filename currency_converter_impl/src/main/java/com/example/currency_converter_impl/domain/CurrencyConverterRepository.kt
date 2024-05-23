package com.example.currency_converter_impl.domain

import com.example.network_api.models.CurrencyResponse

interface CurrencyConverterRepository {

    suspend fun getCurrencies(): CurrencyResponse
}