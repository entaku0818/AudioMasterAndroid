package com.example.myapplication.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.TextToSpeechConverter
import kotlinx.coroutines.launch

class TextToSpeechViewModel(application: Application) : AndroidViewModel(application) {
    private val textToSpeechConverter = TextToSpeechConverter(application)

    fun speak(text: String, mode: String) {
        viewModelScope.launch {
            when (mode) {
                "fast" -> textToSpeechConverter.speakFast(text)
                "slow" -> textToSpeechConverter.speakSlow(text)
                "highPitch" -> textToSpeechConverter.speakHighPitch(text)
                "lowPitch" -> textToSpeechConverter.speakLowPitch(text)
                "normal" -> textToSpeechConverter.speakNormal(text)
            }
        }
    }

    override fun onCleared() {
        textToSpeechConverter.shutdown()
        super.onCleared()
    }
}
