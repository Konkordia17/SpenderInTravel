package com.example.spenderintravel

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ActivityScenario
import com.example.activity.MainActivity
import org.junit.Rule
import org.junit.Test

class SpendingInTravelUiTest {

  @get:Rule val composeTestRule = createComposeRule()

  @Test
  fun testSplashScreenIsDisplayed() {
    ActivityScenario.launch(MainActivity::class.java).use { scenario ->
      composeTestRule.onNodeWithTag("SplashScreenBox").assertIsDisplayed()
      composeTestRule.onNodeWithTag("SplashScreenBox").performClick()
      testMenuScreenIsDisplayed()
    }
  }

  private fun testMenuScreenIsDisplayed() {
    composeTestRule.onNodeWithTag("MenuScreen").assertIsDisplayed()
    composeTestRule.onNodeWithTag("Учет расходов").assertIsDisplayed()
    composeTestRule.onNodeWithTag("Учет расходов").performClick()
    testCostAccountingScreenIsDisplayed()
  }

  private fun testCostAccountingScreenIsDisplayed() {
    composeTestRule.onNodeWithTag("CostAccountingScreen").assertIsDisplayed()
    showCurrencyError()
  }

    private fun showCurrencyError() {
        composeTestRule.onNodeWithContentDescription("Еда").assertIsDisplayed().performClick()
        composeTestRule.onNodeWithTag("currency_error", useUnmergedTree = true).assertIsDisplayed()
        chooseCurrency()
    }

    private fun chooseCurrency() {
        composeTestRule
            .onNodeWithContentDescription("choose_currency")
            .assertIsDisplayed()
            .performClick()
        composeTestRule
            .onNodeWithTag("Hong Kong Dollar — Гонконгский доллар (Гонконг)", useUnmergedTree = true)
            .assertIsDisplayed()
            .performClick()
        addSpending()
    }

    private fun addSpending() {
        composeTestRule.onNodeWithContentDescription("Путешествия").assertIsDisplayed().performClick()
        composeTestRule.onNodeWithTag("InputDialog").assertIsDisplayed()
        composeTestRule.onNodeWithTag("alert_confirm_button", useUnmergedTree = true).assertIsDisplayed().performClick()
        composeTestRule.onNodeWithTag("input_error", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag("costs").performTextInput("3000")
        composeTestRule.onNodeWithTag("comments").performTextInput("блаблабла")
        composeTestRule.onNodeWithTag("alert_confirm_button", useUnmergedTree = true).assertIsDisplayed().performClick()
        composeTestRule.onNodeWithTag("InputDialog").assertIsNotDisplayed()
        showSpendingList()
    }

    private fun showSpendingList() {
        composeTestRule.onNodeWithTag("1. Путешествия 3000.0 HKD", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithTag("CostAccountingButton").assertIsEnabled().performClick()
    }
}
