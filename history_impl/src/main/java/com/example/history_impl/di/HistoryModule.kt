package com.example.history_impl.di

import com.example.history_impl.data.CurrenciesApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
@Module
class HistoryModule {
    @Provides
    fun provideApiService(retrofit: Retrofit): CurrenciesApi = retrofit.create(CurrenciesApi::class.java)
}