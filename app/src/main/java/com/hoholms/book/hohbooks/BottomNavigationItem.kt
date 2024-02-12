package com.hoholms.book.hohbooks

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.ChildCare
import androidx.compose.material.icons.filled.CropSquare
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.ChildCare
import androidx.compose.material.icons.outlined.CropSquare
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val title: String = "",
    val selectedIcon: ImageVector = Icons.Filled.CropSquare,
    val unselectedIcon: ImageVector = Icons.Outlined.CropSquare,
    val hasNews: Boolean = false,
    val badgeCount: Int? = null,
    val route: String = ""
) {
    fun bottomNavigationItems(): List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                title = "Books",
                selectedIcon = Icons.Filled.Book,
                unselectedIcon = Icons.Outlined.Book,
                hasNews = false,
                route = Screens.Books.route
            ),
            BottomNavigationItem(
                title = "For kids",
                selectedIcon = Icons.Filled.ChildCare,
                unselectedIcon = Icons.Outlined.ChildCare,
                hasNews = false,
                route = Screens.ForKids.route
            ),
            BottomNavigationItem(
                title = "Settings",
                selectedIcon = Icons.Filled.Settings,
                unselectedIcon = Icons.Outlined.Settings,
                hasNews = true,
                route = Screens.Settings.route
            )
        )
    }
}