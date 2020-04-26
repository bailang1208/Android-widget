package com.bailang.android.widget.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.bailang.android.widget.R;

public class ClickButton extends AppCompatTextView {

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
    }
}
