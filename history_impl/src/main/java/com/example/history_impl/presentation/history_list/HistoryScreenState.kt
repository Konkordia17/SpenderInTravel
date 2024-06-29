package com.example.history_impl.presentation.history_list

import com.example.history_impl.presentation.models.GroupedCostsUiModel
import kotlinx.collections.immutable.ImmutableList

sealed interface HistoryScreenState {

  object Loading : HistoryScreenState

  data class HistoryList(val costs: ImmutableList<GroupedCostsUiModel>) : HistoryScreenState

  object Error : HistoryScreenState
}
