package com.bailang.android.widget.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.bailang.android.widget.R;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ClickButton extends AppCompatTextView {

    private static int NORMAL   = 1001;
    private static int PRESSED  = 1002;

    private int DEFAULT_NORMAL_COLOR;
    private int DEFAULT_PRESSED_COLOR;
    private int DEFAULT_DISABLE_COLOR;

    private int DEFAULT_NORMAL_BORDER_COLOR;
    private int DEFAULT_PRESSED_BORDER_COLOR;
    private int DEFAULT_DISABLE_BORDER_COLOR;

    private float cornerRadius;
    private float leftTopRadius;
    private float rightTopRadius;
    private float leftBottomRadius;
    private float rightBottomRadius;

    private int borderWidth;

    private int normalColor;
    private int pressedColor;
    private int disableColor;
    private int normalBorderColor;
    private int pressedBorderColor;
    private int disableBorderColor;

    boolean m_bOutOf = false;

    private Rect rect;    // Variable rect to hold the bounds of the view

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
        DEFAULT_NORMAL_COLOR = ContextCompat.getColor(context, R.color.primary);
        DEFAULT_PRESSED_COLOR = ContextCompat.getColor(context, R.color.primary_dark);
        DEFAULT_DISABLE_COLOR = ContextCompat.getColor(context, R.color.gray);

        DEFAULT_NORMAL_BORDER_COLOR = ContextCompat.getColor(context, R.color.transparent);
        DEFAULT_PRESSED_BORDER_COLOR = ContextCompat.getColor(context, R.color.transparent);
        DEFAULT_DISABLE_BORDER_COLOR = ContextCompat.getColor(context, R.color.transparent);

        final String androidNamespace = "http://schemas.android.com/apk/res/android";
        String textColor = attrs.getAttributeValue(androidNamespace, "textColor");
        if(textColor == null || textColor.equals("")) {
            setTextColor(context.getResources().getColorStateList(R.drawable.btn_text));
        }

        String gravity = attrs.getAttributeValue(androidNamespace, "gravity");
        if(gravity == null || gravity.equals("")) {
            setGravity(Gravity.CENTER);
        }

        @SuppressLint("Recycle")
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ClickButton, 0, 0);
        cornerRadius = a.getDimensionPixelSize(R.styleable.ClickButton_radius, 0);
        leftTopRadius = a.getDimensionPixelSize(R.styleable.ClickButton_left_top_radius, 0);
        rightTopRadius = a.getDimensionPixelSize(R.styleable.ClickButton_right_top_radius, 0);
        leftBottomRadius = a.getDimensionPixelSize(R.styleable.ClickButton_left_bottom_radius, 0);
        rightBottomRadius = a.getDimensionPixelSize(R.styleable.ClickButton_right_bottom_radius, 0);

        borderWidth = a.getDimensionPixelSize(R.styleable.ClickButton_border_width, 0);

        normalColor = a.getColor(R.styleable.ClickButton_normal_color, DEFAULT_NORMAL_COLOR);
        pressedColor = a.getColor(R.styleable.ClickButton_pressed_color, DEFAULT_PRESSED_COLOR);
        disableColor = a.getColor(R.styleable.ClickButton_disable_color, DEFAULT_DISABLE_COLOR);
        normalBorderColor = a.getColor(R.styleable.ClickButton_normal_border_color, DEFAULT_NORMAL_BORDER_COLOR);
        pressedBorderColor = a.getColor(R.styleable.ClickButton_pressed_border_color, DEFAULT_PRESSED_BORDER_COLOR);
        disableBorderColor = a.getColor(R.styleable.ClickButton_disable_border_color, DEFAULT_DISABLE_BORDER_COLOR);

