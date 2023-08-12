package com.example.animationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.airbnb.lottie.LottieAnimationView

private const val LOTTIE_FRAME_KEY = "frame"
private const val LOTTIE_ANIMATION_KEY = "need_animation"

class LottieAnimationActivity : AppCompatActivity() {

    private lateinit var lottie: LottieAnimationView
    private lateinit var startStopBtn: Button
    private var needAnimated: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        lottie = findViewById(R.id.lottie_view)
        startStopBtn = findViewById(R.id.stop_btn)
    }

    override fun onStart() {
        super.onStart()

        startStopBtn.setOnClickListener {
            if (lottie.isAnimating) {
                pauseAnimation()
            } else {
                resumeAnimation()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(LOTTIE_FRAME_KEY, lottie.frame)
        outState.putBoolean(LOTTIE_ANIMATION_KEY, needAnimated)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        lottie.frame = savedInstanceState.getInt(LOTTIE_FRAME_KEY)
        needAnimated = savedInstanceState.getBoolean(LOTTIE_ANIMATION_KEY)
    }

    override fun onResume() {
        super.onResume()

        if (needAnimated) {
            resumeAnimation()
        }
    }

    override fun onPause() {
        lottie.pauseAnimation()

        super.onPause()
    }

    private fun pauseAnimation() {
        lottie.pauseAnimation()
        startStopBtn.text = "Старт"
        needAnimated = false
    }

    private fun resumeAnimation() {
        lottie.resumeAnimation()
        startStopBtn.text = "Пауза"
        needAnimated = true
    }
}