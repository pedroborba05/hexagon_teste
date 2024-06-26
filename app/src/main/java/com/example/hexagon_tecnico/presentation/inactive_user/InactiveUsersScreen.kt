package com.example.hexagon_tecnico.presentation.inactive_user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hexagon_tecnico.presentation.inactive_user.components.InactiveUsersTopBar
import com.example.hexagon_tecnico.presentation.inactive_user.components.ReturnWithoutInactiveUsers
import com.example.hexagon_tecnico.presentation.inactive_user.components.UsersInactiveContent
import com.example.hexagon_tecnico.presentation.users.UsersViewModel

@Composable
@ExperimentalMaterialApi
fun InactiveUsersScreen(
    viewModel: UsersViewModel = hiltViewModel()
    ) {

    val usersInactive by viewModel.inactiveUsers.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            InactiveUsersTopBar()
        },
        content = { padding ->
            if (usersInactive.isEmpty()) {
                ReturnWithoutInactiveUsers()
            } else {
                Column(modifier = Modifier.padding(padding)) {
                    UsersInactiveContent(
                        padding = padding,
                        usersInactive = usersInactive,
                        activeUsers = {
                                userInactive -> viewModel.activeUser(userInactive)
                        }
                    )
                }
            }
        },
    )
}
