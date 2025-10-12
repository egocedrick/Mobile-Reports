package com.example.mobilereport.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

// 🔑 Top-level extension (must be outside the object)
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

object ThemePreferences {
    private val DARK_THEME_ENABLED = booleanPreferencesKey("dark_theme_enabled")

    // Read preference as Flow
    fun getThemePreference(context: Context): Flow<Boolean> {
        return context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[DARK_THEME_ENABLED] ?: false // default = light mode
            }
    }

    // Save preference (suspend function, lifecycle-safe)
    suspend fun saveThemePreference(context: Context, isDarkThemeEnabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DARK_THEME_ENABLED] = isDarkThemeEnabled
        }
    }
}