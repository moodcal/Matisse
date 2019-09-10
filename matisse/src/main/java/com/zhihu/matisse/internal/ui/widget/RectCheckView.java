package com.zhihu.matisse.internal.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;

public class RectCheckView extends CheckView {
    float smallRate = 0.3f;
    float largeRate = 0.7f;

    public RectCheckView(Context context) {
        super(context);
    }

    public RectCheckView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RectCheckView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float corner = 1.0f * mDensity;

        float w, smallPadding, largePadding;
        RectF rectF;

//        // draw outer and inner shadow
//        initShadowPaint();
//        float w = (STROKE_RADIUS + STROKE_WIDTH + SHADOW_WIDTH) * mDensity * 2 * 0.8f;
//        float smallPadding = (SIZE * mDensity - w) * smallRate;
//        float largePadding = (SIZE * mDensity - w) * largeRate;
//        RectF rectF = new RectF(largePadding, smallPadding, w+largePadding, w+smallPadding);
//        canvas.drawRoundRect(rectF, corner, corner, mShadowPaint);

        // draw white stroke
        w = STROKE_RADIUS * 2 * mDensity * 0.8f;
        smallPadding = (SIZE * mDensity - w) * smallRate;
        largePadding = (SIZE * mDensity - w) * largeRate;
        rectF = new RectF(largePadding, smallPadding, w+largePadding, w+smallPadding);
        canvas.drawRoundRect(rectF, corner, corner, mStrokePaint);

        // draw content
        if (mCountable) {
            if (mCheckedNum != UNCHECKED) {
                initBackgroundPaint();
                w = BG_RADIUS * 2 * mDensity * 0.8f;
                smallPadding = (SIZE * mDensity - w) * smallRate;
                largePadding = (SIZE * mDensity - w) * largeRate;
                rectF = new RectF(largePadding, smallPadding, w+largePadding, w+smallPadding);
                canvas.drawRoundRect(rectF, corner, corner, mBackgroundPaint);

                initTextPaint();
                String text = String.valueOf(mCheckedNum);
                int baseX = (int) (canvas.getWidth() - mTextPaint.measureText(text)) / 2;
                int baseY = (int) (canvas.getHeight() - mTextPaint.descent() - mTextPaint.ascent()) / 2;
                canvas.drawText(text, baseX, baseY, mTextPaint);
            }
        } else {
            if (mChecked) {
                initBackgroundPaint();
                w = BG_RADIUS * 2 * mDensity * 0.8f;
                smallPadding = (SIZE * mDensity - w) * smallRate;
                largePadding = (SIZE * mDensity - w) * largeRate;
                rectF = new RectF(largePadding, smallPadding, w+largePadding, w+smallPadding);
                canvas.drawRoundRect(rectF, corner, corner, mBackgroundPaint);

                mCheckDrawable.setBounds(getCheckRect());
                mCheckDrawable.draw(canvas);
            }
        }

        // enable hint
        setAlpha(mEnabled ? 1.0f : 0.5f);
    }

    // rect for drawing checked number or mark
    @Override
    protected Rect getCheckRect() {
        if (mCheckRect == null) {
//            int rectPadding = (int) (SIZE * mDensity / 2 - CONTENT_SIZE * mDensity / 2);
//            int offset = (int)(rectPadding*0.45);

            int w = (int)(CONTENT_SIZE * mDensity * 0.8f);
            int smallPadding = (int)((SIZE * mDensity - w) * smallRate);
            int largePadding = (int)((SIZE * mDensity - w) * largeRate);

            mCheckRect = new Rect(largePadding, smallPadding, w+largePadding, w+smallPadding);

        }

        return mCheckRect;
    }

}
