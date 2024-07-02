package com.example.currency_converter_impl.domain

import com.example.core_api.api_models.Currencies
import com.example.core_api.api_models.CurrencyItemDataModel
import com.example.currency_converter_impl.presentation.CurrenciesConverterUiModel
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class CurrencyConverterInteractorTest {

  lateinit var sut: CurrencyConverterInteractor
  private val currencyConverterRepository: CurrencyConverterRepository = mockk(relaxed = true)

  @Before
  fun setUp() {
    sut = CurrencyConverterInteractor(currencyConverterRepository)
  }

  @Test
  fun `test changeCurrencyValue with valid input`() {
    val initialModel =
        CurrenciesConverterUiModel(
            currencyFrom = Currencies.USD,
            currencyFromResult = 100.0,
            currencyTo = Currencies.EUR,
            currencyToResult = 85.0)

    val currencies =
        listOf(
            CurrencyItemDataModel(Currencies.USD, 1.0), CurrencyItemDataModel(Currencies.EUR, 0.85))

    val updatedModel =
        sut.changeCurrencyValue(
            model = initialModel,
            currencies = currencies,
            isCurrencyFrom = true,
            value = "200",
            currency = null)

    assertNotNull(updatedModel)
    assertEquals(200.0, updatedModel?.currencyFromResult)
    assertEquals(170.0, updatedModel?.currencyToResult)
  }

  @Test
  fun `test changeCurrencyValue with null value`() {
    val initialModel =
        CurrenciesConverterUiModel(
            currencyFrom = Currencies.USD,
            currencyFromResult = 100.0,
            currencyTo = Currencies.EUR,
            currencyToResult = 85.0)

    val currencies =
        listOf(
            CurrencyItemDataModel(Currencies.USD, 1.0), CurrencyItemDataModel(Currencies.EUR, 0.85))

    val updatedModel =
        sut.changeCurrencyValue(
            model = initialModel,
            currencies = currencies,
            isCurrencyFrom = true,
            value = null,
            currency = null)

    assertNotNull(updatedModel)
    assertEquals(100.0, updatedModel?.currencyFromResult)
    assertEquals(85.0, updatedModel?.currencyToResult)
  }

  @Test
  fun `test changeCurrencyValue with invalid currency`() {
    val initialModel =
        CurrenciesConverterUiModel(
            currencyFrom = Currencies.USD,
            currencyFromResult = 100.0,
            currencyTo = Currencies.EUR,
            currencyToResult = 85.0)

    val currencies = listOf(CurrencyItemDataModel(Currencies.USD, 1.0))

    val updatedModel =
        sut.changeCurrencyValue(
            model = initialModel,
            currencies = currencies,
            isCurrencyFrom = true,
            value = "200",
            currency = null)

    assertNull(updatedModel)
  }
}
