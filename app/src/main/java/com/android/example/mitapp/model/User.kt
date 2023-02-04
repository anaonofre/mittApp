package com.android.example.mitapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class User (
    @PrimaryKey(autoGenerate = false)
    val user: String
)