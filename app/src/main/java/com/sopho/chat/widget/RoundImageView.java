package com.sopho.chat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.sopho.chat.R;

/**
 * author: kang4
 * Date: 2019/5/30
 * Description:
 */
public class RoundImageView extends android.support.v7.widget.AppCompatImageView {

    private Paint mPaint;
    private Rect mBound;

    public RoundImageView(Context context) {
        super(context);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyCustomView);
        array.recycle();
        init();
    }

    public void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        //mPaint.setColor(titleBackgroundColor);
        canvas.drawCircle(getWidth() / 2f, getWidth() / 2f, getWidth() / 2f, mPaint);
        // mPaint.setColor(titleColor);
        // canvas.drawText(titleText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
        Bitmap bitmap1=((BitmapDrawable)drawable).getBitmap();
        canvas.drawCircle(getWidth()/2f,getWidth()/2f,getWidth()/2f,mPaint);


    }


}
