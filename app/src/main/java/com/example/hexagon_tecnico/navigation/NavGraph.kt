package com.example.hexagon_tecnico.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hexagon_tecnico.navigation.Screen.UpdateUsersScreen
import com.example.hexagon_tecnico.navigation.Screen.UsersScreen
import com.example.hexagon_tecnico.presentation.users.UsersScreen


@Composable
@ExperimentalMaterialApi
fun NavGraph (
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = UsersScreen.route
    ) {
        composable(
            route = UsersScreen.route
        ) {
            UsersScreen(
                navigateToUpdateUserScreen = { bookId ->
                    navController.navigate(
                        route = "${UpdateUsersScreen.route}/${bookId}"
                    )
                }
            )
        }
    }
}