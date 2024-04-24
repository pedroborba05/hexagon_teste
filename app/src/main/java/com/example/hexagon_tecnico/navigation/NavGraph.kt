package com.example.hexagon_tecnico.navigation

import AddUsersScreen
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.hexagon_tecnico.core.Constants
import com.example.hexagon_tecnico.navigation.Screen.AddUsersScreen
import com.example.hexagon_tecnico.navigation.Screen.InactivateUsersScreen
import com.example.hexagon_tecnico.navigation.Screen.UpdateUsersScreen
import com.example.hexagon_tecnico.navigation.Screen.UsersScreen
import com.example.hexagon_tecnico.presentation.inactive_user.InactiveUsersScreen
import com.example.hexagon_tecnico.presentation.update_user.UpdateUsersScreen
import com.example.hexagon_tecnico.presentation.users.UsersScreen


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = UsersScreen.route,
        modifier = modifier
    ) {
        composable(
            route = UsersScreen.route
        ) {
            UsersScreen(
                navigateToUpdateUserScreen = { userId ->
                    navController.navigate(
                        route = "${UpdateUsersScreen.route}/${userId}"
                    ) }
            )
        }
        composable(
            route = InactivateUsersScreen.route
        ) {
            InactiveUsersScreen()
        }
        composable(
            route = AddUsersScreen.route
        ) {
            AddUsersScreen()
        }
        composable(
            route = "${UpdateUsersScreen.route}/{${Constants.USER_ID}}",
            arguments = listOf(
                navArgument(Constants.USER_ID) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt(Constants.USER_ID) ?: 0
            UpdateUsersScreen(
                userId = userId,
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}