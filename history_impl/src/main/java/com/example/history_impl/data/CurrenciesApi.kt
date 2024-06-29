package com.example.history_impl.data

import com.example.history_impl.data.models.CurrencyResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_TOKEN = "fca_live_1rYwQjEsJKxIrVDFn4JJsrPz4HRUTGAbYiZFbIbP"
interface CurrenciesApi {

    @GET("/v1/historical/")
    suspend fun getHistoricalCurrency(
        @Query("apikey") apiKey: String = API_TOKEN,
        @Query("date") date: String
    ): CurrencyResponse
}