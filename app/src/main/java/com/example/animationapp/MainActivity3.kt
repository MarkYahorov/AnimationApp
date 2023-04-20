package com.example.animationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.airbnb.lottie.LottieAnimationView

class MainActivity3 : AppCompatActivity() {

    private lateinit var lottie: LottieAnimationView
    private lateinit var sp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        lottie = findViewById(R.id.lottie_view)
        sp = findViewById(R.id.stop_btn)
    }

    override fun onStart() {
        super.onStart()

        sp.setOnClickListener {
            if (lottie.isAnimating) {
                lottie.pauseAnimation();
                sp.text = "Старт";
            } else {
                lottie.resumeAnimation();
                sp.text = "Пауза";
            }
        }
    }
}