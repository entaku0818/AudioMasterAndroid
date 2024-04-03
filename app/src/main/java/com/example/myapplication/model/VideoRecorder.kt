package com.example.myapplication.model

import android.Manifest
import android.media.MediaRecorder
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.os.Handler
import android.os.HandlerThread
import android.view.Surface
import androidx.core.app.ActivityCompat

class VideoRecorder(private val context: Context) {
    private var cameraDevice: CameraDevice? = null
    private var captureSession: CameraCaptureSession? = null
    private var mediaRecorder: MediaRecorder? = null
    private var backgroundHandler: Handler? = null
    private var backgroundThread: HandlerThread? = null

    fun startBackgroundThread() {
        backgroundThread = HandlerThread("CameraBackground").also { it.start() }
        backgroundHandler = Handler(backgroundThread!!.looper)
    }

    fun stopBackgroundThread() {
        backgroundThread?.quitSafely()
        try {
            backgroundThread?.join()
            backgroundThread = null
            backgroundHandler = null
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    fun openCamera() {
        val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
        try {
            val cameraId = cameraManager.cameraIdList[0] // 前面または背面カメラを選択
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            cameraManager.openCamera(cameraId, object : CameraDevice.StateCallback() {
                override fun onOpened(camera: CameraDevice) {
                    cameraDevice = camera
                    // カメラが開いたら、プレビューを開始または録画準備を行う
                }

                override fun onDisconnected(camera: CameraDevice) {
                    camera.close()
                    cameraDevice = null
                }

                override fun onError(camera: CameraDevice, error: Int) {
                    camera.close()
                    cameraDevice = null
                }
            }, backgroundHandler)
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }

    fun startRecording(outputPath: String) {
        if (cameraDevice == null) return

        mediaRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setVideoSource(MediaRecorder.VideoSource.SURFACE)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setVideoEncoder(MediaRecorder.VideoEncoder.H264)
            setOutputFile(outputPath)
            setVideoSize(1920, 1080)
            setVideoFrameRate(30)
            prepare()
        }

        val surfaces = ArrayList<Surface>().apply {
            add(mediaRecorder!!.surface)
            // プレビューディスプレイ用のSurfaceも追加する場合はここに追加
        }

        cameraDevice?.createCaptureSession(surfaces, object : CameraCaptureSession.StateCallback() {
            override fun onConfigured(session: CameraCaptureSession) {
                captureSession = session
                try {
                    // ここでキャプチャセッションを開始
                    mediaRecorder?.start()
                } catch (e: CameraAccessException) {
                    e.printStackTrace()
                }
            }

            override fun onConfigureFailed(session: CameraCaptureSession) {
                // キャプチャセッションの設定失敗
            }
        }, backgroundHandler)
    }

    fun stopRecording() {
        captureSession?.close()
        captureSession = null
        mediaRecorder?.apply {
            stop()
            reset()
        }
        mediaRecorder = null
        // 必要に応じてカメラを再開するか、リソースを解放する
    }

    fun release() {
        cameraDevice?.close()
        cameraDevice = null
        stopBackgroundThread()
    }
}