//        int[][] states = new int[][] {
//                new int[] { android.R.attr.state_empty},
//                new int[] { android.R.attr.state_pressed},
//                new int[] { android.R.attr.state_selected},
//                new int[] { android.R.attr.state_focused},
//                new int[] { android.R.attr.state_enabled}, // enabled
//                new int[] {-android.R.attr.state_enabled} // disabled
//        };
//        int[] colors = new int[] {
//                normalColor,
//                pressedColor,
//                pressedColor,
//                pressedColor,
//                pressedColor,
//                disableColor
//        };
//        ColorStateList colorStateList = new ColorStateList(states, colors);
//
//        int[][] borderStates = new int[][] {
//                new int[] { android.R.attr.drawable},
//                new int[] { android.R.attr.state_pressed},
//                new int[] { android.R.attr.state_selected},
//                new int[] { android.R.attr.state_focused},
//                new int[] { android.R.attr.state_enabled}, // enabled
//                new int[] {-android.R.attr.state_enabled} // disabled
//        };
//        int[] borderColors = new int[] {
//                normalBorderColor,
//                pressedBorderColor,
//                pressedBorderColor,
//                pressedBorderColor,
//                pressedBorderColor,
//                disableBorderColor
//        };
//        ColorStateList borderColorStateList = new ColorStateList(borderStates, borderColors);
//
//        GradientDrawable drawable = new GradientDrawable();
//        drawable.setColor(colorStateList);
//        if(leftTopRadius != 0 || rightTopRadius != 0 || rightBottomRadius != 0 || leftBottomRadius != 0) {
//            float[] radiusList = new float[] {
//                    leftTopRadius,
//                    leftTopRadius,
//                    rightTopRadius,
//                    rightTopRadius,
//                    rightBottomRadius,
//                    rightBottomRadius,
//                    leftBottomRadius,
//                    leftBottomRadius
//            };
//            drawable.setCornerRadii(radiusList);
//        }
//        else {
//            drawable.setCornerRadius(cornerRadius);
//        }
//        drawable.setStroke(borderWidth, borderColorStateList);
//        setBackground(drawable);

        updateBackground(NORMAL);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                rect = new Rect(getLeft(), getTop(), getRight(), getBottom());
                m_bOutOf = false;
                updateBackground(PRESSED);
                return true;

            case MotionEvent.ACTION_CANCEL:
                //clear the overlay
                //Log.e("test", "CANCEL");
                updateBackground(NORMAL);
                return true;
            case MotionEvent.ACTION_MOVE:
                //clear the overlay
                if (m_bOutOf)  	return false;

                if(!rect.contains(getLeft() + (int) event.getX(), getTop() + (int) event.getY())){
                    m_bOutOf = true;
                    //Log.e("test", "OUTSIDE");
                } else {
                    //Log.e("test", "INSIDE");
                }
                return true;
            case MotionEvent.ACTION_UP:
                if (m_bOutOf == false) {
                    performClick();
                    updateBackground(NORMAL);
                }
                return true;
        }

        return false;
    }

    private void updateBackground(int state) {
        ColorStateList colorStateList;
        ColorStateList borderColorStateList;
        if(state == NORMAL) {
            int[][] states = new int[][] {
                    new int[] {}};
            int[] colors = new int[] {
                    normalColor
            };
            colorStateList = new ColorStateList(states, colors);

            int[][] borderStates = new int[][] {
                    new int[] {}
            };
            int[] borderColors = new int[] {
                    normalBorderColor
            };
            borderColorStateList = new ColorStateList(borderStates, borderColors);
        }
        else {
            int[][] states = new int[][] {
                    new int[] {}};
            int[] colors = new int[] {
                    pressedColor
            };
            colorStateList = new ColorStateList(states, colors);

            int[][] borderStates = new int[][] {
                    new int[] {}
            };
            int[] borderColors = new int[] {
                    pressedBorderColor
            };
            borderColorStateList = new ColorStateList(borderStates, borderColors);
        }

        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(colorStateList);
        drawable.setStroke(borderWidth, borderColorStateList);
        if(leftTopRadius != 0 || rightTopRadius != 0 || rightBottomRadius != 0 || leftBottomRadius != 0) {
            float[] radiusList = new float[] {
                    leftTopRadius,
                    leftTopRadius,
                    rightTopRadius,
                    rightTopRadius,
                    rightBottomRadius,
                    rightBottomRadius,
                    leftBottomRadius,
                    leftBottomRadius
            };
            drawable.setCornerRadii(radiusList);
        }
        else {
            drawable.setCornerRadius(cornerRadius);
        }
        setBackground(drawable);
    }
}
