package com.example.hexagon_tecnico.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import com.example.hexagon_tecnico.data.local.db.entity.Person
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import com.example.hexagon_tecnico.R


@Composable
fun PersonListScreen(persons: List<Person>, padding: Any?) {
    LazyColumn {
        items(items = persons.filter { it.isActive }) { person ->
            if (person.isActive) {
                PersonItem(person)
            }
        }
    }
}

@Composable
fun PersonItem(person: Person) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = person.photoResId),
            contentDescription = "Profile Picture of ${person.name}",
            modifier = Modifier.size(64.dp)
        )
        Column(modifier = Modifier
            .weight(1f)
            .padding(horizontal = 8.dp)) {
            Text(text = "Nome: ${person.name}")
            Text(text = "Cidade: ${person.city}")
            Text(text = "Idade: ${person.birthDate}")
        }
        IconButton(onClick = {  }) {
            Icon(painter = painterResource(id = R.drawable.ic_to_edit), contentDescription = "Edit")
        }
        Switch(
            checked = person.isActive,
            onCheckedChange = {  }
        )
    }
}