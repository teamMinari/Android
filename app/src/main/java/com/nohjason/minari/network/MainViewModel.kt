package com.nohjason.myapplication.network

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.minari.network.response.BookResponse
import com.nohjason.minari.screens.profile.ProfileResponse
import com.nohjason.minari.screens.profile.my_dictionary.model.DictionaryModel
import com.nohjason.minari.screens.profile.my_dictionary.model.toModel
import com.nohjason.myapplication.network.RetrofitInstance.api
import com.nohjason.myapplication.network.response.Term
import com.nohjason.myapplication.network.response.TermResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

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


//    private val _book = MutableStateFlow<List<DictionaryModel>>(emptyList())
//    val books = _book.asStateFlow()

//    fun fetchAllBookTerms() {
//        viewModelScope.launch {
//            try {
//                val book = RetrofitInstance.api.getBookTerms()
//                _book.value = book.data.map {
//                    it.toModel()
//                }
//            } catch (e: Exception) {
//                // Handle error
//                Log.e("TAG", "fetch ALL", e)
//            }
//        }
//    }

//    fun addDelete(word: String) {
//        viewModelScope.launch {
//            try {
//                val job = async { RetrofitInstance.api.addDeleteTerm(word) }
//                job.await()
//                fetchAllBookTerms()
//            } catch (e: Exception) {
//                Log.e("TAG", "addDelete: ", e)
//            }
//        }
//    }

//    fun checkedThat(wordNm: String) = viewModelScope.launch {
//        _book.update {
//            it.map { item ->
//                if (item.termNm == wordNm) {
//                    item.copy(
//                        isChecked = item.isChecked.not()
//                    )
//                } else {
//                    item
//                }
//            }
//        }
//    }
}