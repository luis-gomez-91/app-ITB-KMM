package org.itb.sga.data.network

import kotlinx.serialization.Serializable

@Serializable
data class AluDocumento (
    val archivo: String?,
    val idDocumento: Int,
    val nombreDocumento: String,
    val verificado: Boolean
)