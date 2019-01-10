package com.jola.onlineedu.wxapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.jola.onlineedu.app.App;
import com.jola.onlineedu.app.Constants;
import com.jola.onlineedu.mode.bean.response.ResThirdpartLoginBean;
import com.jola.onlineedu.mode.http.MyApis;
import com.jola.onlineedu.ui.activity.MainActivity;
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

import static com.jola.onlineedu.mode.prefs.PreferencesHelperImpl.SHAREDPREFERENCES_NAME;
import static com.jola.onlineedu.mode.prefs.PreferencesHelperImpl.TAG_USER_TOKEN;


public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

//    private static final int TIMELINE_SUPPORTED_VERSION = 0x21020001;
//    private static final int RETURN_MSG_TYPE_LOGIN = 1;
//    private static final int RETURN_MSG_TYPE_SHARE = 2;


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
        requestParams.put("wx_unionid",openId);
        App.getmAsyncHttpClient().post(MyApis.HOST+"v1/user/thirdsignup/", requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String resultString = new String(responseBody);
                Log.e("jola",resultString);
                ResThirdpartLoginBean loginResultBean = new Gson().fromJson(new String(responseBody), ResThirdpartLoginBean.class);
                int code = loginResultBean.getError_code();
                if (code == 0) {
                    ToastUtil.toastShort("微信登录成功！");

                    String token = loginResultBean.getData().getToken();
//                    dataManager.setUserToken(loginResultBean.getData().getToken());
                    App.getInstance().getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE)
                            .edit().putString(TAG_USER_TOKEN,token).apply();

                    startActivity(new Intent(WXEntryActivity.this, MainActivity.class));
                    WXEntryActivity.this.finish();
                } else  {
                    ToastUtil.toastShort("该微信账号还未绑定乐购账号,请绑定乐购账号！");
                    startActivity(new Intent(WXEntryActivity.this, PersonInfoImproveActivity.class));
                    WXEntryActivity.this.finish();
                }
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