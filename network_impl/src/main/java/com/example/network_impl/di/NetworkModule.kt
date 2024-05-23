package com.example.network_impl.di

import com.example.network_api.domain.CurrencyRepository
import com.example.network_api.provides.CurrencyApi
import com.example.network_impl.data.CurrencyRepositoryImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object NetworkModule {
  @Provides
  fun provideOkHttpClient(): OkHttpClient {
    val loggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
  }

  @Provides
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    val converterFactory = GsonConverterFactory.create()
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(converterFactory)
        .client(okHttpClient)
        .build()
  }

  @Provides
  fun provideApiService(retrofit: Retrofit): CurrencyApi = retrofit.create(CurrencyApi::class.java)
    private const val BASE_URL = "https://api.freecurrencyapi.com/"
  @Provides
  fun provideCurrencyRepository(currencyRepository: CurrencyRepositoryImpl): CurrencyRepository {
    return currencyRepository
  }

}
