package com.example.currency_converter_impl.data.mapper

import com.example.currency_converter_impl.getCurrenciesDataModel
import com.example.currency_converter_impl.getCurrencyResponse
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
