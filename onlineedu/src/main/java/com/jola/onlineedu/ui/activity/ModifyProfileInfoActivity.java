package com.jola.onlineedu.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.jola.onlineedu.R;
import com.jola.onlineedu.app.App;
import com.jola.onlineedu.app.MyLog;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.component.ImageLoader;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResUpdatepersonalInfoBean;
import com.jola.onlineedu.mode.bean.response.ResUploadUserImageBean;
import com.jola.onlineedu.mode.bean.response.ResUserInfoBean;
import com.jola.onlineedu.mode.bean.response.ResponseSimpleResult;
import com.jola.onlineedu.mode.http.MyApis;
import com.jola.onlineedu.util.AddressPickTask;
import com.jola.onlineedu.util.RxUtil;
import com.jola.onlineedu.util.SystemUtil;
import com.jola.onlineedu.util.ToastUtil;
import com.jola.onlineedu.widget.DialogInputOneConentView;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.addapp.pickers.entity.City;
import cn.addapp.pickers.entity.County;
import cn.addapp.pickers.entity.Province;
import cz.msebera.android.httpclient.Header;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.functions.Consumer;

public class ModifyProfileInfoActivity extends SimpleActivity {


    @Inject
    DataManager mDataManager;


    @BindView(R.id.toolbar_view)
    Toolbar toolbar;
    @BindView(R.id.tv_pet_name)
    TextView tv_pet_name;
    @BindView(R.id.tv_phone_no)
    TextView tv_phone_no;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_school)
    TextView tv_school;
    @BindView(R.id.tv_teach_course)
    TextView tv_teach_course;

