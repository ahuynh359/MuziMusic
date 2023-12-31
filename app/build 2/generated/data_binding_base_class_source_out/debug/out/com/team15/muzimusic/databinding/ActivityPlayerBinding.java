// Generated by view binder compiler. Do not edit!
package com.team15.muzimusic.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.slider.Slider;
import com.team15.muzimusic.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityPlayerBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final ImageView btnNext;

  @NonNull
  public final ImageView btnPlayPause;

  @NonNull
  public final ImageView btnPrev;

  @NonNull
  public final ImageView btnRepeat;

  @NonNull
  public final ImageView btnShuffle;

  @NonNull
  public final ImageView dot0;

  @NonNull
  public final ImageView dot1;

  @NonNull
  public final ImageView dot2;

  @NonNull
  public final RelativeLayout layoutControl;

  @NonNull
  public final LinearLayout layoutDot;

  @NonNull
  public final RelativeLayout layoutSeekbar;

  @NonNull
  public final Slider sliderSong;

  @NonNull
  public final ToolbarPlayerBinding toolbar;

  @NonNull
  public final TextView tvSongDuration;

  @NonNull
  public final ViewPager2 viewPager;

  private ActivityPlayerBinding(@NonNull RelativeLayout rootView, @NonNull ImageView btnNext,
      @NonNull ImageView btnPlayPause, @NonNull ImageView btnPrev, @NonNull ImageView btnRepeat,
      @NonNull ImageView btnShuffle, @NonNull ImageView dot0, @NonNull ImageView dot1,
      @NonNull ImageView dot2, @NonNull RelativeLayout layoutControl,
      @NonNull LinearLayout layoutDot, @NonNull RelativeLayout layoutSeekbar,
      @NonNull Slider sliderSong, @NonNull ToolbarPlayerBinding toolbar,
      @NonNull TextView tvSongDuration, @NonNull ViewPager2 viewPager) {
    this.rootView = rootView;
    this.btnNext = btnNext;
    this.btnPlayPause = btnPlayPause;
    this.btnPrev = btnPrev;
    this.btnRepeat = btnRepeat;
    this.btnShuffle = btnShuffle;
    this.dot0 = dot0;
    this.dot1 = dot1;
    this.dot2 = dot2;
    this.layoutControl = layoutControl;
    this.layoutDot = layoutDot;
    this.layoutSeekbar = layoutSeekbar;
    this.sliderSong = sliderSong;
    this.toolbar = toolbar;
    this.tvSongDuration = tvSongDuration;
    this.viewPager = viewPager;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityPlayerBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityPlayerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_player, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityPlayerBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnNext;
      ImageView btnNext = ViewBindings.findChildViewById(rootView, id);
      if (btnNext == null) {
        break missingId;
      }

      id = R.id.btnPlayPause;
      ImageView btnPlayPause = ViewBindings.findChildViewById(rootView, id);
      if (btnPlayPause == null) {
        break missingId;
      }

      id = R.id.btnPrev;
      ImageView btnPrev = ViewBindings.findChildViewById(rootView, id);
      if (btnPrev == null) {
        break missingId;
      }

      id = R.id.btnRepeat;
      ImageView btnRepeat = ViewBindings.findChildViewById(rootView, id);
      if (btnRepeat == null) {
        break missingId;
      }

      id = R.id.btnShuffle;
      ImageView btnShuffle = ViewBindings.findChildViewById(rootView, id);
      if (btnShuffle == null) {
        break missingId;
      }

      id = R.id.dot0;
      ImageView dot0 = ViewBindings.findChildViewById(rootView, id);
      if (dot0 == null) {
        break missingId;
      }

      id = R.id.dot1;
      ImageView dot1 = ViewBindings.findChildViewById(rootView, id);
      if (dot1 == null) {
        break missingId;
      }

      id = R.id.dot2;
      ImageView dot2 = ViewBindings.findChildViewById(rootView, id);
      if (dot2 == null) {
        break missingId;
      }

      id = R.id.layoutControl;
      RelativeLayout layoutControl = ViewBindings.findChildViewById(rootView, id);
      if (layoutControl == null) {
        break missingId;
      }

      id = R.id.layoutDot;
      LinearLayout layoutDot = ViewBindings.findChildViewById(rootView, id);
      if (layoutDot == null) {
        break missingId;
      }

      id = R.id.layoutSeekbar;
      RelativeLayout layoutSeekbar = ViewBindings.findChildViewById(rootView, id);
      if (layoutSeekbar == null) {
        break missingId;
      }

      id = R.id.sliderSong;
      Slider sliderSong = ViewBindings.findChildViewById(rootView, id);
      if (sliderSong == null) {
        break missingId;
      }

      id = R.id.toolbar;
      View toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }
      ToolbarPlayerBinding binding_toolbar = ToolbarPlayerBinding.bind(toolbar);

      id = R.id.tvSongDuration;
      TextView tvSongDuration = ViewBindings.findChildViewById(rootView, id);
      if (tvSongDuration == null) {
        break missingId;
      }

      id = R.id.viewPager;
      ViewPager2 viewPager = ViewBindings.findChildViewById(rootView, id);
      if (viewPager == null) {
        break missingId;
      }

      return new ActivityPlayerBinding((RelativeLayout) rootView, btnNext, btnPlayPause, btnPrev,
          btnRepeat, btnShuffle, dot0, dot1, dot2, layoutControl, layoutDot, layoutSeekbar,
          sliderSong, binding_toolbar, tvSongDuration, viewPager);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
