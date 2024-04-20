package com.example.hexagon_tecnico.data.network

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hexagon_tecnico.data.dao.UserDao
import com.example.hexagon_tecnico.domain.model.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class UserDb : RoomDatabase() {
    abstract val userDao: UserDao
}