package com.nohjason.minari.screens.profile.my_dictionary.db

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository(private val userDao: UserDao) {
    val allUser: LiveData<List<UserEntity>> = userDao.getAllUser()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun upsertUser(newuser: UserEntity) {
        coroutineScope.launch(Dispatchers.IO) {
            userDao.upsert(newuser)
        }
    }

    fun deleteUser(newuser: UserEntity) {
        coroutineScope.launch(Dispatchers.IO) {
            userDao.delete(newuser)
        }
    }
}