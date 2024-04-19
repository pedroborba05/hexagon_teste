package com.example.hexagon_tecnico.util

import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
import java.util.*

fun CalculateAge(birthDate: Date): Int {
    val birthLocalDate = birthDate.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
    val today = LocalDate.now()
    return Period.between(birthLocalDate, today).years
}
