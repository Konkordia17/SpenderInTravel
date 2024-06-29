package com.example.main.di

import com.example.core_api.mediator.ProvidersFacade
import com.example.main.presentation.MenuFragment
import com.example.main.presentation.MenuViewModelFactory
import dagger.Component

@Component(
    dependencies = [ProvidersFacade::class],
    modules = [MenuModule::class]
)
interface MenuComponent {

    companion object {

        fun create(providersFacade: ProvidersFacade): MenuComponent {
            return DaggerMenuComponent
                .builder()
                .providersFacade(providersFacade)
                .build()
        }
    }

    fun getViewModelFactory(): MenuViewModelFactory

    fun inject(fragment: MenuFragment)
}
