package com.example.hexagon_tecnico.navigation

import com.example.hexagon_tecnico.core.Constants.Companion.BOOKS_SCREEN
import com.example.hexagon_tecnico.core.Constants.Companion.UPDATE_BOOK_SCREEN

sealed class Screen(val route: String) {
    object BooksScreen: Screen(BOOKS_SCREEN)
    object UpdateBookScreen: Screen(UPDATE_BOOK_SCREEN)
}