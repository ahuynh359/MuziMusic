// Generated by Dagger (https://dagger.dev).
package com.team15.muzimusic.data.services.notification;

import com.team15.muzimusic.data.apis.NotificationAPI;
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
public final class NotificationRemoteService_Factory implements Factory<NotificationRemoteService> {
  private final Provider<NotificationAPI> notificationAPIProvider;

  public NotificationRemoteService_Factory(Provider<NotificationAPI> notificationAPIProvider) {
    this.notificationAPIProvider = notificationAPIProvider;
  }

  @Override
  public NotificationRemoteService get() {
    return newInstance(notificationAPIProvider.get());
  }

  public static NotificationRemoteService_Factory create(
      Provider<NotificationAPI> notificationAPIProvider) {
    return new NotificationRemoteService_Factory(notificationAPIProvider);
  }

  public static NotificationRemoteService newInstance(NotificationAPI notificationAPI) {
    return new NotificationRemoteService(notificationAPI);
  }
}
