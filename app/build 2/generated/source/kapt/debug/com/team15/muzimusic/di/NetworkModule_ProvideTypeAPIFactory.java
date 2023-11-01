// Generated by Dagger (https://dagger.dev).
package com.team15.muzimusic.di;

import com.team15.muzimusic.data.apis.TypeAPI;
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
public final class NetworkModule_ProvideTypeAPIFactory implements Factory<TypeAPI> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideTypeAPIFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public TypeAPI get() {
    return provideTypeAPI(retrofitProvider.get());
  }

  public static NetworkModule_ProvideTypeAPIFactory create(Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideTypeAPIFactory(retrofitProvider);
  }

  public static TypeAPI provideTypeAPI(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideTypeAPI(retrofit));
  }
}
