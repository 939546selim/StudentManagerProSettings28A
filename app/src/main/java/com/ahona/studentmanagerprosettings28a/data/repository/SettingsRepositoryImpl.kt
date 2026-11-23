package com.ahona.studentmanagerprosettings28a.data.repository



import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import com.ahona.studentmanagerprosettings28a.data.datastore.SettingsKeys
import com.ahona.studentmanagerprosettings28a.data.datastore.settingsDataStore
import com.ahona.studentmanagerprosettings28a.domain.model.SettingsModel
import android.content.Context

class SettingsRepositoryImpl(
    private val context: Context
) : SettingsRepository {

    override val settings: Flow<SettingsModel> =
        context.settingsDataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(androidx.datastore.preferences.core.emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->

                SettingsModel(
                    darkMode = preferences[SettingsKeys.DARK_MODE] ?: false,
                    notificationsEnabled =
                        preferences[SettingsKeys.NOTIFICATIONS_ENABLED] ?: true,
                    username = preferences[SettingsKeys.USERNAME] ?: "",
                    sortOrder = preferences[SettingsKeys.SORT_ORDER] ?: "name"
                )
            }

    override suspend fun setDarkMode(enabled: Boolean) {
        context.settingsDataStore.edit { preferences ->
            preferences[SettingsKeys.DARK_MODE] = enabled
        }
    }

    override suspend fun setNotificationsEnabled(enabled: Boolean) {
        context.settingsDataStore.edit { preferences ->
            preferences[SettingsKeys.NOTIFICATIONS_ENABLED] = enabled
        }
    }

    override suspend fun setUsername(username: String) {
        context.settingsDataStore.edit { preferences ->
            preferences[SettingsKeys.USERNAME] = username
        }
    }

    override suspend fun setSortOrder(sortOrder: String) {
        context.settingsDataStore.edit { preferences ->
            preferences[SettingsKeys.SORT_ORDER] = sortOrder
        }
    }

    override suspend fun resetSettings() {
        context.settingsDataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
