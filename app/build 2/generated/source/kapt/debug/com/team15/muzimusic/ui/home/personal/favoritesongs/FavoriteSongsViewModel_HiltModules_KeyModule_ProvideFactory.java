// Generated by Dagger (https://dagger.dev).
package com.team15.muzimusic.ui.home.library.favoritesongs;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.internal.lifecycle.HiltViewModelMap.KeySet")
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class FavoriteSongsViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<String> {
  @Override
  public String get() {
    return provide();
  }

  public static FavoriteSongsViewModel_HiltModules_KeyModule_ProvideFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static String provide() {
    return Preconditions.checkNotNullFromProvides(FavoriteSongsViewModel_HiltModules.KeyModule.provide());
  }

  private static final class InstanceHolder {
    private static final FavoriteSongsViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new FavoriteSongsViewModel_HiltModules_KeyModule_ProvideFactory();
  }
}
