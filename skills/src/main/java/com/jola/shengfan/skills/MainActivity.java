package com.jola.shengfan.skills;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jola.shengfan.skills.ai.tensor_flow_google.TensorFlowLiteActivity;
import com.jola.shengfan.skills.picture_in_picture.PictureInPictureActivity;

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
}
