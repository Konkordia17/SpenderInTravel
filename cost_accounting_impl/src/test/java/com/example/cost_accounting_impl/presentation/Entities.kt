package com.example.cost_accounting_impl.presentation

import com.example.core_api.database.models.CostDbItem
import com.example.cost_accounting_impl.presentation.models.CostItemUiModel
import java.time.ZoneOffset

fun getCostItemUiModel(date: Long, categoryStub: String = "category"): CostItemUiModel {
   val category = categoryStub
   val comments = "comments"
   val currency = "currency"
    val sum = 1.0
   return CostItemUiModel(
        category = category,
        comments = comments,
        currency = currency,
        sum = sum,
        date = date)
}

fun getCostDbItem(date: Long) = CostDbItem (
    id = 0,
    category = "category",
    comments = "comments",
    date = date,
    currency = "currency",
    sum = 1.0
)