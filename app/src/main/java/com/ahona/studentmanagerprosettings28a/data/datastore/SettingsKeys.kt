package com.ahona.studentmanagerprosettings28a.data.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object SettingsKeys {

    val DARK_MODE = booleanPreferencesKey("dark_mode")

    val NOTIFICATIONS_ENABLED =
        booleanPreferencesKey("notifications_enabled")

    val USERNAME = stringPreferencesKey("username")

    val SORT_ORDER = stringPreferencesKey("sort_order")
}
