package com.example.hexagon_tecnico.presentation.users

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hexagon_tecnico.core.Constants.Companion.EMPTY_STRING
import com.example.hexagon_tecnico.domain.model.User
import com.example.hexagon_tecnico.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repo: UserRepository
) : ViewModel() {
    val activeUsers: Flow<List<User>> = repo.getActiveUsersFromRoom()
    val inactiveUsers: Flow<List<User>> = repo.getInactiveUsersFromRoom()


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

    fun updateUser(user: User) = viewModelScope.launch {
        repo.updateUserInRoom(user)
    }

    fun inativeUser(user: User) = viewModelScope.launch {
        repo.inactivateUserInRoom(user)
    }

    fun activeUser(user: User) = viewModelScope.launch {
        repo.activeUserInRoom(user)
    }

    fun updateName(name: String) {
        user = user.copy(
            name = name
        )
    }

    fun updateAge(age: String) {
        user = user.copy(
            age = age
        )
    }

    fun updateCpf(cpf: String) {
        user = user.copy(
            cpf = cpf
        )
    }

    fun updateCity(city: String) {
        user = user.copy(
            city = city
        )
    }

    fun updateImageUri(imageUri: Uri?) {
        user = user.copy(
            imageUri = imageUri
        )
    }

    fun openDialog() {
        openDialog = true
    }

    fun closeDialog() {
        openDialog = false
    }
}