package com.example.history_impl.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GroupedCostsUiModel(
    val title: String,
    val costs: List<CostItemUiModel>,
    val totalSum: Double
): Parcelable
