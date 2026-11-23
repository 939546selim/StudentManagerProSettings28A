package com.ahona.studentmanagerprosettings28a.di




import android.content.Context
import  com.ahona.studentmanagerprosettings28a.data.repository.SettingsRepository
import  com.ahona.studentmanagerprosettings28a.data.repository.SettingsRepositoryImpl

class AppContainer(context: Context) {

    val settingsRepository: SettingsRepository =
        SettingsRepositoryImpl(context.applicationContext)
}
