package com.example.network_impl.di

import com.example.network_api.domain.CurrencyRepository
import com.example.network_api.provides.NetworkRepositoryProvider
import com.example.network_impl.data.CurrencyRepositoryImpl
import dagger.Component

@Component(modules = [NetworkModule::class])
interface NetworkComponent : NetworkRepositoryProvider {

val repository: CurrencyRepository
  @Component.Factory
  interface Factory {
    fun create(): NetworkComponent
  }
}
