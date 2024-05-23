package com.example.spenderintravel.di

import android.content.Context
import com.example.core.CoreProvidersFactory
import com.example.core_api.CoreComponentProvider
import com.example.core_api.CoreFacadeComponentProviders
import com.example.core_api.di.ApplicationScope
import dagger.Component

@ApplicationScope
@Component(
    dependencies = [CoreComponentProvider::class]
)
interface CoreFacadeComponent : CoreFacadeComponentProviders {

    companion object {

        fun init(context: Context): CoreFacadeComponent {
            val coreContextProvider =
                AppComponent.getApplicationComponent(context)
            val coreComponentProvider =
                CoreProvidersFactory.getCoreComponent(coreContextProvider)

            return DaggerCoreFacadeComponent
                .builder()
                .coreComponentProvider(coreComponentProvider)
                .build()
        }
    }
}