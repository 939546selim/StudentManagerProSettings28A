package com.ahona.studentmanagerprosettings28a.ui.screens
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ahona.studentmanagerprosettings28a.ui.viewmodel.SettingsViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel
) {
    val settings by viewModel.settings.collectAsStateWithLifecycle()

    var username by remember(settings.username) {
        mutableStateOf(settings.username)
    }

    var sortMenuExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Text(
            text = "App Settings",
            style = MaterialTheme.typography.headlineMedium
        )

        // Dark Mode
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Dark Mode")

            Switch(
                checked = settings.darkMode,
                onCheckedChange = viewModel::setDarkMode
            )
        }

        // Notifications
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Notifications")

            Switch(
                checked = settings.notificationsEnabled,
                onCheckedChange = viewModel::setNotificationsEnabled
            )
        }

        // Username
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                viewModel.setUsername(username)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Username")
        }

        // Sorting
        Column {
            Text("Sort Students")

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { sortMenuExpanded = true }
            ) {
                Text("Sort by: ${settings.sortOrder}")
            }

            DropdownMenu(
                expanded = sortMenuExpanded,
                onDismissRequest = {
                    sortMenuExpanded = false
                }
            ) {
                DropdownMenuItem(
                    text = { Text("Name") },
                    onClick = {
                        viewModel.setSortOrder("name")
                        sortMenuExpanded = false
                    }
                )

                DropdownMenuItem(
                    text = { Text("Age") },
                    onClick = {
                        viewModel.setSortOrder("age")
                        sortMenuExpanded = false
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Reset
        Button(
            onClick = {
                viewModel.resetSettings()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reset Settings")
        }
    }
}
