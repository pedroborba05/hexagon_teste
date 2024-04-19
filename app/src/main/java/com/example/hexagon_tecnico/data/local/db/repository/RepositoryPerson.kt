package com.example.hexagon_tecnico.data.local.db.repository

import androidx.lifecycle.LiveData
import com.example.hexagon_tecnico.data.local.db.daos.DaoPerson
import com.example.hexagon_tecnico.data.local.db.entity.Person

class RepositoryPerson(private val daoPerson: DaoPerson) {

        fun getAllPeople(): LiveData<List<Person>> = daoPerson.getAll()

    fun getAllPeopleActive(): LiveData<List<Person>> = daoPerson.getAllActive()

    suspend fun insert(person: Person) {
        daoPerson.insert(person)
    }
}