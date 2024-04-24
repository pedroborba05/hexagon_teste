package com.example.hexagon_tecnico.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation (
        backgroundColor = Color.White,
    ) {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Usuários") },
            label = { Text("Usuários") },
            selected = currentRoute == Screen.UsersScreen.route,
            onClick = { if (currentRoute != Screen.UsersScreen.route) navController.navigate(Screen.UsersScreen.route) }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.AddCircle, contentDescription = "Adicionar usuários") },
            label = { Text("Adicionar usuários") },
            selected = currentRoute == Screen.AddUsersScreen.route,
            onClick = { if (currentRoute != Screen.AddUsersScreen.route) navController.navigate(Screen.AddUsersScreen.route) }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Usuários inativos") },
            label = { Text("Usuários inativos") },
            selected = currentRoute == Screen.InactivateUsersScreen.route,
            onClick = { if (currentRoute != Screen.InactivateUsersScreen.route) navController.navigate(Screen.InactivateUsersScreen.route) }
        )
    }
}
