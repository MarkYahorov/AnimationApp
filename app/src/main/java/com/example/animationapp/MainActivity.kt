package com.example.animationapp

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import android.view.animation.ScaleAnimation
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var view: TextView
    private lateinit var view2: Button
    private lateinit var view3: Button
    private lateinit var view4: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view = findViewById(R.id.some_id)
        view2= findViewById(R.id.property_anim)
        view3 = findViewById(R.id.button2)
        view4 = findViewById(R.id.button3)
    }

    override fun onStart() {
        super.onStart()

        view2.setOnClickListener {
            val animationX = ObjectAnimator.ofFloat(view, "scaleX", 200F)
            val animationY = ObjectAnimator.ofFloat(view, "scaleY", 200F)
            val set = AnimatorSet()
            set.play(animationX)
                .with(animationY)
            set.duration = 500
            set.start()
        }

        view3.setOnClickListener {
            val anim = ScaleAnimation(0F, 1F, 0F, 1F,
                0F, view.measuredHeight.toFloat())
            anim.duration = 500
            anim.interpolator = DecelerateInterpolator()
            anim.fillAfter = true
            view.startAnimation(anim)
        }
        view4.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }
    }
}