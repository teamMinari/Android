package com.nohjason.minari.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,
    val imageUri : String,
)