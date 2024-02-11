package com.example.myapplication


import AudioRecorderViewModel
import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
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

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (!isGranted) {
            // Handle permission denial
        }
    }

    private val viewModel: AudioRecorderViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissions()
        setContent {
            AudioRecorderUI(viewModel = viewModel)
        }
    }

    private fun requestPermissions() {
        requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
    }

    @Composable
    fun AudioRecorderUI(viewModel: AudioRecorderViewModel) {
        Column(modifier = Modifier.padding(PaddingValues(16.dp))) {
            Button(onClick = {
                val outputFile = getExternalFilesDir(null)?.absolutePath + "/recording.3gp"
                viewModel.startRecording(outputFile)
            }) {
                Text("Start Recording")
            }
            Button(onClick = { viewModel.stopRecording() }) {
                Text("Stop Recording")
            }
            Button(onClick = {
                val outputFile = getExternalFilesDir(null)?.absolutePath + "/recording.3gp"
                viewModel.playRecordedFile(outputFile)
            }) {
                Text("Play Recording")
            }
        }
    }
}
