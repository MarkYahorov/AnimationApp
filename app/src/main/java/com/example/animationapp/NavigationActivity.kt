package com.example.animationapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class NavigationActivity : AppCompatActivity() {

    private lateinit var toAnimatorSetBtn: Button
    private lateinit var toMotionBtn: Button
    private lateinit var toLottieBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_activity)

        toAnimatorSetBtn = findViewById(R.id.to_anim_set_button)
        toMotionBtn = findViewById(R.id.to_motion_layout_btn)
        toLottieBtn = findViewById(R.id.to_lottie_btn)
    }

    override fun onStart() {
        super.onStart()

        toAnimatorSetBtn.setOnClickListener {
            startActivity(Intent(this, AnimatorSetActivity::class.java))
        }

        toMotionBtn.setOnClickListener {
            startActivity(Intent(this, MotionLayoutActivity::class.java))
        }

        toLottieBtn.setOnClickListener {
            startActivity(Intent(this, LottieAnimationActivity::class.java))
        }
    }
}