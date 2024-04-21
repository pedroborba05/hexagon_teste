package com.example.hexagon_tecnico.navigation

import com.example.hexagon_tecnico.core.Constants.Companion.INACTIVE_USERS_SCREEN
import com.example.hexagon_tecnico.core.Constants.Companion.UPDATE_USERS_SCREEN
import com.example.hexagon_tecnico.core.Constants.Companion.USERS_SCREEN

sealed class Screen(val route: String) {
    object UsersScreen: Screen(USERS_SCREEN)
    object UpdateUsersScreen: Screen(UPDATE_USERS_SCREEN)
    object InactivateUsersScreen: Screen(INACTIVE_USERS_SCREEN)
}