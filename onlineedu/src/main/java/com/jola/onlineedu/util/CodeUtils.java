//package com.jola.onlineedu.util;
//
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.util.Log;
//
//import com.jola.onlineedu.app.App;
//
//import java.util.Random;
//
//
//public class CodeUtils {
//
////    private static final char[] CHARS = {
////            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
////            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
////            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
////            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
////            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
////    };
//
//    private static final char[] CHARS = {
//            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
//            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
//    };
//
////    private String
//
//    private static CodeUtils mCodeUtils;
//    private int mPaddingLeft;
////            , mPaddingTop;
//    private StringBuilder mBuilder = new StringBuilder();
//    private Random mRandom = new Random();
//
//    //Default Settings
////    private static final int DEFAULT_CODE_LENGTH = 6;//验证码的长度  这里是6位
//    private static final int DEFAULT_CODE_LENGTH = 4;//验证码的长度  这里是4位
//
//    private static final int DEFAULT_FONT_SIZE = 35;//字体大小
//    private static final int DEFAULT_LINE_NUMBER = 4;//多少条干扰线
////    private static final int BASE_PADDING_LEFT = 10; //左边距
////    private static final int RANGE_PADDING_LEFT = 15;//左边距范围值
//    private static final int BASE_PADDING_TOP = 25;//上边距
////    private static final int RANGE_PADDING_TOP = 30;//上边距范围值
//    private static final int DEFAULT_WIDTH = 130;//默认宽度.图片的总宽 130dp
//    private static final int DEFAULT_HEIGHT = 32;//默认高度.图片的总高 32dp
//    private static final int DEFAULT_COLOR = 0xDF;//默认背景颜色值
//
//    private String code;
//
//    public static CodeUtils getInstance() {
//        if (mCodeUtils == null) {
//            mCodeUtils = new CodeUtils();
//        }
//        return mCodeUtils;
//    }
//
//    //生成验证码图片  返回类型为bitmap 直接用imageview.setbitmap()即可
//    public Bitmap createBitmap() {
//
//
//        Bitmap bitmap = Bitmap.createBitmap(PUtil.dip2px(App.getInstance(),DEFAULT_WIDTH), PUtil.dip2px(App.getInstance(),DEFAULT_HEIGHT), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);
//
//        mPaddingLeft = PUtil.dip2px(App.getInstance(),DEFAULT_WIDTH )/ DEFAULT_CODE_LENGTH;
//
//        code = createCode();
//
//        canvas.drawColor(Color.rgb(DEFAULT_COLOR, DEFAULT_COLOR, DEFAULT_COLOR));
//        Paint paint = new Paint();
//        paint.setTextSize(PUtil.dip2px(App.getInstance(),DEFAULT_FONT_SIZE));
//
//        for (int i = 0; i < code.length(); i++) {
//            randomTextStyle(paint);
////            randomPadding();
//            canvas.drawText(code.charAt(i) + "", 10+mPaddingLeft*i, PUtil.dip2px(App.getInstance(),BASE_PADDING_TOP), paint);
//        }
//
//        //干扰线
//        for (int i = 0; i < DEFAULT_LINE_NUMBER; i++) {
//            drawLine(canvas, paint);
//        }
//
//        canvas.save(Canvas.ALL_SAVE_FLAG);//保存
//        canvas.restore();
//        return bitmap;
//    }
//
//    /**
//     * 得到图片中的验证码字符串
//     *
//     * @return
//     */
//    public String getCode() {
//        return code;
//    }
//
//    //生成验证码
//    public String createCode() {
//        mBuilder.delete(0, mBuilder.length()); //使用之前首先清空内容
//
//        for (int i = 0; i < DEFAULT_CODE_LENGTH; i++) {
//            mBuilder.append(CHARS[mRandom.nextInt(CHARS.length)]);
//        }
//
//        return mBuilder.toString();
//    }
//
//    //生成干扰线
//    private void drawLine(Canvas canvas, Paint paint) {
//        int color = randomColor();
//        int startX = mRandom.nextInt(PUtil.dip2px(App.getInstance(),DEFAULT_WIDTH));
//        int startY = mRandom.nextInt(PUtil.dip2px(App.getInstance(),DEFAULT_HEIGHT));
//        int stopX = mRandom.nextInt(PUtil.dip2px(App.getInstance(),DEFAULT_WIDTH));
//        int stopY = mRandom.nextInt(PUtil.dip2px(App.getInstance(),DEFAULT_HEIGHT));
//        paint.setStrokeWidth(1);
//        paint.setColor(color);
//        canvas.drawLine(startX, startY, stopX, stopY, paint);
//    }
//
//    //随机颜色
//    private int randomColor() {
//        mBuilder.delete(0, mBuilder.length()); //使用之前首先清空内容
//
//        String haxString;
//        for (int i = 0; i < 3; i++) {
//            haxString = Integer.toHexString(mRandom.nextInt(0xFF));
//            if (haxString.length() == 1) {
//                haxString = "0" + haxString;
//            }
//
//            mBuilder.append(haxString);
//        }
//
//        return Color.parseColor("#" + mBuilder.toString());
//    }
//
//    //随机文本样式
//    private void randomTextStyle(Paint paint) {
//        int color = randomColor();
//        paint.setColor(color);
//        paint.setFakeBoldText(mRandom.nextBoolean());  //true为粗体，false为非粗体
//        float skewX = mRandom.nextInt(11) / 10;
//        skewX = mRandom.nextBoolean() ? skewX : -skewX;
//        paint.setTextSkewX(skewX); //float类型参数，负数表示右斜，整数左斜
////        paint.setUnderlineText(true); //true为下划线，false为非下划线
////        paint.setStrikeThruText(true); //true为删除线，false为非删除线
//    }
//
////    //随机间距
////    private void randomPadding() {
////        mPaddingLeft += BASE_PADDING_LEFT + mRandom.nextInt(RANGE_PADDING_LEFT);
//////        mPaddingTop = BASE_PADDING_TOP + mRandom.nextInt(RANGE_PADDING_TOP);
////    }
//}
