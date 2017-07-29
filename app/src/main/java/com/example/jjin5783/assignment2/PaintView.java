package com.example.jjin5783.assignment2;

/**
 * Created by jjin5783 on 7/9/2017.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;


public class PaintView extends View {
    //set paintView attributes
    public static int BRUSH_SIZE = 20;
    public static final int DEFAULT_COLOR = Color.RED;
    public static final int DEFAULT_BG_COLOR = Color.WHITE;
    private static final float TOUCH_TOLERANCE = 4;
   //set location
    private float mX, mY;
    private Path mPath;
    private Paint mPaint;
    //crate an array to put paint
    private ArrayList<FingerPath> paths = new ArrayList<>();
    //initialize brush attributes
    private int currentColor;
    private int backgroundColor = DEFAULT_BG_COLOR;
    private int strokeWidth;
    private boolean emboss;
    private boolean blur;
    private MaskFilter mEmboss;
    private MaskFilter mBlur;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mBitmapPaint = new Paint(Paint.DITHER_FLAG);

    //implement class
    public PaintView(Context context) {
        this(context, null);
    }
    //create paint methods
    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //add values in paint variables
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(DEFAULT_COLOR);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setXfermode(null);
        mPaint.setAlpha(0xff);
        mEmboss = new EmbossMaskFilter(new float[] {1, 1, 1}, 0.4f, 6, 3.5f);
        mBlur = new BlurMaskFilter(5, BlurMaskFilter.Blur.NORMAL);
    }
    //diaplay method
    public void init(DisplayMetrics metrics) {
        //create paint metrics
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;
        //initialize butmap and draw on the canvas
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        //add color and width
        currentColor = DEFAULT_COLOR;
        strokeWidth = BRUSH_SIZE;
    }

    //
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        mCanvas.drawColor(backgroundColor);

        for (FingerPath fp : paths) {
            mPaint.setColor(fp.color);
            mPaint.setStrokeWidth(fp.strokeWidth);
            mPaint.setMaskFilter(null);

            if (fp.emboss)
                mPaint.setMaskFilter(mEmboss);
            else if (fp.blur)
                mPaint.setMaskFilter(mBlur);

            mCanvas.drawPath(fp.path, mPaint);

        }

        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        canvas.restore();
    }
    //get the position when touching the screen
    private void touchStart(float x, float y) {
        //create path and save fingerpath into it
        mPath = new Path();
        FingerPath fp = new FingerPath(currentColor, emboss, blur, strokeWidth, mPath);
        paths.add(fp);
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }
    //add movepath into path when move the finger on the screen
    private void touchMove(float x, float y) {
        //create float variable to calculate location changes
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }
    //
    private void touchUp() {
        mPath.lineTo(mX, mY);
    }
    //create touch event to realize touch and draw function
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //create float variable to get position
        float x = event.getX();
        float y = event.getY();
        //catch finger state
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                touchStart(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE :
                touchMove(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP :
                touchUp();
                invalidate();
                break;
        }

        return true;
    }
}