package api.v1

import Config
import api.apiClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class ChatApiClient {

    @Throws(Exception::class)
    suspend fun completions(content: String): ChatCompletionsResponse {
        val response: ChatCompletionsResponse =
            apiClient.post("${Config.BASE_API_URL}/v1/chat/completions") {
                setBody(
                    RequestData(
                        "gpt-3.5-turbo",
                        listOf(RequestData.Message("user", content))
                    )
                )
            }.body()
        return response
    }
}
