package com.example.hexagon_tecnico.data.repository

import com.example.hexagon_tecnico.data.dao.UserDao
import com.example.hexagon_tecnico.domain.model.User
import com.example.hexagon_tecnico.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {
    override fun getUsersFromRoom() = userDao.getUsers()

    override suspend fun getUserFromRoom(id: Int) = userDao.getUser(id)

    override suspend fun addUserToRoom(user: User) = userDao.addUser(user)

    override suspend fun updateUserInRoom(user: User) = userDao.updateUser(user)

    override suspend fun deleteUserFromRoom(user: User) = userDao.deleteUser(user)

    override suspend fun inactivateUserInRoom(user: User) = userDao.inactivateUser(user.id, false)

    override suspend fun activeUserInRoom(user: User) = userDao.activeUser(user.id, true)

    override fun getActiveUsersFromRoom(): Flow<List<User>> = userDao.getActiveUsers()

    override fun getInactiveUsersFromRoom(): Flow<List<User>> = userDao.getInactiveUsers()
}