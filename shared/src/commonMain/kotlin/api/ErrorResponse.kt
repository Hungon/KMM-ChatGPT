package api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    @SerialName("error")
    val error: Error
) {
    @Serializable
    data class Error(
        @SerialName("message")
        val message: String,
        @SerialName("type")
        val type: String,
        @SerialName("param")
        val param: String?,
        @SerialName("code")
        val code: String?
    )
}
