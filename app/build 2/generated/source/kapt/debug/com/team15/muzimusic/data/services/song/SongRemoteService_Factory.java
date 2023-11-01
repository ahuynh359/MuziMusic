// Generated by Dagger (https://dagger.dev).
package com.team15.muzimusic.data.services.song;

import com.team15.muzimusic.data.apis.SongAPI;
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
public final class SongRemoteService_Factory implements Factory<SongRemoteService> {
  private final Provider<SongAPI> songAPIProvider;

  public SongRemoteService_Factory(Provider<SongAPI> songAPIProvider) {
    this.songAPIProvider = songAPIProvider;
  }

  @Override
  public SongRemoteService get() {
    return newInstance(songAPIProvider.get());
  }

  public static SongRemoteService_Factory create(Provider<SongAPI> songAPIProvider) {
    return new SongRemoteService_Factory(songAPIProvider);
  }

  public static SongRemoteService newInstance(SongAPI songAPI) {
    return new SongRemoteService(songAPI);
  }
}
