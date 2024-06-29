package com.example.cost_accounting_impl.di

import com.example.cost_accounting_impl.data.CostAccountingRepositoryImpl
import com.example.cost_accounting_impl.domain.CostAccountingRepository
import dagger.Binds
import dagger.Module

@Module
interface CostAccountingBinsModule {
    @Binds
    fun bindsRepository(repositoryImpl: CostAccountingRepositoryImpl): CostAccountingRepository
}