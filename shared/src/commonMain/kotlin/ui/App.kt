package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import api.v1.ChatApiClient
import kotlinx.coroutines.launch

@Composable
fun App() {
    MaterialTheme {
        var inputMessage by remember { mutableStateOf("") }
        var response by remember { mutableStateOf("") }
        val scope = rememberCoroutineScope()
        Column(
            Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Text(
                "Input prompt here:",
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(5.dp))
            TextField(
                value = inputMessage,
                onValueChange = { inputMessage = it }
            )
            Spacer(Modifier.height(10.dp))
            Text("Response:\n$response")
            Spacer(Modifier.height(20.dp))
            Button(onClick = {
                scope.launch {
                    response = try {
                        ChatApiClient().completions(inputMessage).choices.firstOrNull()?.message?.content
                            ?: "Error"
                    } catch (e: Exception) {
                        e.message ?: "Error"
                    }
                }
            }) {
                Text("Send")
            }
        }
    }
}
