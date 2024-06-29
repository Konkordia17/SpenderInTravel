package com.example.currency_converter_impl.presentation

import androidx.compose.runtime.MutableState

sealed interface CurrencyScreenState {

  object LoadingState : CurrencyScreenState

  data class ChosenCurrenciesState(val uiModel: MutableState<CurrenciesConverterUiModel>) :
      CurrencyScreenState

  data class Error(val doOnError: () -> Unit) : CurrencyScreenState
}
