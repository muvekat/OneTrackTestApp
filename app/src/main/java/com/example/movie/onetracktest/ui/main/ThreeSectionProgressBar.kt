package com.example.movie.onetracktest.ui.main

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.View
import com.example.movie.onetracktest.R

class ThreeSectionProgressBar : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attributeSet,
        defStyleAttr,
        defStyleRes
    )

    private val roundRadius = 10.0f
    private val separatorWidth = 10.0f
    private val firstProgressColor = Paint().apply {
        color = resources.getColor(R.color.first_progress, null)
    }
    private val secondProgressColor = Paint().apply {
        color = resources.getColor(R.color.second_progress, null)
    }
    private val thirdProgressColor = Paint().apply {
        color = resources.getColor(R.color.third_progress, null)
    }
    private val backgroundColor = Paint()

    var firstProgress: Int = 0
        set(value) {
            field = value
            invalidate()
        }
    var secondProgress: Int = 0
        set(value) {
            field = value
            invalidate()
        }
    var thirdProgress: Int = 0
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val backgroundColorDrawable = background as ColorDrawable?
        backgroundColor.color = backgroundColorDrawable?.color ?: resources.getColor(R.color.colorPrimaryDark, null)

        val totalProgress = firstProgress + secondProgress + thirdProgress

        if (totalProgress > 0) {
            var firstRight = 0.0f
            var secondRight = 0.0f
            var thirdRight = 0.0f

            if (firstProgress > 0) {
                firstRight = 1.0f * firstProgress / totalProgress * width
                canvas?.drawRoundRect(
                    0.0f,
                    0.0f,
                    firstRight,
                    height.toFloat(),
                    roundRadius,
                    roundRadius,
                    firstProgressColor
                )
            }

            if (secondProgress > 0) {
                secondRight = firstRight + (1.0f * secondProgress / totalProgress * width)
                canvas?.drawRoundRect(
                    firstRight,
                    0.0f,
                    secondRight,
                    height.toFloat(),
                    roundRadius,
                    roundRadius,
                    secondProgressColor
                )
            }

            if (thirdProgress > 0) {
                thirdRight = secondRight + (1.0f * thirdProgress / totalProgress * width)
                canvas?.drawRoundRect(
                    secondRight,
                    0.0f,
                    thirdRight,
                    height.toFloat(),
                    roundRadius,
                    roundRadius,
                    thirdProgressColor
                )
            }

            if (firstRight > 0 && (secondRight > 0 || thirdRight > 0)){
                val left = firstRight - (separatorWidth/2)
                val right = firstRight + (separatorWidth/2)
                canvas?.drawRect(left, 0.0f, right, height.toFloat(), backgroundColor)
            }
            if (secondRight > 0 && thirdRight > 0){
                val left = secondRight - (separatorWidth/2)
                val right = secondRight + (separatorWidth/2)
                canvas?.drawRect(left, 0.0f, right, height.toFloat(), backgroundColor)
            }
        }
    }


}