package com.example.myapplication.ui
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.myapplication.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView

@Composable
fun VideoPlayerComponent() {
    val context = LocalContext.current
    val exoPlayer = ExoPlayer.Builder(context).build()

    // rawリソースのビデオファイルのURIを作成
    val videoUri = "android.resource://${context.packageName}/${R.raw.sample_movie}"

    val mediaItem = MediaItem.fromUri(Uri.parse(videoUri))
    exoPlayer.setMediaItem(mediaItem)
    exoPlayer.prepare()
    exoPlayer.play()

    AndroidView(
        factory = { ctx ->
            PlayerView(ctx).also {
                it.player = exoPlayer
                it.onResume()
            }
        }
    )
}
