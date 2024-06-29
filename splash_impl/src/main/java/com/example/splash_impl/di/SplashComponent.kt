package com.example.splash_impl.di

import com.example.core_api.mediator.ProvidersFacade
import com.example.splash_impl.SplashFragment
import dagger.Component

@Component(
    dependencies = [ProvidersFacade::class],
    modules = [SplashModule::class],
)
interface SplashComponent {

    companion object {

        fun create(providersFacade: ProvidersFacade): SplashComponent {
            return DaggerSplashComponent
                .builder()
                .providersFacade(providersFacade)
                .build()
        }
    }

    fun inject(fragment: SplashFragment)
}
