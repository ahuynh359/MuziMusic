// Generated by Dagger (https://dagger.dev).
package com.team15.muzimusic.ui.home.library.favoritesongs;

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
public final class FavoriteSongsViewModel_Factory implements Factory<FavoriteSongsViewModel> {
  private final Provider<SongRepository> songRepositoryProvider;

  public FavoriteSongsViewModel_Factory(Provider<SongRepository> songRepositoryProvider) {
    this.songRepositoryProvider = songRepositoryProvider;
  }

  @Override
  public FavoriteSongsViewModel get() {
    return newInstance(songRepositoryProvider.get());
  }

  public static FavoriteSongsViewModel_Factory create(
      Provider<SongRepository> songRepositoryProvider) {
    return new FavoriteSongsViewModel_Factory(songRepositoryProvider);
  }

  public static FavoriteSongsViewModel newInstance(SongRepository songRepository) {
    return new FavoriteSongsViewModel(songRepository);
  }
}