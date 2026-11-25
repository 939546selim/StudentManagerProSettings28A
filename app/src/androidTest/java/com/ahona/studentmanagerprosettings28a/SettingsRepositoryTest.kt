package com.ahona.studentmanagerprosettings28a

import androidx.test.ext.junit.runners.AndroidJUnit4
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.ahona.studentmanagerprosettings28a.data.repository.SettingsRepositoryImpl
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.flow.first
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

@RunWith(AndroidJUnit4::class)
class SettingsRepositoryTest {

    private lateinit var repository: SettingsRepositoryImpl

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        repository = SettingsRepositoryImpl(context)
    }

    @Test
    fun defaultSettingsAreCorrect() = runBlocking {

        repository.resetSettings()

        val settings = repository.settings.first()

        assertEquals(false, settings.darkMode)
        assertEquals(true, settings.notificationsEnabled)
        assertEquals("", settings.username)
        assertEquals("name", settings.sortOrder)

        repository.resetSettings()
    }

    @Test
    fun usernameIsSavedCorrectly() = runBlocking {

        repository.resetSettings()

        repository.setUsername("Selim")

        val settings = repository.settings.first()

        assertEquals("Selim", settings.username)

        repository.resetSettings()
    }

    @Test
    fun darkModeIsSavedCorrectly() = runBlocking {

        repository.resetSettings()

        repository.setDarkMode(true)

        val settings = repository.settings.first()

        assertEquals(true, settings.darkMode)

        repository.resetSettings()
    }

    @Test
    fun notificationsPreferenceIsSavedCorrectly() = runBlocking {

        repository.resetSettings()

        repository.setNotificationsEnabled(false)

        val settings = repository.settings.first()

        assertEquals(false, settings.notificationsEnabled)

        repository.resetSettings()
    }

    @Test
    fun sortOrderIsSavedCorrectly() = runBlocking {

        repository.resetSettings()

        repository.setSortOrder("age")

        val settings = repository.settings.first()

        assertEquals("age", settings.sortOrder)

        repository.resetSettings()
    }

    @Test
    fun resetSettingsRestoresDefaultValues() = runBlocking {

        repository.setDarkMode(true)
        repository.setNotificationsEnabled(false)
        repository.setUsername("Selim")
        repository.setSortOrder("age")

        repository.resetSettings()

        val settings = repository.settings.first()

        assertEquals(false, settings.darkMode)
        assertEquals(true, settings.notificationsEnabled)
        assertEquals("", settings.username)
        assertEquals("name", settings.sortOrder)
    }

    @Test
    fun flowObservesUsernameChanges() = runBlocking {

        repository.resetSettings()

        val updatedUsername = async {
            repository.settings.first { it.username == "FlowTest" }
        }

        repository.setUsername("FlowTest")

        val settings = updatedUsername.await()

        assertEquals("FlowTest", settings.username)

        repository.resetSettings()
    }
}