package com.example.currency_converter_impl.presentation

import com.example.currency_converter_impl.domain.CurrencyConverterInteractor
import com.example.currency_converter_impl.getCurrenciesDataModel
import com.github.terrakok.cicerone.Router
import io.mockk.coEvery
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

@ExperimentalCoroutinesApi
class CurrencyConverterViewModelTest {

  lateinit var sut: CurrencyConverterViewModel

  private val currencyConverterInteractor: CurrencyConverterInteractor = mockk(relaxed = true)
  private val router: Router = mockk(relaxed = true)
  private val testDispatcher = UnconfinedTestDispatcher()

  @Before
  fun setUp() {
    Dispatchers.setMain(testDispatcher)
    sut = CurrencyConverterViewModel(currencyConverterInteractor, router)
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun `WHEN view model init THEN currencies received`() = runTest {
    coEvery { currencyConverterInteractor.getCurrencies() } returns getCurrenciesDataModel()
    coVerify(exactly = 1) { currencyConverterInteractor.getCurrencies() }
  }

  @Test
  fun `WHEN on back click THEN navigate to previous screen`() {
    sut.back()
    verify(exactly = 1) { router.exit() }
  }
}
