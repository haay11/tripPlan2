package com.firstapp.hytripplan.toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.firstapp.hytripplan.R;


public class CustomToolbar extends FrameLayout {
    public TextView app_title;
    public FrameLayout toolbar_;
    public ImageView back, back_empty, forward, forward_empty;
    public String infService, appTitleID;
    public LayoutInflater li;
    public View v;

    public CustomToolbar(@NonNull Context context) {
        super(context);
        initView();
    }

    public CustomToolbar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
        getAttrs(attrs);
    }

    public CustomToolbar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        getAttrs(attrs, defStyleAttr);
    }

    public CustomToolbar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
        getAttrs(attrs, defStyleAttr);
    }


    @SuppressLint("ResourceType")
    private void initView() {

        infService = Context.LAYOUT_INFLATER_SERVICE;
        li = (LayoutInflater) getContext().getSystemService(infService);
        v = li.inflate(R.layout.main_toolbar, this, false);
        addView(v);

        toolbar_ = (FrameLayout) v.findViewById(R.id.toolbar_);
        app_title = (TextView) v.findViewById(R.id.app_title);
        back = (ImageView) v.findViewById(R.id.back);
        back_empty = (ImageView) v.findViewById(R.id.back_empty);
        forward = (ImageView) v.findViewById(R.id.forward);
        forward_empty = (ImageView) v.findViewById(R.id.forward_empty);

    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.main_toolbar);
        setTypeArray(typedArray);
    }
    private void getAttrs(AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.main_toolbar, defStyleAttr, 0);
        setTypeArray(typedArray);
    }

    private void setTypeArray(TypedArray typedArray) {

        appTitleID = typedArray.getString(R.styleable.main_toolbar_app_title);
        app_title.setText(appTitleID);

        int backID = typedArray.getResourceId(R.styleable.main_toolbar_back, R.drawable.ic_icon_left_arrow);
        back.setImageResource(backID);
        int backEmptyID = typedArray.getResourceId(R.styleable.main_toolbar_back_empty, R.drawable.ic_icon_left_arrow1);
        back_empty.setImageResource(backEmptyID);
        int forwardID = typedArray.getResourceId(R.styleable.main_toolbar_forward, R.drawable.ic_icon_right_arrow);
        forward.setImageResource(forwardID);
        int forwordEmptyID = typedArray.getResourceId(R.styleable.main_toolbar_forward_empty, R.drawable.ic_icon_right_arrow1);
        forward_empty.setImageResource(forwordEmptyID);

        typedArray.recycle();
    }


}
