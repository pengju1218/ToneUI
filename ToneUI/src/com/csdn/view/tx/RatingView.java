package com.csdn.view.tx;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.csdn.view.R;


/**
 * Created by zsd on 2017/8/7 11:50
 * desc:评分控件 仿淘宝,京东
 */

public class RatingView extends View {
    private Bitmap mStarNormal, mStarSelected;
    private int mStarNumbers = 5;//数量
    private int mStarSpaceing = 5;//间距
    private int mCurrentStar = 0;//当前星星


    public RatingView(Context context) {
        this(context, null);
    }

    public RatingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RatingView);
        int starNormalId = array.getResourceId(R.styleable.RatingView_starNormal, 0);
        if (starNormalId == 0) {
            throw new RuntimeException("请在xml文件中为RatingView设置startNormal属性");
        }
        mStarNormal = BitmapFactory.decodeResource(getResources(), starNormalId);
        int starSelectedId = array.getResourceId(R.styleable.RatingView_starSelected, 0);
        if (starSelectedId == 0) {
            throw new RuntimeException("请在xml文件中为RatingView设置startSelected属性");
        }
        mStarSelected = BitmapFactory.decodeResource(getResources(), starSelectedId);
        mStarNumbers = array.getInt(R.styleable.RatingView_starNumbers, mStarNumbers);
        mStarSpaceing = (int) array.getDimension(R.styleable.RatingView_starSpace, mStarSpaceing);
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //控件高度为图片的高度加上上下的padding值
        int height = mStarNormal.getHeight() + getPaddingTop() + getPaddingBottom();
        //控件的宽高为五张图片的宽度，加上左右的padding值，然后加上每张图片的左边间隔值，这里每张图片都会有一个左边间隔，为了控件美观右边多加了一个间隔
        int width = mStarNormal.getWidth() * mStarNumbers + (mStarNumbers + 1) * mStarSpaceing + getPaddingLeft() + getPaddingRight();
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //循环的来画星星
        for (int i = 0; i < mStarNumbers; i++) {
            int x = getPaddingLeft() + i * mStarNormal.getWidth() + (i + 1) * mStarSpaceing;
            int top = getPaddingTop();
            if (mCurrentStar > i) {
                // 当前分数之前
                canvas.drawBitmap(mStarSelected, x, top, null);
            } else {
                canvas.drawBitmap(mStarNormal, x, top, null);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float moveX = event.getX();
                //计算当前手指在哪个星星
                int currentStar = (int) ((moveX - getPaddingLeft()) / (mStarNormal.getWidth() + mStarSpaceing) + 1);
                // 范围问题
                if (currentStar < 0) {
                    currentStar = 0;
                }
                if (currentStar > mStarNumbers) {
                    currentStar = mStarNumbers;
                }
                // 分数相同的情况下不要绘制了 , 尽量减少onDraw()的调用
                if (currentStar == mCurrentStar) {
                    return true;
                }

                // 再去刷新显示
                mCurrentStar = currentStar;
                invalidate();//重新绘制会调用onDraw()
                break;
        }
        return true;
    }
}
