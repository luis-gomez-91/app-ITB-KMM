package org.example.aok.features.student.facturacion_electronica

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import org.example.aok.data.network.AluFacturacion
import org.example.aok.data.network.AluFacturacionResult
import org.example.aok.data.network.AluFacturacionXML
import org.example.aok.data.network.Error

class AluFacturacionService(
    private val client: HttpClient
) {
    suspend fun fetchAluFacturacion(id: Int): AluFacturacionResult {
        return try {
            val response = client.get("https://sga.itb.edu.ec/api_rest?action=alu_facturacion_electronica&id=$id")

            if (response.status == HttpStatusCode.OK) {
                val data = response.body<List<AluFacturacion>>()
                AluFacturacionResult.Success(data)
            } else {
                val error = response.body<Error>()
                AluFacturacionResult.Failure(error)
            }
        } catch (e: Exception) {
            val error = Error("Error inesperado: ${e.message}")
            AluFacturacionResult.Failure(error)
        }
    }

    suspend fun downloadXML(ruta: String): AluFacturacionXML {
        return try {
            val response = client.get("https://sga.itb.edu.ec$ruta")
            response.body<AluFacturacionXML>()
        } catch (e: Exception) {
            throw Exception("Error al descargar XML: ${e.message}")
        }
    }


}
