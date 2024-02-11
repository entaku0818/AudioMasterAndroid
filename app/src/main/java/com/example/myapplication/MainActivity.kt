package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }

    @Composable
    fun MyApp() {
        val context = LocalContext.current
        Column(modifier = Modifier.padding(PaddingValues(16.dp))) {
            Button(onClick = {
                context.startActivity(Intent(context, AudioPlayerActivity::class.java))
            }) {
                Text(text = "Go to Audio Player")
            }

            Button(onClick = {
                context.startActivity(Intent(context, AudioRecorderActivity::class.java))
            }) {
                Text(text = "Go to Audio Recorder")
            }
        }
    }
}
