package com.example.cost_accounting_impl.presentation

import com.example.cost_accounting.R
import com.example.cost_accounting_impl.domain.CostAccountingInteractor
import com.example.cost_accounting_impl.presentation.models.CategoryItem
import com.example.cost_accounting_impl.presentation.models.CostAccountingScreenState
import com.example.cost_accounting_impl.presentation.models.CostItemUiModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset
import junit.framework.TestCase.assertEquals
import kotlinx.collections.immutable.persistentListOf
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
class CostAccountingViewModelTest {

  private lateinit var sut: CostAccountingViewModel
  private val costAccountingInteractor: CostAccountingInteractor = mockk(relaxed = true)
  private val testDispatcher = UnconfinedTestDispatcher()
  private lateinit var category: String
  private lateinit var comments: String
  private lateinit var currency: String
  private val sum = 1.0

  private val categoriesList =
      persistentListOf(
          CategoryItem(name = "Здоровье", imageRes = R.drawable.ic_health),
          CategoryItem(name = "Еда", imageRes = R.drawable.ic_food),
          CategoryItem(name = "Развлечения", imageRes = R.drawable.ic_entertainment),
          CategoryItem(name = "Транспорт", imageRes = R.drawable.ic_transport),
          CategoryItem(name = "Путешествия", imageRes = R.drawable.ic_travel),
          CategoryItem(name = "Дом", imageRes = R.drawable.ic_spends),
          CategoryItem(name = "Одежда", imageRes = R.drawable.ic_clothes),
          CategoryItem(name = "Другое", imageRes = R.drawable.ic_money),
      )

  @Before
  fun setUp() {
    Dispatchers.setMain(testDispatcher)
    category = "category"
    comments = "comments"
    currency = "currency"
    sut = CostAccountingViewModel(costAccountingInteractor)
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun `WHEN add cost THEN cost was added`() {
    val date = sut.localDate.value.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()
    val expected = persistentListOf(getCostItemUiModel(date))
    sut.addCost(category, comments, currency, sum)
    assertEquals(expected, sut.costsList.value)
  }

  @Test
  fun `WHEN delete cost THEN cost was deleted`() {
    val date = sut.localDate.value.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()
    val expected = persistentListOf(getCostItemUiModel(date), getCostItemUiModel(date, ""))
    sut.addCost(category, comments, currency, sum)
    sut.addCost(category, comments, currency, sum)
    sut.addCost("", comments, currency, sum)
    sut.deleteCost(1)
    assertEquals(expected, sut.costsList.value)
  }

  @Test
  fun `WHEN try to get categories THEN get list categories`() {
    assertEquals(categoriesList, sut.getCategories())
  }

  @Test
  fun `WHEN save costs THEN costs was saved and alert was shown`() = runTest {
    coEvery { costAccountingInteractor.saveCosts(persistentListOf()) } returns Unit
    sut.saveCosts()
    coVerify(exactly = 1) { costAccountingInteractor.saveCosts(persistentListOf()) }
    assertEquals(CostAccountingScreenState.Initial, sut.costsState.value)
    assertEquals(persistentListOf<CostItemUiModel>(), sut.costsList.value)
    assertEquals(true, sut.isAlertShown.value)
  }

  @Test
  fun `WHEN save costs THEN error is shown`() = runTest {
    coEvery { costAccountingInteractor.saveCosts(persistentListOf()) } returns sut.saveCosts()
    coVerify(exactly = 1) { costAccountingInteractor.saveCosts(persistentListOf()) }
    assertEquals(CostAccountingScreenState.Error, sut.costsState.value)
  }

  @Test
  fun `WHEN change date THEN date is changed`() = runTest {
    val date = 300009L
    val expected = Instant.ofEpochMilli(date).atZone(ZoneId.of("UTC")).toLocalDate()
    sut.changeDate(date)
    assertEquals(expected, sut.localDate.value)
  }

  @Test
  fun `WHEN close alert THEN alert is closed`() = runTest {
    sut.closeAlert()
    assertEquals(false, sut.isAlertShown.value)
  }
}
