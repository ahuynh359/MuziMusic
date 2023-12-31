// Generated by Dagger (https://dagger.dev).
package com.team15.muzimusic.activities;

import com.team15.muzimusic.data.repositories.AccountRepository;
import com.team15.muzimusic.data.repositories.CommentRepository;
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
public final class TricksViewModel_Factory implements Factory<TricksViewModel> {
  private final Provider<SongRepository> songRepositoryProvider;

  private final Provider<CommentRepository> commentRepositoryProvider;

  private final Provider<AccountRepository> accountRepositoryProvider;

  public TricksViewModel_Factory(Provider<SongRepository> songRepositoryProvider,
      Provider<CommentRepository> commentRepositoryProvider,
      Provider<AccountRepository> accountRepositoryProvider) {
    this.songRepositoryProvider = songRepositoryProvider;
    this.commentRepositoryProvider = commentRepositoryProvider;
    this.accountRepositoryProvider = accountRepositoryProvider;
  }

  @Override
  public TricksViewModel get() {
    return newInstance(songRepositoryProvider.get(), commentRepositoryProvider.get(), accountRepositoryProvider.get());
  }

  public static TricksViewModel_Factory create(Provider<SongRepository> songRepositoryProvider,
      Provider<CommentRepository> commentRepositoryProvider,
      Provider<AccountRepository> accountRepositoryProvider) {
    return new TricksViewModel_Factory(songRepositoryProvider, commentRepositoryProvider, accountRepositoryProvider);
  }

  public static TricksViewModel newInstance(SongRepository songRepository,
      CommentRepository commentRepository, AccountRepository accountRepository) {
    return new TricksViewModel(songRepository, commentRepository, accountRepository);
  }
}
