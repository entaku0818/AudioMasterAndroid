package com.example.myapplication.model

import android.media.MediaRecorder
import java.io.IOException

class VideoRecorder {
    private var mediaRecorder: MediaRecorder? = null
    private var isRecording = false

    fun startRecording(outputPath: String) {
        if (isRecording) return

        mediaRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setVideoSource(MediaRecorder.VideoSource.CAMERA)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setVideoEncoder(MediaRecorder.VideoEncoder.H264)
            setOutputFile(outputPath)
            setVideoSize(1920, 1080)
            setVideoFrameRate(30)

            try {
                prepare()
            } catch (e: IOException) {
                throw RuntimeException("MediaRecorder prepare failed", e)
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

    fun release() {
        if (isRecording) {
            stopRecording()
        }
        mediaRecorder?.release()
        mediaRecorder = null
    }
}
