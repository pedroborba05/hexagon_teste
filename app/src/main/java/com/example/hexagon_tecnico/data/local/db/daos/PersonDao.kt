package com.example.hexagon_tecnico.data.local.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.hexagon_tecnico.data.local.db.entity.Person

@Dao
interface PersonDao {

    @Insert
    suspend fun insert(person: Person)

    @Update
    suspend fun update(person: Person)

    @Query("SELECT * FROM Person WHERE isActive = 1")
    fun getAllActive(): List<Person>

    @Query("SELECT * FROM Person WHERE isActive = 0")
    fun getAllInactive(): List<Person>

    @Query("UPDATE Person SET isActive = :isActive WHERE id = :id")
    suspend fun updateStatus(id: Int, isActive: Boolean)
}