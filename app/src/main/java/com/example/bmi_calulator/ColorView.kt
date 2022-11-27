package com.example.bmi_calulator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class ColorView {

    class HelloView(context: Context?) : View(context) {

        private val rectPaint: Paint = Paint()
        private val textPaint: Paint = Paint()

        override fun onDraw(canvas: Canvas) {

            rectPaint.color = Color.GRAY

            textPaint.color = Color.YELLOW
            textPaint.textSize = 100F

            canvas.drawColor(Color.BLUE)
            canvas.drawRect(0F, 0F, width.toFloat(), height.toFloat(), rectPaint)
            /*canvas.drawText("Hello World", 100F, 100F, textPaint)*/
        }

    }

}