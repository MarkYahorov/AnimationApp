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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.motion_activity)

        motionLayout = findViewById(R.id.m_lay)
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
                animateTextBtn.isEnabled = false
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                super.onTransitionCompleted(motionLayout, currentId)

                if (currentId == R.id.animend) {
                   animateTextBtn.text = "Animate text to top"
                } else if (currentId == R.id.start){
                    animateTextBtn.text = "Animate text to bottom"
                }
                animateTextBtn.isEnabled = true
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