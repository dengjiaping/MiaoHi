package com.haiqiu.miaohi.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ProgressBar;


public class SoftUpdateView extends ProgressBar {
    private String text;
    private Paint mPaint;
    private boolean isTextVisibility = true;

    public SoftUpdateView(Context context) {
        super(context);
        initText();
    }

    public SoftUpdateView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initText();
    }

    public SoftUpdateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initText();
    }

    @Override
    public void setProgress(int progress) {
        setText(progress);
        super.setProgress(progress);

    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect = new Rect();
        this.mPaint.getTextBounds(this.text, 0, this.text.length(), rect);
        int x = (getWidth() / 2) - rect.centerX();
        int y = (getHeight() / 2) - rect.centerY();
        canvas.drawText(this.text, x, y, this.mPaint);
    }

    // 初始化，画笔
    private void initText() {
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setTextSize(20);
        this.mPaint.setColor(Color.WHITE);

    }

    // 设置文字内容
    @SuppressLint("DefaultLocale")
    private void setText(int progress) {
        if (isTextVisibility) {
            float percent = (progress * 1.0f / this.getMax()) * 100;
            this.text = String.format("%.2f%%", percent);
        } else {
            this.text = "";
        }

    }

    public void setTextVisibility(boolean isTextVisibility) {
        this.isTextVisibility = isTextVisibility;
    }

}
