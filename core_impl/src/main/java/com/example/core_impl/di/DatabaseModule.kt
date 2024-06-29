package com.example.core_impl.di

import android.content.Context
import androidx.room.Room
import com.example.core_api.database.CostsDao
import com.example.core_api.database.CostsDatabaseContract
import com.example.core_impl.CostsDatabase
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

private const val COSTS_DATABASE_NAME = "COSTS_DB"

@Module
class DatabaseModule {
  @Provides
  @Reusable
  fun provideCostsDao(costsDatabaseContract: CostsDatabaseContract): CostsDao {
    return costsDatabaseContract.costsDao()
  }

  @Provides
  @Singleton
  fun provideCostsDatabase(context: Context): CostsDatabaseContract {
    return Room.databaseBuilder(context, CostsDatabase::class.java, COSTS_DATABASE_NAME).build()
  }
}
