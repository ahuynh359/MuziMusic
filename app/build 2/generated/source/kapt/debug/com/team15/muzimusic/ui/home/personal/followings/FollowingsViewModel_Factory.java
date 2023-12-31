// Generated by Dagger (https://dagger.dev).
package com.team15.muzimusic.ui.home.library.followings;

import com.team15.muzimusic.data.repositories.AccountRepository;
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
public final class FollowingsViewModel_Factory implements Factory<FollowingsViewModel> {
  private final Provider<AccountRepository> accountRepositoryProvider;

  public FollowingsViewModel_Factory(Provider<AccountRepository> accountRepositoryProvider) {
    this.accountRepositoryProvider = accountRepositoryProvider;
  }

  @Override
  public FollowingsViewModel get() {
    return newInstance(accountRepositoryProvider.get());
  }

  public static FollowingsViewModel_Factory create(
      Provider<AccountRepository> accountRepositoryProvider) {
    return new FollowingsViewModel_Factory(accountRepositoryProvider);
  }

  public static FollowingsViewModel newInstance(AccountRepository accountRepository) {
    return new FollowingsViewModel(accountRepository);
  }
}
