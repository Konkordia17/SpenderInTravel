package com.example.main.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.core_api.navigation.register

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    dependencyProvider: DependencyProvider,
) {

  NavHost(
      navController = navController,
      startDestination = dependencyProvider.homeFeatureApi.homeRoute,
      enterTransition = { EnterTransition.None },
      exitTransition = { ExitTransition.None }) {
        register(
            featureApi = dependencyProvider.homeFeatureApi,
            navController = navController,
            modifier = modifier)
        register(
            featureApi = dependencyProvider.currencyConverterApi,
            navController = navController,
            modifier = modifier)
      }
}
