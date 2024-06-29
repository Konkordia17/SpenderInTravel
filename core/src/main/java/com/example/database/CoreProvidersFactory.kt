package com.example.database

import com.example.core_api.database.DatabaseProvider
import com.example.core_api.mediator.AppProvider
import com.example.core_impl.di.DaggerDatabaseComponent

object CoreProvidersFactory {

    fun createDatabaseBuilder(appProvider: AppProvider): DatabaseProvider {
        return DaggerDatabaseComponent.builder().appProvider(appProvider).build()
    }
}