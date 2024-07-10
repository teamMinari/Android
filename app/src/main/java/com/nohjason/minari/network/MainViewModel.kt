package com.nohjason.myapplication.network

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.minari.network.response.BookResponse
import com.nohjason.myapplication.network.RetrofitInstance.api
import com.nohjason.myapplication.network.response.Term
import com.nohjason.myapplication.network.response.TermResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val _routine = MutableStateFlow<List<TermResponse>>(emptyList())
    val routines = _routine.asStateFlow()

    fun fetchAllTerms() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) { RetrofitInstance.api.getTerms() }
                _routine.value = response
            } catch (e: Exception) {
                // Handle error
                Log.e("TAG", "fetch ALL", e)
            }
        }
    }

    private val _term = MutableStateFlow<Term?>(null)
    val term: StateFlow<Term?> = _term

    fun fetchTerm(termNm: String) {
        viewModelScope.launch {
            try {
                val term = api.getOneTerm(termNm = termNm)
                _term.value = term
            } catch (e: Exception) {
                // 오류 처리
            }
        }
    }

    private val _book = MutableStateFlow<BookResponse?>(null)
    val books = _book.asStateFlow()

    fun fetchAllBookTerms() {
        viewModelScope.launch {
            try {
                val book = withContext(Dispatchers.IO) { RetrofitInstance.api.getBookTerms() }
                _book.value = book
            } catch (e: Exception) {
                // Handle error
                Log.e("TAG", "fetch ALL", e)
            }
        }
    }

//    fun addWord(word: String) {
//        viewModelScope.launch {
//            try {
//                RetrofitInstance.api.addWord(word = word)
//            } catch (e: Exception) {
//
//            }
//        }
//    }

//    fun createRoutine(routine: Routine) {
//        viewModelScope.launch {
//            try {
//                RetrofitInstance.api.createRoutine(routine)
//                Log.d("MainViewModel", "Task created successfully")
//            } catch (e: Exception) {
//                Log.e("MainViewModel", "Error creating task", e)
//            }
//        }
//    }

//    private val _routine = mutableStateOf<List<Routine>>(emptyList())
//    val routines: State<List<Routine>> get() = _routine
//
//    fun fetchRoutine() {
//        viewModelScope.launch {
//            try {
//                val response = withContext(Dispatchers.IO) { RetrofitInstance.api.getRoutine() }
//                if (response.status == 200) {
//                    _routine.value = response.data
//                } else {
//                    // Handle error
//                }
//            } catch (e: Exception) {
//                // Handle error
//            }
//        }
//    }

//    fun deleteRoutine(id: Int) {
//        viewModelScope.launch {
//            try {
//                val response: Response<Void> = RetrofitInstance.api.deleteRoutine(id)
//                if (response.isSuccessful) {
//                    Log.d("MainViewModel", "Task deleted successfully: $id")
//                    // 태스크 삭제 후 목록을 다시 가져옴
//                    fetchRoutine()
//                } else {
//                    Log.e(
//                        "MainViewModel",
//                        "Error deleting task: ${response.code()} ${response.message()}"
//                    )
//                }
//            } catch (e: Exception) {
//                Log.e("MainViewModel", "Exception deleting task", e)
//            }
//        }
//    }
//
//    fun updateRoutine(id: Int, routine: Routine) {
//        viewModelScope.launch {
//            try {
//                val response: Response<Routine> = RetrofitInstance.api.updateRoutine(id, routine)
//                if (response.isSuccessful) {
//                    Log.d("MainViewModel", "Task updated successfully: $id")
//                    fetchRoutine() // 업데이트 후 목록을 다시 불러옵니다.
//                } else {
//                    Log.e(
//                        "MainViewModel",
//                        "Error updating task: ${response.code()} ${response.message()}"
//                    )
//                }
//            } catch (e: Exception) {
//                Log.e("MainViewModel", "Exception updating task", e)
//            }
//        }
//    }
//
//    fun updateSetRoutine(id: Int, routine: Routine) {
//        viewModelScope.launch {
//            try {
//                val response: Response<Routine> = RetrofitInstance.api.updateSetRoutine(id, routine)
//                if (response.isSuccessful) {
//                    Log.d("MainViewModel", "Routine updated successfully: $id")
//                    fetchRoutine() // 업데이트 후 목록을 다시 불러옵니다.
//                } else {
//                    Log.e(
//                        "MainViewModel",
//                        "Error updating routine: ${response.code()} ${response.message()}"
//                    )
//                }
//            } catch (e: Exception) {
//                Log.e("MainViewModel", "Exception updating routine", e)
//            }
//        }
//    }
//
//    //----
//    fun createTask(task: Task) {
//        viewModelScope.launch {
//            try {
//                RetrofitInstance.api.createTask(task)
//                Log.d("MainViewModel", "Task created successfully")
//            } catch (e: Exception) {
//                Log.e("MainViewModel", "Error creating task", e)
//            }
//        }
//    }
//
//    private val _tasksByDate = mutableStateOf<List<Task>>(emptyList())
//    val tasksByDate: State<List<Task>> get() = _tasksByDate
//
//    fun fetchTasksDate(date: String) {
//        viewModelScope.launch {
//            try {
//                val response = RetrofitInstance.api.getTasksDate(date)
//                if (response.isSuccessful) {
//                    _tasksByDate.value = response.body()?.data ?: emptyList()
//                } else {
//                    Log.e("MainViewModel", "Error fetching tasks by date: ${response.code()} ${response.message()}")
//                }
//            } catch (e: Exception) {
//                Log.e("MainViewModel", "Exception fetching tasks by date", e)
//            }
//        }
//    }
//
//    fun deleteTask(id: Int, date: String) {
//        viewModelScope.launch {
//            try {
//                val response = RetrofitInstance.api.deleteTask(id)
//                if (response.isSuccessful) {
//                    Log.d("MainViewModel", "Task deleted successfully: $id")
//                    fetchTasksDate(date)
//                } else {
//                    Log.e("MainViewModel", "Error deleting task: ${response.code()} ${response.message()}")
//                }
//            } catch (e: Exception) {
//                Log.e("MainViewModel", "Exception deleting task", e)
//            }
//        }
//    }
//
//    fun updateTask(id: Int, task: Task, date: String) {
//        viewModelScope.launch {
//            try {
//                val response = RetrofitInstance.api.updateTask(id, task)
//                if (response.isSuccessful) {
//                    Log.d("MainViewModel", "Task updated successfully: $id")
//                    fetchTasksDate(date) // Refresh the list after update
//                } else {
//                    Log.e("MainViewModel", "Error updating task: ${response.code()} ${response.message()}")
//                }
//            } catch (e: Exception) {
//                Log.e("MainViewModel", "Exception updating task", e)
//            }
//        }
//    }
//
//    fun updateSetTask(id: Int, date: String) {
//        viewModelScope.launch {
//            try {
//                val response = RetrofitInstance.api.updateSetTask(id)
//                if (response.isSuccessful) {
//                    Log.d("MainViewModel", "Task updated successfully: $id")
//                    fetchTasksDate(date) // Refresh the list after update
//                } else {
//                    Log.e("MainViewModel", "Error updating task: ${response.code()} ${response.message()}")
//                }
//            } catch (e: Exception) {
//                Log.e("MainViewModel", "Exception updating task", e)
//            }
//        }
//    }
}