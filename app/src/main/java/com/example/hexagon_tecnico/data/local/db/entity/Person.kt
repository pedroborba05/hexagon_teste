package com.example.hexagon_tecnico.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Person(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val birthDate: String,
    val cpf: String,
    val city: String,
    val photoResId: Int,
    val isActive: Boolean
)