package com.example.hexagon_tecnico.ui.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hexagon_tecnico.data.local.db.daos.DaoPerson
import com.example.hexagon_tecnico.data.local.db.entity.Person
import com.example.hexagon_tecnico.data.local.db.repository.RepositoryPerson
import com.example.hexagon_tecnico.ui.MainActivity
import kotlinx.coroutines.launch

class PersonViewModel(application: Application) : AndroidViewModel(application) {
    private val daoPerson: DaoPerson = (application as MainActivity).database.daoPerson()
    private val repository: RepositoryPerson = RepositoryPerson(daoPerson)

    val allPeople: LiveData<List<Person>> = repository.getAllPeople()

    fun insertPerson(person: Person) {
        viewModelScope.launch {
            repository.insert(person)
        }
    }
}

