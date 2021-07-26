package com.example.presentation.commonView

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.example.presentation.R
import java.util.concurrent.TimeUnit


class TimerView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private var circleColor: Int = Color.RED
    private var circleRadius: Int = 30

    private lateinit var mBitmap: Bitmap
    private lateinit var mCanvas: Canvas

    private lateinit var mCircleOuterBounds: RectF
    private lateinit var mCircleInnerBounds: RectF

    private var circlePaint: Paint = Paint().apply {
        isAntiAlias = true
        color = circleColor
    }

    private var mTimerAnimator: ValueAnimator? = null

    init {
        if (attrs != null) {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.TimerView);
            circleColor = ta.getColor(R.styleable.TimerView_circle_color, circleColor)
            circleRadius =
                ta.getDimensionPixelOffset(R.styleable.TimerView_circle_radius, circleRadius)
            ta.recycle()
            circlePaint = Paint().apply {
                isAntiAlias = true
                color = circleColor
            }
        }
    }

    private var textPaint: Paint = Paint().apply {
        isAntiAlias = true
        color = circleColor
        textSize = TEXT_SIZE
        textAlign = Paint.Align.CENTER
    }
    private var mEraserPaint: Paint = Paint().apply {
        isAntiAlias = true
        color = Color.TRANSPARENT
        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }
//
//
//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        if (w != oldw || h != oldh) {
            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
            mBitmap.eraseColor(Color.TRANSPARENT)
            mCanvas = Canvas(mBitmap)
        }

        super.onSizeChanged(w, h, oldw, oldh)
        textPaint.textSize = width * TEXT_SCALE
        updateBounds()
    }

    private fun updateBounds() {
        val thickness = width * THICKNESS_SCALE
        mCircleOuterBounds = RectF(0F, 0F, width.toFloat(), height.toFloat())
        mCircleInnerBounds = RectF(
            mCircleOuterBounds.left + thickness,
            mCircleOuterBounds.top + thickness,
            mCircleOuterBounds.right - thickness,
            mCircleOuterBounds.bottom - thickness
        )
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        mCanvas.drawColor(0, PorterDuff.Mode.CLEAR)

        if (mCircleSweepAngle > 0f) {
            mCanvas.drawArc(
                mCircleOuterBounds,
                ARC_START_ANGLE,
                mCircleSweepAngle,
                true,
                circlePaint
            )
            mCanvas.drawOval(mCircleInnerBounds, mEraserPaint)
        }


        val timerDuration = mTimerAnimator?.duration
        val currentTime = mTimerAnimator?.currentPlayTime
        if (timerDuration != null && currentTime != null) {
            val timeToEnd = timerDuration - currentTime
            val minutes = TimeUnit.MILLISECONDS.toMinutes(timeToEnd)
            val seconds = TimeUnit.MILLISECONDS.toSeconds(timeToEnd)
            val timerText = "$minutes:$seconds"
            val yPos = (mCircleInnerBounds.centerX() - (textPaint.descent() + textPaint.ascent()) / 2).toInt()
            canvas.drawText(
                timerText,
                mCircleInnerBounds.centerX(),
                yPos.toFloat(),
                textPaint
            )
        }
        canvas.drawBitmap(mBitmap, 0f, 0f, null)
    }

    fun start(secs: Long) {
        stop()
        mTimerAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
            duration = TimeUnit.SECONDS.toMillis(secs)
            interpolator = LinearInterpolator()
            addUpdateListener { animation ->
                drawProgress(
                    animation.animatedValue as Float
                )
            }
            start()
        }
    }

    fun stop() {
        if (mTimerAnimator?.isRunning == true) {
            mTimerAnimator?.cancel()
            mTimerAnimator?.removeAllListeners()
            mTimerAnimator = null
            drawProgress(0f)
        }
    }

    private fun drawProgress(progress: Float) {
        mCircleSweepAngle = 360 * progress
        invalidate()
    }

    companion object {
        private const val ARC_START_ANGLE = 270F // 12 o'clock
        private const val THICKNESS_SCALE = 0.1f
        private const val TEXT_SCALE = 0.2f
        private const val TEXT_SIZE = 90f
        private var mCircleSweepAngle = 0f

    }
}