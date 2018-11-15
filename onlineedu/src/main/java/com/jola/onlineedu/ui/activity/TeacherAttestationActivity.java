package com.jola.onlineedu.ui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.jola.onlineedu.R;
import com.jola.onlineedu.app.MyLog;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.component.ImageLoader;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResTeacherAttestation;
import com.jola.onlineedu.util.RxUtil;
import com.jola.onlineedu.util.ToastUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;

public class TeacherAttestationActivity extends SimpleActivity {


    @Inject
    DataManager mDataManager;


    @BindView(R.id.toolbar_view)
    Toolbar toolbar;
    @BindView(R.id.et_input_teacher_iccard)
    EditText et_input_teacher_iccard;

    @BindView(R.id.iv_iccard_front)
    ImageView iv_iccard_front;
    @BindView(R.id.iv_upload_ic_card_front)
    ImageView iv_upload_ic_card_front;

    @BindView(R.id.iv_iccard_back)
    ImageView iv_iccard_back;
    @BindView(R.id.iv_upload_iccard_back)
    ImageView iv_upload_iccard_back;

    @BindView(R.id.iv_teacher_card)
    ImageView iv_teacher_card;
    @BindView(R.id.iv_upload_teacher_card)
    ImageView iv_upload_teacher_card;

    private int themeId = R.style.picture_default_style;
    int currIndex = -1;
    private String mICCardFilePathFront;
    private String mICCardFilePathBack;
    private String mTeacherCardFilePath;


    @Override
    protected int getLayout() {
        return R.layout.activity_teacher_attestation;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolbar, getString(R.string.teacher_attestation_title));
        getActivityComponent().inject(this);
    }




    @OnClick({
            R.id.iv_upload_ic_card_front,
            R.id.iv_upload_iccard_back,
            R.id.iv_upload_teacher_card,
            R.id.tv_verify_teacher
    })
    public void doClick(View view){
        switch (view.getId()){
            case R.id.iv_upload_ic_card_front:
                currIndex = 1;
                chooseOrTakePhoto();
                break;
            case R.id.iv_upload_iccard_back:
                currIndex = 2;
                chooseOrTakePhoto();
                break;
            case R.id.iv_upload_teacher_card:
                currIndex = 3;
                chooseOrTakePhoto();
                break;
            case R.id.tv_verify_teacher:
                confirmVerify();
                break;
        }
    }

    private void confirmVerify() {
        String teacherCardNo = et_input_teacher_iccard.getText().toString();
        if (TextUtils.isEmpty(teacherCardNo)){
            ToastUtil.toastShort("请填写教师资格证号码后，再重试！");
            return;
        }
        if (TextUtils.isEmpty(mICCardFilePathFront)){
            ToastUtil.toastShort("请选择身份证正面照后，再重试！");
            return;
        }
        if (TextUtils.isEmpty(mICCardFilePathBack)){
            ToastUtil.toastShort("请选择身份证背面照后，再重试！");
            return;
        }
        if (TextUtils.isEmpty(mICCardFilePathFront)){
            ToastUtil.toastShort("请选择教师资格证照片后，再重试！");
            return;
        }
        showLoadingDialog();
        File fileIcCardFront = new File(mICCardFilePathFront);
        File fileIcCardBack = new File(mICCardFilePathBack);
        File fileTeacherCard = new File(mTeacherCardFilePath);

        MultipartBody multipartBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("teacher_certification_id", teacherCardNo)
                .addFormDataPart("teacher_certification", fileTeacherCard.getName(), RequestBody.create(MediaType.parse("image/*"), fileTeacherCard))
                .addFormDataPart("id_card_front_pic", fileIcCardFront.getName(), RequestBody.create(MediaType.parse("image/*"), fileIcCardFront))
                .addFormDataPart("id_card_behind_pic", fileIcCardBack.getName(), RequestBody.create(MediaType.parse("image/*"), fileIcCardBack))
                .build();

        addSubscribe(mDataManager.teacherVerify(mDataManager.getUserToken(),multipartBody)
            .compose(RxUtil.<ResTeacherAttestation>rxSchedulerHelper())
                .subscribe(new Consumer<ResTeacherAttestation>() {
                    @Override
                    public void accept(ResTeacherAttestation resTeacherAttestation) throws Exception {
                        hideLoadingDialog();
                        int error_code = resTeacherAttestation.getError_code();
                        if (error_code == 0){
                            ToastUtil.toastShort("资料上传成功，请等待审核！");
                        }else{
                            ToastUtil.toastLong(resTeacherAttestation.getError_msg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        hideLoadingDialog();
                        tipServerError();
                    }
                })
        );
    }


    private void chooseOrTakePhoto() {
        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(themeId)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(1)// 最大图片选择数量
                .minSelectNum(0)// 最小选择数量
                .imageSpanCount(3)// 每行显示个数
                .selectionMode(PictureConfig.SINGLE)// 多选 PictureConfig.MULTIPLE or 单选 PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片
//                        .previewVideo(false)// 是否可预览视频
//                        .enablePreviewAudio(false) // 是否可播放音频
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                .enableCrop(false)// 是否裁剪
                .compress(false)// 是否压缩
                .synOrAsy(true)//同步true或异步------false 压缩 默认同步
                //.compressSavePath(getPath())//压缩图片保存地址
                //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                        .withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
//                        .hideBottomControls(cb_hide.isChecked() ? false : true)// 是否显示uCrop工具栏，默认不显示
                .isGif(false)// 是否显示gif图片
//                        .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
//                        .circleDimmedLayer(cb_crop_circular.isChecked())// 是否圆形裁剪
//                        .showCropFrame(cb_showCropFrame.isChecked())// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
//                        .showCropGrid(cb_showCropGrid.isChecked())// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
//                        .openClickSound(cb_voice.isChecked())// 是否开启点击声音
//                .selectionMedia(selectList)// 是否传入已选图片
                //.isDragFrame(false)// 是否可拖动裁剪框(固定)
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
                //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                //.rotateEnabled(true) // 裁剪是否可旋转图片
                //.scaleEnabled(true)// 裁剪是否可放大缩小图片
                //.videoQuality()// 视频录制质量 0 or 1
                //.videoSecond()//显示多少秒以内的视频or音频也可适用
                //.recordVideoSecond()//录制视频秒数 默认60s
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    @Override
    public  void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> localMedia = PictureSelector.obtainMultipleResult(data);
                    if (null != localMedia && localMedia.size() > 0){
                        String path = localMedia.get(0).getPath();
                        switch (currIndex){
                            case 1:
                                mICCardFilePathFront = path;
                                ImageLoader.load(TeacherAttestationActivity.this,path,iv_iccard_front);
                                iv_upload_ic_card_front.setImageResource(R.drawable.upload_retry2x);
                                break;
                            case 2:
                                mICCardFilePathBack = path;
                                ImageLoader.load(TeacherAttestationActivity.this,path,iv_iccard_back);
                                iv_upload_iccard_back.setImageResource(R.drawable.upload_retry2x);
                                break;
                            case 3:
                                mTeacherCardFilePath = path;
                                ImageLoader.load(TeacherAttestationActivity.this,path,iv_teacher_card);
                                iv_upload_teacher_card.setImageResource(R.drawable.upload_retry2x);
                                break;
                        }
//                        MyLog.logMy(path);
//                        showLoadingDialog();
//                        uploadPicture(path);
                    }else{
                        ToastUtil.toastShort("获取图片失败！");
                    }
                    break;
            }
        }
    }



}
