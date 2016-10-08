package com.lava.viewgroupdemo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

public class DrawBitmapView extends View {
    private Resources mResources;
    private Paint mBitPaint;
    private Bitmap mBitmap;
    private Rect mSrcRect, mDestRect;
    private Context mContext;
    private int mStartLeft, mStartTop, mToLeft, mToTop, mBitWidth, mBitHeight;

    // view 的宽高
    private int mTotalWidth, mTotalHeight;


    public DrawBitmapView(Context context) {
        this(context, null);
    }

    public DrawBitmapView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawBitmapView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public DrawBitmapView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        mResources = getResources();
        initBitmap();
        initPaint();
    }

    private void initPaint() {
        mBitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitPaint.setFilterBitmap(true);
        mBitPaint.setDither(true);
    }

    private void initBitmap() {
        mBitmap = ((BitmapDrawable) mResources.getDrawable(R.drawable.movie)).getBitmap();
        mBitWidth = mBitmap.getWidth();
        mBitHeight = mBitmap.getHeight();

        mSrcRect = new Rect(0, 0, mBitWidth, mBitHeight);
        mDestRect = new Rect(0, 0, mBitWidth, mBitHeight);

        // 无法获取屏幕宽高 720x1080
        int left = 360 - mBitWidth / 2;
        int top = 500 - mBitHeight / 2;

        mDestRect = new Rect(left, top, left + mBitWidth, top + mBitHeight);
    }

    public void startTranslate() {
        startTranslate(0, 0, 200, 200, 2000);
    }

    /**
     * 移动位图
     *
     * @param startLeft 起始左边距
     * @param startTop  起始距上边距离
     * @param toLeft    到达左边距
     * @param toTop     到达上边距
     * @param duration  时长
     */
    public void startTranslate(int startLeft, int startTop, int toLeft, int toTop, long duration) {
        mStartLeft = startLeft;
        mStartTop = startTop;

        mToLeft = toLeft;
        mToTop = toTop;

        // 使用ValueAnimator创建一个过程
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(duration);
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                // 不断重新计算上下左右位置
                float fraction = (Float) animator.getAnimatedValue();
                int currentLeft = (int) ((mToLeft - mStartLeft) * fraction + mStartLeft);
                int currentTop = (int) ((mToTop - mStartTop) * fraction + mStartTop);
                if (mDestRect == null) {
                    mDestRect = new Rect(currentLeft, currentTop, currentLeft + mBitWidth, currentTop + mBitHeight);
                }
                mDestRect.left = currentLeft;
                mDestRect.right = currentLeft + mBitWidth;
                mDestRect.top = currentTop;
                mDestRect.bottom = currentTop + mBitHeight;
                // 重绘
                postInvalidate();
            }
        });
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, mSrcRect, mDestRect, mBitPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTotalWidth = w;
        mTotalHeight = h;
    }
}