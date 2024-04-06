package com.nohjason.minari.room

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nohjason.myapplicationroom.room.UserDatabase

class MainViewModel(application: Application) : ViewModel() {

    val allProducts: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDb = UserDatabase.getUser(application)
        val userDao = userDb.userDao()
        repository = UserRepository(userDao)

        allProducts = repository.allUser
    }

    fun insertProduct(user: User) {
        repository.insertUser(user)
    }
}