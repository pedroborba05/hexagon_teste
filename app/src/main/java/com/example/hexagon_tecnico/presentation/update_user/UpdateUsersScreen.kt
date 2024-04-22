//package com.example.hexagon_tecnico.presentation.update_user
//
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.example.hexagon_tecnico.presentation.update_user.components.UpdateUsersTopBar
//import com.example.hexagon_tecnico.presentation.users.UsersViewModel
//
//@Composable
//fun UpdateUsersScreen(
//    viewModel: UsersViewModel = hiltViewModel(),
//    userId: Int,
//    navigateBack: () -> Unit
//) {
//    LaunchedEffect(Unit) {
//        viewModel.getUser(userId)
//    }
//    Scaffold(
//        topBar = {
//            UpdateUsersTopBar(
//                navigateBack = navigateBack
//            )
//        },
//        content = { padding ->
//            UpdateUsersContent(
//                padding = padding,
//                book = viewModel.book,
//                updateTitle = { title ->
//                    viewModel.updateTitle(title)
//                },
//                updateAuthor = { author ->
//                    viewModel.updateAuthor(author)
//                },
//                updateBook = { book ->
//                    viewModel.updateBook(book)
//                },
//                navigateBack = navigateBack
//            )
//        }
//    )
//}