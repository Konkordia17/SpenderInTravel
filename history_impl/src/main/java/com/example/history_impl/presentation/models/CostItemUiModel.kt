package com.example.history_impl.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CostItemUiModel(
    val category: String,
    val currency: String,
    val comments: String,
    val date: Long,
    val sum: Double
) : Parcelable
