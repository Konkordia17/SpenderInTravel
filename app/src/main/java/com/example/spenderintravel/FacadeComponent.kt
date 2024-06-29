package com.example.spenderintravel

import android.app.Application
import com.example.core_api.database.DatabaseProvider
import com.example.core_api.mediator.AppProvider
import com.example.core_api.mediator.ProvidersFacade
import com.example.cost_accounting_impl.di.CostAccountingExternalModule
import com.example.currency_converter_impl.di.CurrencyConverterExternalModule
import com.example.database.CoreProvidersFactory
import com.example.history_impl.di.HistoryExternalModule
import com.example.main.di.MenuExternalModule
import com.example.splash_impl.di.SplashExternalModule
import dagger.Component

@Component(
    dependencies = [AppProvider::class, DatabaseProvider::class],
    modules =
        [
            CurrencyConverterExternalModule::class,
            SplashExternalModule::class,
            MenuExternalModule::class,
            CostAccountingExternalModule::class,
            HistoryExternalModule::class])
interface FacadeComponent : ProvidersFacade {

  companion object {

    fun init(application: Application): FacadeComponent =
        DaggerFacadeComponent.builder()
            .appProvider(AppComponent.create(application))
            .databaseProvider(
                CoreProvidersFactory.createDatabaseBuilder(AppComponent.create(application)))
            .build()
  }
}
