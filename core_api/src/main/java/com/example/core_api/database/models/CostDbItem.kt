package com.example.core_api.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "COSTS")
data class CostDbItem(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val category: String,
    val currency: String,
    val comments: String,
    val date: Long,
    val sum: Double
)
