package api.v1

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionsResponse(
    val id: String,
    val `object`: String,
    val created: Int,
    val choices: List<Choice>,
    val usage: Usage
) {
    @Serializable
    data class Choice(
        val index: Int,
        val message: Message,
        @SerialName("finish_reason")
        val finishReason: String
    ) {
        @Serializable
        data class Message(
            val role: String,
            val content: String
        )
    }

    @Serializable
    data class Usage(
        @SerialName("prompt_tokens")
        val promptTokens: Int,
        @SerialName("completion_tokens")
        val completionTokens: Int,
        @SerialName("total_tokens")
        val totalTokens: Int
    )
}
