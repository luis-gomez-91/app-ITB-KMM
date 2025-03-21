package org.itb.sga.core

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import org.itb.sga.data.domain.ChipState
import org.itb.sga.data.network.Response

fun String.capitalizeWords(): String {
    return this.lowercase().split(" ").joinToString(" ") { word ->
        word.lowercase().replaceFirstChar { it.uppercase() }
    }
}

@Composable
fun formatoText(titulo: String, descripcion: String): AnnotatedString {
    return buildAnnotatedString {
        append(
            AnnotatedString(
                text = titulo,
                spanStyle = MaterialTheme.typography.titleSmall.toSpanStyle()
            )
        )
        append(" ")
        append(
            AnnotatedString(
                text = descripcion,
                spanStyle = MaterialTheme.typography.labelSmall.toSpanStyle()
            )
        )
    }
}

@Composable
fun getChipState(documentoVerificado: Boolean): ChipState {
    return if (documentoVerificado) {
        ChipState(
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            labelColor = MaterialTheme.colorScheme.onPrimary,
            icon = Icons.Filled.Check,
            label = "Documento verificado"
        )
    } else {
        ChipState(
            containerColor = MaterialTheme.colorScheme.errorContainer,
            labelColor = MaterialTheme.colorScheme.error,
            icon = Icons.Filled.Error,
            label = "Documento no verificado"
        )
    }
}

suspend fun requestPostDispatcher(client: HttpClient, form: Any, action: String = "postDispatcher"): Response {
    return try {
        val response = client.post("${SERVER_URL}api_rest?action=${action}") {
            contentType(ContentType.Application.Json)
            setBody(form)
        }
        if (response.status.isSuccess()) {
            val successResponse = response.body<Response>()
            successResponse
        } else {
            Response(status = "error", message = "Unexpected response status: ${response.status}")
        }
    } catch (e: Exception) {
        e.printStackTrace()
        Response(status = "error", message = "Exception occurred: ${e.message}")
    }
}