package com.example.hexagon_tecnico.util

import com.example.hexagon_tecnico.R
import com.example.hexagon_tecnico.data.local.db.entity.Person
import java.util.Date

object PersonUtil {
    fun samplePersonList(): List<Person> {
        return listOf(
            Person(id = 1, name = "Victor Rayan", birthDate = Date(), cpf = "123.456.789-09", city = "Samambaia Sul", photoResId = R.drawable.photo_victor, isActive = true),
            Person(id = 2, name = "Yago Moreira", birthDate = Date(), cpf = "987.654.321-09", city = "Samambaia Norte", photoResId = R.drawable.photo_yago, isActive = true),
            Person(id = 3, name = "Lucas Melo", birthDate = Date(), cpf = "987.654.321-10", city = "Brasilia", photoResId = R.drawable.photo_victor, isActive = true),
            Person(id = 4, name = "Pedro Henrique", birthDate = Date(), cpf = "987.654.321-10", city = "Asa sul", photoResId = R.drawable.photo_victor, isActive = true)

        )
    }
}