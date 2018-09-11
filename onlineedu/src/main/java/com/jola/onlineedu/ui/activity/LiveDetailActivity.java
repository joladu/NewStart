package com.jola.onlineedu.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.view.View;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;

import butterknife.OnClick;

public class LiveDetailActivity extends SimpleActivity {


    @Override
    protected int getLayout() {
        return R.layout.activity_live_detail;
    }

    @Override
    protected void initEventAndData() {
        changeFullScreen();
    }

    public void changeFullScreen(){
        if (Build.VERSION.SDK_INT >= 21) {
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                View decorView = getWindow().getDecorView();
                decorView.setSystemUiVisibility(option);
                getWindow().setStatusBarColor(Color.TRANSPARENT);

        }
    }
    @OnClick({R.id.iv_back})
    public void doClick(View view){
        switch (view.getId()){
            case R.id.iv_back:
                this.finish();
                break;
        }
    }

}
