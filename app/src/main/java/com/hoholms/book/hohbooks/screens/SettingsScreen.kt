package com.hoholms.book.hohbooks.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.hoholms.book.hohbooks.ui.theme.HohBooksTheme
import com.hoholms.book.hohbooks.viewmodel.ThemeSetting
import com.hoholms.book.hohbooks.viewmodel.ThemeViewModel

@Composable
fun SettingsScreen(navController: NavController, themeViewModel: ThemeViewModel = viewModel()) {
    var selectedTheme by remember { mutableStateOf(themeViewModel.themeSetting.value) }

    HohBooksTheme(themeSetting = themeViewModel.themeSetting.value) {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "Settings",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 20.dp)
                )

                // Theme selection
                ThemeSetting.entries.forEach { theme ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        RadioButton(
                            selected = selectedTheme == theme,
                            onClick = {
                                selectedTheme = theme
                                themeViewModel.setTheme(theme)
                            }
                        )
                        Text(
                            text = theme.name,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    }
}
