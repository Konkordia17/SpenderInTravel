package com.example.activity.di

import com.example.activity.MainActivity
import com.example.core_api.mediator.ProvidersFacade
import dagger.Component

@Component(
    modules = [MainActivityModule::class],
    dependencies = [ProvidersFacade::class]
)
interface MainActivityComponent {

    companion object {

        fun create(providersFacade: ProvidersFacade): MainActivityComponent {
            return DaggerMainActivityComponent
                .builder()
                .providersFacade(providersFacade)
                .build()
        }
    }

    fun inject(mainActivity: MainActivity)
}
