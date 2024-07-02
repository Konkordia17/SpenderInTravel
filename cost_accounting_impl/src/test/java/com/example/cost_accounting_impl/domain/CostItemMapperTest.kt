package com.example.cost_accounting_impl.domain

import com.example.cost_accounting_impl.presentation.getCostDbItem
import com.example.cost_accounting_impl.presentation.getCostItemUiModel
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class CostItemMapperTest {

  lateinit var sut: CostItemMapper

  @Before
  fun setUp() {
    sut = CostItemMapper()
  }

  @Test
  fun `map success`() {
    val date = 1000000L
    val costs = listOf(getCostItemUiModel(date))
    val expected = listOf(getCostDbItem(date))
    assertEquals(expected, sut.mapCostItem(costs))
  }
}
