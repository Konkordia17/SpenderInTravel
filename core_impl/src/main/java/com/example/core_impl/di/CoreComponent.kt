package com.example.core_impl.di

import com.example.core_api.CoreComponentProvider
import com.example.core_api.CoreContextProvider
import com.example.core_api.di.ApplicationScope
import dagger.Component

@ApplicationScope
@Component(dependencies = [CoreContextProvider::class])
interface CoreComponent : CoreComponentProvider {
}
