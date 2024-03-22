package com.example.myapplication.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import com.example.myapplication.R

class MotionActivity : ComponentActivity() {

    private lateinit var motionLayout: MotionLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // MotionLayoutのインスタンスを取得
        motionLayout = findViewById(R.id.motionLayout)

        // 必要に応じてリスナーを設定
        motionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {
                // アニメーション開始時の処理
            }

            override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {
                // アニメーション進行中の処理
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                // アニメーション完了時の処理
            }

            override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) {
                // トリガーイベント発生時の処理
            }
        })
    }
}
