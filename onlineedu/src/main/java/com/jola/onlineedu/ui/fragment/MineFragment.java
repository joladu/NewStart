package com.jola.onlineedu.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jola.onlineedu.R;
import com.jola.onlineedu.app.MyLog;
import com.jola.onlineedu.base.SimpleFragment;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResUploadUserImageBean;
import com.jola.onlineedu.mode.bean.response.ResUserInfoBean;
import com.jola.onlineedu.mode.http.MyApis;
import com.jola.onlineedu.ui.activity.ForumPublishActivity;
import com.jola.onlineedu.util.RxUtil;
import com.jola.onlineedu.util.SystemUtil;
import com.jola.onlineedu.util.ToastUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.functions.Consumer;

/**
 * Created by jola on 2018/9/6.
 */

public class MineFragment extends SimpleFragment {


    @Inject
    DataManager dataManager;

    @BindView(R.id.civ_head_user)
    CircleImageView civ_head_user;
    @BindView(R.id.tv_user_name)
    TextView tv_user_name;
    private int themeId = R.style.picture_default_style;


//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        MyLog.logMy("MineFragment : onCreate");
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initEventAndData() {
        getFragmentComponent().inject(this);
        getUserInfo();
    }

    private void getUserInfo() {
        addSubscribe(dataManager.getUserInfo(dataManager.getUserToken())
        .compose(RxUtil.<ResUserInfoBean>rxSchedulerHelper())
        .subscribe(new Consumer<ResUserInfoBean>() {
            @Override
            public void accept(ResUserInfoBean resUserInfoBean) throws Exception {
                int error_code = resUserInfoBean.getError_code();
                if (error_code == 0){
                    tv_user_name.setText(resUserInfoBean.getData().getUser().getUsername());
                    String headImgUrl = resUserInfoBean.getData().getUser().getAvatar();
                    Glide.with(mActivity).load(headImgUrl).apply(new RequestOptions().placeholder(R.drawable.person_holder_logout_x2).error(R.drawable.person_holder_logout_x2)).into(civ_head_user);
                }else{
                    ToastUtil.toastShort(resUserInfoBean.getError_msg());
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                ToastUtil.toastShort(getString(R.string.error_server_message));
            }
        }));
    }

    @OnClick({R.id.civ_head_user})
    public void doClick(View view){
        switch (view.getId()){
            case R.id.civ_head_user:
                chooseOrTakePhoto();
                break;
        }
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
                        MyLog.logMy(path);
                        showLoadingDialog();
                        uploadPicture(path);
                    }else{
                        ToastUtil.toastShort("获取图片失败！");
                    }
                    break;
            }
        }
    }

    private void uploadPicture(String path) {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        if (null != bitmap){
            String encodeImageWithBase64 = SystemUtil.encodeImageWithBase64(bitmap);
            MyLog.logMy(encodeImageWithBase64);
            addSubscribe(dataManager.uploadUserImage(dataManager.getUserToken(),encodeImageWithBase64)
            .compose(RxUtil.<ResUploadUserImageBean>rxSchedulerHelper())
                    .subscribe(new Consumer<ResUploadUserImageBean>() {
                        @Override
                        public void accept(ResUploadUserImageBean resUploadUserImageBean) throws Exception {
                            hideLoadingDialog();
                            if (resUploadUserImageBean.getError_code() == 0){

                                ToastUtil.toastShort("图片上传成功");
                                String avatar = resUploadUserImageBean.getData().getUser().getAvatar();
                                MyLog.logMy(avatar);
                                Glide.with(mContext).load(avatar).into(civ_head_user);
                            }else{
                                ToastUtil.toastShort(resUploadUserImageBean.getError_msg());
                            }

                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            hideLoadingDialog();
                            ToastUtil.toastShort(getString(R.string.error_server_message));
                        }
                    })
            );
        }else{
            ToastUtil.toastShort("获取图片失败！");
        }
    }

}
