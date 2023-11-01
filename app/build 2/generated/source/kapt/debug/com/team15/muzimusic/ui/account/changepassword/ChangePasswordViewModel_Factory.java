// Generated by Dagger (https://dagger.dev).
package com.team15.muzimusic.ui.account.changepassword;

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
public final class ChangePasswordViewModel_Factory implements Factory<ChangePasswordViewModel> {
  private final Provider<AccountRepository> accountRepositoryProvider;

  public ChangePasswordViewModel_Factory(Provider<AccountRepository> accountRepositoryProvider) {
    this.accountRepositoryProvider = accountRepositoryProvider;
  }

  @Override
  public ChangePasswordViewModel get() {
    return newInstance(accountRepositoryProvider.get());
  }

  public static ChangePasswordViewModel_Factory create(
      Provider<AccountRepository> accountRepositoryProvider) {
    return new ChangePasswordViewModel_Factory(accountRepositoryProvider);
  }

  public static ChangePasswordViewModel newInstance(AccountRepository accountRepository) {
    return new ChangePasswordViewModel(accountRepository);
  }
}
