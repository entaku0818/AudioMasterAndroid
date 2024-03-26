package com.example.myapplication.ui

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.hardware.SensorEvent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import com.example.myapplication.R

class MotionActivity : ComponentActivity(), SensorEventListener {

    private lateinit var motionLayout: MotionLayout
    private lateinit var sensorManager: SensorManager
    private var gravitySensor: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        motionLayout = findViewById(R.id.motionLayout)

        // センサーマネージャーの初期化
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        gravitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)

        // 重力センサーのリスナーを登録
        gravitySensor?.also { gravity ->
            sensorManager.registerListener(this, gravity, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let { sensorEvent ->
            if (sensorEvent.sensor.type == Sensor.TYPE_GRAVITY) {

                val gravityX = sensorEvent.values[0]
                // Y軸の重力加速度を取得
                val gravityY = sensorEvent.values[1]

                // X軸の値を使って左右の傾きを判断
                when {
                    gravityX > 1 -> {
                        // デバイスが右に傾いている場合
                        motionLayout.transitionToState(R.id.endStateRight)
                    }
                    gravityX < -1 -> {
                        // デバイスが左に傾いている場合
                        motionLayout.transitionToState(R.id.endStateLeft)
                    }
                }

                when {
                    gravityY > 1 -> {
                        motionLayout.transitionToState(R.id.endStateForward)
                    }
                    gravityY < -1 -> {
                        motionLayout.transitionToState(R.id.endStateBackward)
                    }
                }
            }
        }
    }


    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // センサーの精度が変わった時の処理（必要に応じて）
    }

    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(this)
    }
}
