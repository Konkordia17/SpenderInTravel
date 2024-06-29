package com.example.core_api.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core_api.database.models.CostDbItem

@Dao
interface CostsDao {

    @Query("SELECT * FROM COSTS")
    suspend fun getCosts(): List<CostDbItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createCosts(costs: List<CostDbItem>)
}