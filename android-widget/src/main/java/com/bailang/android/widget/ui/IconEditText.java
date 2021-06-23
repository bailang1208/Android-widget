package com.bailang.android.widget.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;

import com.bailang.android.widget.R;

public class IconEditText extends LinearLayout {

    LinearLayout container;
    RelativeLayout editBox;
    ImageView icon;
    EditText editText;
    View line;

    boolean showBottomLine;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceType")
    public IconEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BLWidget, 0, 0);
        int leftIcon = a.getResourceId(R.styleable.BLWidget_left_icon, -1);
        ColorStateList leftTint = a.getColorStateList(R.styleable.BLWidget_left_icon_tint);
        String hint = a.getString(R.styleable.BLWidget_hint);
        showBottomLine = a.getBoolean(R.styleable.BLWidget_show_bottom_line, true);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.icon_edit_text, this, true);

        container = view.findViewById(R.id.container);
        editBox = view.findViewById(R.id.edit_box);
        icon = view.findViewById(R.id.icon);
        editText = view.findViewById(R.id.edit_text);
        line = view.findViewById(R.id.line);

        editText.setHint(hint);

        if(leftIcon > -1) {
            icon.setImageResource(leftIcon);
        }

        if(leftTint != null) {
            icon.setImageTintList(leftTint);
        }

        if(showBottomLine) {
            line.setVisibility(VISIBLE);
        }
        else {
            line.setVisibility(GONE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public IconEditText(Context context) {
        this(context, null);
    }
}
