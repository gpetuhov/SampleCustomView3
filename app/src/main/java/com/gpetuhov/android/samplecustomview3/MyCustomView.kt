package com.gpetuhov.android.samplecustomview3

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import timber.log.Timber


class MyCustomView : View {

    private var paint = Paint(ANTI_ALIAS_FLAG)
    private var radius = 0

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        if (attrs != null) {
            val attributesArray = context.obtainStyledAttributes(attrs, R.styleable.MyCustomView)
            paint.color = attributesArray?.getColor(R.styleable.MyCustomView_square_color, Color.GREEN) ?: Color.GREEN
            attributesArray.recycle()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val viewWidth = width / 2
        val viewHeight = height / 2

        val leftTopX = viewWidth - 150
        val leftTopY = viewHeight - 150

        val rightBotX = viewWidth + 150
        val rightBotY = viewHeight + 150

        canvas?.drawRoundRect(
            leftTopX.toFloat(),
            leftTopY.toFloat(),
            rightBotX.toFloat(),
            rightBotY.toFloat(),
            radius.toFloat(),
            radius.toFloat(),
            paint
        )
    }

    fun animateToCircle() {
        val animator = ValueAnimator.ofInt(0, 150)
        animator?.duration = 2000
        animator?.addUpdateListener { animation ->
            radius = animation.animatedValue as Int
            invalidate()
        }
        animator?.start()
    }
}