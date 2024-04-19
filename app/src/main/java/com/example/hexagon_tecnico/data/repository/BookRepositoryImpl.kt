package com.example.hexagon_tecnico.data.repository

import com.example.hexagon_tecnico.data.dao.BookDao
import com.example.hexagon_tecnico.domain.model.Book
import com.example.hexagon_tecnico.domain.repository.BookRepository

class BookRepositoryImpl(
    private val bookDao: BookDao
) : BookRepository {
    override fun getBooksFromRoom() = bookDao.getBooks()

    override suspend fun getBookFromRoom(id: Int) = bookDao.getBook(id)

    override suspend fun addBookToRoom(book: Book) = bookDao.addBook(book)

    override suspend fun updateBookInRoom(book: Book) = bookDao.updateBook(book)

    override suspend fun deleteBookFromRoom(book: Book) = bookDao.deleteBook(book)
}