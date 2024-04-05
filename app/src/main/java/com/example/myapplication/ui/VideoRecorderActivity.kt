package com.example.myapplication.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.SurfaceTexture
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.hardware.camera2.CameraMetadata
import android.hardware.camera2.CaptureRequest
import android.media.MediaRecorder
import android.os.Bundle
import android.view.Surface
import android.view.TextureView
import android.widget.Button
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import com.example.myapplication.R


class VideoRecorderActivity : ComponentActivity() {

    private lateinit var textureView: TextureView
    private lateinit var videoView: VideoView
    private var cameraDevice: CameraDevice? = null
    private var cameraCaptureSessions: CameraCaptureSession? = null
    private var captureRequestBuilder: CaptureRequest.Builder? = null
    private var mediaRecorder: MediaRecorder? = null
    private var isRecording = false
    private lateinit var videoPath: String

    private val surfaceTextureListener = object : TextureView.SurfaceTextureListener {
        override fun onSurfaceTextureAvailable(surface: SurfaceTexture, width: Int, height: Int) {
            openCamera()
        }

        override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture, width: Int, height: Int) {}

        override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean = true

        override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {}
    }

    private val stateCallback = object : CameraDevice.StateCallback() {
        override fun onOpened(camera: CameraDevice) {
            cameraDevice = camera
            createCameraPreviewSession()
        }

        override fun onDisconnected(camera: CameraDevice) {
            cameraDevice?.close()
        }

        override fun onError(camera: CameraDevice, error: Int) {
            cameraDevice?.close()
            cameraDevice = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        textureView = findViewById(R.id.textureView)
        videoView = findViewById(R.id.videoView)
        val recordButton: Button = findViewById(R.id.recordButton)

        videoPath = "${getExternalFilesDir(null)?.absolutePath}/myVideo.mp4"

        textureView.surfaceTextureListener = surfaceTextureListener

        recordButton.setOnClickListener {
            if (isRecording) {
                stopRecording()
                recordButton.text = "録画開始"
                isRecording = false
            } else {
                startRecording()
                recordButton.text = "録画停止"
                isRecording = true
            }
        }
    }

    private fun openCamera() {
        val manager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        try {
            val cameraId = manager.cameraIdList[0]
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                return
            }
            manager.openCamera(cameraId, stateCallback, null)
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }

    private fun createCameraPreviewSession() {
        try {
            val texture = textureView.surfaceTexture!!
            val surface = Surface(texture)
            captureRequestBuilder = cameraDevice?.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)?.apply {
                addTarget(surface)
            }

            cameraDevice?.createCaptureSession(listOf(surface), object : CameraCaptureSession.StateCallback() {
                override fun onConfigured(session: CameraCaptureSession) {
                    cameraCaptureSessions = session
                    updatePreview()
                }

                override fun onConfigureFailed(session: CameraCaptureSession) {
                    Toast.makeText(this@VideoRecorderActivity, "Configuration failed", Toast.LENGTH_SHORT).show()
                }
            }, null)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun updatePreview() {
        if (cameraDevice == null) return
        captureRequestBuilder?.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO)
        try {
            cameraCaptureSessions?.setRepeatingRequest(captureRequestBuilder!!.build(), null, null)
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }

    private fun startRecording() {
        // TODO: Implement start recording functionality using MediaRecorder
    }

    private fun stopRecording() {
        // TODO: Implement stop recording functionality and play recorded video
    }


}
