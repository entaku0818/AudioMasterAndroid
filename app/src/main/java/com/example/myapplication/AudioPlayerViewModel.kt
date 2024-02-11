package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.myapplication.model.AudioPlayer

class AudioPlayerViewModel(application: Application) : AndroidViewModel(application) {

    private val audioPlayer = AudioPlayer(application)

    fun playAudio(resourceId: Int) {
        audioPlayer.playAudio(resourceId)
    }

    fun stopAudio() {
        audioPlayer.stopAudio()
    }

    override fun onCleared() {
        super.onCleared()
        audioPlayer.stopAudio()
    }
}
