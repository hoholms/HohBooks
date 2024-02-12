package com.hoholms.book.hohbooks

sealed class Screens(val route: String) {
    object Books : Screens("books_route")
    object ForKids : Screens("for_kids_route")
    object Settings : Screens("settings_route")
}