package com.jola.onlineedu.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.gson.Gson;
import com.jola.onlineedu.R;
import com.jola.onlineedu.app.App;
import com.jola.onlineedu.app.Constants;
import com.jola.onlineedu.base.SimpleActivity;
import com.jola.onlineedu.mode.DataManager;
import com.jola.onlineedu.mode.bean.response.ResUserLogin;
import com.jola.onlineedu.mode.http.MyApis;
import com.jola.onlineedu.util.RxUtil;
import com.jola.onlineedu.util.StatusBarUtil;
import com.jola.onlineedu.util.SystemUtil;
import com.jola.onlineedu.util.ToastUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

public class LoginActivity extends SimpleActivity {


    @Inject
    DataManager mDataManager;

    @BindView(R.id.et_account)
    EditText et_account;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.cb_remember_password)
    CheckBox cb_remember_password;

    private IWXAPI iwxapi;

    private Tencent mTencent;
    BaseUiListener baseUiListener;





    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData() {
        StatusBarUtil.setStatusBarTranslucent(this);
        getActivityComponent().inject(this);
        String userName = mDataManager.getUserName();
        if (!TextUtils.isEmpty(userName)){
            et_account.setText(userName);
        }
        String userPassword = mDataManager.getUserPassword();
        if (!TextUtils.isEmpty(userPassword)){
            et_password.setText(userPassword);
        }
        registerToWeChat();
        registerToQq();
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideLoadingDialog();
    }

    /**
     * qq 登录、快速支付登录、应用分享、应用邀请等接口
     */
    private class BaseUiListener implements IUiListener {

        //            {
//                "ret":0,
//                    "pay_token":"xxxxxxxxxxxxxxxx",
//                    "pf":"openmobile_android",
//                    "expires_in":"7776000",
//                    "openid":"xxxxxxxxxxxxxxxxxxx",
//                    "pfkey":"xxxxxxxxxxxxxxxxxxx",
//                    "msg":"sucess",
//                    "access_token":"xxxxxxxxxxxxxxxxxxxxx"
//            }
        @Override
        public void onComplete(Object o) {
            try {
                JSONObject jo = (JSONObject) o;
                int ret = jo.getInt("ret");
                if (ret == 0) {
                    String openID = jo.getString("openid");
                    Log.e("jola","qq_openId"+openID);
                    doQQLogin(openID);
//                    String accessToken = jo.getString("access_token");
//                    String expires = jo.getString("expires_in");
//                    mTencent.setOpenId(openID);
//                    mTencent.setAccessToken(accessToken, expires);
                }
            } catch (Exception e) {
//                Util.showToast(LoginActivity.this,"qq 登陆失败！",false);
                ToastUtil.toastShort("qq 登陆失败！");
               hideLoadingDialog();
            }
        }

        @Override

        public void onError(UiError e) {

            hideLoadingDialog();
//            Util.showToast(LoginActivity.this,e.errorMessage+":"+e.errorDetail,false);
            ToastUtil.toastShort(e.errorMessage+":"+e.errorDetail);

        }

        @Override

        public void onCancel() {
//            Util.showToast(LoginActivity.this,"cancel",false);
            ToastUtil.toastShort("您取消qq登录");
        }

    }

    private void registerToQq() {
        baseUiListener = new BaseUiListener();
        mTencent = Tencent.createInstance(Constants.Qq_app_id, this.getApplicationContext());
    }

    private void doQQLogin(final String qqOpenId) {
        showLoadingDialog();

        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("name", null);
//        map.put("avatar", null);
        map.put("qq_uid", qqOpenId);
        mDataManager.thirdpartLogin(map)
                .compose(RxUtil.<String>rxSchedulerHelper())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.print(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        hideLoadingDialog();
                        tipServerError();
                    }
                });
    }



    @OnClick(R.id.tv_login)
    public void login(View view){
        final String account = et_account.getText().toString();
        final String password = et_password.getText().toString();
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)){
            ToastUtil.toastShort("请输入账号和密码后，再重试！");
            return;
        }
        if (!SystemUtil.isNetworkConnected()) {
            ToastUtil.toastShort("当前无网络连接！");
            return;
        }

            showLoadingDialog();
        addSubscribe(mDataManager.getUserLoginInfo(account,password)
            .compose(RxUtil.<ResUserLogin>rxSchedulerHelper())
            .subscribe(new Consumer<ResUserLogin>() {
                @Override
                public void accept(ResUserLogin resUserLogin) {
                    hideLoadingDialog();
                    int error_code = resUserLogin.getError_code();
                    if (error_code == 0) {
                        ToastUtil.toastShort("登陆成功！");
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        mDataManager.setUserPhone(resUserLogin.getData().getUser().getMobile());
                        mDataManager.setUserId(resUserLogin.getData().getUser().getId()+"");
                        mDataManager.setUserToken(resUserLogin.getData().getToken());
                        if (cb_remember_password.isChecked()){
                            mDataManager.setUserName(resUserLogin.getData().getUser().getUsername());
                            mDataManager.setUserPassword(password);
                        }
                        LoginActivity.this.finish();
                    } else {
                        ToastUtil.toastLong(resUserLogin.getError_msg());
                    }
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable){
                    hideLoadingDialog();
                    ToastUtil.toastLong(getString(R.string.error_server_message));
                }
            }));
    }

    @OnClick(R.id.tv_forget_password)
    public void forgetPassword(View view){
//        ToastUtil.shortShow("忘记密码");
        startActivity(new Intent(LoginActivity.this,ForgetPasswordActivity.class));
    }

    @OnClick(R.id.tv_tip_register_green)
    public void register(View view){
//        ToastUtil.toastShort("注册");
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
    }

//    @OnClick(R.id.iv_ali_login)
//    public void aliLogin(View view){
//        startActivity(new Intent(LoginActivity.this, MainActivity.class));
////        startActivity(new Intent(LoginActivity.this, ForumListActivity.class));
//    }

    private void registerToWeChat() {
        iwxapi = WXAPIFactory.createWXAPI(this.getApplicationContext(),Constants.Wx_app_id,true);
        iwxapi.registerApp(Constants.Wx_app_id);
    }

    @OnClick(R.id.iv_qq_login)
    public void qqLogin(View view){
      showLoadingDialog();

        if (!mTencent.isSessionValid())
        {
            mTencent.login(this, "all",baseUiListener);
        }else{
         hideLoadingDialog();
            ToastUtil.toastShort("未检测到QQ客户端，无法完成QQ登录");
        }
    }




    @OnClick(R.id.iv_weibo_login)
    public void weiboLogin(View view){

    }

    @OnClick(R.id.iv_wechat_login)
    public void wechatLogin(View view){
       showLoadingDialog();
        if (!iwxapi.isWXAppInstalled()){
            ToastUtil.toastShort("未检测到微信客户端，无法完成微信登录");
            hideLoadingDialog();
            return;
        }

        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "onlineedu_wechat_login";
        iwxapi.sendReq(req);

    }


}
