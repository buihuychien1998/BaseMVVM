// Generated by Dagger (https://dagger.dev).
package com.example.baseapplication.data.remote.config;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class CustomInterceptor_Factory implements Factory<CustomInterceptor> {
  @Override
  public CustomInterceptor get() {
    return newInstance();
  }

  public static CustomInterceptor_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CustomInterceptor newInstance() {
    return new CustomInterceptor();
  }

  private static final class InstanceHolder {
    private static final CustomInterceptor_Factory INSTANCE = new CustomInterceptor_Factory();
  }
}
