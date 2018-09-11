package com.jola.onlineedu.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jola.onlineedu.R;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResForumTypeBean;
import com.jola.onlineedu.mode.bean.response.ResUploadFourmImageBean;
import com.jola.onlineedu.mode.bean.response.ResponseSimpleResult;
import com.jola.onlineedu.ui.adapter.GridImageAdapter;
import com.jola.onlineedu.util.RxUtil;
import com.jola.onlineedu.util.ToastUtil;
import com.jola.onlineedu.widget.FullyGridLayoutManager;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ForumPublishActivity extends SimpleActivity {



    @Inject
    DataManager dataManager;

    @BindView(R.id.toolbar_view)
    Toolbar toolbar;
    @BindView(R.id.rv_images_forum)
    RecyclerView recyclerView;

    @BindView(R.id.spinner_forum_content)
    Spinner spinner_forum_content;

    @BindView(R.id.et_input_title_forum)
    EditText et_input_title_forum;
    @BindView(R.id.et_input_content_forum)
    EditText et_input_content_forum;


    private int maxSelectImages = 3;
    private int themeId = R.style.picture_default_style;
    private GridImageAdapter gridImageAdapter;
    private List<LocalMedia> selectList = new ArrayList<>();


    private ArrayList<String> lableList;
    private HashMap<String, Integer> map_lableIds;
    private String curForumTypeId;

    private List<String> listUploadedImageUrls = new ArrayList<>();
    private int i = 0;


    @Override
    protected int getLayout() {
        return R.layout.activity_forum_publish;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolbar,getString(R.string.forum_publish));
        getActivityComponent().inject(this);

        getForumTypeInfo();

        spinner_forum_content.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (null != lableList){
                    String forumTypeText = lableList.get(position);
                    curForumTypeId = map_lableIds.get(forumTypeText)+"";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        gridImageAdapter = new GridImageAdapter(this, onAddPicClickListener,maxSelectImages);
        gridImageAdapter.setList(selectList);
        recyclerView.setAdapter(gridImageAdapter);
        gridImageAdapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).themeStyle(themeId).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(ForumPublishActivity.this).themeStyle(themeId).openExternalPreview(position, selectList);
                            break;
//                        case 2:
//                            // 预览视频
//                            PictureSelector.create(ForumPublishActivity.this).externalPictureVideo(media.getPath());
//                            break;
//                        case 3:
//                            // 预览音频
//                            PictureSelector.create(ForumPublishActivity.this).externalPictureAudio(media.getPath());
//                            break;
                    }
                }
            }
        });

        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
