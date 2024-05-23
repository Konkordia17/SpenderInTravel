package com.example.network_api.domain

import com.example.network_api.models.CurrencyResponse

interface CurrencyRepository {

    suspend fun getCurrencies(): CurrencyResponse
}