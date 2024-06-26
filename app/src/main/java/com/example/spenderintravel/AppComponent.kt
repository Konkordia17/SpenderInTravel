package com.example.spenderintravel

import android.app.Application
import android.content.Context
import com.example.core_api.mediator.AppProvider
import com.example.network_impl.di.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent : AppProvider {

  companion object {

    private var appComponent: AppProvider? = null

    fun create(application: Application): AppProvider {
      return appComponent
          ?: DaggerAppComponent.builder().application(application.applicationContext).build().also {
            appComponent = it
          }
    }
  }

  @Component.Builder
  interface Builder {

    @BindsInstance fun application(context: Context): Builder

    fun build(): AppComponent
  }
}
