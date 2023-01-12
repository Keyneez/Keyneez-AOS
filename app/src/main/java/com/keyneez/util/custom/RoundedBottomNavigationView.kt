package com.keyneez.util.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.keyneez.util.extension.dpToPx
import com.lab.keyneez.R

class RoundedBottomNavigationView : BottomNavigationView {
    private var mPath: Path = Path()
    private var mPaint: Paint = Paint()

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context) : super(context) {
        init()
    }

    private fun init() {
        mPaint.style = Paint.Style.FILL_AND_STROKE
        mPaint.color = ContextCompat.getColor(context, R.color.gray050)
        setBackgroundColor(Color.TRANSPARENT)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val mNavigationBarWidth = width.toFloat() // whole width : 360dp
        val mNavigationBarHeight = (height + context.dpToPx(30)).toFloat() // 84dp
        val holeWidth = width * 0.21866666f // 상대크기 : 15
        val holeHeight = height * 0.27380952f // 상대크기 : 26
        val controlInterval = holeWidth / 15f // 상대 크기 : 1.5

        mPath.moveTo(0f, 0f)

        // first Curve Start, controlPoint1, controlPoint2, End
        val firstCurveStartPoint = Pair((width - holeWidth) / 2, 0f)
        val firstCurveEndPoint = Pair(width * 0.5f, holeHeight)
        val firstCurveControlPoint1 =
            Pair(firstCurveStartPoint.first + controlInterval, 0f)
        val firstCurveControlPoint2 =
            Pair(firstCurveStartPoint.first + controlInterval, holeHeight)
        // second Curve Start, controlPoint1, controlPoint2, End
        val secondCurveEndPoint = Pair((width + holeWidth) / 2, 0f)
        val secondCurveControlPoint1 =
            Pair(secondCurveEndPoint.first - controlInterval, holeHeight)
        val secondCurveControlPoint2 =
            Pair(secondCurveEndPoint.first - controlInterval, 0f)

        mPath.reset()
        mPath.moveTo(0F, 0F)
        mPath.lineTo(firstCurveStartPoint.first, firstCurveStartPoint.second)

        // first curve
        mPath.cubicTo(
            firstCurveControlPoint1.first,
            firstCurveControlPoint1.second,
            firstCurveControlPoint2.first,
            firstCurveControlPoint2.second,
            firstCurveEndPoint.first,
            firstCurveEndPoint.second
        )
        // second curve
        mPath.cubicTo(
            secondCurveControlPoint1.first,
            secondCurveControlPoint1.second,
            secondCurveControlPoint2.first,
            secondCurveControlPoint2.second,
            secondCurveEndPoint.first,
            secondCurveEndPoint.second
        )

        mPath.lineTo(mNavigationBarWidth, 0F)
        mPath.lineTo(mNavigationBarWidth, mNavigationBarHeight.toFloat())
        mPath.lineTo(0F, mNavigationBarHeight.toFloat())
        mPath.close()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(mPath, mPaint)
    }
}
