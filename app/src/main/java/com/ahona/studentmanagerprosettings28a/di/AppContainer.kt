package com.ahona.studentmanagerprosettings28a.di




import android.content.Context
import  com.ahona.studentmanagerprosettings28a.data.repository.SettingsRepository
import  com.ahona.studentmanagerprosettings28a.data.repository.SettingsRepositoryImpl
import com.ahona.studentmanagerprosettings28a.data.datastore.createUserSettingsDataStore
import com.ahona.studentmanagerprosettings28a.data.proto_repository.ProtoSettingsRepository
import com.ahona.studentmanagerprosettings28a.data.proto_repository.ProtoSettingsRepositoryImpl

class AppContainer(context: Context) {

    val settingsRepository: SettingsRepository =
        SettingsRepositoryImpl(context.applicationContext)

    val protoSettingsDataStore =
        createUserSettingsDataStore(context.applicationContext)

    val protoSettingsRepository: ProtoSettingsRepository =
        ProtoSettingsRepositoryImpl(protoSettingsDataStore)
}
