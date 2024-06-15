package com.nohjason.minari.screens.profile.my_dictionary.db

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : ViewModel() {

    val allProducts: LiveData<List<UserEntity>>
    private val repository: UserRepository

    init {
        val userDb = UserDatabase.getUser(application)
        val userDao = userDb.userDao()
        repository = UserRepository(userDao)

        allProducts = repository.allUser
    }

    fun upsertProduct(user: UserEntity) {
        repository.upsertUser(user)
    }

    fun deleteProduct(user: UserEntity) {
        repository.deleteUser(user)
    }
}