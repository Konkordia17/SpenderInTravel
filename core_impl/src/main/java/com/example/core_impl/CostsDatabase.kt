package com.example.core_impl

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core_api.database.CostsDatabaseContract
import com.example.core_api.database.models.CostDbItem

@Database(entities = [CostDbItem::class], version = 1)
abstract class CostsDatabase : RoomDatabase(), CostsDatabaseContract
