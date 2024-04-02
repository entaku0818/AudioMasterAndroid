package com.example.myapplication.ui

import android.app.Application
import android.os.Environment
import androidx.lifecycle.AndroidViewModel
import com.example.myapplication.model.VideoRecorder
import java.io.File

class VideoRecorderViewModel(application: Application) : AndroidViewModel(application) {
    private val videoRecorder = VideoRecorder() // VideoRecorderはMediaRecorderをラップするクラスです

    fun startRecording() {
        // Scoped Storageに対応した保存先のパスを設定
        val outputPath = getVideoFilePath()
        videoRecorder.startRecording(outputPath)
    }

    fun stopRecording() {
        videoRecorder.stopRecording()
    }

    override fun onCleared() {
        super.onCleared()
        videoRecorder.release() // ViewModelが破棄される時にリソースを解放
    }

    private fun getVideoFilePath(): String {
        val externalFilesDir = getApplication<Application>().getExternalFilesDir(Environment.DIRECTORY_MOVIES)
        if (externalFilesDir == null) {
            // 外部ストレージがマウントされていないか利用不可の場合はエラー処理
            throw IllegalStateException("External storage is not available.")
        } else {
            // 外部ストレージが利用可能な場合は、ファイルパスを生成
            val videoFile = File(externalFilesDir, "my_video_${System.currentTimeMillis()}.mp4")
            return videoFile.absolutePath
        }
    }
}
