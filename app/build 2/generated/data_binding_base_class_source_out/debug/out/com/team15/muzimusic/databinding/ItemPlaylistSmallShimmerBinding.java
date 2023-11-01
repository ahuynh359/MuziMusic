// Generated by view binder compiler. Do not edit!
package com.team15.muzimusic.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.makeramen.roundedimageview.RoundedImageView;
import com.team15.muzimusic.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemPlaylistSmallShimmerBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final RoundedImageView imgAvatar;

  @NonNull
  public final AppCompatTextView tvPlaylistName;

  private ItemPlaylistSmallShimmerBinding(@NonNull LinearLayout rootView,
      @NonNull RoundedImageView imgAvatar, @NonNull AppCompatTextView tvPlaylistName) {
    this.rootView = rootView;
    this.imgAvatar = imgAvatar;
    this.tvPlaylistName = tvPlaylistName;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemPlaylistSmallShimmerBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemPlaylistSmallShimmerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_playlist_small_shimmer, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemPlaylistSmallShimmerBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.img_avatar;
      RoundedImageView imgAvatar = ViewBindings.findChildViewById(rootView, id);
      if (imgAvatar == null) {
        break missingId;
      }

      id = R.id.tv_playlist_name;
      AppCompatTextView tvPlaylistName = ViewBindings.findChildViewById(rootView, id);
      if (tvPlaylistName == null) {
        break missingId;
      }

      return new ItemPlaylistSmallShimmerBinding((LinearLayout) rootView, imgAvatar,
          tvPlaylistName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}