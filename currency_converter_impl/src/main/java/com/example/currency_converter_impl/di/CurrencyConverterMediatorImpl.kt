package com.example.currency_converter_impl.di

import com.example.currency_converter.CurrencyConverterMediator
import com.example.currency_converter_impl.presentation.CurrencyConverterFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class CurrencyConverterMediatorImpl @Inject constructor() : CurrencyConverterMediator {

  override fun getCurrencyConverterScreen(): Screen {
    return FragmentScreen { CurrencyConverterFragment() }
  }
}
