package com.example.history_impl.presentation.mappers

import com.example.core_api.database.models.CostDbItem
import com.example.history_impl.presentation.models.CostItemUiModel
import javax.inject.Inject

class CostItemMapper @Inject constructor() {

  fun map(entity: CostDbItem): CostItemUiModel {
    return CostItemUiModel(
        category = entity.category,
        comments = entity.comments,
        currency = entity.currency,
        sum = entity.sum,
        date = entity.date)
  }
}
