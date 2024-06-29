package com.example.core_api.database

interface DatabaseProvider {

  fun provideDatabase(): CostsDatabaseContract

  fun costsDao(): CostsDao
}
