package com.example.myapplication.ui;

import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

class VideoPlaybackActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val videoPath = intent.getStringExtra("videoPath") ?: ""

        setContent {
            VideoPlayer(videoPath = videoPath)
        }
    }
}

@Composable
fun VideoPlayer(videoPath: String) {
    AndroidView(
        factory = { context ->
            VideoView(context).apply {
                setVideoURI(Uri.parse(videoPath))
                start()
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}
