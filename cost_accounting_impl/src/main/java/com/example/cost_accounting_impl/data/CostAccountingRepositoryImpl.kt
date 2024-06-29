package com.example.cost_accounting_impl.data

import com.example.core_api.database.CostsDao
import com.example.core_api.database.models.CostDbItem
import com.example.cost_accounting_impl.domain.CostAccountingRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CostAccountingRepositoryImpl @Inject constructor(private val dao: CostsDao) :
    CostAccountingRepository {
  override suspend fun saveCosts(costs: List<CostDbItem>) {
    withContext(Dispatchers.IO) { dao.createCosts(costs) }
  }
}
