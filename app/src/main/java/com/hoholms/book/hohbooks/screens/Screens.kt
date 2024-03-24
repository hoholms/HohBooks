package com.hoholms.book.hohbooks.screens

sealed class Screens(val route: String) {
    data object Books : Screens("books_route")
    data object ForKids : Screens("for_kids_route")
    data object Settings : Screens("settings_route")
}
