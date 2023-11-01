// Generated by Dagger (https://dagger.dev).
package com.team15.muzimusic.ui.search;

import com.team15.muzimusic.data.repositories.SearchHistoryRepository;
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
public final class SearchViewModel_Factory implements Factory<SearchViewModel> {
  private final Provider<SongRepository> songRepositoryProvider;

  private final Provider<SearchHistoryRepository> searchHistoryRepositoryProvider;

  public SearchViewModel_Factory(Provider<SongRepository> songRepositoryProvider,
      Provider<SearchHistoryRepository> searchHistoryRepositoryProvider) {
    this.songRepositoryProvider = songRepositoryProvider;
    this.searchHistoryRepositoryProvider = searchHistoryRepositoryProvider;
  }

  @Override
  public SearchViewModel get() {
    return newInstance(songRepositoryProvider.get(), searchHistoryRepositoryProvider.get());
  }

  public static SearchViewModel_Factory create(Provider<SongRepository> songRepositoryProvider,
      Provider<SearchHistoryRepository> searchHistoryRepositoryProvider) {
    return new SearchViewModel_Factory(songRepositoryProvider, searchHistoryRepositoryProvider);
  }

  public static SearchViewModel newInstance(SongRepository songRepository,
      SearchHistoryRepository searchHistoryRepository) {
    return new SearchViewModel(songRepository, searchHistoryRepository);
  }
}
