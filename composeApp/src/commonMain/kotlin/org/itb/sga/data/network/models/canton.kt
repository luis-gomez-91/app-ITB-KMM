package org.itb.sga.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class Canton(
    val descripcion: String,
    val id: Int
)