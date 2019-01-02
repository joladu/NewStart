package com.jola.onlineedu.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.jola.onlineedu.app.App;
import com.jola.onlineedu.app.Constants;
import com.jola.onlineedu.mode.http.MyApis;
import com.jola.onlineedu.ui.activity.PersonInfoImproveActivity;
import com.jola.onlineedu.util.ToastUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

//
//import com.shengfan.jola.killsaver.activity.BindingActivity;
//import com.shengfan.jola.killsaver.activity.MainActivity;
//import com.shengfan.jola.killsaver.activity.MyApplication;
//import com.shengfan.jola.killsaver.beans.LoginResultBean;
//import com.shengfan.jola.killsaver.constants.Https;
//import com.shengfan.jola.killsaver.utils.SPUtil;
//import com.shengfan.jola.killsaver.utils.Util;


public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

//    private static final int TIMELINE_SUPPORTED_VERSION = 0x21020001;
//    private static final int RETURN_MSG_TYPE_LOGIN = 1;
//    private static final int RETURN_MSG_TYPE_SHARE = 2;

//	private Button gotoBtn, regBtn, launchBtn, checkBtn, payBtn, favButton;

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        api = WXAPIFactory.createWXAPI(this, Constants.Wx_app_id, false);
        api.registerApp(Constants.Wx_app_id);

        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {

    }

    @Override
    public void onResp(BaseResp resp) {

        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
//                Util.showToast(this, "", false);
                ToastUtil.toastShort("您取消了授权！");
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
//                Util.showToast(this, "您取消了操作！", false);
                ToastUtil.toastShort("您取消了操作！");
                break;
            case BaseResp.ErrCode.ERR_OK:
//						//拿到了微信返回的code,立马再去请求access_token
                String code = ((SendAuth.Resp) resp).code;
//                Log.e("jola", "code:" + code);
//                        String state = ((SendAuth.Resp) resp).state;
                getOpenId(code);
                break;
        }
    }

    private void getOpenId(String code) {
        // APP_ID和APP_Secret在微信开发平台添加应用的时候会生成，grant_type 用默认的"authorization_code"即可.
        String urlStr = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + Constants.Wx_app_id+ "&secret=" + Constants.Wx_app_secret+
                "&code=" + code + "&grant_type=authorization_code";

        App.getmAsyncHttpClient().get(this, urlStr, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String resultStr = new String(responseBody);
                JSONObject obj;
                try {
                    obj = new JSONObject(resultStr);
                    String openid = obj.getString("openid");
                    doWeChatLogin(openid);
                } catch (JSONException e) {
//                    Util.showToast(WXEntryActivity.this, "微信登录异常！", false);
                    ToastUtil.toastShort("微信登录异常！");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                Util.showToast(WXEntryActivity.this, "网络请求失败！", false);
                ToastUtil.toastShort("网络请求失败！");
            }
        });

    }


    private void doWeChatLogin(final String openId) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("file", "login");
        long curSeconds = System.currentTimeMillis() / 1000;
        App.getmAsyncHttpClient().post(MyApis.HOST, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                LoginResultBean loginResultBean = new Gson().fromJson(new String(responseBody), LoginResultBean.class);
//                int code = loginResultBean.getCode();
//                if (code == 1) {
//                    Util.showToast(WXEntryActivity.this, "微信登录成功！", false);
//                    SharedPreferences.Editor edit = SPUtil.getAppSPEdit();
//                    edit.putInt(SPUtil.UserIdTag, loginResultBean.getUserid());
//                    edit.putString(SPUtil.TelephoneTag, loginResultBean.getTelephone());
//                    edit.putString(SPUtil.UserNameTag, loginResultBean.getUsername());
//                    edit.apply();
//                          进入主界面=====
//                    startActivity(new Intent(WXEntryActivity.this, MainActivity.class));
//                    WXEntryActivity.this.finish();
//                } else if (code == 2) {
//                    Util.showToast(WXEntryActivity.this, "该微信账号还未绑定乐购账号,请绑定乐购账号！！", true);
//                    Intent intent = new Intent(WXEntryActivity.this, BindingActivity.class);
//                    intent.putExtra("type", 2);
//                    intent.putExtra("openId", openId);
//                    startActivity(intent);
//                    WXEntryActivity.this.finish();
//                } else {
//                    Util.showToast(WXEntryActivity.this, loginResultBean.getMsg(), true);
//                    WXEntryActivity.this.finish();
//                }

                startActivity(new Intent(WXEntryActivity.this, PersonInfoImproveActivity.class));



            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                Util.showToast(WXEntryActivity.this, "网络连接失败或服务器异常！", true);

                WXEntryActivity.this.finish();
                ToastUtil.toastShort("网络连接失败或服务器异常！");
            }
        });

    }


//	private void goToGetMsg() {
//		Intent intent = new Intent(this, GetFromWXActivity.class);
//		intent.putExtras(getIntent());
//		startActivity(intent);
//		finish();
//	}

//	private void goToShowMsg(ShowMessageFromWX.Req showReq) {
//		WXMediaMessage wxMsg = showReq.message;
//		WXAppExtendObject obj = (WXAppExtendObject) wxMsg.mediaObject;
//
//		StringBuffer msg = new StringBuffer(); // ��֯һ������ʾ����Ϣ����
//		msg.append("description: ");
//		msg.append(wxMsg.description);
//		msg.append("\n");
//		msg.append("extInfo: ");
//		msg.append(obj.extInfo);
//		msg.append("\n");
//		msg.append("filePath: ");
//		msg.append(obj.filePath);
//
//		Intent intent = new Intent(this, ShowFromWXActivity.class);
//		intent.putExtra(Constants.ShowMsgActivity.STitle, wxMsg.title);
//		intent.putExtra(Constants.ShowMsgActivity.SMessage, msg.toString());
//		intent.putExtra(Constants.ShowMsgActivity.BAThumbData, wxMsg.thumbData);
//		startActivity(intent);
//		finish();
//	}
}