package com.example.hexagon_tecnico.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hexagon_tecnico.navigation.Screen.BooksScreen
import com.example.hexagon_tecnico.navigation.Screen.UpdateBookScreen
import com.example.hexagon_tecnico.presentation.books.BooksScreen


@Composable
@ExperimentalMaterialApi
fun NavGraph (
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BooksScreen.route
    ) {
        composable(
            route = BooksScreen.route
        ) {
            BooksScreen(
                navigateToUpdateBookScreen = { bookId ->
                    navController.navigate(
                        route = "${UpdateBookScreen.route}/${bookId}"
                    )
                }
            )
        }
    }
}