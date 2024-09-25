package com.nohjason.myapplication.network

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.myapplication.network.RetrofitInstance.api
import com.nohjason.myapplication.network.response.Term
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {


    private val _term = MutableStateFlow<Term?>(null)
    val term: StateFlow<Term?> = _term

    fun fetchTerm(termNm: String, token: String) {
        viewModelScope.launch {
            try {
                val term = api.getOneTerm(termNm = termNm, token = token)
                _term.value = term
            } catch (e: Exception) {
                Log.e("FetchTermError", "Error fetching term: ${e.message}", e)
            }
        }
    }
}