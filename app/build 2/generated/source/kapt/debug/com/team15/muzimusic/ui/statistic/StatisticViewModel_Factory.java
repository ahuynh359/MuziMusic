// Generated by Dagger (https://dagger.dev).
package com.team15.muzimusic.ui.statistic;

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
public final class StatisticViewModel_Factory implements Factory<StatisticViewModel> {
  private final Provider<SongRepository> songRepositoryProvider;

  public StatisticViewModel_Factory(Provider<SongRepository> songRepositoryProvider) {
    this.songRepositoryProvider = songRepositoryProvider;
  }

  @Override
  public StatisticViewModel get() {
    return newInstance(songRepositoryProvider.get());
  }

  public static StatisticViewModel_Factory create(Provider<SongRepository> songRepositoryProvider) {
    return new StatisticViewModel_Factory(songRepositoryProvider);
  }

  public static StatisticViewModel newInstance(SongRepository songRepository) {
    return new StatisticViewModel(songRepository);
  }
}
