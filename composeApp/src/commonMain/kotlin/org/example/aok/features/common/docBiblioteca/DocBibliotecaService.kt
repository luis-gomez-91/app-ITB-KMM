package org.example.aok.features.common.docBiblioteca

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import org.example.aok.core.SERVER_URL
import org.example.aok.data.network.DocBibliotecaResult
import org.example.aok.data.network.DocBibliotecas
import org.example.aok.data.network.Error

class DocBibliotecaService(
    private val client: HttpClient
) {
    suspend fun fetchDocBiblioteca(search: String? = null, page: Int): DocBibliotecaResult {
        return try {
            val response = client.get("${SERVER_URL}api_rest?action=documentos&page=${page}")

            if (response.status == HttpStatusCode.OK) {
                val data = response.body<DocBibliotecas>()
                DocBibliotecaResult.Success(data)
            } else {
                val error = response.body<Error>()
                DocBibliotecaResult.Failure(error)
            }
        } catch (e: Exception) {
            val error = Error("Error", "Error inesperado: ${e.message}")
            DocBibliotecaResult.Failure(error)
        }
    }
}