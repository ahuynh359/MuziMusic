// Generated by Dagger (https://dagger.dev).
package com.team15.muzimusic.ui.forgetpassword;

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
public final class ForgetPasswordViewModel_Factory implements Factory<ForgetPasswordViewModel> {
  private final Provider<AccountRepository> accountRepositoryProvider;

  public ForgetPasswordViewModel_Factory(Provider<AccountRepository> accountRepositoryProvider) {
    this.accountRepositoryProvider = accountRepositoryProvider;
  }

  @Override
  public ForgetPasswordViewModel get() {
    return newInstance(accountRepositoryProvider.get());
  }

  public static ForgetPasswordViewModel_Factory create(
      Provider<AccountRepository> accountRepositoryProvider) {
    return new ForgetPasswordViewModel_Factory(accountRepositoryProvider);
  }

  public static ForgetPasswordViewModel newInstance(AccountRepository accountRepository) {
    return new ForgetPasswordViewModel(accountRepository);
  }
}
