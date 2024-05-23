package com.example.currency_converter_impl.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.core_api.navigation.NavigationItem
import com.example.currency_converter_api.CurrencyConverterApi
import com.example.currency_converter_impl.presentation.CurrencyConverterScreen
import com.example.currency_converter_impl.presentation.CurrencyConverterViewModel
import javax.inject.Inject

class CurrencyConverterImpl @Inject constructor() : CurrencyConverterApi {

  override val homeRoute: String = NavigationItem.CurrencyConverter.router

  @Inject lateinit var viewModel: CurrencyConverterViewModel

  override fun registerGraph(
      navGraphBuilder: NavGraphBuilder,
      navController: NavHostController,
      modifier: Modifier,
  ) {

    navGraphBuilder.composable(homeRoute) {
      viewModel.getCurrencies()
      CurrencyConverterScreen(viewModel)
    }
  }
}
