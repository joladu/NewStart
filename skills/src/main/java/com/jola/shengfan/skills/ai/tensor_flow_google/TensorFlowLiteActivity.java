package com.jola.shengfan.skills.ai.tensor_flow_google;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.jola.shengfan.skills.MainActivity;
import com.jola.shengfan.skills.R;

import org.tensorflow.lite.Interpreter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class TensorFlowLiteActivity extends AppCompatActivity {

    public static final String TAG = "TensorFlowLiteActivity";
    private final int REQUEST_PERMISSION_CODE = 101;
    private static final int USE_PHOTO = 1001;
    private static final int START_CAMERA = 1002;

    private String camera_image_path;



    private String assets_path = "lite_images";


    private ImageView show_image;
    private TextView result_text;

    private List<String> resultLabel = new ArrayList<>();


    private final String[] PADDLE_MODE = new String[]{
            "mobilenet_v1",
            "mobilenet_quant_v1_224",
            "mobilenet_v1_1.0_224",
            "mobilenet_v2"
    };

    private int mode_index = 0;

    private Interpreter tflite = null;

    private boolean load_result = false;

    private int[] ddims = {1, 3, 224, 224};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tensor_flow_lite);
        requestPermissions();
        initView();
        readCacheLabelFromLocalFile();
        String sdcard_path = Environment.getExternalStorageDirectory()
                + File.separator + assets_path;
        copy_file_from_asset(this, assets_path, sdcard_path);
    }

    // copy file from asset to sdcard
    public void copy_file_from_asset(Context context, String oldPath, String newPath) {
        try {
            String[] fileNames = context.getAssets().list(oldPath);
            if (fileNames.length > 0) {
                // directory
                File file = new File(newPath);
                if (!file.exists()) {
                    file.mkdirs();
                }
                // copy recursivelyC
                for (String fileName : fileNames) {
                    copy_file_from_asset(context, oldPath + "/" + fileName, newPath + "/" + fileName);
                }
                Log.d(TAG, "copy files finish");
            } else {
                // file
                File file = new File(newPath);
                // if file exists will never copy
                if (file.exists()) {
                    return;
                }

                // copy file to new path
                InputStream is = context.getAssets().open(oldPath);
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                int byteCount;
                while ((byteCount = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, byteCount);
                }
                fos.flush();
                is.close();
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readCacheLabelFromLocalFile() {
        try {
            AssetManager assetManager = getApplicationContext().getAssets();
            BufferedReader reader = new BufferedReader(new InputStreamReader(assetManager.open("cacheLabel.txt")));
            String readLine = null;
            while ((readLine = reader.readLine()) != null) {
                resultLabel.add(readLine);
            }
            reader.close();
        } catch (Exception e) {
            Log.e("labelCache", "error " + e);
        }
    }

    private void requestPermissions() {
        if (Build.VERSION_CODES.M > Build.VERSION.SDK_INT){
            return;
        }
        ArrayList<String> listPermissions = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            listPermissions.add(Manifest.permission.CAMERA);
        }
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            listPermissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            listPermissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (listPermissions.size() > 0){
                requestPermissions(listPermissions.toArray(new String[listPermissions.size()]),REQUEST_PERMISSION_CODE);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_PERMISSION_CODE:
                if (grantResults.length > 0){
                    for (int curResult: grantResults) {
                        if (curResult == PackageManager.PERMISSION_DENIED){
                            requestPermissions();
                            break;
                        }
                    }
                }
                    break;
        }
    }

    private void initView() {
        show_image = (ImageView) findViewById(R.id.show_image);
        result_text = (TextView) findViewById(R.id.result_text);
        result_text.setMovementMethod(ScrollingMovementMethod.getInstance());

        Button load_model = (Button) findViewById(R.id.load_model);
        Button use_photo = (Button) findViewById(R.id.use_photo);
        Button start_photo = (Button) findViewById(R.id.start_camera);
        Button infer00 = (Button) findViewById(R.id.infer100);


        load_model.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        // use photo click
        use_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!load_result) {
                    Toast.makeText(TensorFlowLiteActivity.this, "never load model", Toast.LENGTH_SHORT).show();
                    return;
                }
                PhotoUtil.userPhoto(TensorFlowLiteActivity.this, USE_PHOTO);
            }
        });


        infer00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!load_result) {
                    Toast.makeText(TensorFlowLiteActivity.this, "never load model", Toast.LENGTH_SHORT).show();
                    return;
                }
                predict_100image();
            }
        });

        // start camera click
        start_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!load_result) {
                    Toast.makeText(TensorFlowLiteActivity.this, "never load model", Toast.LENGTH_SHORT).show();
                    return;
                }
                camera_image_path = PhotoUtil.startCamera(TensorFlowLiteActivity.this, START_CAMERA);
            }
        });

    }


    private void predict_100image() {
        // 100 images root path
        String image100_path = Environment.getExternalStorageDirectory()
                + File.separator + assets_path;
        // list file path
        String list_path = image100_path + File.separator + "images100/" + File.separator + "image_label.txt";
        try {
            // read label file
            File file = new File(list_path);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
            BufferedReader br = new BufferedReader(reader);
            List<String> images = new ArrayList<>();
            List<Integer> labels = new ArrayList<>();
            String s = "";
            while ((s = br.readLine()) != null) {
                String[] split = s.split(",");
                images.add(split[0]);
                labels.add(Integer.parseInt(split[1]));
            }
            br.close();
            reader.close();

            StringBuilder show_text = new StringBuilder();

            long start = System.currentTimeMillis();
            float error = 0.0f;
            for (int j = 0; j < images.size(); j++) {
                String image = image100_path + File.separator + images.get(j);
                // picture to float array
                Bitmap bmp = PhotoUtil.getScaleBitmap(image);
                // saveBitmap(bmp);
                ByteBuffer inputData = PhotoUtil.getScaledMatrix(bmp, ddims);
                float[][] labelProbArray = new float[1][1001];
                // get predict result
                tflite.run(inputData, labelProbArray);
                float[] result = new float[labelProbArray[0].length];
                System.arraycopy(labelProbArray[0], 0, result, 0, labelProbArray[0].length);
                // first label is background
                int r = get_max_result(result) - 1;
                Log.d(TAG, "result:" + r);
                if (r != labels.get(j)) {
                    error = error + 1.0f;
                }
            }

            // show infer time and accuracy
            long end = System.currentTimeMillis();
            show_text.append("The mean infer time is:").append((end - start) / images.size()).append("ms\n");
            Log.d(TAG, "SIZE:" + images.size());
            Log.d(TAG, "ERROR:" + error);
            show_text.append("The accuracy is:").append(1.0f - (error / images.size()));
            result_text.setText(show_text.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String image_path;
        RequestOptions options = new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE);
        switch (requestCode){
            case USE_PHOTO:
                if (data == null){
                    Log.w(TAG, "user photo data is null");
                    return;
                }
                Uri uri = data.getData();
                Glide.with(TensorFlowLiteActivity.this)
                        .load(uri)
                        .apply(options)
                        .into(show_image);

                image_path = PhotoUtil.get_path_from_URI(TensorFlowLiteActivity.this, uri);
                predictImage(image_path);
                break;
            case START_CAMERA:
                // show photo
                Glide.with(TensorFlowLiteActivity.this).load(camera_image_path).apply(options).into(show_image);
                // predict image
                predictImage(camera_image_path);
                break;
        }
    }

    private void predictImage(String image_path) {
        Bitmap bmp = PhotoUtil.getScaleBitmap(image_path);
        ByteBuffer inputData = PhotoUtil.getScaledMatrix(bmp, ddims);

        float[][] labelProbArray = new float[1][1001];
        long start = System.currentTimeMillis();
        tflite.run(inputData, labelProbArray);
        long end = System.currentTimeMillis();
        long time = end - start;
        float[] results = new float[labelProbArray[0].length];
        System.arraycopy(labelProbArray[0], 0, results, 0, labelProbArray[0].length);
        int r = get_max_result(results);
        String show_text = "result：" + r + "\nname：" + resultLabel.get(r) + "\nprobability：" + results[r] + "\ntime：" + time + "ms";
        result_text.setText(show_text);


    }


    // get max probability label
    private int get_max_result(float[] result) {
        float probability = result[0];
        int r = 0;
        for (int i = 0; i < result.length; i++) {
            if (probability < result[i]) {
                probability = result[i];
                r = i;
            }
        }
        return r;
    }


    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please select mode");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setCancelable(true);
        builder.setNegativeButton("cancel",null);
//        set list
        builder.setSingleChoiceItems(PADDLE_MODE, mode_index, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mode_index = which;
                loadMode(PADDLE_MODE[which]);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void loadMode(String modeName) {

        try {
//            memory-mep the mode file
            AssetFileDescriptor assetFileDescriptor = getApplicationContext().getAssets().openFd(modeName + ".tflite");
            FileInputStream fileInputStream = new FileInputStream(assetFileDescriptor.getFileDescriptor());
            FileChannel channel = fileInputStream.getChannel();
            long startOffset = assetFileDescriptor.getStartOffset();
            long declaredLength = assetFileDescriptor.getDeclaredLength();
            MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);

//            load infer mode
            tflite = new Interpreter(mappedByteBuffer);
            Toast.makeText(this, modeName + " model load success", Toast.LENGTH_SHORT).show();
            Log.d(TAG, modeName + " model load success");

//            tflite.setNumThreads(4);
            load_result = true;

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, modeName + " model load fail", Toast.LENGTH_SHORT).show();
            Log.d(TAG, modeName + " model load fail");
            load_result = false;
        }

    }




}
