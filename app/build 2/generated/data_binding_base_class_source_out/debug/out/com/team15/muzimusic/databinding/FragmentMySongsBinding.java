// Generated by view binder compiler. Do not edit!
package com.team15.muzimusic.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.team15.muzimusic.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentMySongsBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView btnAddSong;

  @NonNull
  public final ImageView btnPlayMusic;

  @NonNull
  public final CircularProgressIndicator pbLoading;

  @NonNull
  public final RecyclerView recyclerSong;

  @NonNull
  public final TextView tvAlert;

  private FragmentMySongsBinding(@NonNull LinearLayout rootView, @NonNull TextView btnAddSong,
      @NonNull ImageView btnPlayMusic, @NonNull CircularProgressIndicator pbLoading,
      @NonNull RecyclerView recyclerSong, @NonNull TextView tvAlert) {
    this.rootView = rootView;
    this.btnAddSong = btnAddSong;
    this.btnPlayMusic = btnPlayMusic;
    this.pbLoading = pbLoading;
    this.recyclerSong = recyclerSong;
    this.tvAlert = tvAlert;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentMySongsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentMySongsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_my_songs, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentMySongsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnAddSong;
      TextView btnAddSong = ViewBindings.findChildViewById(rootView, id);
      if (btnAddSong == null) {
        break missingId;
      }

      id = R.id.btnPlayMusic;
      ImageView btnPlayMusic = ViewBindings.findChildViewById(rootView, id);
      if (btnPlayMusic == null) {
        break missingId;
      }

      id = R.id.pbLoading;
      CircularProgressIndicator pbLoading = ViewBindings.findChildViewById(rootView, id);
      if (pbLoading == null) {
        break missingId;
      }

      id = R.id.recyclerSong;
      RecyclerView recyclerSong = ViewBindings.findChildViewById(rootView, id);
      if (recyclerSong == null) {
        break missingId;
      }

      id = R.id.tvAlert;
      TextView tvAlert = ViewBindings.findChildViewById(rootView, id);
      if (tvAlert == null) {
        break missingId;
      }

      return new FragmentMySongsBinding((LinearLayout) rootView, btnAddSong, btnPlayMusic,
          pbLoading, recyclerSong, tvAlert);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
