package com.example.myapplication

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import android.content.Context
import android.media.MediaPlayer
import com.example.myapplication.model.AudioPlayer

@RunWith(MockitoJUnitRunner::class)
class AudioPlayerTest {

    @Test
    fun playAudio_startsMediaPlayer() {
        val context = mock(Context::class.java)
        val mediaPlayer = mock(MediaPlayer::class.java)
        val mediaPlayerFactory = { _: Context, _: Int -> mediaPlayer }
        val audioPlayer = AudioPlayer(context, mediaPlayerFactory)

        audioPlayer.playAudio(R.raw.audio)

        verify(mediaPlayer).start()
    }

    @Test
    fun stopAudio_releasesMediaPlayer() {
        val context = mock(Context::class.java)
        val mediaPlayer = mock(MediaPlayer::class.java)
        val mediaPlayerFactory = { _: Context, _: Int -> mediaPlayer }
        val audioPlayer = AudioPlayer(context, mediaPlayerFactory)

        audioPlayer.playAudio(R.raw.audio)
        audioPlayer.stopAudio()

        verify(mediaPlayer).release()
    }
}
