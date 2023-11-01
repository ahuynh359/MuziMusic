// Generated by Dagger (https://dagger.dev).
package com.team15.muzimusic.di;

import com.team15.muzimusic.data.apis.AccountAPI;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.inject.Provider;
import retrofit2.Retrofit;

@ScopeMetadata
@QualifierMetadata("javax.inject.Named")
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class NetworkModule_ProvideAccountAPIFactory implements Factory<AccountAPI> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideAccountAPIFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public AccountAPI get() {
    return provideAccountAPI(retrofitProvider.get());
  }

  public static NetworkModule_ProvideAccountAPIFactory create(Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideAccountAPIFactory(retrofitProvider);
  }

  public static AccountAPI provideAccountAPI(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideAccountAPI(retrofit));
  }
}