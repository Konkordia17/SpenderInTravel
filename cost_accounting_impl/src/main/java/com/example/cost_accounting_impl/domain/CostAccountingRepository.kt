package com.example.cost_accounting_impl.domain

import com.example.core_api.database.models.CostDbItem

interface CostAccountingRepository {
  suspend fun saveCosts(costs: List<CostDbItem>)
}
