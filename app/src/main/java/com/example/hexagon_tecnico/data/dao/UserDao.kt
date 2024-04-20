package com.example.hexagon_tecnico.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import androidx.room.Update
import com.example.hexagon_tecnico.core.Constants.Companion.USER_TABLE
import com.example.hexagon_tecnico.domain.model.User
import com.example.hexagon_tecnico.domain.repository.Users
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM $USER_TABLE ORDER BY id ASC")
    fun getUsers(): Flow<Users>

    @Query("SELECT * FROM $USER_TABLE WHERE id = :id")
    suspend fun getUser(id: Int): User

    @Insert(onConflict = IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}