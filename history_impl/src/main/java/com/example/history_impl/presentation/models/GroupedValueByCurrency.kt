package com.example.history_impl.presentation.models

import com.example.core_api.api_models.Currencies

data class GroupedValueByCurrency(
    val currency: Currencies,
    val value: Double
)
