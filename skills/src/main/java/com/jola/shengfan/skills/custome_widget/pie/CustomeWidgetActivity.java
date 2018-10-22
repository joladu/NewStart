package com.jola.shengfan.skills.custome_widget.pie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jola.shengfan.skills.R;

public class CustomeWidgetActivity extends AppCompatActivity {

    private AnimatedPieView animatedPieView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animated_pie);
        initView();
    }

    private void initView() {
        animatedPieView = (AnimatedPieView) findViewById(R.id.apv_customer_pie);
    }
}