//    @BindView(R.id.et_input_password)
//    EditText et_input_password;

    @BindView(R.id.civ_head_user)
    CircleImageView civ_head_user;

    private int mCurrentIndex = -1;
    String mTitle;
    private DialogInputOneConentView dialogInputOneConentView;

    private final int Tag_Pet_Name = 1;
    private final int Tag_Phoneno = 2;
    private final int Tag_School = 3;
    private final int Tag_Teach_Course = 4;
    private String provinceText;
    private String cityText;
    private String areaText;
    private String provinceCode;
    private String cityCode;
    private String areaCode;


    @Override
    protected int getLayout() {
        return R.layout.activity_modify_user_profile;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolbar, getString(R.string.modify_person_info));
        getActivityComponent().inject(this);
        initData();
    }

    private void initData() {
        String userAvater = mDataManager.getUserAvater();
        ImageLoader.load(this,userAvater,civ_head_user);
        tv_pet_name.setText(mDataManager.getUserName());
        tv_phone_no.setText(mDataManager.getUserPhone());
        tv_address.setText(mDataManager.getUserAddress());
        tv_school.setText(mDataManager.getUserSchool());
        tv_teach_course.setText(mDataManager.getUserTeachCourse());
    }

    @Override
    protected void onResume() {
        super.onResume();
        asyncUserInfo();
    }

    private void asyncUserInfo() {
        App.getmAsyncHttpClient().addHeader(MyApis.TAG_AUTHORIZATION, mDataManager.getUserToken());
        App.getmAsyncHttpClient().get("http://yunketang.dev.attackt.com/api/v1/user/myprofile/", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                ResUserInfoBean resUserInfoBean = new Gson().fromJson(new String(responseBody), ResUserInfoBean.class);
                int error_code = resUserInfoBean.getError_code();
                if (error_code == 0){
                    String mobile = resUserInfoBean.getData().getUser().getMobile();
                    tv_phone_no.setText(mobile);
                    mDataManager.setUserPhone(mobile);
                }else{
                    ToastUtil.toastShort(resUserInfoBean.getError_msg());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                ToastUtil.toastShort(getString(R.string.error_server_message));
            }
        });
    }

    @OnClick({
            R.id.rl_head,
            R.id.rl_pet_name,
            R.id.rl_phone_no,
            R.id.rl_address,
            R.id.rl_school,
            R.id.rl_teach_course,
            R.id.tv_modify_info

    })
    public void doClick(View view){
        switch (view.getId()){
            case R.id.rl_head:
                uploadUserHead();
                break;
            case R.id.rl_pet_name:
                mCurrentIndex = Tag_Pet_Name;
                mTitle = "请输入昵称";
                showInpuDialog();
                break;
            case R.id.rl_phone_no:
//                mCurrentIndex = Tag_Phoneno;
//                mTitle = "请输入手机号";
//                showInpuDialog();
                startActivity(new Intent(this,ModifyPhoneNoActivity.class));
                break;
            case R.id.rl_address:
                showAddressChoose();
                break;
            case R.id.rl_school:
                mCurrentIndex = Tag_School;
                mTitle = "请输入学校";
                showInpuDialog();
                break;
            case R.id.rl_teach_course:
                mCurrentIndex = Tag_Teach_Course;
                mTitle = "请输入所教课程";
                showInpuDialog();
                break;

            case R.id.tv_modify_info:
                confirmModify();
                break;
        }
    }

    private void showAddressChoose() {
            AddressPickTask task = new AddressPickTask(this);
            task.setHideProvince(false);
            task.setHideCounty(false);
            task.setCallback(new AddressPickTask.Callback() {
                @Override
                public void onAddressInitFailed() {
                    ToastUtil.toastShort("地址数据初始化失败");
                }

                @Override
                public void onAddressPicked(Province province, City city, County county) {
                    if (county == null) {
                        ToastUtil.toastShort(province.getAreaName() + city.getAreaName());
                    } else {
                        provinceCode = province.getAreaId();
                        provinceText = province.getAreaName();

                        cityText = city.getAreaName();
                        cityCode = city.getAreaId();

                        areaText = county.getAreaName();
                        areaCode = county.getAreaId();

                        tv_address.setText(provinceText + " "+cityText + " "+ areaText);

                    }
                }
            });
            task.execute("北京市", "北京市", "东城区");
    }

    private void confirmModify() {
        String userAvater = mDataManager.getUserAvater();
        String petName = tv_pet_name.getText().toString();
        String phoneno = tv_phone_no.getText().toString();
        String address = tv_address.getText().toString();
        final String schoolInput = tv_school.getText().toString();
        String teachCourse = tv_teach_course.getText().toString();
//        String passwordInput = et_input_password.getText().toString();
        if (TextUtils.isEmpty(userAvater)
                ||TextUtils.isEmpty(petName)
                ||TextUtils.isEmpty(phoneno)
                ||TextUtils.isEmpty(address)
                ||TextUtils.isEmpty(schoolInput)
                ||TextUtils.isEmpty(teachCourse)
//                ||TextUtils.isEmpty(passwordInput)
                ){
            ToastUtil.toastShort("请完成所有输入项后，再确认修改！");
            return;
        }


//        HashMap<String, String> map = new HashMap<>();
//        map.put("mobile",phoneno);
//        map.put("name",petName);
//        map.put("avatar",userAvater);
//        map.put("password",passwordInput);
//        map.put("teaching_courses",teachCourse);
//        map.put("school_name",schoolInput);
//        map.put("province_text",province);
//        map.put("city_text",city);
//        map.put("cdistrict_text",area);
//        map.put("province",provinceCode);
//        map.put("city",cityCode);
//        map.put("district",areaCode);
//        showLoadingDialog();
//        addSubscribe(mDataManager.updateUserProfile(map)
//        .compose(RxUtil.<ResUpdatepersonalInfoBean>rxSchedulerHelper())
//                .subscribe(new Consumer<ResUpdatepersonalInfoBean>() {
//                    @Override
//                    public void accept(ResUpdatepersonalInfoBean responseSimpleResult) throws Exception {
//                        hideLoadingDialog();
//                        if (responseSimpleResult.getError_code() == 0){
//                            ToastUtil.toastShort("资料修改成功！");
//                            ResUpdatepersonalInfoBean.DataBean data = responseSimpleResult.getData();
//                            String token = data.getToken();
//                            mDataManager.setUserToken(token);
//                            ResUpdatepersonalInfoBean.DataBean.UserBean user = data.getUser();
//                            String name = user.getName();
//                            mDataManager.setUserName(name);
//                            mDataManager.setUserAvater(user.getAvatar());
//                            mDataManager.setUserTeachCourse(user.getTeaching_courses());
//                            mDataManager.setUserPhone(user.getMobile());
//                            ResUpdatepersonalInfoBean.DataBean.UserBean.SchoolBean school = user.getSchool();
//                            mDataManager.setUserAddress(school.getProvince_text() +"  "+school.getCity_text() +"  "+school.getDistrict_text());
//                        }else {
//                            ToastUtil.toastLong(responseSimpleResult.getError_msg());
//                        }
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        hideLoadingDialog();
//                        tipServerError();
//                    }
//                })
//        );


        showLoadingDialog();
        RequestParams map = new RequestParams();
//        map.put("mobile",phoneno);
        map.put("name",petName);
        map.put("avatar",userAvater);
//        map.put("password",passwordInput);
        map.put("teaching_courses",teachCourse);
        map.put("school_name",schoolInput);
        map.put("province_text",provinceText);
        map.put("city_text",cityText);
        map.put("cdistrict_text",areaText);
        map.put("province",provinceCode);
        map.put("city",cityCode);
        map.put("district",areaCode);
        App.getmAsyncHttpClient().addHeader(MyApis.TAG_AUTHORIZATION, mDataManager.getUserToken());
        App.getmAsyncHttpClient().put("http://yunketang.dev.attackt.com/api/v1/user/change/profile/", map, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                ResUpdatepersonalInfoBean resultBean = new Gson().fromJson(new String(responseBody), ResUpdatepersonalInfoBean.class);
                hideLoadingDialog();
                if (resultBean.getError_code() == 0){
                    ToastUtil.toastShort("资料修改成功！");
                    ResUpdatepersonalInfoBean.DataBean data = resultBean.getData();
                    String token = data.getToken();
                    mDataManager.setUserToken(token);
                    ResUpdatepersonalInfoBean.DataBean.UserBean user = data.getUser();
                    String name = user.getName();
                    mDataManager.setUserName(name);
                    mDataManager.setUserAvater(user.getAvatar());
                    mDataManager.setUserTeachCourse(user.getTeaching_courses());
                    mDataManager.setUserPhone(user.getMobile());
                    ResUpdatepersonalInfoBean.DataBean.UserBean.SchoolBean school = user.getSchool();
                    mDataManager.setUserAddress(school.getProvince_text() +"  "+school.getCity_text() +"  "+school.getDistrict_text());
                }else {
                    ToastUtil.toastLong(resultBean.getError_msg());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                hideLoadingDialog();
                tipServerError();
            }
        });


    }

    private void showInpuDialog(){
        dialogInputOneConentView = new DialogInputOneConentView(this, mTitle, "取消", "确认", new DialogInputOneConentView.ClickListenerInterface() {
            @Override
            public void doLeft() {
                if (null != dialogInputOneConentView){
                    dialogInputOneConentView.dismiss();
                }
            }

            @Override
            public void doRight() {
                String inputContent = dialogInputOneConentView.getInputContent();
                if (TextUtils.isEmpty(inputContent)){
                    ToastUtil.toastShort("请填写内容后，再确认修改！");
                }else{
                    confirmChange(inputContent);
                    if (null != dialogInputOneConentView){
                        dialogInputOneConentView.dismiss();
                    }
                }
            }
        });
        dialogInputOneConentView.show();
    }

    private void confirmChange(String inputContent) {
        switch (mCurrentIndex){
            case Tag_Pet_Name:
                tv_pet_name.setText(inputContent);
                break;
            case Tag_Phoneno:
                tv_phone_no.setText(inputContent);
                break;
            case Tag_School:
                tv_school.setText(inputContent);
                break;
            case Tag_Teach_Course:
                tv_teach_course.setText(inputContent);
                break;
        }
    }

    private void uploadUserHead() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
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
//                        MyLog.logMy(path);
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
            addSubscribe(mDataManager.uploadUserImage(mDataManager.getUserToken(),encodeImageWithBase64)
                            .compose(RxUtil.<ResUploadUserImageBean>rxSchedulerHelper())
                            .subscribe(new Consumer<ResUploadUserImageBean>() {
                                @Override
                                public void accept(ResUploadUserImageBean resUploadUserImageBean) throws Exception {
                                    hideLoadingDialog();
                                    if (resUploadUserImageBean.getError_code() == 0){

                                        ToastUtil.toastShort("图片上传成功");
                                        String avatar = resUploadUserImageBean.getData().getUser().getAvatar();
//                                MyLog.logMy(avatar);
                                        Glide.with(mContext).load(avatar).into(civ_head_user);
                                        mDataManager.setUserAvater(avatar);
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
