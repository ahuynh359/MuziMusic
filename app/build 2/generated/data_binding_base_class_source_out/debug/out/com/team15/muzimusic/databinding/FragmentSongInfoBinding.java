// Generated by view binder compiler. Do not edit!
package com.team15.muzimusic.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.team15.muzimusic.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentSongInfoBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final RecyclerView recyclerSong;

  @NonNull
  public final ShimmerFrameLayout shimmerSong;

  @NonNull
  public final TextView tvAlbumName;

  @NonNull
  public final TextView tvLikes;

  @NonNull
  public final TextView tvListens;

  @NonNull
  public final TextView tvSingerName;

  @NonNull
  public final TextView tvSongName;

  @NonNull
  public final TextView tvType;

  private FragmentSongInfoBinding(@NonNull LinearLayout rootView,
      @NonNull RecyclerView recyclerSong, @NonNull ShimmerFrameLayout shimmerSong,
      @NonNull TextView tvAlbumName, @NonNull TextView tvLikes, @NonNull TextView tvListens,
      @NonNull TextView tvSingerName, @NonNull TextView tvSongName, @NonNull TextView tvType) {
    this.rootView = rootView;
    this.recyclerSong = recyclerSong;
    this.shimmerSong = shimmerSong;
    this.tvAlbumName = tvAlbumName;
    this.tvLikes = tvLikes;
    this.tvListens = tvListens;
    this.tvSingerName = tvSingerName;
    this.tvSongName = tvSongName;
    this.tvType = tvType;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSongInfoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSongInfoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_song_info, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSongInfoBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.recyclerSong;
      RecyclerView recyclerSong = ViewBindings.findChildViewById(rootView, id);
      if (recyclerSong == null) {
        break missingId;
      }

      id = R.id.shimmerSong;
      ShimmerFrameLayout shimmerSong = ViewBindings.findChildViewById(rootView, id);
      if (shimmerSong == null) {
        break missingId;
      }

      id = R.id.tvAlbumName;
      TextView tvAlbumName = ViewBindings.findChildViewById(rootView, id);
      if (tvAlbumName == null) {
        break missingId;
      }

      id = R.id.tvLikes;
      TextView tvLikes = ViewBindings.findChildViewById(rootView, id);
      if (tvLikes == null) {
        break missingId;
      }

      id = R.id.tvListens;
      TextView tvListens = ViewBindings.findChildViewById(rootView, id);
      if (tvListens == null) {
        break missingId;
      }

      id = R.id.tvSingerName;
      TextView tvSingerName = ViewBindings.findChildViewById(rootView, id);
      if (tvSingerName == null) {
        break missingId;
      }

      id = R.id.tvSongName;
      TextView tvSongName = ViewBindings.findChildViewById(rootView, id);
      if (tvSongName == null) {
        break missingId;
      }

      id = R.id.tvType;
      TextView tvType = ViewBindings.findChildViewById(rootView, id);
      if (tvType == null) {
        break missingId;
      }

      return new FragmentSongInfoBinding((LinearLayout) rootView, recyclerSong, shimmerSong,
          tvAlbumName, tvLikes, tvListens, tvSingerName, tvSongName, tvType);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
