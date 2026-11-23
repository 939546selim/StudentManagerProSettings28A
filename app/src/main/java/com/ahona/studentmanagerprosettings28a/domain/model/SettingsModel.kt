package com.ahona.studentmanagerprosettings28a.domain.model

data class SettingsModel(
    val darkMode: Boolean = false,
    val notificationsEnabled: Boolean = true,
    val username: String = "",
    val sortOrder: String = "name"
)
