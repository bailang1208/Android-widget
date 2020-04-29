package com.bailang.android.widgetsample;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bailang.android.widget.ui.ActionBar;
import com.bailang.android.widget.ui.ClickButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActionBar actionBar01;
    ActionBar actionBar02;
    ClickButton btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar01 = findViewById(R.id.action_bar_01);
        actionBar02 = findViewById(R.id.action_bar_02);
        btnClick = findViewById(R.id.btn_click);

        btnClick.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
