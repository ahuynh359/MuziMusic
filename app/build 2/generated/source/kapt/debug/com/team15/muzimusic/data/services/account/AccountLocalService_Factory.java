// Generated by Dagger (https://dagger.dev).
package com.team15.muzimusic.data.services.account;

import com.team15.muzimusic.data.database.daos.AccountDAO;
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
public final class AccountLocalService_Factory implements Factory<AccountLocalService> {
  private final Provider<AccountDAO> accountDAOProvider;

  public AccountLocalService_Factory(Provider<AccountDAO> accountDAOProvider) {
    this.accountDAOProvider = accountDAOProvider;
  }

  @Override
  public AccountLocalService get() {
    return newInstance(accountDAOProvider.get());
  }

  public static AccountLocalService_Factory create(Provider<AccountDAO> accountDAOProvider) {
    return new AccountLocalService_Factory(accountDAOProvider);
  }

  public static AccountLocalService newInstance(AccountDAO accountDAO) {
    return new AccountLocalService(accountDAO);
  }
}
