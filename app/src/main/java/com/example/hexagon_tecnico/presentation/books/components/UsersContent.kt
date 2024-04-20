package com.example.hexagon_tecnico.presentation.books.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.hexagon_tecnico.domain.model.User
import com.example.hexagon_tecnico.domain.repository.Users

@Composable
@ExperimentalMaterialApi
fun UsersContent(
    padding: PaddingValues,
    users: Users,
    deleteBook: (user: User) -> Unit,
    navigateToUpdateBookScreen: (bookId: Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(padding)
    ) {
        items(
            items = users
        ) { book ->
            UsersCard(
                user = book,
                deleteBook = {
                    deleteBook(book)
                },
                navigateToUpdateBookScreen = navigateToUpdateBookScreen
            )
        }
    }
}