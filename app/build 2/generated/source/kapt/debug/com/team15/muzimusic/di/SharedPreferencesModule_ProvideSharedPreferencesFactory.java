// Generated by Dagger (https://dagger.dev).
package com.team15.muzimusic.di;

import android.content.Context;
import com.team15.muzimusic.common.AppSharedPreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class SharedPreferencesModule_ProvideSharedPreferencesFactory implements Factory<AppSharedPreferences> {
  private final SharedPreferencesModule module;

  private final Provider<Context> contextProvider;

  public SharedPreferencesModule_ProvideSharedPreferencesFactory(SharedPreferencesModule module,
      Provider<Context> contextProvider) {
    this.module = module;
    this.contextProvider = contextProvider;
  }

  @Override
  public AppSharedPreferences get() {
    return provideSharedPreferences(module, contextProvider.get());
  }

  public static SharedPreferencesModule_ProvideSharedPreferencesFactory create(
      SharedPreferencesModule module, Provider<Context> contextProvider) {
    return new SharedPreferencesModule_ProvideSharedPreferencesFactory(module, contextProvider);
  }

  public static AppSharedPreferences provideSharedPreferences(SharedPreferencesModule instance,
      Context context) {
    return Preconditions.checkNotNullFromProvides(instance.provideSharedPreferences(context));
  }
}
