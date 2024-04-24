package com.example.hexagon_tecnico.presentation.inactive_user.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hexagon_tecnico.domain.model.User
import com.example.hexagon_tecnico.presentation.users.components.TextAuthor
import com.example.hexagon_tecnico.presentation.users.components.TextTitle
import com.example.hexagon_tecnico.presentation.users.components.UserImage


@Composable
@ExperimentalMaterialApi
fun UsersInactiveCard(
    usersInactive: User,
    activeUsers: () -> Unit,
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 4.dp,
                bottom = 4.dp
            )
            .fillMaxWidth(),
        elevation = 3.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                UserImage(
                    uri = usersInactive.imageUri,
                    Modifier
                )
                TextTitle(
                    userName = usersInactive.name
                )
                usersInactive.age?.let {
                    TextAuthor(
                        userAge = it
                    )
                }
            }
            Spacer(
                modifier = Modifier.weight(1f)
            )
            ActiveUsersIcon(
                activeUsers = activeUsers
            )
        }
    }
}