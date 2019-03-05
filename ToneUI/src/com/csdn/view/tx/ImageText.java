package com.csdn.view.tx;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csdn.view.R;



public class ImageText extends LinearLayout {



    private TextView textView;
    private ImageView img;

    private String text;
    private int draw,top, text_size,text_color,img_height,img_width;

    public ImageText(Context context) {
        super(context);

    }

    public ImageText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public ImageText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }


    public void init(Context context, AttributeSet attrs){
        LinearLayout.LayoutParams layoutParams =   new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(layoutParams);
        this.setOrientation(LinearLayout.VERTICAL);

        textView=new TextView(getContext());
        img=new ImageView(getContext());
        this.addView(img);
        this.addView(textView);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.imagetext, 0, 0);
       draw=a.getResourceId(R.styleable.imagetext_imgT_img,R.drawable.ic_launcher);
       top=a.getDimensionPixelSize(R.styleable.imagetext_imgT_top,20);
       text=a.getString(R.styleable.imagetext_imgT_text);

        text_size=a.getDimensionPixelSize(R.styleable.imagetext_imgT_size,20);
        text_color=a.getColor(R.styleable.imagetext_imgT_color,20);
        img_height=a.getDimensionPixelSize(R.styleable.imagetext_imgT_height,20);
        img_width=a.getDimensionPixelSize(R.styleable.imagetext_imgT_width,20);

        ViewGroup.LayoutParams imgp = this.getLayoutParams();

        imgp.height=img_height;
        imgp.width=img_width;
        img.setLayoutParams(imgp);

        textView.setText(text);
        textView.setTextColor(text_color);
        textView.setTextSize(text_size);
        textView.setSingleLine(true);
        LinearLayout.LayoutParams ls =   new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        ls.setMargins(0,top,0,0);
       textView.setLayoutParams(ls);
        img.setImageResource(draw);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
}
