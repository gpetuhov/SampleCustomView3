package com.gpetuhov.android.samplecustomview3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.animation.ValueAnimator
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animateToLog.setOnClickListener { animateToLog() }
    }

    private fun animateToLog() {
        // ValueAnimator can animate any value.
        // Here ValueAnimator outputs a sequence of numbers
        // from 0 to 100 during the period of 2 seconds.
        val animator = ValueAnimator.ofInt(0, 100)
        animator?.duration = 2000
        animator?.addUpdateListener { animation ->
            val value = animation.animatedValue as Int
            Timber.d("Animated value = $value")
        }
        animator?.start()
    }
}
