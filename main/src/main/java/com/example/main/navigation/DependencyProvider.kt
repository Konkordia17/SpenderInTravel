package com.example.main.navigation

import com.example.currency_converter_api.CurrencyConverterApi
import com.example.main_api.MainFeatureApi
import javax.inject.Inject

class DependencyProvider @Inject constructor(
    val homeFeatureApi: MainFeatureApi,
    val currencyConverterApi: CurrencyConverterApi
//    val settingsFeatureApi: SettingsFeatureApi,
//    val onboardingFeatureApi: OnboardingFeatureApi
)