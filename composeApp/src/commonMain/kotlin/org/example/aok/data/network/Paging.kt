package org.example.aok.data.network

import kotlinx.serialization.Serializable

@Serializable
data class Paging(
    val firstPage: Int,
    val lastPage: Int
)