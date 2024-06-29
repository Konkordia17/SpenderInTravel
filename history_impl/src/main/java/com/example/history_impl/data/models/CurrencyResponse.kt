package com.example.history_impl.data.models

import com.google.gson.annotations.SerializedName

data class CurrencyResponse(
    @SerializedName("data")
    val data: Map<String, CurrencyRates>
)