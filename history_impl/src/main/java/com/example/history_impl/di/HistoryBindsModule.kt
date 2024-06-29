package com.example.history_impl.di

import com.example.history_impl.data.HistoryRepositoryImpl
import com.example.history_impl.domain.HistoryRepository
import dagger.Binds
import dagger.Module

@Module
interface HistoryBindsModule {
    @Binds
    fun bindHistoryRepository(historyRepository: HistoryRepositoryImpl): HistoryRepository
}