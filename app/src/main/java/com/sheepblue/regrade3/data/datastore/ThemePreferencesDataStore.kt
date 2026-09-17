package com.sheepblue.regrade3.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton // só precisa de uma instancia cuidando do tema no app inteiro
class ThemePreferencesDataStore @Inject constructor(private val dataStore: DataStore<Preferences>) {
    private val isDarkModePreferences = booleanPreferencesKey(name = "is_dark_mode")

    // transforma o fluxo bruto de Preferences em Flow<Boolean> para obter ler o estado atual do tema
    val isDarkMode = dataStore.data.map { preferences ->
        preferences[isDarkModePreferences] ?: false
    }

    suspend fun saveDarkMode(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[isDarkModePreferences] = enabled
        }
    }
}
