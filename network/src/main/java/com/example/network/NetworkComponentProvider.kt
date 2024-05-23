package com.example.network

import com.example.network_api.provides.NetworkRepositoryProvider
import com.example.network_impl.di.NetworkComponent

interface NetworkComponentProvider: NetworkRepositoryProvider {

  val networkComponent: NetworkComponent
}
