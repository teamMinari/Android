package com.nohjason.minari.screens.profile.my_dictionary.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert

@Dao
interface UserDao {

    @Upsert
    suspend fun upsert(user: UserEntity)

    @Delete
    suspend fun delete(user: UserEntity)

    @Query("SELECT * FROM user")
    fun getAllUser(): LiveData<List<UserEntity>>
}