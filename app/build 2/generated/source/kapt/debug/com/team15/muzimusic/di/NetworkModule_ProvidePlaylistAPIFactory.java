// Generated by Dagger (https://dagger.dev).
package com.team15.muzimusic.di;

import com.team15.muzimusic.data.apis.PlaylistAPI;
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
public final class NetworkModule_ProvidePlaylistAPIFactory implements Factory<PlaylistAPI> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvidePlaylistAPIFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public PlaylistAPI get() {
    return providePlaylistAPI(retrofitProvider.get());
  }

  public static NetworkModule_ProvidePlaylistAPIFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvidePlaylistAPIFactory(retrofitProvider);
  }

  public static PlaylistAPI providePlaylistAPI(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.providePlaylistAPI(retrofit));
  }
}
