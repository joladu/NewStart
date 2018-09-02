package com.jola.onlineedu.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.jola.onlineedu.R;

/**
 * Created by lenovo on 2018/8/24.
 * 加载中
 */

public class DialogLoadingView extends Dialog {

    private Context context;


    public DialogLoadingView(@NonNull Context context) {
        super(context, R.style.Confirm0rRefuseDialogStyel);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.view_loading, null, false);
        setContentView(view);
        setCancelable(true);
        setCanceledOnTouchOutside(false);

    }

}
