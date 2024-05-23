package com.example.spenderintravel.di

import android.content.Context
import com.example.core_api.CoreContextProvider
import com.example.core_api.di.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component
interface AppComponent : CoreContextProvider {

  companion object {

    private var applicationComponent: AppComponent? = null

    fun getApplicationComponent(context: Context): CoreContextProvider =
        applicationComponent
            ?: DaggerAppComponent.factory().create(context).also { applicationComponent = it }
  }

  @Component.Factory
  interface Factory {

    fun create(@BindsInstance context: Context): AppComponent
  }
}
