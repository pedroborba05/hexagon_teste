import android.Manifest
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hexagon_tecnico.domain.model.User
import com.example.hexagon_tecnico.presentation.update_user.components.AddUsersTopBar
import com.example.hexagon_tecnico.presentation.users.UsersViewModel
import com.example.hexagon_tecnico.presentation.users.components.loadImageBitmap

@Composable
fun AddUsersScreen(
    viewModel: UsersViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    val context = LocalContext.current

    val openDocument = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        uri?.let {
            val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
            context.contentResolver.takePersistableUriPermission(uri, takeFlags)

            imageUri = uri
        }
    }

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            openDocument.launch(arrayOf("image/*"))
        } else {
            Toast.makeText(context, "Permissão recusada", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            AddUsersTopBar(navigateBack = navigateBack)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nome do Usuário") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = age,
                onValueChange = { age = it },
                label = { Text("Idade") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = cpf,
                onValueChange = { cpf = it },
                label = { Text("CPF") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = city,
                onValueChange = { city = it },
                label = { Text("Cidade") }
            )
            Button(onClick = {
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }) {
                Text("Selecionar Imagem")
            }
            imageUri?.let { uri ->
                LaunchedEffect(uri) {
                    imageBitmap = loadImageBitmap(context, uri)
                }
                imageBitmap?.let { img ->
                    Image(
                        bitmap = img,
                        contentDescription = "Imagem selecionada",
                        modifier = Modifier.height(200.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val user = User(0, name, age, cpf, city, imageUri, isActive = true) // Ajuste conforme seu modelo de dados
                    viewModel.addUser(user)
                    Toast.makeText(context, "Usuário adicionado!", Toast.LENGTH_SHORT).show()

                    name = ""; age = ""; cpf = ""; city = ""
                }
            ) {
                Text("Adicionar Usuário")
            }
        }
    }
}
