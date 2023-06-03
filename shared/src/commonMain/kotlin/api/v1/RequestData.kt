package api.v1

import kotlinx.serialization.Serializable

@Serializable
data class RequestData(
    val model: String,
    val messages: List<Message>
) {
    @Serializable
    data class Message(
        val role: String,
        val content: String
    )
}
