package org.itb.sga.features.teacher.pro_cronograma

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import org.itb.sga.core.SERVER_URL
import org.itb.sga.data.network.Error
import org.itb.sga.data.network.ProCronogramaResult
import org.itb.sga.data.network.pro_cronograma.ProCronograma

class ProCronogramaService(
    private val client: HttpClient
) {
    suspend fun fetchProCronograma(id: Int): ProCronogramaResult {
        return try {
            val response = client.get("${SERVER_URL}api_rest?action=pro_cronograma&id=${id}")

            if (response.status == HttpStatusCode.OK) {
                val data = response.body<List<ProCronograma>>()
                ProCronogramaResult.Success(data)
            } else {
                val error = response.body<Error>()
                ProCronogramaResult.Failure(error)
            }
        } catch (e: Exception) {
            val error = Error("Error", "Error inesperado: ${e.message}")
            ProCronogramaResult.Failure(error)
        }
    }
}