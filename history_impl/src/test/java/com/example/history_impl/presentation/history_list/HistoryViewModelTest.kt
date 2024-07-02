package com.example.history_impl.presentation.history_list

import com.example.history_impl.domain.HistoryInteractor
import com.example.history_impl.getGroupedCostsUiModel
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
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
class HistoryViewModelTest {
  lateinit var sut: HistoryViewModel

  private val historyInteractor = mockk<HistoryInteractor>(relaxed = true)
  private val router = mockk<Router>(relaxed = true)
  private val testDispatcher = UnconfinedTestDispatcher()

  @Before
  fun setUp() {
    Dispatchers.setMain(testDispatcher)
    sut = HistoryViewModel(historyInteractor, router)
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun `WHEN open cost per day THEN screen was opened`() {
    val costs = getGroupedCostsUiModel()
    sut.openCostsPerDay(costs)
    verify(exactly = 1) { router.navigateTo(any<Screen>()) }
  }

  @Test
  fun `WHEN on back click THEN navigate to previous screen`() {
    sut.back()
    verify(exactly = 1) { router.exit() }
  }

  @Test
  fun `WHEN view model init THEN costs received`() = runTest {
    coVerify(exactly = 1) { historyInteractor.getCosts() }
  }
}
