package com.example.myapplication.model

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.Locale

class TextToSpeechConverter(context: Context) {
    private var textToSpeech: TextToSpeech? = null

    init {
        textToSpeech = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech?.language = Locale.JAPANESE
            }
        }
    }

    // 早口で話す
    fun speakFast(text: String, language: Locale = Locale.JAPAN) {
        textToSpeech?.language = language
        textToSpeech?.setSpeechRate(2.0f) // Max speed depends on the device
        speak(text)
    }

    // ゆっくり話す
    fun speakSlow(text: String, language: Locale = Locale.JAPAN) {
        textToSpeech?.language = language
        textToSpeech?.setSpeechRate(0.5f)
        speak(text)
    }

    // 高いピッチで話す
    fun speakHighPitch(text: String, language: Locale = Locale.JAPAN) {
        textToSpeech?.language = language
        textToSpeech?.setPitch(2.0f)
        speak(text)
    }

    // 低いピッチで話す
    fun speakLowPitch(text: String, language: Locale = Locale.JAPAN) {
        textToSpeech?.language = language
        textToSpeech?.setPitch(0.5f)
        speak(text)
    }

    // 通常の話し方
    fun speakNormal(text: String, language: Locale = Locale.JAPAN) {
        textToSpeech?.language = language
        textToSpeech?.setPitch(1.0f)
        textToSpeech?.setSpeechRate(1.0f)
        speak(text)
    }

    private fun speak(text: String) {
        textToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    fun shutdown() {
        textToSpeech?.stop()
        textToSpeech?.shutdown()
    }
}
