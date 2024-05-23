package com.example.spenderintravel

import android.app.Application
import com.example.core_api.AppWithCoreFacade
import com.example.core_api.CoreFacadeComponentProviders
import com.example.currency_converter_api.CurrencyConverterApi
import com.example.currency_converter_impl.di.CurrencyConverterComponent
import com.example.currency_converter_impl.di.DaggerCurrencyConverterComponent
import com.example.main.di.DaggerMainComponent
import com.example.main.di.MainComponent
import com.example.network_api.domain.CurrencyRepository
import com.example.network_impl.di.DaggerNetworkComponent
import com.example.network_impl.di.NetworkComponent
import com.example.spenderintravel.di.AppComponent
import com.example.spenderintravel.di.ComponentsProvider
import com.example.spenderintravel.di.CoreFacadeComponent
import com.example.spenderintravel.di.DaggerAppComponent

class App() : Application(), ComponentsProvider, AppWithCoreFacade {

  lateinit var appComponent: AppComponent

  override val coreFacadeComponent: CoreFacadeComponentProviders by lazy {
    CoreFacadeComponent.init(context = this)
  }

  override val networkComponent: NetworkComponent by lazy {
    DaggerNetworkComponent.factory().create()
  }

  override fun provideCurrencyRepository(): CurrencyRepository {
    return networkComponent.repository
  }
  override fun onCreate() {
    super.onCreate()
    appComponent = DaggerAppComponent.factory().create(this)
  }

  override val currencyConverterComponent: CurrencyConverterComponent by lazy {
    DaggerCurrencyConverterComponent.factory().create(this)
  }

  override fun provideCurrencyConverterApi(): CurrencyConverterApi {
    return currencyConverterComponent.provideCurrencyConverterApi()
  }

  override val mainComponent: MainComponent by lazy {
    DaggerMainComponent.factory().create(this, coreFacadeComponent, this)
  }
}
