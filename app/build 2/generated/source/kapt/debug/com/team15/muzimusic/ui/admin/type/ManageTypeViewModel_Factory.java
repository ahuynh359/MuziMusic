// Generated by Dagger (https://dagger.dev).
package com.team15.muzimusic.ui.admin.type;

import com.team15.muzimusic.data.repositories.SongRepository;
import com.team15.muzimusic.data.repositories.TypeRepository;
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
public final class ManageTypeViewModel_Factory implements Factory<ManageTypeViewModel> {
  private final Provider<SongRepository> songRepositoryProvider;

  private final Provider<TypeRepository> typeRepositoryProvider;

  public ManageTypeViewModel_Factory(Provider<SongRepository> songRepositoryProvider,
      Provider<TypeRepository> typeRepositoryProvider) {
    this.songRepositoryProvider = songRepositoryProvider;
    this.typeRepositoryProvider = typeRepositoryProvider;
  }

  @Override
  public ManageTypeViewModel get() {
    return newInstance(songRepositoryProvider.get(), typeRepositoryProvider.get());
  }

  public static ManageTypeViewModel_Factory create(Provider<SongRepository> songRepositoryProvider,
      Provider<TypeRepository> typeRepositoryProvider) {
    return new ManageTypeViewModel_Factory(songRepositoryProvider, typeRepositoryProvider);
  }

  public static ManageTypeViewModel newInstance(SongRepository songRepository,
      TypeRepository typeRepository) {
    return new ManageTypeViewModel(songRepository, typeRepository);
  }
}
