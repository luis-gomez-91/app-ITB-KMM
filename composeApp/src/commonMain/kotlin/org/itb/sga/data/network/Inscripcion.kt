package org.itb.sga.data.network

import kotlinx.serialization.Serializable

@Serializable
data class Inscripcion(
    val activo: Boolean,
    val carrera: String,
    val celular: String?,
    val convencional: String?,
    val email: String,
    val email_personal: String?,
    val grupo: String,
    val id: Int,
    val idPersona: Int,
    val idUsuario: Int,
    val identificacion: String,
    val nombre: String,
    val password: String,
    val username: String,
    var foto: String,
    val fecha: String
)