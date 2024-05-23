package com.example.main.di

import android.app.Application
import com.example.core_api.CoreFacadeComponentProviders
import com.example.currency_converter.CurrencyConverterComponentProvider
import com.example.main.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies =
        [
            CoreFacadeComponentProviders::class,
            CurrencyConverterComponentProvider::class],
    modules = [MainModule::class])
interface MainComponent {

  fun inject(mainActivity: MainActivity)

  @Component.Factory
  interface Factory {

    fun create(
        @BindsInstance application: Application,
        coreFacadeComponentProviders: CoreFacadeComponentProviders,
        currencyConverterComponentProvider: CurrencyConverterComponentProvider,
    ): MainComponent
  }
}
