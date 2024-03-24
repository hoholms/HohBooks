package com.hoholms.book.hohbooks.navigation.bottom

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hoholms.book.hohbooks.screens.BooksScreen
import com.hoholms.book.hohbooks.screens.ForKidsScreen
import com.hoholms.book.hohbooks.screens.Screens
import com.hoholms.book.hohbooks.screens.SettingsScreen
import com.hoholms.book.hohbooks.ui.theme.HohBooksTheme
import com.hoholms.book.hohbooks.viewmodel.ThemeViewModel

@Composable
fun BottomNavigationBar(themeViewModel: ThemeViewModel = viewModel()) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val navigationSelectedItem = navBackStackEntry?.destination

    HohBooksTheme(themeSetting = themeViewModel.themeSetting.value) {
        Scaffold(bottomBar = {
            NavigationBar {
                BottomNavigationItem().bottomNavigationItems().forEachIndexed { _, item ->
                    NavigationBarItem(selected = item.route == navigationSelectedItem?.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        label = {
                            Text(text = item.title)
                        },
                        icon = {
                            BadgedBox(badge = {
                                if (item.badgeCount != null) {
                                    Badge {
                                        Text(text = item.badgeCount.toString())
                                    }
                                } else if (item.hasNews) {
                                    Badge()
                                }
                            }) {
                                Icon(
                                    imageVector = if (item.route == navigationSelectedItem?.route) {
                                        item.selectedIcon
                                    } else {
                                        item.unselectedIcon
                                    }, contentDescription = item.title
                                )
                            }
                        })
                }
            }
        }) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Screens.Books.route,
                modifier = Modifier.padding(paddingValues = paddingValues)
            ) {
                composable(Screens.Books.route) {
                    BooksScreen(
                        navController, themeViewModel
                    )
                }
                composable(Screens.ForKids.route) {
                    ForKidsScreen(
                        navController, themeViewModel
                    )
                }
                composable(Screens.Settings.route) {
                    SettingsScreen(
                        navController, themeViewModel
                    )
                }
            }
        }
    }
}

