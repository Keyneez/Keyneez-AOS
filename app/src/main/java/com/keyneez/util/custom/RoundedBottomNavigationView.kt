package com.keyneez.util.custom

import android.content.Context
import android.graphics.* // ktlint-disable no-wildcard-imports
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lab.keyneez.R

class RoundedBottomNavigationView : BottomNavigationView {
    private var mPath: Path = Path()
    private var mPaint: Paint = Paint()

    private val CURVE_CIRCLE_RADIUS = 190 / 2

    private val mFirstCurveStartPoint = Point()
    private val mFirstCurveEndPoint = Point()
    private val mFirstCurveControlPoint1 = Point()
    private val mFirstCurveControlPoint2 = Point()

    private var mSecondCurveStartPoint = Point()
    private val mSecondCurveEndPoint = Point()
    private val mSecondCurveControlPoint1 = Point()
    private val mSecondCurveControlPoint2 = Point()
    private var mNavigationBarWidth: Int = 0
    private var mNavigationBarHeight: Int = 0

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
        mPaint.color = ContextCompat.getColor(context, R.color.gray500)
        setBackgroundColor(Color.TRANSPARENT)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        mNavigationBarWidth = width
        mNavigationBarHeight = height

        mFirstCurveStartPoint.set( // CURVE_CIRCLE_RADIUS 굴곡담당
            (mNavigationBarWidth / 1.92 - CURVE_CIRCLE_RADIUS * 2 - CURVE_CIRCLE_RADIUS / 3).toInt(),
            0
        )
        mFirstCurveEndPoint.set(
            (mNavigationBarWidth / 2),
            CURVE_CIRCLE_RADIUS + CURVE_CIRCLE_RADIUS / 4
        )
        mSecondCurveStartPoint = mFirstCurveEndPoint
        mSecondCurveEndPoint.set(
            (mNavigationBarWidth / 2.1 + CURVE_CIRCLE_RADIUS * 2 + CURVE_CIRCLE_RADIUS / 3).toInt(),
            0
        )

        mFirstCurveControlPoint1.set(
            mFirstCurveStartPoint.x + CURVE_CIRCLE_RADIUS + CURVE_CIRCLE_RADIUS / 4,
            mFirstCurveStartPoint.y
        )

        mFirstCurveControlPoint2.set(
            (mFirstCurveEndPoint.x - CURVE_CIRCLE_RADIUS * 2.29 + CURVE_CIRCLE_RADIUS).toInt(),
            mFirstCurveEndPoint.y
        )

        mSecondCurveControlPoint1.set(
            (mSecondCurveStartPoint.x + CURVE_CIRCLE_RADIUS * 2.29 - CURVE_CIRCLE_RADIUS).toInt(),
            mSecondCurveStartPoint.y
        )
        mSecondCurveControlPoint2.set(
            mSecondCurveEndPoint.x - (CURVE_CIRCLE_RADIUS + CURVE_CIRCLE_RADIUS / 4),
            mSecondCurveEndPoint.y
        )

        mPath.reset()
        mPath.moveTo(0F, 0F)
        mPath.lineTo(mFirstCurveStartPoint.x.toFloat(), mFirstCurveStartPoint.y.toFloat())

        mPath.cubicTo(
            mFirstCurveControlPoint1.x.toFloat(),
            mFirstCurveControlPoint1.y.toFloat(),
            mFirstCurveControlPoint2.x.toFloat(),
            mFirstCurveControlPoint2.y.toFloat(),
            mFirstCurveEndPoint.x.toFloat(),
            mFirstCurveEndPoint.y.toFloat()
        )

        mPath.cubicTo(
            mSecondCurveControlPoint1.x.toFloat(),
            mSecondCurveControlPoint1.y.toFloat(),
            mSecondCurveControlPoint2.x.toFloat(),
            mSecondCurveControlPoint2.y.toFloat(),
            mSecondCurveEndPoint.x.toFloat(),
            mSecondCurveEndPoint.y.toFloat()
        )

        mPath.lineTo(mNavigationBarWidth.toFloat(), 0F)
        mPath.lineTo(mNavigationBarWidth.toFloat(), mNavigationBarHeight.toFloat())
        mPath.lineTo(0F, mNavigationBarHeight.toFloat())
        mPath.close()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(mPath, mPaint)
    }
}
