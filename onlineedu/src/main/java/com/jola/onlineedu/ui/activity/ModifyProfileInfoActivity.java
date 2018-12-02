package com.jola.onlineedu.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.jola.onlineedu.mode.bean.response.ResClassListBean;
import com.jola.onlineedu.mode.bean.response.ResGradeListBean;
import com.jola.onlineedu.mode.bean.response.ResMajorListBean;
import com.jola.onlineedu.mode.bean.response.ResSchoolListBean;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.addapp.pickers.entity.City;
import cn.addapp.pickers.entity.County;
import cn.addapp.pickers.entity.Province;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.listeners.OnSingleWheelListener;
import cn.addapp.pickers.picker.SinglePicker;
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
    @BindView(R.id.tv_major)
    TextView tv_major;
    @BindView(R.id.tv_grade)
    TextView tv_grade;
    @BindView(R.id.tv_the_class)
    TextView tv_the_class;
    @BindView(R.id.tv_department)
    TextView tv_department;
    @BindView(R.id.tv_student_id)
    TextView tv_student_id;
    @BindView(R.id.tv_teach_course)
    TextView tv_teach_course;
    @BindView(R.id.civ_head_user)
    CircleImageView civ_head_user;


    @BindView(R.id.rl_grade)
    RelativeLayout rl_grade;
    @BindView(R.id.rl_major)

    RelativeLayout rl_major;

    @BindView(R.id.rl_the_class)
    RelativeLayout rl_the_class;

    @BindView(R.id.rl_department)
    RelativeLayout rl_department;


    @BindView(R.id.rl_student_id)
    RelativeLayout rl_student_id;

    @BindView(R.id.rl_teach_course)
    RelativeLayout rl_teach_course;



    private int mCurrentIndex = -1;
    String mTitle;
    private DialogInputOneConentView dialogInputOneConentView;

    private final int Tag_Pet_Name = 1;
    private final int Tag_Phoneno = 2;
    private final int Tag_School = 3;
    private final int Tag_Teach_Course = 4;
    private final int Tag_Department = 5;
    private final int Tag_Student_id = 6;
    private final int Tag_Grade = 7;
    private final int Tag_Class = 8;
    private final int Tag_Major = 9;

    private String provinceText;
    private String cityText;
    private String areaText;

    private String provinceCode;
    private String cityCode;
    private String areaCode;

    private int mChooseType = 0;

    private Map<String,Integer> map = new HashMap<>();
    private String mSchoolId;
    private String mMajorId;
    private String mGradeId;
    private String mClassId;

    ResUserInfoBean.DataBean.UserBean user;



    int userRole = 1;

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

        userRole = mDataManager.getUserRole();
        if (userRole == 1){
            showStudentView();
        }else{
            showTeacherView();
        }

        String userInfoJson = mDataManager.getUserInfoJson();
        ResUserInfoBean resUserInfoBean = new Gson().fromJson(userInfoJson, ResUserInfoBean.class);

        user = resUserInfoBean.getData().getUser();

        ImageLoader.load(this,user.getAvatar(),civ_head_user);

        tv_address.setText(mDataManager.getUserAddress());

        tv_pet_name.setText(user.getUsername());

        tv_phone_no.setText(mDataManager.getUserPhone());

        tv_school.setText(user.getSchool().getName());
        mSchoolId = user.getSchool().getId();
        tv_major.setText(user.getMajor().getName());
        mMajorId = (user.getMajor().getId());
        tv_grade.setText(user.getGrade().getName());
        mGradeId = (user.getGrade().getId());
        tv_the_class.setText(user.getClassX().getName());
        mClassId = (user.getClassX().getId());
        tv_department.setText(user.getDepartment());
        tv_student_id.setText(user.getStudent_code());

