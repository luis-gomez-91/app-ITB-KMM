package org.example.aok.features.common.home

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.call.receive
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import org.example.aok.core.logInfo
import org.example.aok.data.network.Error
import org.example.aok.data.network.Home
import org.example.aok.data.network.HomeResult
import org.example.aok.data.network.PagoOnlineForm
import org.example.aok.data.network.Report
import org.example.aok.data.network.ReportForm
import org.example.aok.data.network.ReportResult

class HomeService(
    private val client: HttpClient
) {
    suspend fun fetchHome(id: Int): HomeResult {
        return try {
            val response = client.get("https://sga.itb.edu.ec/api_rest?action=panel&id=$id")

            if (response.status == HttpStatusCode.OK) {
                val home = response.body<Home>()
                HomeResult.Success(home)
            } else {
                val error = response.body<Error>()
                HomeResult.Failure(error)
            }
        } catch (e: Exception) {
            val error = Error("Error", "Error inesperado: ${e.message}")
            HomeResult.Failure(error)
        }
    }

    suspend fun fetchReport(form: ReportForm): ReportResult {
        return try {
            val response =
                client.post("https://sga.itb.edu.ec/api_rest?action=runReport") {
                    contentType(ContentType.Application.Json)
                    setBody(form)
                }
            if (response.status == HttpStatusCode.OK) {
                val data = response.body<Report>()
                ReportResult.Success(data)

            } else {
                val error = response.body<Error>()
                ReportResult.Failure(error)
            }
        } catch (e: Exception) {
            val error = Error("Error", "Error inesperado: ${e.message}")
            ReportResult.Failure(error)
        } finally {
        }
    }
}