package com.example.history_impl

import com.example.history_impl.data.models.CurrencyResponse
import com.google.gson.Gson

fun getCurrencyResponse(): CurrencyResponse {
  val string =
      """{
        "data": {
        "2022-01-01": {
        "AUD": 1.0,
        "BGN": 2.0,
        "BRL":3.0   
        }
        }
        }"""
          .trimMargin()
  return Gson().fromJson(string, CurrencyResponse::class.java)
}