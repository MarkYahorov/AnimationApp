package com.example.animationapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter

private const val TRANSITION_STATE_KEY = "transition_key"

class MotionLayoutActivity : AppCompatActivity() {

    private lateinit var motionLayout: MotionLayout
    private lateinit var animateTextBtn: Button
    private lateinit var navigateBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        motionLayout = findViewById(R.id.m_lay)
        navigateBtn = findViewById(R.id.to_lottie)
        animateTextBtn = findViewById(R.id.motion_button)
    }

    override fun onStart() {
        super.onStart()

        motionLayout.addTransitionListener(object : TransitionAdapter() {

            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {
                super.onTransitionStarted(motionLayout, startId, endId)
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                super.onTransitionCompleted(motionLayout, currentId)

                if (currentId == R.id.end) {
                    startActivity(Intent(this@MotionLayoutActivity, LottieAnimationActivity::class.java))
                }
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putBundle(TRANSITION_STATE_KEY, motionLayout.transitionState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        motionLayout.transitionState = savedInstanceState.getBundle(TRANSITION_STATE_KEY)
    }
}