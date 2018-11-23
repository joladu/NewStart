package com.jola.shengfan.skills;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jola.shengfan.skills.ai.tensor_flow_google.TensorFlowLiteActivity;
import com.jola.shengfan.skills.collapsing_toolbar_layout.CollapsingToolbarLayoutActivity;
import com.jola.shengfan.skills.custome_widget.pie.CustomeWidgetActivity;
import com.jola.shengfan.skills.picture_in_picture.PictureInPictureActivity;
import com.jola.shengfan.skills.pull_refresh_custom.CustomPullRefreshActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pictureInPic(View veiw){
        startActivity(new Intent(this, PictureInPictureActivity.class));
    }

    public void tensorFlow(View veiw){
        startActivity(new Intent(this, TensorFlowLiteActivity.class));
    }

    public void animatedPieView(View veiw){
        startActivity(new Intent(this, CustomeWidgetActivity.class));
    }

    public void customPullRefresh(View veiw){
        startActivity(new Intent(this, CustomPullRefreshActivity.class));
    }

    public void collapsingToolbar(View view){
        startActivity(new Intent(this, CollapsingToolbarLayoutActivity.class));
    }


}
