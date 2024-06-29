package com.example.currency_converter_impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currency_converter_impl.domain.CurrencyConverterInteractor
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class CurrencyConverterViewModelFactory
@Inject
constructor(
    private val currencyConverterInteractor: CurrencyConverterInteractor,
    private val router: Router
) : ViewModelProvider.Factory {

  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    return when (modelClass) {
      CurrencyConverterViewModel::class.java ->
          CurrencyConverterViewModel(currencyConverterInteractor, router)
      else -> throw IllegalArgumentException("Unknown ViewModel class")
    }
        as T
  }
}
