package com.example.history_impl.presentation.models

import com.example.core_api.api_models.Currencies

sealed interface CostsPerDayScreenState {

    object Loading: CostsPerDayScreenState

    data class Currency(val currency: Currencies, val sum: Double): CostsPerDayScreenState

    data class Error(val doOnError: ()-> Unit): CostsPerDayScreenState
}