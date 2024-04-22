package com.example.hexagon_tecnico.navigation

import AddUsersScreen
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType.Companion.IntType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.hexagon_tecnico.core.Constants.Companion.USER_ID
import com.example.hexagon_tecnico.navigation.Screen.AddUsersScreen
import com.example.hexagon_tecnico.navigation.Screen.InactivateUsersScreen
import com.example.hexagon_tecnico.navigation.Screen.UpdateUsersScreen
import com.example.hexagon_tecnico.navigation.Screen.UsersScreen
import com.example.hexagon_tecnico.presentation.inactive_user.InactiveUsersScreen
import com.example.hexagon_tecnico.presentation.update_user.UpdateUsersScreen
import com.example.hexagon_tecnico.presentation.users.UsersScreen


@Composable
@ExperimentalMaterialApi
fun NavGraph (
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = UsersScreen.route,
    ) {
        composable(
            route = UsersScreen.route
        ) {
            UsersScreen(
                navigateToUpdateUserScreen = { userId ->
                    navController.navigate(
                        route = "${UpdateUsersScreen.route}/${userId}"
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
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = AddUsersScreen.route
        ) {
            AddUsersScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = "${UpdateUsersScreen.route}/{$USER_ID}",
            arguments = listOf(
                navArgument(USER_ID) {
                    type = IntType
                }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt(USER_ID) ?: 0
            UpdateUsersScreen(
                userId = userId,
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}