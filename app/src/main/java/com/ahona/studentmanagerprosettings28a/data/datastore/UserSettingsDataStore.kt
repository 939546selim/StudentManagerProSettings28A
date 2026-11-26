package com.ahona.studentmanagerprosettings28a.data.datastore



import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import com.ahona.studentmanagerprosettings28a.data.UserSettingsSerializer
import com.ahona.studentmanagerprosettings28a.data.UserSettings
import java.io.File

fun createUserSettingsDataStore(
    context: Context
): DataStore<UserSettings> {

    return DataStoreFactory.create(
        serializer = UserSettingsSerializer,
        produceFile = {
            File(context.filesDir, "user_settings.pb")
        }
    )
}