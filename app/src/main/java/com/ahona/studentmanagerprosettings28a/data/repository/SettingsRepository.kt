package com.ahona.studentmanagerprosettings28a.data.repository




import kotlinx.coroutines.flow.Flow
import  com.ahona.studentmanagerprosettings28a.domain.model.SettingsModel

interface SettingsRepository {

    val settings: Flow<SettingsModel>

    suspend fun setDarkMode(enabled: Boolean)

    suspend fun setNotificationsEnabled(enabled: Boolean)

    suspend fun setUsername(username: String)

    suspend fun setSortOrder(sortOrder: String)

    suspend fun resetSettings()
}
