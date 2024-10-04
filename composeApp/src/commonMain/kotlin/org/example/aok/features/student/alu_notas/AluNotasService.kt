package org.example.aok.features.student.alu_notas

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import org.example.aok.data.network.AluNotas
import org.example.aok.data.network.AluNotasResult
import org.example.aok.data.network.Error

class AluNotasService(
    private val client: HttpClient
) {
    suspend fun fetchAluNota(id: Int): AluNotasResult {
        return try {
            val response = client.get("https://sga.itb.edu.ec/api_rest?action=alu_notas&id=$id")

            if (response.status == HttpStatusCode.OK) {
                val data = response.body<List<AluNotas>>()
                AluNotasResult.Success(data)
            } else {
                val error = response.body<Error>()
                AluNotasResult.Failure(error)
            }
        } catch (e: Exception) {
            val error = Error("Error inesperado: ${e.message}")
            AluNotasResult.Failure(error)
        }
    }
}