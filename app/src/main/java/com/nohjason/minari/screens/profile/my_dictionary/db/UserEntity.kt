package com.nohjason.minari.screens.profile.my_dictionary.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity (
    @PrimaryKey
    var id: String,
    var check: Boolean
)