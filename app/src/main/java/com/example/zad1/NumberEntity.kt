package com.example.zad1

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NumberEntity(
    @PrimaryKey
    @NonNull
    val uid: String,
    @ColumnInfo(name = "username") val username: String?,
    @ColumnInfo(name= "counter") val counter: Int?
)