// Generated by view binder compiler. Do not edit!
package com.team15.muzimusic.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.team15.muzimusic.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemSongStatisticBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatImageButton btnHeadphone;

  @NonNull
  public final AppCompatImageButton btnHeart;

  @NonNull
  public final CircleImageView imvSongAvatar;

  @NonNull
  public final AppCompatTextView tvAccountName;

  @NonNull
  public final AppCompatTextView tvIndex;

  @NonNull
  public final AppCompatTextView tvSongName;

  @NonNull
  public final AppCompatTextView tvTotalHeart;

  @NonNull
  public final AppCompatTextView tvTotalListen;

  private ItemSongStatisticBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppCompatImageButton btnHeadphone, @NonNull AppCompatImageButton btnHeart,
      @NonNull CircleImageView imvSongAvatar, @NonNull AppCompatTextView tvAccountName,
      @NonNull AppCompatTextView tvIndex, @NonNull AppCompatTextView tvSongName,
      @NonNull AppCompatTextView tvTotalHeart, @NonNull AppCompatTextView tvTotalListen) {
    this.rootView = rootView;
    this.btnHeadphone = btnHeadphone;
    this.btnHeart = btnHeart;
    this.imvSongAvatar = imvSongAvatar;
    this.tvAccountName = tvAccountName;
    this.tvIndex = tvIndex;
    this.tvSongName = tvSongName;
    this.tvTotalHeart = tvTotalHeart;
    this.tvTotalListen = tvTotalListen;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemSongStatisticBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemSongStatisticBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_song_statistic, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemSongStatisticBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_headphone;
      AppCompatImageButton btnHeadphone = ViewBindings.findChildViewById(rootView, id);
      if (btnHeadphone == null) {
        break missingId;
      }

      id = R.id.btn_heart;
      AppCompatImageButton btnHeart = ViewBindings.findChildViewById(rootView, id);
      if (btnHeart == null) {
        break missingId;
      }

      id = R.id.imv_song_avatar;
      CircleImageView imvSongAvatar = ViewBindings.findChildViewById(rootView, id);
      if (imvSongAvatar == null) {
        break missingId;
      }

      id = R.id.tv_account_name;
      AppCompatTextView tvAccountName = ViewBindings.findChildViewById(rootView, id);
      if (tvAccountName == null) {
        break missingId;
      }

      id = R.id.tv_index;
      AppCompatTextView tvIndex = ViewBindings.findChildViewById(rootView, id);
      if (tvIndex == null) {
        break missingId;
      }

      id = R.id.tv_song_name;
      AppCompatTextView tvSongName = ViewBindings.findChildViewById(rootView, id);
      if (tvSongName == null) {
        break missingId;
      }

      id = R.id.tv_total_heart;
      AppCompatTextView tvTotalHeart = ViewBindings.findChildViewById(rootView, id);
      if (tvTotalHeart == null) {
        break missingId;
      }

      id = R.id.tv_total_listen;
      AppCompatTextView tvTotalListen = ViewBindings.findChildViewById(rootView, id);
      if (tvTotalListen == null) {
        break missingId;
      }

      return new ItemSongStatisticBinding((ConstraintLayout) rootView, btnHeadphone, btnHeart,
          imvSongAvatar, tvAccountName, tvIndex, tvSongName, tvTotalHeart, tvTotalListen);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
