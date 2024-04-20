package com.example.hexagon_tecnico.domain.repository

import com.example.hexagon_tecnico.domain.model.User
import kotlinx.coroutines.flow.Flow

typealias Users = List<User>

interface UserRepository {
    fun getUsersFromRoom(): Flow<Users>

    suspend fun getUserFromRoom(id: Int): User

    suspend fun addUserToRoom(user: User)

    suspend fun updateUserInRoom(user: User)

    suspend fun deleteUserFromRoom(user: User)
}