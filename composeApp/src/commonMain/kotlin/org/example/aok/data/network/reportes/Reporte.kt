package org.example.aok.data.network.reportes

import kotlinx.serialization.Serializable

@Serializable
data class Reporte(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val parametros: List<ReporteParametro>
)