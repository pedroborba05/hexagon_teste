package com.example.hexagon_tecnico.presentation.inactiveusers.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.hexagon_tecnico.domain.model.User

@Composable
@ExperimentalMaterialApi
fun UsersInactiveContent(
    padding: PaddingValues,
    users: List<User>,
    activeUser: (user: User) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(padding)
    ) {
        items(
            items = users
        ) { user ->
            UsersInactiveCard(
                user = user,
                deleteUser = {
                    activeUser(user)
                }
            )
        }
    }
}