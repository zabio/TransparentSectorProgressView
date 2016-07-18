package com.hy.tspv;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by henry  16/4/28.
 */
public class Tspv extends View {
    private int svp_color;
    private Paint paint;
    private RectF oval;


    private float startAngle;
    private float sweepAngle;
    private int svp_percent;


    public Tspv(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Tspv, 0, 0);

        try {
            svp_color = a.getColor(R.styleable.Tspv_tspv_color, 60000000);
            svp_percent = a.getInteger(R.styleable.Tspv_tspv_percent, 0);

        } finally {
            a.recycle();
        }

        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(svp_color);
        updateArgs(svp_percent * 3.6f);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        float xp = (float) (getPaddingLeft() + getPaddingRight());
        float yp = (float) (getPaddingBottom() + getPaddingTop());

        float wwd = (float) w - xp;
        float hhd = (float) h - yp;

        oval = new RectF(getPaddingLeft(), getPaddingTop(), getPaddingLeft() + wwd, getPaddingTop() + hhd);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawArc(oval, startAngle, sweepAngle, true, paint);
    }


    private void refreshTheLayout() {
        invalidate();
        requestLayout();
    }


    public void setPercent(int percent) {
        final float degree = percent * 3.6f;
        post(new Runnable() {
            @Override
            public void run() {
                updateArgs(degree);
                refreshTheLayout();

            }
        });
    }


    private void updateArgs(float degree) {
        startAngle = degree + 270f;
        if (startAngle >= 360f) {
            startAngle -= 360f;
            sweepAngle = 270f - startAngle;
        } else {
            sweepAngle = 360f - degree;
        }
    }

}