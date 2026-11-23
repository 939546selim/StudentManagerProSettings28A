
package com.ahona.studentmanagerprosettings28a

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ahona.studentmanagerprosettings28a.di.AppContainer
import com.ahona.studentmanagerprosettings28a.ui.screens.SettingsScreen
import com.ahona.studentmanagerprosettings28a.ui.viewmodel.SettingsViewModel
import com.ahona.studentmanagerprosettings28a.ui.viewmodel.SettingsViewModelFactory

class MainActivity : ComponentActivity() {

    private lateinit var appContainer: AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appContainer = AppContainer(applicationContext)

        setContent {
            val settingsViewModel: SettingsViewModel = viewModel(
                factory = SettingsViewModelFactory(
                    appContainer.settingsRepository
                )
            )

            MaterialTheme {
                Surface {
                    SettingsScreen(
                        viewModel = settingsViewModel
                    )
                }
            }
        }
    }
}
