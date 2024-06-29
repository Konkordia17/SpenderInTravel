package com.example.cost_accounting_impl.di

import com.example.cost_accounting_api.CostAccountingMediator
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface CostAccountingExternalModule {
    @Binds
    @IntoMap
    @ClassKey(CostAccountingMediator::class)
    fun bindMediator(mediator: CostAccountingMediatorImpl): Any
}