package com.example.history_impl.presentation.costs_per_day

import com.example.core_api.api_models.Currencies
import com.example.history_impl.getGroupedCostsUiModel
import com.example.history_impl.domain.HistoryInteractor
import com.example.history_impl.presentation.models.CostsPerDayScreenState
import com.github.terrakok.cicerone.Router
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

@ExperimentalCoroutinesApi
class CostsPerDayViewModelTest {

  lateinit var sut: CostsPerDayViewModel

  private val historyInteractor = mockk<HistoryInteractor>(relaxed = true)
  private val router = mockk<Router>(relaxed = true)
  private val testDispatcher = UnconfinedTestDispatcher()

  @Before
  fun setUp() {
    Dispatchers.setMain(testDispatcher)
    sut = CostsPerDayViewModel(historyInteractor, router)
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun `WHEN on back click THEN navigate to previous screen`() {
    sut.back()
    verify(exactly = 1) { router.exit() }
  }

  @Test
  fun `WHEN try to get historical currencies THEN currencies received`() = runTest {
    val date = 123810984103L
    val groupedCostsUiModel = getGroupedCostsUiModel()
    sut.getHistoricalCurrencies(date, groupedCostsUiModel)
    coVerify(exactly = 1) { historyInteractor.getHistoricalCurrency(date) }
  }

  @Test
  fun `WHEN try to update currencies THEN currencies were updated`() {
    val currency = Currencies.AUD
    val groupedCostsUiModel = getGroupedCostsUiModel()
    val sum = 0.0
    val expected = CostsPerDayScreenState.Currency(currency, sum)
    sut.updateCurrency(currency, groupedCostsUiModel)
    assertEquals(expected, sut.screenState.value)
  }
}
