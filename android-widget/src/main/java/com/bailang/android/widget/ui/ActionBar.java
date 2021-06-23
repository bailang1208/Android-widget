package com.bailang.android.widget.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.bailang.android.widget.R;

public class ActionBar extends LinearLayout implements View.OnClickListener {

    LinearLayout container;

    View viewTopBar;
    RelativeLayout contentBar;

    ClickImageButton btnLeft;
    TextView lblTitle;
    ClickImageButton btnRight;

    ImageView imgLogo;

    View viewShadow;

    OnClickListener m_listener = null;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceType")
    public ActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BLWidget, 0, 0);
        String titleText = a.getString(R.styleable.BLWidget_title);
        int leftIcon = a.getResourceId(R.styleable.BLWidget_left_icon, -1);
        int rightIcon = a.getResourceId(R.styleable.BLWidget_right_icon, -1);
        int logo = a.getResourceId(R.styleable.BLWidget_logo, -1);

        ColorStateList leftTint = a.getColorStateList(R.styleable.BLWidget_left_icon_tint);
        ColorStateList rightTint = a.getColorStateList(R.styleable.BLWidget_right_icon_tint);
        ColorStateList logoTint = a.getColorStateList(R.styleable.BLWidget_logo_tint);
//        int background = a.getColor(R.styleable.BLWidget_background, -1);

        boolean isHideLogo = a.getBoolean(R.styleable.BLWidget_hide_logo, true);
        boolean isHideTopBar = a.getBoolean(R.styleable.BLWidget_hide_top_bar, false);
        boolean isHideShadow = a.getBoolean(R.styleable.BLWidget_hide_shadow, false);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.action_bar, this, true);

        container = view.findViewById(R.id.container);

        viewTopBar = view.findViewById(R.id.view_top_bar);

        contentBar = view.findViewById(R.id.content_bar);
        btnLeft = view.findViewById(R.id.btn_left);
        lblTitle = view.findViewById(R.id.lbl_title);
        btnRight = view.findViewById(R.id.btn_right);

        imgLogo = view.findViewById(R.id.img_logo);

        viewShadow = view.findViewById(R.id.view_shadow);

        lblTitle.setText(titleText);

        if(leftIcon > -1) {
            btnLeft.setImageResource(leftIcon);
        }

        if(rightIcon > -1) {
            btnRight.setImageResource(rightIcon);
        }

        if(logo > -1) {
            imgLogo.setImageResource(logo);
        }

        if(leftTint != null) {
            btnLeft.setImageTintList(leftTint);
        }

        if(rightTint != null) {
            btnRight.setImageTintList(rightTint);
        }

        if(logoTint != null) {
            imgLogo.setImageTintList(logoTint);
        }

//        if(background != -1) {
//            container.setBackgroundColor(background);
//        }

        if(isHideLogo) {
            imgLogo.setVisibility(GONE);
        }
        else {
            imgLogo.setVisibility(VISIBLE);
        }

        if(isHideTopBar) {
            viewTopBar.setVisibility(GONE);
        }
        else {
            viewTopBar.setVisibility(VISIBLE);
        }

        if(isHideShadow) {
            viewShadow.setVisibility(GONE);
        }
        else {
            viewShadow.setVisibility(VISIBLE);
        }

        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ActionBar(Context context) {
        this(context, null);
    }

    public void setOnClickListener(OnClickListener listener) {
        m_listener = listener;
    }

    @Override
    public void onClick(View view) {
        int vId = view.getId();
        if(vId == R.id.btn_left) {
            if(m_listener != null) {
                m_listener.onClickedButtonLeft();
            }
        }
        else if(vId == R.id.btn_right) {
            if(m_listener != null) {
                m_listener.onClickedButtonRight();
            }
        }
    }

    public void setTitle(String title) {
        lblTitle.setText(title);
    }

    public void setTitle(int title) {
        lblTitle.setText(title);
    }

    public void showLeftButton() {
        btnLeft.setVisibility(VISIBLE);
    }
    public void hideLeftButton() {
        btnLeft.setVisibility(GONE);
    }

    public void showRightButton() {
        btnRight.setVisibility(VISIBLE);
    }

    public void hideRightButton() {
        btnRight.setVisibility(GONE);
    }

    public interface OnClickListener {
        void onClickedButtonLeft();
        void onClickedButtonRight();
    }
}
