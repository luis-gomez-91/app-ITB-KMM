package org.example.aok.data.network

import kotlinx.serialization.Serializable

@Serializable
data class PagoOnline(
    val datosFacturacion: DatosFacturacion,
    val rubros: List<RubroX>
)