package com.csdn.view.tx;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;  
import android.graphics.Paint;  
import android.util.AttributeSet;  
import android.widget.EditText;

import com.csdn.view.R;


@SuppressLint("AppCompatCustomView")
public class LineEditText extends EditText {
  
    private Paint mPaint;
    private int color;
    /** 
     * @param context 
     * @param attrs 
     */  
    public LineEditText(Context context, AttributeSet attrs) {  
        super(context, attrs);
        this.setBackground(null);
        init(context, attrs,0);
        // TODO Auto-generated constructor stub  
        mPaint = new Paint();
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);  
        mPaint.setColor(color);


    }





    private void init(Context context, AttributeSet attrs, int defStyle) {
        // TypeArray中含有我们需要使用的参数
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.edit_line, defStyle, 0);
     color=a.getColor(R.styleable.edit_line_edit_lineColor,Color.LTGRAY);
    }

    @Override
    public void onDraw(Canvas canvas)  
    {  
        super.onDraw(canvas);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
//      画底线  
       canvas.drawLine(0,this.getHeight()-1,  this.getWidth()-1, this.getHeight()-1, mPaint);
    }  
}  