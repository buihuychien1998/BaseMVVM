package com.example.baseapplication.di.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * @author ITSOL JAPAN
 * Created on 5/25/2021.
 * Copyright © 2020 YSL Solution Co., Ltd. All rights reserved.
 **/
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface OkHttpClientNetwork {}
