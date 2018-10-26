package com.jola.shengfan.skills.value_animation;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by lenovo on 2018/10/25
 * <p>
 * ValueAnimation 的使用受到 手机-设置-开发者选项-动画时长缩放 设置的影响
 * 如果设置了 “关闭动画” ValueAnimation 会无法发挥作用
 */

public class ValueAnimationUtil {

    public void testValueAnimation() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 100).setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float)animation.getAnimatedValue();
            }
        });

        valueAnimator.start();
    }


    public static void resetDurationScals() {
        if (getDurationScale() == 0) {
            setDurationScale();
        }
    }


    public static float getDurationScale() {
        try {
            return getField().getFloat(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void setDurationScale() {
        try {
            getField().setFloat(null, 1);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static Field getField() throws NoSuchFieldException {
//        反射-类加载方式一 ： 类名.class 不执行动态 静态代码块 构造方法
        Field sDurationScaleField = ValueAnimator.class.getDeclaredField("sDurationScale");
        sDurationScaleField.setAccessible(true);

        try {

            //        反射-类加载方式二 ： Class.forName(className)执行静态代码块 不执行动态和构造方法
            Class<?> valueAnimator = Class.forName("android.animation.ValueAnimator");
            Field sDurationScale = valueAnimator.getDeclaredField("sDurationScale");

//              反射-类加载方式二 ：clazz.getClass() 会创建对象 静态 、动态、构造方法都会执行
//            Class<? extends Class> va = valueAnimator.getClass();
//            Field sDurationScale1 = valueAnimatorClass.getDeclaredField("sDurationScale");
//            sDurationScale1.setAccessible(true);


            sDurationScale.setAccessible(true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return sDurationScaleField;
    }


}
