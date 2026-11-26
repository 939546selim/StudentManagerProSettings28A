
package com.ahona.studentmanagerprosettings28a

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import com.ahona.studentmanagerprosettings28a.data.UserSettings
import com.ahona.studentmanagerprosettings28a.data.UserSettingsSerializer
import com.ahona.studentmanagerprosettings28a.data.proto_repository.ProtoSettingsRepositoryImpl
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.flow.first
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File
import java.util.UUID
import kotlinx.coroutines.async

@RunWith(AndroidJUnit4::class)
class ProtoSettingsRepositoryTest {

    private lateinit var dataStore: DataStore<UserSettings>
    private lateinit var repository: ProtoSettingsRepositoryImpl

    @Before
    fun setup() {
        val context =
            ApplicationProvider.getApplicationContext<Context>()

        val testFile = File(
            context.cacheDir,
            "proto_test_${UUID.randomUUID()}.pb"
        )

        dataStore = DataStoreFactory.create(
            serializer = UserSettingsSerializer,
            produceFile = { testFile }
        )

        repository = ProtoSettingsRepositoryImpl(dataStore)
    }

    @Test
    fun defaultSettingsAreCorrect() = runBlocking {
        val settings = repository.userSettings.first()

        assertEquals(false, settings.darkMode)
        assertEquals(false, settings.notificationsEnabled)
        assertEquals("", settings.username)
        assertEquals("", settings.sortOrder)
    }

    @Test
    fun usernameIsSavedCorrectly() = runBlocking {
        repository.updateUsername("Selim")

        val settings = repository.userSettings.first()

        assertEquals("Selim", settings.username)
    }

    @Test
    fun darkModeIsSavedCorrectly() = runBlocking {
        repository.updateDarkMode(true)

        val settings = repository.userSettings.first()

        assertEquals(true, settings.darkMode)
    }

    @Test
    fun notificationsAreSavedCorrectly() = runBlocking {
        repository.updateNotificationsEnabled(true)

        val settings = repository.userSettings.first()

        assertEquals(true, settings.notificationsEnabled)
    }

    @Test
    fun sortOrderIsSavedCorrectly() = runBlocking {
        repository.updateSortOrder("age")

        val settings = repository.userSettings.first()

        assertEquals("age", settings.sortOrder)
    }

    @Test
    fun resetSettingsRestoresDefaultValues() = runBlocking {
        repository.updateUsername("Selim")
        repository.updateDarkMode(true)
        repository.updateNotificationsEnabled(true)
        repository.updateSortOrder("age")

        repository.resetSettings()

        val settings = repository.userSettings.first()

        assertEquals(false, settings.darkMode)
        assertEquals(false, settings.notificationsEnabled)
        assertEquals("", settings.username)
        assertEquals("", settings.sortOrder)
    }

//    @Test
//    fun flowObservesUsernameChanges() = runBlocking {
//
//        val updatedSettings = async {
//            repository.userSettings.first {
//                it.username == "FlowTest"
//            }
//        }
//
//        repository.updateUsername("FlowTest")
//
//        val settings = updatedSettings.await()
//
//        assertEquals("FlowTest", settings.username)
//    }

    @Test
    fun flowObservesUsernameChanges() = runBlocking {

        val updatedSettings = async {
            repository.userSettings.first {
                it.username == "FlowTest"
            }
        }

        repository.updateUsername("FlowTest")

        val settings = updatedSettings.await()

        assertEquals("FlowTest", settings.username)

        repository.resetSettings()
    }
}