//        tv_teach_course.setText(mDataManager.getUserTeachCourse());
        tv_teach_course.setText(user.getTeaching_courses());

        provinceCode = user.getProvince() +"";
        provinceText = user.getProvince_text();
        cityCode = user.getCity()+"";
        cityText = user.getCity_text();
        areaCode = user.getDistrict() +"";
        areaText = user.getDistrict_text();


    }



    private void showStudentView() {
        rl_major.setVisibility(View.VISIBLE);
        rl_grade.setVisibility(View.VISIBLE);
        rl_the_class.setVisibility(View.VISIBLE);
        rl_department.setVisibility(View.VISIBLE);
        rl_student_id.setVisibility(View.VISIBLE);
    }

    private void showTeacherView() {
        rl_teach_course.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        asyncUserInfo();
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
            R.id.tv_modify_info,

            R.id.rl_major,
            R.id.rl_grade,
            R.id.rl_the_class,
            R.id.rl_department,
            R.id.rl_student_id,

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
                showChooseList(Tag_School);
                break;
            case R.id.rl_major:
                showChooseList(Tag_Major);
                break;
            case R.id.rl_grade:
                showChooseList(Tag_Grade);
                break;

            case R.id.rl_the_class:
                showChooseList(Tag_Class);
                break;

            case R.id.rl_department:
                mCurrentIndex = Tag_Department;
                mTitle = "请输入所在 系/科";
                showInpuDialog();
                break;

            case R.id.rl_student_id:
                mCurrentIndex = Tag_Student_id;
                mTitle = "请输入学号";
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

    private void showChooseList(int chooseType) {
        mChooseType = chooseType;
        switch (mChooseType){
            case Tag_School:
                loadSchoolData();
                break;
            case Tag_Major:
                loadMajorData();
                break;
            case Tag_Grade:
                loadGradeData();
                break;
            case Tag_Class:
                loadClassData();
                break;
        }
    }




    private void loadSchoolData() {
        showLoadingDialog();
        mDataManager.getSchoolList(mDataManager.getUserToken(),1,100)
                .compose(RxUtil.<ResSchoolListBean>rxSchedulerHelper())
               .subscribe(new Consumer<ResSchoolListBean>() {
                   @Override
                   public void accept(ResSchoolListBean resSchoolListBean) throws Exception {
                       map.clear();
                       List<ResSchoolListBean.DataBean.SchoolsBean> schools = resSchoolListBean.getData().getSchools();
                       for (ResSchoolListBean.DataBean.SchoolsBean tempBean : schools){
                            map.put(tempBean.getName(),tempBean.getId());
                       }
                       onOptionPicker();
                       hideLoadingDialog();
                   }
               }, new Consumer<Throwable>() {
                   @Override
                   public void accept(Throwable throwable) throws Exception {
                       hideLoadingDialog();
                       tipServerError();
                   }
               });
    }

    private void loadMajorData() {
        showLoadingDialog();
        mDataManager.getMajorList(mDataManager.getUserToken(),1,100)
                .compose(RxUtil.<ResMajorListBean>rxSchedulerHelper())
                .subscribe(new Consumer<ResMajorListBean>() {
                    @Override
                    public void accept(ResMajorListBean resSchoolListBean) throws Exception {
                        map.clear();
                        List<ResMajorListBean.DataBean.MajorsBean> majors = resSchoolListBean.getData().getMajors();
                        for (ResMajorListBean.DataBean.MajorsBean tempBean : majors){
                            map.put(tempBean.getName(),tempBean.getId());
                        }
                        onOptionPicker();
                        hideLoadingDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        hideLoadingDialog();
                        tipServerError();
                    }
                });

    }


    private void loadGradeData() {
        showLoadingDialog();
        mDataManager.getGradeList(mDataManager.getUserToken(),1,100)
                .compose(RxUtil.<ResGradeListBean>rxSchedulerHelper())
                .subscribe(new Consumer<ResGradeListBean>() {
                    @Override
                    public void accept(ResGradeListBean resSchoolListBean) throws Exception {
                        map.clear();
                        List<ResGradeListBean.DataBean.GradesBean> grades = resSchoolListBean.getData().getGrades();
                        for (ResGradeListBean.DataBean.GradesBean tempBean : grades){
                            map.put(tempBean.getName(),tempBean.getId());
                        }
                        onOptionPicker();
                        hideLoadingDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        hideLoadingDialog();
                        tipServerError();
                    }
                });

    }


    private void loadClassData() {
        showLoadingDialog();
        mDataManager.getClassList(mDataManager.getUserToken(),1,100)
                .compose(RxUtil.<ResClassListBean>rxSchedulerHelper())
                .subscribe(new Consumer<ResClassListBean>() {
                    @Override
                    public void accept(ResClassListBean resSchoolListBean) throws Exception {

                        map.clear();
                        List<ResClassListBean.DataBean.ClassesBean> classes = resSchoolListBean.getData().getClasses();
                        for (ResClassListBean.DataBean.ClassesBean tempBean : classes){
                            map.put(tempBean.getName(),tempBean.getId());
                        }
                        onOptionPicker();
                        hideLoadingDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        hideLoadingDialog();
                        tipServerError();
                    }
                });
    }


    public void onOptionPicker() {

        ArrayList<String> list = new ArrayList<>();

        for (Map.Entry<String,Integer> entry :map.entrySet()){
            list.add(entry.getKey());
        }
        SinglePicker<String> picker = new SinglePicker<>(this, list);
        picker.setCanLoop(false);//不禁用循环
        picker.setLineVisible(true);
        picker.setTextSize(18);
        picker.setSelectedIndex(0);
        picker.setWheelModeEnable(false);
        //启用权重 setWeightWidth 才起作用
        picker.setLabel("");
        picker.setWeightEnable(false);
        picker.setWeightWidth(1);
        picker.setSelectedTextColor(Color.GREEN);//前四位值是透明度
        picker.setUnSelectedTextColor(Color.GRAY);
        picker.setOnSingleWheelListener(new OnSingleWheelListener() {
            @Override
            public void onWheeled(int index, String item) {
//                ToastUtil.toastShort("index=" + index + ", item=" + item);
            }
        });
        picker.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
//                ToastUtil.toastShort("index=" + index + ", item=" + item);
                Integer key = map.get(item);
                switch (mChooseType){
                    case Tag_School:
                        mSchoolId = key +"";
                        tv_school.setText(item);
                        break;
                    case Tag_Major:
                        mMajorId = key+"";
                        tv_major.setText(item);
                        break;
                    case Tag_Grade:
                        mGradeId = key+"";
                        tv_grade.setText(item);
                        break;
                    case Tag_Class:
                        mClassId = key+"";
                        tv_the_class.setText(item);
                        break;
                    case Tag_Department:
//                        mDepartid = key;
                        tv_department.setText(item);
                        break;
                }
            }
        });
        picker.show();
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
        String schoolInput = tv_school.getText().toString();
        String major = tv_major.getText().toString();
        String grade = tv_grade.getText().toString();
        String classOf = tv_the_class.getText().toString();
        String department = tv_department.getText().toString();
        String teachCourse = tv_teach_course.getText().toString();
        String student_id = tv_student_id.getText().toString();

