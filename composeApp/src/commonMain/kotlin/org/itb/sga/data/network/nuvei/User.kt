package org.itb.sga.data.network.nuvei

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email: String?,
    val id: String?
)