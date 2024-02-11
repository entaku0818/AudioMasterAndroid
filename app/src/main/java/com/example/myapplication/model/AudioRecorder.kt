package com.example.myapplication.model

import android.media.MediaPlayer
import android.media.MediaRecorder
import java.io.IOException

class AudioRecorder(private val outputFile: String) {

    private var mediaRecorder: MediaRecorder? = null
    private var mediaPlayer: MediaPlayer? = null
    private var isRecording = false

    fun startRecording() {
        if (isRecording) return

        // MediaRecorderのインスタンスを直接生成
        mediaRecorder = MediaRecorder().apply {
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

    fun playRecordedFile() {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.stop()
            mediaPlayer?.release()
        }

        mediaPlayer = MediaPlayer().apply {
            try {
                setDataSource(outputFile)
                prepare()
                start()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}
