// Generated by Dagger (https://dagger.dev).
package com.team15.muzimusic.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("javax.inject.Named")
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class NetworkModule_ProvideRetrofitMainSiteFactory implements Factory<Retrofit> {
  private final Provider<OkHttpClient> okHttpClientProvider;

  private final Provider<MoshiConverterFactory> moshiConverterFactoryProvider;

  public NetworkModule_ProvideRetrofitMainSiteFactory(Provider<OkHttpClient> okHttpClientProvider,
      Provider<MoshiConverterFactory> moshiConverterFactoryProvider) {
    this.okHttpClientProvider = okHttpClientProvider;
    this.moshiConverterFactoryProvider = moshiConverterFactoryProvider;
  }

  @Override
  public Retrofit get() {
    return provideRetrofitMainSite(okHttpClientProvider.get(), moshiConverterFactoryProvider.get());
  }

  public static NetworkModule_ProvideRetrofitMainSiteFactory create(
      Provider<OkHttpClient> okHttpClientProvider,
      Provider<MoshiConverterFactory> moshiConverterFactoryProvider) {
    return new NetworkModule_ProvideRetrofitMainSiteFactory(okHttpClientProvider, moshiConverterFactoryProvider);
  }

  public static Retrofit provideRetrofitMainSite(OkHttpClient okHttpClient,
      MoshiConverterFactory moshiConverterFactory) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideRetrofitMainSite(okHttpClient, moshiConverterFactory));
  }
}
