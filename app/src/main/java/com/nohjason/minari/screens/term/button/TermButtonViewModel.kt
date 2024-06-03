package com.nohjason.minari.screens.term.button

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TermButtonViewModel : ViewModel() {
    private val _stateMap = MutableStateFlow(mapOf<String, Boolean>())
    val stateMap: StateFlow<Map<String, Boolean>> = _stateMap


    fun setState(key: String, state: Boolean) {
        _stateMap.value = _stateMap.value.toMutableMap().apply { put(key, state) }
    }

    fun toggleState(key: String) {
        val currentState = _stateMap.value[key] ?: true
        _stateMap.value = _stateMap.value.toMutableMap().apply { put(key, !currentState) }
    }
}