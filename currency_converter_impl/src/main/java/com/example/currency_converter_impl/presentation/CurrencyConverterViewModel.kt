package com.example.currency_converter_impl.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_api.api_models.Currencies
import com.example.core_api.api_models.CurrencyItemDataModel
import com.example.currency_converter_impl.domain.CurrencyConverterInteractor
import com.github.terrakok.cicerone.Router
import java.lang.Exception
import javax.inject.Inject
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CurrencyConverterViewModel
@Inject
constructor(
    private val currencyConverterInteractor: CurrencyConverterInteractor,
    private val router: Router
) : ViewModel() {

  private val _currencyScreenState =
      MutableStateFlow<CurrencyScreenState>(CurrencyScreenState.LoadingState)
  val currencyScreenState: StateFlow<CurrencyScreenState> = _currencyScreenState

  private val currencies = MutableStateFlow<List<CurrencyItemDataModel>>(emptyList())

  init {
    getCurrencies()
  }

  fun back() {
    router.exit()
  }

  fun changeCurrency(currency: Currencies, isCurrencyFrom: Boolean) {
    updateValue(isCurrencyFrom = isCurrencyFrom, currency = currency)
  }

  fun getListCurrencies(): ImmutableList<CurrencyItemDataModel> {
    return currencies.value.toImmutableList()
  }

  fun updateValue(isCurrencyFrom: Boolean, value: String? = null, currency: Currencies? = null) {
    val currentState = _currencyScreenState.value as? CurrencyScreenState.ChosenCurrenciesState
    val currentData = currentState?.uiModel

    val result =
        currencyConverterInteractor.changeCurrencyValue(
            model = currentData?.value,
            currencies = currencies.value,
            isCurrencyFrom = isCurrencyFrom,
            value = value,
            currency = currency)
    result?.let {
      _currencyScreenState.value =
          CurrencyScreenState.ChosenCurrenciesState(uiModel = mutableStateOf(it))
    }
  }

  fun changeCurrencies() {
    val currentState = _currencyScreenState.value as? CurrencyScreenState.ChosenCurrenciesState
    val currentData = currentState?.uiModel?.value
    currentData?.let { data ->
      _currencyScreenState.value =
          CurrencyScreenState.ChosenCurrenciesState(
              uiModel =
                  mutableStateOf(
                      CurrenciesConverterUiModel(
                          currencyFrom = data.currencyTo,
                          currencyFromResult = data.currencyToResult,
                          currencyTo = data.currencyFrom,
                          currencyToResult = data.currencyFromResult)))
    }
  }

  private fun getCurrencies() {
    _currencyScreenState.value = CurrencyScreenState.LoadingState
    viewModelScope.launch {
      try {
        val result = currencyConverterInteractor.getCurrencies()
        currencies.value = result.currencies
        _currencyScreenState.value =
            CurrencyScreenState.ChosenCurrenciesState(
                mutableStateOf(
                    CurrenciesConverterUiModel(
                        currencyFrom = Currencies.RUB,
                        currencyFromResult =
                            result.currencies.find { it.currency == Currencies.RUB }?.value ?: 0.0,
                        currencyTo = Currencies.USD,
                        currencyToResult =
                            result.currencies.find { it.currency == Currencies.USD }?.value ?: 0.0,
                    )))
      } catch (e: Exception) {
        _currencyScreenState.value = CurrencyScreenState.Error(::getCurrencies)
      }
    }
  }
}
