package com.example.hexagon_tecnico.presentation.books

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hexagon_tecnico.core.Constants.Companion.EMPTY_STRING
import com.example.hexagon_tecnico.domain.model.User
import com.example.hexagon_tecnico.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repo: UserRepository
) : ViewModel() {
    var user by mutableStateOf(User(0, EMPTY_STRING, EMPTY_STRING))
        private set
    var openDialog by mutableStateOf(false)

    val users = repo.getUsersFromRoom()

    fun getUser(id: Int) = viewModelScope.launch {
        user = repo.getUserFromRoom(id)
    }

    fun addBook(user: User) = viewModelScope.launch {
        repo.addUserToRoom(user)
    }

    fun updateBook(user: User) = viewModelScope.launch {
        repo.updateUserInRoom(user)
    }

    fun deleteUser(user: User) = viewModelScope.launch {
        repo.deleteUserFromRoom(user)
    }

    fun updateTitle(title: String) {
        user = user.copy(
            title = title
        )
    }

    fun updateAuthor(author: String) {
        user = user.copy(
            author = author
        )
    }

    fun openDialog() {
        openDialog = true
    }

    fun closeDialog() {
        openDialog = false
    }
}