//        RxPermissions permissions = new RxPermissions(this);
//        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//            }
//
//            @Override
//            public void onNext(Boolean aBoolean) {
//                if (aBoolean) {
//                    PictureFileUtils.deleteCacheDirFile(ForumPublishActivity.this);
//                } else {
//                    Toast.makeText(ForumPublishActivity.this,
//                            getString(R.string.picture_jurisdiction), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//            }
//
//            @Override
//            public void onComplete() {
//            }
//        });

        addSubscribe(new RxPermissions(this).request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .subscribe(new Consumer<Boolean>() {
                @Override
                public void accept(Boolean aBoolean) throws Exception {
                    if (aBoolean){
                        PictureFileUtils.deleteCacheDirFile(ForumPublishActivity.this);
                    }else{
                        ToastUtil.toastShort(getString(R.string.picture_jurisdiction));
                    }
                }
            })
        );





    }

    private void getForumTypeInfo() {
        addSubscribe(dataManager.getForumTypeInfo()
                .compose(RxUtil.<ResForumTypeBean>rxSchedulerHelper())
                .subscribe(new Consumer<ResForumTypeBean>() {
                    @Override
                    public void accept(ResForumTypeBean resForumTypeBean) throws Exception {
                        int error_code = resForumTypeBean.getError_code();
                        lableList = new ArrayList<>();
                        map_lableIds = new HashMap<>();
                        if (error_code == 0) {
                            ResForumTypeBean.DataBean data = resForumTypeBean.getData();
                            List<ResForumTypeBean.DataBean.TypesBean> types = data.getTypes();
                            for (ResForumTypeBean.DataBean.TypesBean typesBean : types) {
                                int id = typesBean.getId();
                                String name = typesBean.getName();
                                lableList.add(name);
                                map_lableIds.put(name, id);
                            }
                            ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(ForumPublishActivity.this, android.R.layout.simple_list_item_1, lableList);
                            spinner_forum_content.setAdapter(stringArrayAdapter);
                        }else{
                            ToastUtil.toastShort(resForumTypeBean.getError_msg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                       ToastUtil.toastShort(getString(R.string.error_server_message));
                    }
                }));
    }

    @OnClick(R.id.send_icon_in_tool)
    public void confirmSendForum(View view){

        if (null != selectList && selectList.size() > 0){
            listUploadedImageUrls = new ArrayList<String>();
            showLoadingDialog();
            for ( i = 0;i<selectList.size();i++){
                String path = selectList.get(i).getPath();
                File file = new File(path);
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part[]  filePart = new MultipartBody.Part[1];
                filePart[0] = MultipartBody.Part.createFormData("img", file.getName(), requestBody);
//                file[1] = MultipartBody.Part.createFormData("file", file1.getName(), requestFile);
                addSubscribe(dataManager.uploadForumImage(filePart)
                        .compose(RxUtil.<ResUploadFourmImageBean>rxSchedulerHelper())
                        .subscribe(new Consumer<ResUploadFourmImageBean>() {
                            @Override
                            public void accept(ResUploadFourmImageBean resultBean) throws Exception {
                                int error_code = resultBean.getError_code();
                                if (error_code == 0) {
                                    String img_url = resultBean.getData().getImg_url();
                                    if (!TextUtils.isEmpty(img_url)){
                                        listUploadedImageUrls.add(img_url);
                                    }
                                    if (i == selectList.size() - 1){
                                        publishforumContent();
                                    }
                                }else{
                                    ToastUtil.toastShort(resultBean.getError_msg());
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                hideLoadingDialog();
                                ToastUtil.toastShort("上传图片失败！ "+getString(R.string.error_server_message));
                            }
                        }));

            }
        }else{
            publishforumContent();
        }






    }

    private void publishforumContent(){

        if (null == curForumTypeId || curForumTypeId.length() == 0){
            ToastUtil.toastShort(getString(R.string.tip_no_forum_type_selected));
            return;
        }
        String title = et_input_title_forum.getText().toString();
        if (TextUtils.isEmpty(title)){
            ToastUtil.toastShort(getString(R.string.tip_no_forum_title));
            return;
        }
        String content = et_input_content_forum.getText().toString();
        if (TextUtils.isEmpty(title)){
            ToastUtil.toastShort(getString(R.string.tip_no_forum_content));
            return;
        }

        addSubscribe(dataManager.publishForumContent(curForumTypeId,title,content,listUploadedImageUrls)
                .compose(RxUtil.<ResponseSimpleResult>rxSchedulerHelper())
                .subscribe(new Consumer<ResponseSimpleResult>() {
                    @Override
                    public void accept(ResponseSimpleResult resultBean) throws Exception {
//                        int error_code = resultBean.getError_code();
//                        if (error_code == 0) {
//
//                        }else{
//
//                        }
                        ToastUtil.toastShort(resultBean.getError_msg());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtil.toastShort(getString(R.string.error_server_message));
                    }
                }));

    }

    public void cleranCache(){
        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        RxPermissions permissions = new RxPermissions(this);
        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    PictureFileUtils.deleteCacheDirFile(ForumPublishActivity.this);
                } else {
                    Toast.makeText(ForumPublishActivity.this,
                            getString(R.string.picture_jurisdiction), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    gridImageAdapter.setList(selectList);
                    gridImageAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {

//
//            PictureSelector.create(ForumPublishActivity.this)
//                    .openGallery(PictureMimeType.ofImage())
//                    .forResult(PictureConfig.CHOOSE_REQUEST);
//

            // 进入相册 以下是例子：不需要的api可以不写
                PictureSelector.create(ForumPublishActivity.this)
                        .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                        .theme(themeId)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                        .maxSelectNum(maxSelectImages)// 最大图片选择数量
                        .minSelectNum(0)// 最小选择数量
                        .imageSpanCount(3)// 每行显示个数
                        .selectionMode(PictureConfig.MULTIPLE)// 多选 PictureConfig.MULTIPLE or 单选 PictureConfig.SINGLE
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
                        .selectionMedia(selectList)// 是否传入已选图片
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

    };


}
