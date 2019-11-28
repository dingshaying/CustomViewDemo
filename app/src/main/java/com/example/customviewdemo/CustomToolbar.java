package com.example.customviewdemo;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CustomToolbar extends LinearLayout {
    private LinearLayout rootlayout;
    private ImageView ivBack;
    private TextView tvTitle;
    private ImageView ivMenu;

    private int bgColor;
    private String title;
    private int menuSrc;

    public CustomToolbar(Context context) {
        this(context,null);
    }

    public CustomToolbar(Context context, AttributeSet attrs){
        this(context,attrs,0);
    }

    public CustomToolbar(Context context, AttributeSet attrs, int defStyleAttr){
        super(context,attrs,defStyleAttr);

        initTypeArray(context,attrs,defStyleAttr);
        initView(context);
    }

    private void initTypeArray(Context context,AttributeSet attrs, int defStyleAttr){
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.CustomToolbar,
                defStyleAttr,0);

        bgColor = typedArray.getColor(R.styleable.CustomToolbar_backgroundColor, Color.TRANSPARENT);
        //获取标题属性
        title = typedArray.getString(R.styleable.CustomToolbar_title);
        //获取菜单图片资源属性，未设置菜单图片资源则默认为-1，后面通过判断此值是否为-1决定是否设置图片
        menuSrc = typedArray.getResourceId(R.styleable.CustomToolbar_menuSrc,-1);
        typedArray.recycle();
    }

    private void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.custom_titile,this);

        rootlayout = findViewById(R.id.root_layout);
        ivBack = findViewById(R.id.iv_back);
        tvTitle = findViewById(R.id.tv_title);
        ivMenu = findViewById(R.id.iv_menu);

        rootlayout.setBackgroundColor(bgColor);
        tvTitle.setText(title);
        if (menuSrc != -1){
            ivMenu.setImageResource(menuSrc);
        }

        ivBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
                Toast.makeText(getContext(),"点击左键",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
