package com.nohjason.minari.room

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository(private val userDao: UserDao) {
    val allUser: LiveData<List<User>> = userDao.getAllUser()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertUser(newuser: User) {
        coroutineScope.launch(Dispatchers.IO) {
            userDao.insertUser(newuser)
        }
    }
}