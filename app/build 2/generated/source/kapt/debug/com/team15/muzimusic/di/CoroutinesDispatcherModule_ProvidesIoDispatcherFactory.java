// Generated by Dagger (https://dagger.dev).
package com.team15.muzimusic.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import kotlinx.coroutines.CoroutineDispatcher;

@ScopeMetadata
@QualifierMetadata("com.team15.muzimusic.di.IoDispatcher")
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class CoroutinesDispatcherModule_ProvidesIoDispatcherFactory implements Factory<CoroutineDispatcher> {
  @Override
  public CoroutineDispatcher get() {
    return providesIoDispatcher();
  }

  public static CoroutinesDispatcherModule_ProvidesIoDispatcherFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CoroutineDispatcher providesIoDispatcher() {
    return Preconditions.checkNotNullFromProvides(CoroutinesDispatcherModule.INSTANCE.providesIoDispatcher());
  }

  private static final class InstanceHolder {
    private static final CoroutinesDispatcherModule_ProvidesIoDispatcherFactory INSTANCE = new CoroutinesDispatcherModule_ProvidesIoDispatcherFactory();
  }
}
