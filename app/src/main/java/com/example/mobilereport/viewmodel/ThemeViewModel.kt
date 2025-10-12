package com.example.mobilereport.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mobilereport.data.ThemePreferences
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ThemeViewModel(private val appContext: Context) : ViewModel() {

    // 🔑 Reactive Flow from DataStore
    val isDarkMode: StateFlow<Boolean> =
        ThemePreferences.getThemePreference(appContext)
            .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    // 🔑 Toggle and save
    fun toggleTheme() {
        viewModelScope.launch {
            ThemePreferences.saveThemePreference(appContext, !isDarkMode.value)
        }
    }

    // ✅ Factory for creating ThemeViewModel with Context
    class Factory(private val context: Context) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ThemeViewModel::class.java)) {
                return ThemeViewModel(context.applicationContext) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}