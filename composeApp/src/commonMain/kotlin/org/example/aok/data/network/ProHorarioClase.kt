package org.example.aok.data.network

import kotlinx.serialization.Serializable

@Serializable
data class ProHorarioClase(
    val aula: String,
    val carrera: String,
    val grupo: String,
    val materia: String,
    val materiaDesde: String,
    val materiaHasta: String,
    val nivel: String,
    val turnoComienza: String,
    val turnoTermina: String
)