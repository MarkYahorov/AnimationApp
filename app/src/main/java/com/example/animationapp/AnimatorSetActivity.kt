package com.example.animationapp

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.addListener

private const val SCALE_X_KEY = "scaleX"
private const val SCALE_Y_KEY = "scaleY"
private const val INITIAL_ANIMATED_VALUE = 1f
private const val BIG_SCALE_VALUE = 2f

class AnimatorSetActivity : AppCompatActivity() {

    private lateinit var animatedView: TextView
    private lateinit var bigScaleAnimationBtn: Button
    private lateinit var view4: Button
    private val animatorSet = AnimatorSet().apply {
        addListener(
            onEnd = {
                bigScaleAnimationBtn.text =
                    if (getObjectAnimatorScaleValue(animatedView.scaleX) == INITIAL_ANIMATED_VALUE) {
                        "Click to initial value animation"
                    } else {
                        "Click to Big Scale animation"
                    }
                updateEnabledBtnState(true)
            },
            onStart = { updateEnabledBtnState(false) }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animatedView = findViewById(R.id.some_id)
        bigScaleAnimationBtn = findViewById(R.id.property_anim)
        view4 = findViewById(R.id.button3)
    }

    override fun onStart() {
        super.onStart()

        bigScaleAnimationBtn.setOnClickListener {
            val scaleXValue = getObjectAnimatorScaleValue(animatedView.scaleX)
            val scaleYValue = getObjectAnimatorScaleValue(animatedView.scaleY)
            val animationX = ObjectAnimator.ofFloat(animatedView, SCALE_X_KEY, scaleXValue)
            val animationY = ObjectAnimator.ofFloat(animatedView, SCALE_Y_KEY, scaleYValue)
            animatorSet.cancel()
            animatorSet.duration = 500
            animatorSet.play(animationX)
                .with(animationY)
            animatorSet.start()
        }

        view4.setOnClickListener {
            startActivity(Intent(this, MotionLayoutActivity::class.java))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putFloat(SCALE_X_KEY, animatedView.scaleX)
        outState.putFloat(SCALE_Y_KEY, animatedView.scaleY)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        animatedView.scaleX = savedInstanceState.getFloat(SCALE_X_KEY)
        animatedView.scaleY = savedInstanceState.getFloat(SCALE_Y_KEY)
    }

    private fun getObjectAnimatorScaleValue(scaleValue: Float): Float {
        return if (scaleValue == INITIAL_ANIMATED_VALUE) {
            BIG_SCALE_VALUE
        } else INITIAL_ANIMATED_VALUE
    }

    private fun updateEnabledBtnState(isEnabled: Boolean) {
        bigScaleAnimationBtn.isEnabled = isEnabled
    }

    override fun onPause() {
        super.onPause()

        animatorSet.pause()
    }

    override fun onResume() {
        super.onResume()

        animatorSet.resume()
    }
}