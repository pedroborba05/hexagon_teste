package com.example.hexagon_tecnico.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hexagon_tecnico.navigation.Screen.AddUsersScreen
import com.example.hexagon_tecnico.navigation.Screen.InactivateUsersScreen
import com.example.hexagon_tecnico.navigation.Screen.UpdateUsersScreen
import com.example.hexagon_tecnico.navigation.Screen.UsersScreen
import com.example.hexagon_tecnico.presentation.add_user.AddUsersScreen
import com.example.hexagon_tecnico.presentation.inactive_user.InactiveUsersScreen
import com.example.hexagon_tecnico.presentation.users.UsersScreen


@Composable
@ExperimentalMaterialApi
fun NavGraph (
    navController: NavHostController = rememberNavController()
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
                    ) },
                navigateToInactiveUsersScreen = {
                    navController.navigate(InactivateUsersScreen.route)
                },
                navigateToAddUsersScreen = {
                    navController.navigate(AddUsersScreen.route)
                }
            )
        }
        composable(
            route = InactivateUsersScreen.route
        ) {
            InactiveUsersScreen(
                navigateToUsersScreen = {
                    navController.navigate(UsersScreen.route)
                }
            )
        }
        composable(
            route = AddUsersScreen.route
        ) {
            AddUsersScreen()
        }
    }
}