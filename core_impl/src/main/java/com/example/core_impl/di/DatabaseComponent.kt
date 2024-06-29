package com.example.core_impl.di

import com.example.core_api.database.DatabaseProvider
import com.example.core_api.mediator.AppProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [AppProvider::class], modules = [DatabaseModule::class])
interface DatabaseComponent : DatabaseProvider
