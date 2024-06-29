package com.example.cost_accounting_impl.domain

import com.example.core_api.database.models.CostDbItem
import com.example.cost_accounting_impl.presentation.models.CostItemUiModel
import javax.inject.Inject

class CostItemMapper @Inject constructor() {
  fun mapCostItem(entities: List<CostItemUiModel>): List<CostDbItem> {
    return entities.map { entity ->
      CostDbItem(
          currency = entity.currency,
          category = entity.category,
          comments = entity.comments,
          date = entity.date,
          sum = entity.sum)
    }
  }
}
