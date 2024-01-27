package com.example.myapplication.model
import android.content.Context
import android.media.MediaPlayer

class AudioPlayer(
    private val context: Context,
    private val mediaPlayerFactory: (Context, Int) -> MediaPlayer = { ctx, resId ->
        MediaPlayer.create(ctx, resId)
    }
) {

    private var mediaPlayer: MediaPlayer? = null

    fun playAudio(resourceId: Int) {
        // 既存のMediaPlayerを停止してリリース
        stopAudio()

        // 新しいMediaPlayerを作成し、音声を再生
        mediaPlayer = mediaPlayerFactory(context, resourceId).apply {
            start()
            setOnCompletionListener {
                // 再生が終了したらリリース
                stopAudio()
            }
        }
    }

    fun stopAudio() {
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
