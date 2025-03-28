package org.itb.sga.data.network

import kotlinx.serialization.Serializable

@Serializable
data class AluFacturacion(
    val fecha: String,
    val numero: String,
    val numeroAutorizacion: String,
    val ride: String,
    val tipo: String,
    val xml: String,
    val id: Int
)