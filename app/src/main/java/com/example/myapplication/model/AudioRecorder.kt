package com.example.myapplication.model

import android.media.MediaRecorder
import java.io.IOException

class AudioRecorder(private val mediaRecorderFactory: () -> MediaRecorder) {

    private var mediaRecorder: MediaRecorder? = null
    private var isRecording = false

    fun startRecording(outputFile: String) {
        if (isRecording) return

        mediaRecorder = mediaRecorderFactory().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            setOutputFile(outputFile)

            try {
                prepare()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            start()
            isRecording = true
        }
    }

    fun stopRecording() {
        if (!isRecording) return

        mediaRecorder?.apply {
            stop()
            release()
        }
        mediaRecorder = null
        isRecording = false
    }
}
