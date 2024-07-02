package com.example.history_impl.data.mappers

import com.example.history_impl.getCurrenciesDataModel
import com.example.history_impl.getCurrencyResponse
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class CurrenciesMapperTest {

  lateinit var sut: CurrenciesMapper

  @Before
  fun setUp() {
    sut = CurrenciesMapper()
  }

  @Test
  fun `map success`() {
    val entity = getCurrencyResponse()
    val expected = getCurrenciesDataModel()
    assertEquals(expected, sut.mapCurrenciesResponse(entity))
  }
}
