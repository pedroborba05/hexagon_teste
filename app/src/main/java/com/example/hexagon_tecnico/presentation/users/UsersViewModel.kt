package com.example.hexagon_tecnico.presentation.users

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
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
    val activeUsers: LiveData<List<User>> = repo.getActiveUsersFromRoom().asLiveData(viewModelScope.coroutineContext)
    val inactiveUsers: LiveData<List<User>> = repo.getInactiveUsersFromRoom().asLiveData(viewModelScope.coroutineContext)


    var user by mutableStateOf(User(0, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, Uri.EMPTY, isActive = false))
        private set
    var openDialog by mutableStateOf(false)

    val users = repo.getUsersFromRoom()

    fun getUser(id: Int) = viewModelScope.launch {
        user = repo.getUserFromRoom(id)
    }

    fun addUser(user: User) = viewModelScope.launch {
        repo.addUserToRoom(user)
    }

    fun updateBook(user: User) = viewModelScope.launch {
        repo.updateUserInRoom(user)
    }

    fun deleteUser(user: User) = viewModelScope.launch {
        repo.deleteUserFromRoom(user)
    }

    fun inativeUser(user: User) = viewModelScope.launch {
        repo.inactivateUserInRoom(user)
    }

    fun updateTitle(name: String) {
        user = user.copy(
            name = name
        )
    }

    fun updateAuthor(age: String) {
        user = user.copy(
            age = age
        )
    }

    fun openDialog() {
        openDialog = true
    }

    fun closeDialog() {
        openDialog = false
    }
}