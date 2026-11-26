package com.ahona.studentmanagerprosettings28a.data.proto_repository
import androidx.datastore.core.DataStore
import com.ahona.studentmanagerprosettings28a.data.UserSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

class ProtoSettingsRepositoryImpl(
    private val dataStore: DataStore<UserSettings>
) : ProtoSettingsRepository {

    override val userSettings: Flow<UserSettings> =
        dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(UserSettings.getDefaultInstance())
            } else {
                throw exception
            }
        }

    override suspend fun updateDarkMode(enabled: Boolean) {
        dataStore.updateData { settings ->
            settings.toBuilder()
                .setDarkMode(enabled)
                .build()
        }
    }

    override suspend fun updateNotificationsEnabled(enabled: Boolean) {
        dataStore.updateData { settings ->
            settings.toBuilder()
                .setNotificationsEnabled(enabled)
                .build()
        }
    }

    override suspend fun updateUsername(username: String) {
        dataStore.updateData { settings ->
            settings.toBuilder()
                .setUsername(username)
                .build()
        }
    }

    override suspend fun updateSortOrder(sortOrder: String) {
        dataStore.updateData { settings ->
            settings.toBuilder()
                .setSortOrder(sortOrder)
                .build()
        }
    }

    override suspend fun resetSettings() {
        dataStore.updateData {
            UserSettings.getDefaultInstance()
        }
    }
}