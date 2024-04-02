package com.example.myapplication.ui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
class VideoRecorderActivity : ComponentActivity() {

    private val viewModel: VideoRecorderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VideoRecorderScreen(viewModel)
        }
    }
}
@Composable
fun VideoRecorderScreen(videoRecorderViewModel: VideoRecorderViewModel) {
    var isRecording by remember { mutableStateOf(false) }

    Button(onClick = {
        if (isRecording) {
            videoRecorderViewModel.stopRecording()
        } else {
            videoRecorderViewModel.startRecording()
        }
        isRecording = !isRecording
    }) {
        Text(text = if (isRecording) "Stop Recording" else "Start Recording")
    }
}
