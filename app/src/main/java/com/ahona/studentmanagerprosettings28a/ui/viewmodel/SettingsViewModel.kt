package com.ahona.studentmanagerprosettings28a.ui.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.ahona.studentmanagerprosettings28a.data.repository.SettingsRepository
import com.ahona.studentmanagerprosettings28a.domain.model.SettingsModel

class SettingsViewModel(
    private val repository: SettingsRepository
) : ViewModel() {

    val settings: StateFlow<SettingsModel> =
        repository.settings.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SettingsModel()
        )

    fun setDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            repository.setDarkMode(enabled)
        }
    }

    fun setNotificationsEnabled(enabled: Boolean) {
        viewModelScope.launch {
            repository.setNotificationsEnabled(enabled)
        }
    }

    fun setUsername(username: String) {
        viewModelScope.launch {
            repository.setUsername(username)
        }
    }

    fun setSortOrder(sortOrder: String) {
        viewModelScope.launch {
            repository.setSortOrder(sortOrder)
        }
    }

    fun resetSettings() {
        viewModelScope.launch {
            repository.resetSettings()
        }
    }
}
