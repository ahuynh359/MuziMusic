// Generated by Dagger (https://dagger.dev).
package com.team15.muzimusic.data.services.search_history;

import com.team15.muzimusic.data.database.daos.SearchHistoryDAO;
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
public final class SearchHistoryLocalService_Factory implements Factory<SearchHistoryLocalService> {
  private final Provider<SearchHistoryDAO> searchHistoryDAOProvider;

  public SearchHistoryLocalService_Factory(Provider<SearchHistoryDAO> searchHistoryDAOProvider) {
    this.searchHistoryDAOProvider = searchHistoryDAOProvider;
  }

  @Override
  public SearchHistoryLocalService get() {
    return newInstance(searchHistoryDAOProvider.get());
  }

  public static SearchHistoryLocalService_Factory create(
      Provider<SearchHistoryDAO> searchHistoryDAOProvider) {
    return new SearchHistoryLocalService_Factory(searchHistoryDAOProvider);
  }

  public static SearchHistoryLocalService newInstance(SearchHistoryDAO searchHistoryDAO) {
    return new SearchHistoryLocalService(searchHistoryDAO);
  }
}
