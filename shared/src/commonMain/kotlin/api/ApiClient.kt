package api

import Config
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val apiClient = HttpClient {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
    install(Logging)
    defaultRequest {
        headers {
            append(HttpHeaders.Authorization, "Bearer ${Config.API_KEY}")
            append(HttpHeaders.ContentType, "application/json")
        }
    }
    expectSuccess = true
    HttpResponseValidator {
        handleResponseExceptionWithRequest { exception, _ ->
            val clientException =
                exception as? ClientRequestException ?: return@handleResponseExceptionWithRequest
            val exceptionResponse = clientException.response
            throw exceptionResponse.body<ErrorResponse?>()?.let {
                ApiClientException(it.error.message)
            } ?: exception
        }
    }
}

class ApiClientException(override val message: String) : Exception()
