// Generated by Dagger (https://dagger.dev).
package com.team15.muzimusic.data.repositories;

import com.team15.muzimusic.data.services.notification.NotificationLocalService;
import com.team15.muzimusic.data.services.notification.NotificationRemoteService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;

@ScopeMetadata
@QualifierMetadata("com.team15.muzimusic.di.IoDispatcher")
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class NotificationRepository_Factory implements Factory<NotificationRepository> {
  private final Provider<NotificationRemoteService> remoteServiceProvider;

  private final Provider<NotificationLocalService> localServiceProvider;

  private final Provider<CoroutineDispatcher> dispatcherProvider;

  public NotificationRepository_Factory(Provider<NotificationRemoteService> remoteServiceProvider,
      Provider<NotificationLocalService> localServiceProvider,
      Provider<CoroutineDispatcher> dispatcherProvider) {
    this.remoteServiceProvider = remoteServiceProvider;
    this.localServiceProvider = localServiceProvider;
    this.dispatcherProvider = dispatcherProvider;
  }

  @Override
  public NotificationRepository get() {
    return newInstance(remoteServiceProvider.get(), localServiceProvider.get(), dispatcherProvider.get());
  }

  public static NotificationRepository_Factory create(
      Provider<NotificationRemoteService> remoteServiceProvider,
      Provider<NotificationLocalService> localServiceProvider,
      Provider<CoroutineDispatcher> dispatcherProvider) {
    return new NotificationRepository_Factory(remoteServiceProvider, localServiceProvider, dispatcherProvider);
  }

  public static NotificationRepository newInstance(NotificationRemoteService remoteService,
      NotificationLocalService localService, CoroutineDispatcher dispatcher) {
    return new NotificationRepository(remoteService, localService, dispatcher);
  }
}
