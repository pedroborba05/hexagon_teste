package com.example.hexagon_tecnico.ui.components
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hexagon_tecnico.R
import com.example.hexagon_tecnico.data.local.db.entity.Person

@Composable
fun CreatePersonForm(onCreatePerson: (Person) -> Unit) {
    var name by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var isActive by remember { mutableStateOf(true) }
    var photoResId by remember { mutableIntStateOf(R.drawable.no_camera) }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
        // Implementação simplificada para seleção de data
        OutlinedTextField(
            value = cpf,
            onValueChange = { cpf = it },
            label = { Text("CPF") }
        )
        OutlinedTextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("City") }
        )
        Button(
            onClick = {
                onCreatePerson(Person(id = 0, name, birthDate, cpf, city, photoResId, isActive))
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Create Person")
        }
    }
}
