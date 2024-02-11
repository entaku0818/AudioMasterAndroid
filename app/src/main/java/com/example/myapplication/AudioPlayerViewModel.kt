package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.AudioRecorder
import kotlinx.coroutines.launch

class AudioRecorderViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var audioRecorder: AudioRecorder
    private var outputFile: String? = null

    fun initializeRecorder(outputFile: String) {
        this.outputFile = outputFile
        audioRecorder = AudioRecorder(getApplication(), outputFile)
    }

    fun startRecording() {
        outputFile?.let {
            audioRecorder.startRecording()

        }
    }

    fun stopRecording() {
        viewModelScope.launch {
            audioRecorder.stopRecording()
        }
    }

    fun playRecordedFile() {
        outputFile?.let {
            viewModelScope.launch {
                audioRecorder.playRecordedFile()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        audioRecorder.stopRecording()
    }
}
