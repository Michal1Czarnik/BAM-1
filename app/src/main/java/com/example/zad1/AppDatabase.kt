package com.example.zad1
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NumberEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun numberDao(): NumberDao
}