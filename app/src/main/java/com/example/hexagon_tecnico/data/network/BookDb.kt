package com.example.hexagon_tecnico.data.network

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hexagon_tecnico.data.dao.BookDao
import com.example.hexagon_tecnico.domain.model.Book

@Database(
    entities = [Book::class],
    version = 1,
    exportSchema = false
)
abstract class BookDb : RoomDatabase() {
    abstract val bookDao: BookDao
}