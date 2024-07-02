package com.example.currency_converter_impl

import com.example.currency_converter_impl.data.models.CurrencyResponse
import com.google.gson.Gson

fun getCurrencyResponse(): CurrencyResponse {
  val string = """{
       "data": {
    "AUD": 85.0,
    "BGN": 90.5,
    "BRL": 78.3
}}"""

  return Gson().fromJson(string, CurrencyResponse::class.java)
}
