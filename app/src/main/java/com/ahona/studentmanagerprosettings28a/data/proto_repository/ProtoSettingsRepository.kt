package com.ahona.studentmanagerprosettings28a.data.proto_repository

import kotlinx.coroutines.flow.Flow
import com.ahona.studentmanagerprosettings28a.data.UserSettings

interface ProtoSettingsRepository {

    val userSettings: Flow<UserSettings>

    suspend fun updateDarkMode(enabled: Boolean)

    suspend fun updateNotificationsEnabled(enabled: Boolean)

    suspend fun updateUsername(username: String)

    suspend fun updateSortOrder(sortOrder: String)

    suspend fun resetSettings()
}