package com.example.myapplication.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar

class TextToSpeechActivity : ComponentActivity() {

    private val viewModel: TextToSpeechViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextToSpeechScreen(viewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextToSpeechScreen(viewModel: TextToSpeechViewModel) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Text to Speech") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = { newText ->
                    text = newText
                },
                label = { Text("Enter text here") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            ButtonGroup(viewModel = viewModel, text = text.text)
        }
    }
}

@Composable
fun ButtonGroup(viewModel: TextToSpeechViewModel, text: String) {
    Column {
        val modes = listOf("fast" to "Speak Fast", "slow" to "Speak Slow", "highPitch" to "High Pitch", "lowPitch" to "Low Pitch", "normal" to "Normal")
        modes.forEach { mode ->
            Button(
                onClick = { viewModel.speak(text, mode.first) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text(mode.second)
            }
        }
    }
}
