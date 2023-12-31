// Generated by Dagger (https://dagger.dev).
package com.team15.muzimusic.data.services.album;

import com.team15.muzimusic.data.apis.AlbumAPI;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class AlbumRemoteService_Factory implements Factory<AlbumRemoteService> {
  private final Provider<AlbumAPI> albumAPIProvider;

  public AlbumRemoteService_Factory(Provider<AlbumAPI> albumAPIProvider) {
    this.albumAPIProvider = albumAPIProvider;
  }

  @Override
  public AlbumRemoteService get() {
    return newInstance(albumAPIProvider.get());
  }

  public static AlbumRemoteService_Factory create(Provider<AlbumAPI> albumAPIProvider) {
    return new AlbumRemoteService_Factory(albumAPIProvider);
  }

  public static AlbumRemoteService newInstance(AlbumAPI albumAPI) {
    return new AlbumRemoteService(albumAPI);
  }
}
