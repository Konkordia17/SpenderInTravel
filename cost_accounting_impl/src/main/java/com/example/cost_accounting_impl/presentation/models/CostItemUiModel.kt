package com.example.cost_accounting_impl.presentation.models

data class CostItemUiModel(
    val category: String,
    val comments: String,
    val sum: Double,
    val currency: String,
    val date: Long
)
