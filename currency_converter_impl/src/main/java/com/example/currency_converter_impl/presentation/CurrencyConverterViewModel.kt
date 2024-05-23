package com.example.currency_converter_impl.presentation

import androidx.lifecycle.viewModelScope
import com.example.currency_converter_impl.domain.CurrencyConverterInteractor
import com.example.core_api.ViewModelApi
import java.lang.Exception
import javax.inject.Inject
import kotlinx.coroutines.launch

class CurrencyConverterViewModel
@Inject
constructor(private val currencyConverterInteractor: CurrencyConverterInteractor) :
    ViewModelApi() {

  init {
    println()
  }

  fun getCurrencies() {
    viewModelScope.launch {
      try {
        val result = currencyConverterInteractor.getCurrencies()
        println(result)
      } catch (e: Exception) {
        val exception = e
      }
    }
  }
}
