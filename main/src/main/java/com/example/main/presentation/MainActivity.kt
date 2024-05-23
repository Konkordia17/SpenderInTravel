package com.example.main.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.main.di.MainComponentProvider
import com.example.main.navigation.AppNavGraph
import com.example.main.navigation.DependencyProvider
import com.example.main.theme.SpenderInTravelTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {


  @Inject lateinit var dependencyProvider: DependencyProvider

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    (application as MainComponentProvider).mainComponent.inject(this)

    setContent {
      SpenderInTravelTheme {
        val navController = rememberNavController()
        val router = navController.currentBackStackEntryAsState()

        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          AppNavGraph(
              modifier = Modifier.fillMaxSize(),
              navController = navController,
              dependencyProvider = dependencyProvider)
        }
      }
    }
  }
}
