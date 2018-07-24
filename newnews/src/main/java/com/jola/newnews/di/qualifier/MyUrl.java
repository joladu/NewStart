package com.jola.newnews.di.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * Created by lenovo on 2018/7/24.
 */

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface MyUrl {
}
