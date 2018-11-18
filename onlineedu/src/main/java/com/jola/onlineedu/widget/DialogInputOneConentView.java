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
import android.widget.EditText;
import android.widget.TextView;

import com.jola.onlineedu.R;

/**
 * Created by lenovo on 2018/8/24.
 * 输入一行内容：修改昵称
 */

public class DialogInputOneConentView extends Dialog {


    /**
     * 使用方式
     *  final DialogConfirmView dialogConfirmView = new DialogConfirmView(RfAmountCardMainActivity.this, "确认圈存？", "圈存后，需要将卡插入表中使用后，才能再次圈存！", "取消", "确定");
     dialogConfirmView.setClickListenerInterface(new DialogConfirmView.ClickListenerInterface() {
    @Override
    public void doLeft() {
    dialogConfirmView.dismiss();
    }

    @Override
    public void doRight() {
    dialogConfirmView.dismiss();
    doQuanCun();
    }
    });
     dialogConfirmView.show();
     */
    private Context context;

    private String title;
    private String buttonLeftText;
    private String buttonRightText;

    EditText et_input_content;

    private ClickListenerInterface clickListenerInterface;


    public interface ClickListenerInterface {

        public void doLeft();

        public void doRight();

    }

    public void setClickListenerInterface(ClickListenerInterface clickListenerInterface){
        this.clickListenerInterface = clickListenerInterface;
    }



    public DialogInputOneConentView(@NonNull Context context, String title,
                                    String buttonLeftText, String buttonRightText) {
        super(context);
//        super(context, R.style.Confirm0rRefuseDialogStyel);
        this.context = context;
        this.title = title;
        this.buttonLeftText = buttonLeftText;
        this.buttonRightText = buttonRightText;
    }

    public DialogInputOneConentView(@NonNull Context context, String title, String buttonLeftText, String buttonRightText, ClickListenerInterface clickListenerInterface){
        super(context);
//        super(context,R.style.Confirm0rRefuseDialogStyel);
        this.context = context;
        this.title = title;
        this.buttonLeftText = buttonLeftText;
        this.buttonRightText = buttonRightText;
        this.clickListenerInterface = clickListenerInterface;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_input_content_one, null, false);
        setContentView(view);
        et_input_content = (EditText) view.findViewById(R.id.et_input_content);
        TextView tvLeft = (TextView) view.findViewById(R.id.tvBtnLeft);
        TextView tvRight = (TextView) view.findViewById(R.id.tvBtnRight);
        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);

        if ("".equals(title)) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setText(title);
        }
        if (null == buttonLeftText || buttonLeftText.length() == 0){
            tvLeft.setVisibility(View.GONE);
            view.findViewById(R.id.view_between_two_divider).setVisibility(View.GONE);
        }else{
            tvLeft.setText(buttonLeftText);
            tvLeft.setOnClickListener(new ClickLister());
        }
        tvRight.setText(buttonRightText);
        tvRight.setOnClickListener(new ClickLister());

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        layoutParams.width = (int)(displayMetrics.widthPixels);
        dialogWindow.setAttributes(layoutParams);
    }

    public String getInputContent(){
        return et_input_content.getText().toString();
    }


    public class ClickLister implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tvBtnLeft:
                    clickListenerInterface.doLeft();
                    break;
                case R.id.tvBtnRight:
                    clickListenerInterface.doRight();
                    break;
            }
        }
    }



}
