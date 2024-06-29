package com.example.cost_accounting_impl.domain

import com.example.cost_accounting_impl.presentation.models.CostItemUiModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CostAccountingInteractor
@Inject
constructor(
    private val costAccountingRepository: CostAccountingRepository,
    private val costItemMapper: CostItemMapper
) {

  suspend fun saveCosts(costs: List<CostItemUiModel>) {
    withContext(Dispatchers.IO) {
      costAccountingRepository.saveCosts(costItemMapper.mapCostItem(costs))
    }
  }
}
