package com.example.hexagon_tecnico.data.network

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hexagon_tecnico.data.dao.UserDao
import com.example.hexagon_tecnico.domain.model.User
import com.example.hexagon_tecnico.util.RoomConverter

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    RoomConverter::class
)
abstract class UserDb : RoomDatabase() {
    abstract val userDao: UserDao
}