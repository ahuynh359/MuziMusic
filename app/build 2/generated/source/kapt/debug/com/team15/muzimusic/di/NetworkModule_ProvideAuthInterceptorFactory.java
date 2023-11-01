// Generated by Dagger (https://dagger.dev).
package com.team15.muzimusic.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import okhttp3.Interceptor;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class NetworkModule_ProvideAuthInterceptorFactory implements Factory<Interceptor> {
  @Override
  public Interceptor get() {
    return provideAuthInterceptor();
  }

  public static NetworkModule_ProvideAuthInterceptorFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static Interceptor provideAuthInterceptor() {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideAuthInterceptor());
  }

  private static final class InstanceHolder {
    private static final NetworkModule_ProvideAuthInterceptorFactory INSTANCE = new NetworkModule_ProvideAuthInterceptorFactory();
  }
}
