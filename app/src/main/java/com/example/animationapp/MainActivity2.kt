package com.example.animationapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter

class MainActivity2 : AppCompatActivity() {

    private lateinit var m_lay: MotionLayout
    private lateinit var b: Button
    private lateinit var b2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        m_lay = findViewById(R.id.m_lay)
        b = findViewById(R.id.to_lottie)
        b2 = findViewById(R.id.motion_button)
    }

    override fun onStart() {
        super.onStart()

        m_lay.addTransitionListener(object : TransitionAdapter() {

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
                    startActivity(Intent(this@MainActivity2, MainActivity3::class.java))
                }
            }
        })
    }
}