package com.example.network_api.provides

import com.example.network_api.domain.CurrencyRepository

interface NetworkRepositoryProvider {
    fun provideCurrencyRepository(): CurrencyRepository
}

