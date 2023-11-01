// Generated by Dagger (https://dagger.dev).
package com.team15.muzimusic.data.services.album;

import com.team15.muzimusic.data.database.daos.AlbumDao;
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
public final class AlbumLocalService_Factory implements Factory<AlbumLocalService> {
  private final Provider<AlbumDao> albumDaoProvider;

  public AlbumLocalService_Factory(Provider<AlbumDao> albumDaoProvider) {
    this.albumDaoProvider = albumDaoProvider;
  }

  @Override
  public AlbumLocalService get() {
    return newInstance(albumDaoProvider.get());
  }

  public static AlbumLocalService_Factory create(Provider<AlbumDao> albumDaoProvider) {
    return new AlbumLocalService_Factory(albumDaoProvider);
  }

  public static AlbumLocalService newInstance(AlbumDao albumDao) {
    return new AlbumLocalService(albumDao);
  }
}