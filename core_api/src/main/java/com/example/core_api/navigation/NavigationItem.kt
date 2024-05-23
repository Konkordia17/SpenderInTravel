package com.example.core_api.navigation
enum class Screen {
  EXPENSES,
  SPLASH,
  MENU,
  CURRENCY_CONVERTER
}

sealed class NavigationItem(val router: String) {
  data object ExpensesScreen : NavigationItem(Screen.EXPENSES.name)

  data object SplashScreen : NavigationItem(Screen.SPLASH.name)

  data object MenuScreen : NavigationItem(Screen.MENU.name)

  data object CurrencyConverter : NavigationItem(Screen.CURRENCY_CONVERTER.name)
}
