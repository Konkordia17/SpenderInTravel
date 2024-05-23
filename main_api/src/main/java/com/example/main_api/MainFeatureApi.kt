package com.example.main_api

import com.example.core_api.navigation.FeatureApi


interface MainFeatureApi : FeatureApi {

    val homeRoute: String
}