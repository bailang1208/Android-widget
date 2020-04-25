package com.bailang.android.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bailang.android.R;

public class ActionBar extends ViewGroup implements View.OnClickListener {

    RelativeLayout contentBar;

    ClickImageButton btnLeft;
    TextView lblTitle;
    ClickImageButton btnRight;

    ImageView imgLogo;

    OnClickListener m_listener = null;

    public ActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ActionBar, 0, 0);
        String titleText = a.getString(R.styleable.ActionBar_title);
        int leftIcon = a.getResourceId(R.styleable.ActionBar_ic_left, -1);
        int rightIcon = a.getResourceId(R.styleable.ActionBar_ic_right, -1);

        ColorStateList leftTint = a.getColorStateList(R.styleable.ActionBar_ic_left_tint);
        ColorStateList rightTint = a.getColorStateList(R.styleable.ActionBar_ic_right_tint);
        ColorStateList background = a.getColorStateList(R.styleable.ActionBar_background);

        boolean isHideLogo = a.getBoolean(R.styleable.ActionBar_is_hide_logo, false);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.action_bar, this, true);

        contentBar = view.findViewById(R.id.content_bar);
        btnLeft = view.findViewById(R.id.btn_left);
        lblTitle = view.findViewById(R.id.lbl_title);
        btnRight = view.findViewById(R.id.btn_right);

        imgLogo = view.findViewById(R.id.img_logo);

        lblTitle.setText(titleText);

        if(leftIcon > -1) {
            btnLeft.setImageResource(leftIcon);
        }

        if(rightIcon > -1) {
            btnRight.setImageResource(rightIcon);
        }

        if(leftTint != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                btnLeft.setImageTintList(leftTint);
            }
        }

        if(rightTint != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                btnRight.setImageTintList(rightTint);
            }
        }

        if(background != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                contentBar.setBackgroundTintList(background);
            }
        }

        if(isHideLogo) {
            imgLogo.setVisibility(GONE);
        }
        else {
            imgLogo.setVisibility(VISIBLE);
        }

        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

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
