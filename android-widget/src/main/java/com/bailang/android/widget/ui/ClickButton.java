package com.bailang.android.widget.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatTextView;

import com.bailang.android.widget.R;

public class ClickButton extends AppCompatTextView {

    int normalColor;
    int pressedColor;
    int disableColor;

    public ClickButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        applyTypeface(context, attrs);
    }

    public ClickButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyTypeface(context, attrs);
    }

    public ClickButton(Context context) {
        super(context);
    }

    @SuppressLint("ResourceType")
    private void applyTypeface(Context context, AttributeSet attrs){
        final String androidNamespace = "http://schemas.android.com/apk/res/android";
        String textColor = attrs.getAttributeValue(androidNamespace, "textColor");
        if(textColor == null || textColor.equals("")) {
            setTextColor(context.getResources().getColorStateList(R.drawable.btn_text));
        }

        String gravity = attrs.getAttributeValue(androidNamespace, "gravity");
        if(gravity == null || gravity.equals("")) {
            setGravity(Gravity.CENTER);
        }

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ClickButton, 0, 0);
        normalColor = a.getColor(R.styleable.ClickButton_normal_color, R.color.btn_green);
        pressedColor = a.getColor(R.styleable.ClickButton_normal_color, R.color.btn_green);
        disableColor = a.getColor(R.styleable.ClickButton_normal_color, R.color.btn_green);

        int[][] states = new int[][] {
                new int[] { android.R.attr.state_enabled}, // enabled
                new int[] {-android.R.attr.state_enabled}, // disabled
                new int[] {-android.R.attr.state_checked}, // unchecked
                new int[] { android.R.attr.state_pressed}  // pressed
        };

        int[] colors = new int[] {
                normalColor,
                pressedColor,
                disableColor,
                pressedColor
        };

        ColorStateList myList = new ColorStateList(states, colors);
    }
}
