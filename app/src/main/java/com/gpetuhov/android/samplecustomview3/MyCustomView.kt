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
import android.animation.PropertyValuesHolder




class MyCustomView : View {

    companion object {
        const val PROPERTY_RADIUS = "propertyRadius"
        const val PROPERTY_ROTATE = "propertyRotate"
    }

    private var paint = Paint(ANTI_ALIAS_FLAG)
    private var radius = 0
    private var rotate = 0
    private var animator: ValueAnimator? = null

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

        // Rotate canvas before drawing
        canvas?.rotate(
            rotate.toFloat(),
            viewWidth.toFloat(),
            viewHeight.toFloat()
        )

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
        // Do nothing if animator is already running
        if (animator?.isRunning == true) {
            return
        }

        // One animator can animate more than one property by using PropertyValuesHolder
        val propertyRadius = PropertyValuesHolder.ofInt(PROPERTY_RADIUS, 0, 150)
        val propertyRotate = PropertyValuesHolder.ofInt(PROPERTY_ROTATE, 0, 360)

        animator = ValueAnimator()

        // Here we set animater to animate 2 properties
        animator?.setValues(propertyRadius, propertyRotate)

        animator?.duration = 2000
        animator?.addUpdateListener { animation ->
            // Get current value of the animated properties
            radius = animation.getAnimatedValue(PROPERTY_RADIUS) as Int
            rotate = animation.getAnimatedValue(PROPERTY_ROTATE) as Int

            // Invalidate view to update it with new radius and rotate values
            invalidate()
        }
        animator?.start()
    }
}