package com.example.myapplication

import AudioRecorderViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class AudioRecorderActivity : ComponentActivity() {

    private val viewModel: AudioRecorderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AudioRecorderUI(viewModel)
        }
    }
}

@Composable
fun AudioRecorderUI(viewModel: AudioRecorderViewModel) {
    Column(modifier = Modifier.padding(PaddingValues(16.dp))) {
        Button(onClick = { viewModel.startRecording("output_file_path.3gp") }) {
            Text("Start Recording")
        }
        Button(onClick = { viewModel.stopRecording() }) {
            Text("Stop Recording")
        }
        Button(onClick = { viewModel.playRecordedFile("output_file_path.3gp") }) {
            Text("Play Recording")
        }
    }
}

