// Generated by view binder compiler. Do not edit!
package com.team15.muzimusic.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.team15.muzimusic.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentPlaylistOfAccountBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final RecyclerView recyclerPlaylist;

  private FragmentPlaylistOfAccountBinding(@NonNull LinearLayout rootView,
      @NonNull RecyclerView recyclerPlaylist) {
    this.rootView = rootView;
    this.recyclerPlaylist = recyclerPlaylist;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentPlaylistOfAccountBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentPlaylistOfAccountBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_playlist_of_account, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentPlaylistOfAccountBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.recyclerPlaylist;
      RecyclerView recyclerPlaylist = ViewBindings.findChildViewById(rootView, id);
      if (recyclerPlaylist == null) {
        break missingId;
      }

      return new FragmentPlaylistOfAccountBinding((LinearLayout) rootView, recyclerPlaylist);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}