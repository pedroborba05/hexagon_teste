//package com.example.hexagon_tecnico.presentation.update_user.components
//
//import androidx.compose.material.Icon
//import androidx.compose.material.IconButton
//import androidx.compose.material.Text
//import androidx.compose.material.TopAppBar
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.outlined.ArrowBack
//import androidx.compose.runtime.Composable
//import com.example.hexagon_tecnico.core.Constants.Companion.UPDATE_USERS_SCREEN
//
//@Composable
//fun UpdateUsersTopBar(
//    navigateBack: () -> Unit
//) {
//    TopAppBar (
//        title = {
//            Text(
//                text = UPDATE_USERS_SCREEN
//            )
//        },
//        navigationIcon = {
//            IconButton(
//                onClick = navigateBack
//            ) {
//                Icon(
//                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
//                    contentDescription = null,
//                )
//            }
//        }
//    )
//}