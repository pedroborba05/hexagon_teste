package com.example.hexagon_tecnico.domain.model

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hexagon_tecnico.core.Constants.Companion.USER_TABLE

@Entity(tableName = USER_TABLE)
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val age: String,
    val cpf: String,
    val city: String,
    var imageUri: Uri?,
    val isActive: Boolean
)