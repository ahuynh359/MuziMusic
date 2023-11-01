// Generated by Dagger (https://dagger.dev).
package com.team15.muzimusic.di;

import com.team15.muzimusic.data.apis.AlbumAPI;
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
public final class NetworkModule_ProvideAlbumAPIFactory implements Factory<AlbumAPI> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideAlbumAPIFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public AlbumAPI get() {
    return provideAlbumAPI(retrofitProvider.get());
  }

  public static NetworkModule_ProvideAlbumAPIFactory create(Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideAlbumAPIFactory(retrofitProvider);
  }

  public static AlbumAPI provideAlbumAPI(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideAlbumAPI(retrofit));
  }
}