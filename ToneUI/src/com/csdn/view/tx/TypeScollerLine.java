package com.csdn.view.tx;

import android.app.TabActivity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.csdn.view.tx.NewsTitleTextView;


public class TypeScollerLine extends HorizontalScrollView  implements View.OnClickListener {

    private String[] types;
    public static String SCOLLER="SCOLLER";
    public static String  KEEPWIDTH="KEEPWIDTH";

    private String Type="";

    public void setType(String type) {
        Type = type;
    }

    //获取屏幕的宽度
    public  int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }

    public void setTypes(String[] types) {
        this.types = types;




        for(int i=0;i<types.length;i++){
            NewsTitleTextView tv=new NewsTitleTextView(getContext());
            int itemWidth = (int) tv.getPaint().measureText(types[i]);
            if(SCOLLER.equals(Type)){
                tv.setLayoutParams(new LinearLayout.LayoutParams((itemWidth * 3/2),
                        -1));
                LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                mLayout.setLayoutParams(layoutParams);
            }else if(KEEPWIDTH.equals(Type)){
                tv.setWidth(getScreenWidth(getContext())/types.length);
            }

            tv.setTextSize(14);
            tv.setPadding(10,15,10,15);
            tv.setGravity(Gravity.CENTER);



            tv.setText(types[i]);

            tv.setOnClickListener(this);
            mLayout.addView(tv);
            if (i == 0) {
                tv.setTextColor(Color.GREEN);
                tv.setIsHorizontaline(true);
            } else {
                tv.setTextColor(Color.BLACK);
                tv.setIsHorizontaline(false);
            }
        }
    }
    public String[] getTypes() {
        return types;
    }

    private LinearLayout mLayout;
    public TypeScollerLine(Context context) {
        super(context);

        init();
    }

    public TypeScollerLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TypeScollerLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }



    public void init(){
        this.setFillViewport(true);
        this.setWillNotDraw(false);
        this.setHorizontalScrollBarEnabled(false);
        mLayout=new LinearLayout(getContext());
        mLayout.setOrientation(LinearLayout.HORIZONTAL);

        addView(mLayout);
    }

    @Override
    public void onClick(View view) {
        for(int i=0;i<types.length;i++){
            NewsTitleTextView child = (NewsTitleTextView) mLayout.getChildAt(i);
            if (child == view) {
                //Toast.makeText(getContext(),i+"",Toast.LENGTH_SHORT).show();
                child.setTextColor(Color.GREEN);
                child.setIsHorizontaline(true);
            }else {
                child.setTextColor(Color.BLACK);
                child.setIsHorizontaline(false);

                //      Toast.makeText(getContext(),"2222222222",Toast.LENGTH_SHORT).show();
            }
        }
        invalidate();
    }


}
