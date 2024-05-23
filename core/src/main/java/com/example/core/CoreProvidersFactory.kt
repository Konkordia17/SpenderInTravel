package com.example.core

import com.example.core_api.CoreComponentProvider
import com.example.core_api.CoreContextProvider
import com.example.core_impl.di.DaggerCoreComponent

object CoreProvidersFactory {

    private var coreComponent: CoreComponentProvider? = null

    fun getCoreComponent(coreContextProvider: CoreContextProvider): CoreComponentProvider =
        coreComponent ?: DaggerCoreComponent
            .builder()
            .coreContextProvider(coreContextProvider)
            .build().also {
                coreComponent = it
            }
}