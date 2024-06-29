package com.example.history_impl.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.core_api.api_models.CurrenciesDataModel
import com.example.core_api.database.CostsDao
import com.example.core_api.database.models.CostDbItem
import com.example.history_impl.data.mappers.CurrenciesMapper
import com.example.history_impl.domain.HistoryRepository
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HistoryRepositoryImpl
@Inject
constructor(
    private val dao: CostsDao,
    private val currenciesApi: CurrenciesApi,
    private val mapper: CurrenciesMapper
) : HistoryRepository {
  override suspend fun getCosts(): List<CostDbItem> {
    return withContext(Dispatchers.IO) { dao.getCosts() }
  }

  @RequiresApi(Build.VERSION_CODES.O)
  override suspend fun getHistoricalCurrencies(date: Long): CurrenciesDataModel {
    return withContext(Dispatchers.IO) {
      mapper.mapCurrenciesResponse(
          currenciesApi.getHistoricalCurrency(date = convertLongToDate(date)))
    }
  }

  @RequiresApi(Build.VERSION_CODES.O)
  private fun convertLongToDate(longDate: Long): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(longDate), ZoneId.systemDefault())
    return dateTime.format(formatter)
  }
}
