package com.example.zad1
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NumberDao {
    @Query("SELECT * FROM NumberEntity")
    suspend fun getAll(): List<NumberEntity>

    @Insert
    suspend fun insertAll(vararg numbers: NumberEntity)
}
