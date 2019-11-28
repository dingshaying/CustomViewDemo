package com.example.customviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

public class SimpleImageView2 extends View {
    private Paint paint;
    private Drawable drawable;
    private int width;
    private int height;
    private Bitmap mBitmap;

    public SimpleImageView2(Context context) {
        this(context,null);
    }

    public SimpleImageView2(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public SimpleImageView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
        //根据属性初始化
        initAttrs(attrs);
        paint = new Paint();
        paint.setAntiAlias(true);
    }
    private void initAttrs(AttributeSet attrs) {
        if (attrs != null){
            TypedArray array = null;
            try {
                //获取定义的属性
                array = getContext().obtainStyledAttributes(attrs,R.styleable.SimpleImageView);
                //根据图片id获取drawable对象
                drawable = array.getDrawable(R.styleable.SimpleImageView_src);
                //测量Drawable对象的宽高
                measureDrawable();
            }finally {
                if (array != null){
                    array.recycle();
                }
            }
        }
    }

    private void measureDrawable() {
        if (drawable == null){
            throw new RuntimeException("drawable不能为空");
        }
        width = drawable.getIntrinsicWidth();
        height = drawable.getIntrinsicHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取宽度的模式与大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        //高度的模式与大小
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        //设置View的宽度
        setMeasuredDimension(measuredWidth(widthMode,width),measureHeight(heightMode,height));
    }

    private int measureHeight(int heightMode, int height) {
        switch (heightMode){
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.EXACTLY:
                this.height =height;
                break;
        }
        return height;
    }

    private int measuredWidth(int widthMode, int width) {
        switch (widthMode){
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.EXACTLY:
                this.width =width;
                break;
        }
        return width;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBitmap == null){
            mBitmap = Bitmap.createScaledBitmap(
                    ImageUtils.drawableToBitmap(drawable),getMeasuredWidth(),
                    getMeasuredHeight(),true
            );
        }
        //绘制图片
        canvas.drawBitmap(mBitmap,getLeft(),getTop(),paint);
        //保存画布状态

        canvas.save();
        //旋转90度
        canvas.rotate(90);
        paint.setColor(Color.BLUE);
        paint.setTextSize(50);
        //绘制文本
        canvas.drawText("埃菲尔铁塔",getLeft()+50,getTop()-200,paint);
        //恢复原来的状态
        canvas.restore();
    }
}
