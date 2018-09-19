package com.jola.shengfan.skills.picture_in_picture;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jola.shengfan.skills.R;

/**
 * Created by lenovo on 2018/9/19.
 */

public class PictureInPictureActivity extends AppCompatActivity {

    private String mPlayStr;
    private String mPauseStr;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_picture_in_picture);

//        prepare string resource
        mPlayStr = getString(R.string.play);
        mPauseStr = getString(R.string.pause);


    }
}
