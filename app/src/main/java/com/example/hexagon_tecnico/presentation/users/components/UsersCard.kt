package com.example.hexagon_tecnico.presentation.users.components

import androidx.compose.foundation.clickable
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
import com.example.hexagon_tecnico.util.Converters.Companion.calculateAge
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter


@Composable
@ExperimentalMaterialApi
fun UsersCard(
    users: User,
    inactiveUser: () -> Unit,
    navigateToUpdateUserScreen: (userId: Int) -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxWidth()
            .clickable(onClick = { navigateToUpdateUserScreen(users.id) }),
        elevation = 6.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            UserImage(
                uri = users.imageUri,
                Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                TextName(
                    userName = users.name,
                    style = MaterialTheme.typography.h6
                )
                val userAge = calculateAge(users.age)
                TextAge(
                    userAge = userAge,
                    style = MaterialTheme.typography.body2
                )
            }
            DeleteIcon(
                inactiveUser = inactiveUser
            )
        }
    }
}
