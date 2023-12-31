// Generated by Dagger (https://dagger.dev).
package com.team15.muzimusic.ui.type;

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
public final class TypeViewModel_Factory implements Factory<TypeViewModel> {
  private final Provider<SongRepository> songRepositoryProvider;

  public TypeViewModel_Factory(Provider<SongRepository> songRepositoryProvider) {
    this.songRepositoryProvider = songRepositoryProvider;
  }

  @Override
  public TypeViewModel get() {
    return newInstance(songRepositoryProvider.get());
  }

  public static TypeViewModel_Factory create(Provider<SongRepository> songRepositoryProvider) {
    return new TypeViewModel_Factory(songRepositoryProvider);
  }

  public static TypeViewModel newInstance(SongRepository songRepository) {
    return new TypeViewModel(songRepository);
  }
}
