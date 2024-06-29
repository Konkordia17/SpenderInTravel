package com.example.history_impl.presentation.history_list

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.history_impl.domain.HistoryInteractor
import com.example.history_impl.presentation.mappers.CostItemMapper
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class HistoryViewModelFactory
@Inject
constructor(
    private val historyInteractor: HistoryInteractor,
    private val costItemMapper: CostItemMapper,
    private val router: Router
) : ViewModelProvider.Factory {

  @RequiresApi(Build.VERSION_CODES.O)
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    return when (modelClass) {
      HistoryViewModel::class.java -> HistoryViewModel(historyInteractor, costItemMapper, router)
      else -> throw IllegalArgumentException("Unknown ViewModel class")
    }
        as T
  }
}
