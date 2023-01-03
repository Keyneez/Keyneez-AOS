package com.keyneez.util.custom

import android.content.Context
import android.graphics.* // ktlint-disable no-wildcard-imports
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.lab.keyneez.R

class HorizontalCardFrameView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val path = Path()
    private val rect = RectF()

    private val stroke = Paint().apply {
        isAntiAlias = false
        strokeWidth = 4f
        color = ContextCompat.getColor(context, R.color.gray050)
        style = Paint.Style.STROKE
    }

    private val eraser = Paint().apply {
        isAntiAlias = true
        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    private val holeBorderRadius = 36f

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawHole(requireNotNull(canvas))
        drawBorder(canvas)
    }

    private fun drawBorder(canvas: Canvas) {
        path.rewind()
        path.addRoundRect(
            rect.apply {
                setRect(4f/*border width*/)
            },
            holeBorderRadius,
            holeBorderRadius,
            Path.Direction.CW
        )

        canvas.drawPath(path, stroke)
    }

    private fun drawHole(canvas: Canvas) {
        canvas.drawRoundRect(
            rect.apply {
                setRect()
            },
            holeBorderRadius,
            holeBorderRadius,
            eraser
        )
    }

    private fun setRect(offset: Float = 4f) {
        val holeWidth = 327 * (width / 360f)
        val holeHeight = 200 * (width / 360f)

        rect.set(
            ((width - holeWidth) / 2) - offset,
            ((height - holeHeight) / 2) - offset,
            ((width - holeWidth) / 2 + holeWidth) + offset,
            ((height - holeHeight) / 2 + holeHeight) + offset
        )
    }
}
