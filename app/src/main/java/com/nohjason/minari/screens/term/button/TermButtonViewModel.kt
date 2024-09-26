package com.nohjason.minari.screens.term.button

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TermButtonViewModel : ViewModel() {
    private val _selectedButton = MutableStateFlow<String?>("전체")
    val selectedButton: StateFlow<String?> = _selectedButton

    fun selectButton(key: String) {
        _selectedButton.value = key
    }
}
