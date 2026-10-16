package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StrategyDao {
    @Query("SELECT * FROM saved_strategies ORDER BY createdAt DESC")
    fun getAllStrategies(): Flow<List<StrategyEntity>>

    @Query("SELECT * FROM saved_strategies WHERE id = :id")
    suspend fun getStrategyById(id: Long): StrategyEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStrategy(strategy: StrategyEntity): Long

    @Delete
    suspend fun deleteStrategy(strategy: StrategyEntity)

    @Query("DELETE FROM saved_strategies WHERE id = :id")
    suspend fun deleteById(id: Long)
}
