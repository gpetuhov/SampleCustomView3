package com.gpetuhov.android.samplecustomview3

import android.content.Context
import android.graphics.Canvas
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.Rect


class MyCustomView : View {

    private var paint = Paint(ANTI_ALIAS_FLAG)
    private var rect = Rect()
    private var squareColor = Color.MAGENTA
    private var padding = 0

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
            squareColor = attributesArray?.getColor(R.styleable.MyCustomView_square_color, Color.GREEN) ?: Color.GREEN
            paint.color = squareColor
            attributesArray.recycle()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        rect.left = 0 + padding
        rect.right = width - padding
        rect.top = 0 + padding
        rect.bottom = height - padding

        canvas?.drawRect(rect, paint)
    }
}