//        TextUtils.isEmpty(userAvater)
        if (
                TextUtils.isEmpty(petName)
                ||TextUtils.isEmpty(phoneno)
                ||TextUtils.isEmpty(address)
                ||TextUtils.isEmpty(schoolInput)
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



        RequestParams map = new RequestParams();
        map.put("name",petName);
        map.put("avatar",userAvater);
        map.put("school_name",schoolInput);
        map.put("school",mSchoolId);
        map.put("province_text",provinceText);
        map.put("city_text",cityText);
        map.put("district_text",areaText);
        map.put("province",provinceCode);
        map.put("city",cityCode);
        map.put("district",areaCode);

        if (userRole != 1){
            if (TextUtils.isEmpty(teachCourse)){
                ToastUtil.toastShort("请完成所有输入项后，再确认修改！");
                return;
            }
            map.put("teaching_courses",teachCourse);
        }else{

            if (TextUtils.isEmpty(major)
                    ||TextUtils.isEmpty(grade)
                    ||TextUtils.isEmpty(classOf)
                    ||TextUtils.isEmpty(department)
                    ||TextUtils.isEmpty(student_id)
                    ){
                ToastUtil.toastShort("请完成所有输入项后，再确认修改！");
                return;
            }
            map.put("student_code",student_id);
            map.put("grade",mGradeId);
            map.put("class",mClassId);
            map.put("major",mMajorId);
            map.put("department",department);
        }
        showLoadingDialog();
        App.getmAsyncHttpClient().addHeader(MyApis.TAG_AUTHORIZATION, mDataManager.getUserToken());
        App.getmAsyncHttpClient().put("http://yunketang.dev.attackt.com/api/v1/user/change/profile/", map, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String resultJsonStr = new String(responseBody);
                ResUpdatepersonalInfoBean resultBean = new Gson().fromJson(resultJsonStr, ResUpdatepersonalInfoBean.class);
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

                    mDataManager.setUserInfoJson(resultJsonStr);

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
            case Tag_Student_id:
                tv_student_id.setText(inputContent);
                break;
            case Tag_Department:
                tv_department.setText(inputContent);
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
