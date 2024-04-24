package com.example.hexagon_tecnico.presentation.inactive_user.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hexagon_tecnico.domain.model.User
import com.example.hexagon_tecnico.presentation.users.components.TextAge
import com.example.hexagon_tecnico.presentation.users.components.TextName
import com.example.hexagon_tecnico.presentation.users.components.UserImage
import com.example.hexagon_tecnico.util.Converters.Companion.calculateAge


@Composable
@ExperimentalMaterialApi
fun UsersInactiveCard(
    usersInactive: User,
    activeUsers: () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxWidth(),
        elevation = 6.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            UserImage(
                uri = usersInactive.imageUri,
                Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column (
                modifier = Modifier.weight(1f)
            ) {
                TextName(
                    userName = usersInactive.name,
                    style = MaterialTheme.typography.h6
                )
                usersInactive.age.let {
                    val userAge = calculateAge(usersInactive.age)
                    TextAge(
                        userAge = userAge,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
            ActiveUsersIcon(
                activeUsers = activeUsers
            )
        }
    }
}