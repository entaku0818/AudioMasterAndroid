package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.myapplication.model.AudioPlayer
import com.example.myapplication.R

class AudioPlayerViewModel(
    application: Application
) : AndroidViewModel(application) {
    private val resourceId: Int = R.raw.jinglebells
    private val audioPlayer = AudioPlayer(application, resourceId)

    fun playAudio() {
        audioPlayer.playAudio()
    }

    fun pauseAudio() {
        audioPlayer.pauseAudio()
    }

    fun stopAudio() {
        audioPlayer.stopAudio()
    }

    override fun onCleared() {
        super.onCleared()
        audioPlayer.stopAudio()
    }
}
