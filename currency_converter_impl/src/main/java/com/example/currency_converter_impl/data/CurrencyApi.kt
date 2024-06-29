package com.example.currency_converter_impl.data

import com.example.currency_converter_impl.data.models.CurrencyResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_TOKEN = "fca_live_1rYwQjEsJKxIrVDFn4JJsrPz4HRUTGAbYiZFbIbP"
interface CurrencyApi {

    @GET("/v1/latest/")
    suspend fun getLatestRates(
        @Query("apikey") apiKey: String = API_TOKEN
    ): CurrencyResponse
}