package com.example.myapplication.model

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import androidx.core.app.ActivityCompat
import java.io.File
import java.io.FileOutputStream

class AudioRecordManager(context: Context)  {
    private val appContext = context.applicationContext

    private var recorder: AudioRecord? = null
    private var isRecording = false
    private val sampleRate = 44100 // サンプルレート
    private val channelConfig = AudioFormat.CHANNEL_IN_MONO // モノラル
    private val audioFormat = AudioFormat.ENCODING_PCM_16BIT // 16ビットPCM
    private val bufferSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat)

    fun startRecording(filePath: String) {
        if (ActivityCompat.checkSelfPermission(
                appContext,
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        recorder = AudioRecord(MediaRecorder.AudioSource.MIC, sampleRate, channelConfig, audioFormat, bufferSize)
        recorder?.startRecording()
        isRecording = true
        val file = File(filePath)
        val outputStream = FileOutputStream(file)

        Thread {
            val audioData = ByteArray(bufferSize)
            while (isRecording) {
                val readSize = recorder?.read(audioData, 0, audioData.size) ?: 0
                if (readSize > 0) {
                    outputStream.write(audioData, 0, readSize)
                }
            }
            recorder?.stop()
            recorder?.release()
            outputStream.close()
        }.start()
    }

    fun stopRecording() {
        isRecording = false
    }
}
