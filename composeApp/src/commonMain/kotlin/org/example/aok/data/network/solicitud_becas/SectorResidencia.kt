package org.example.aok.data.network.solicitud_becas

import kotlinx.serialization.Serializable

@Serializable
data class SectorResidencia(
    val descripcion: String,
    val id: Int
)