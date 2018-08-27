package com.jola.onlineedu.widget;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.jola.onlineedu.R;

/**
 * Created by lenovo on 2018/8/27.
 */

public class PopupLogoutView extends PopupWindow {

    public PopupLogoutView(Context context) {
        super(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View inflateView = layoutInflater.inflate(R.layout.popup_logout_view, null);

//        初始化工作

        this.setContentView(inflateView);
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        ColorDrawable colorDrawable = new ColorDrawable(0xb0bfbfbf);
        this.setBackgroundDrawable(colorDrawable);
        inflateView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = inflateView.findViewById(R.id.pop_layout).getTop();
                int y=(int) event.getY();
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(y<height){
                        dismiss();
                    }
                }
                return true;
            }
        });

    }
}
