// Generated by Dagger (https://dagger.dev).
package com.team15.muzimusic.ui.home.white;

import com.team15.muzimusic.data.repositories.SongRepository;
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
public final class ChartViewModel_Factory implements Factory<ChartViewModel> {
  private final Provider<SongRepository> songRepositoryProvider;

  public ChartViewModel_Factory(Provider<SongRepository> songRepositoryProvider) {
    this.songRepositoryProvider = songRepositoryProvider;
  }

  @Override
  public ChartViewModel get() {
    return newInstance(songRepositoryProvider.get());
  }

  public static ChartViewModel_Factory create(Provider<SongRepository> songRepositoryProvider) {
    return new ChartViewModel_Factory(songRepositoryProvider);
  }

  public static ChartViewModel newInstance(SongRepository songRepository) {
    return new ChartViewModel(songRepository);
  }
}
