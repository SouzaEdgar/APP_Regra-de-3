package com.sheepblue.regrade3.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sheepblue.regrade3.data.datastore.ThemePreferencesDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val settingsDataStore: ThemePreferencesDataStore
): ViewModel() {
    val isDarkMode = settingsDataStore.isDarkMode

    fun updateDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            settingsDataStore.saveDarkMode(enabled)
        }
    }
}
