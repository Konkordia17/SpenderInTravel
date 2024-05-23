package com.example.currency_converter

import com.example.currency_converter_api.CurrencyConverterProvider
import com.example.currency_converter_impl.di.CurrencyConverterComponent

interface CurrencyConverterComponentProvider: CurrencyConverterProvider {

  val currencyConverterComponent: CurrencyConverterComponent
}