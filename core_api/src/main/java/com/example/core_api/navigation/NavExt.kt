package com.example.core_api.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

fun NavGraphBuilder.register(
    featureApi: FeatureApi,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
  featureApi.registerGraph(
      navGraphBuilder = this,
      navController = navController,
      modifier = modifier)
}