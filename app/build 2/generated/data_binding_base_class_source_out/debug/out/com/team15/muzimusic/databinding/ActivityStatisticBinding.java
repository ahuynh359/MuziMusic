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
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.chip.Chip;
import com.team15.muzimusic.R;
import com.wang.avi.AVLoadingIndicatorView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityStatisticBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatButton btnFetch;

  @NonNull
  public final Chip chip30;

  @NonNull
  public final Chip chip7;

  @NonNull
  public final Chip chipAll;

  @NonNull
  public final Chip chipChoose;

  @NonNull
  public final ImageView imgCalendar;

  @NonNull
  public final ConstraintLayout layoutInfo;

  @NonNull
  public final LinearLayout layoutRange;

  @NonNull
  public final AVLoadingIndicatorView progress;

  @NonNull
  public final RecyclerView rvSongs;

  @NonNull
  public final Toolbar toolbar;

  @NonNull
  public final TextView tvEnd;

  @NonNull
  public final TextView tvSep;

  @NonNull
  public final TextView tvSortBy;

  @NonNull
  public final TextView tvStart;

  private ActivityStatisticBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppCompatButton btnFetch, @NonNull Chip chip30, @NonNull Chip chip7,
      @NonNull Chip chipAll, @NonNull Chip chipChoose, @NonNull ImageView imgCalendar,
      @NonNull ConstraintLayout layoutInfo, @NonNull LinearLayout layoutRange,
      @NonNull AVLoadingIndicatorView progress, @NonNull RecyclerView rvSongs,
      @NonNull Toolbar toolbar, @NonNull TextView tvEnd, @NonNull TextView tvSep,
      @NonNull TextView tvSortBy, @NonNull TextView tvStart) {
    this.rootView = rootView;
    this.btnFetch = btnFetch;
    this.chip30 = chip30;
    this.chip7 = chip7;
    this.chipAll = chipAll;
    this.chipChoose = chipChoose;
    this.imgCalendar = imgCalendar;
    this.layoutInfo = layoutInfo;
    this.layoutRange = layoutRange;
    this.progress = progress;
    this.rvSongs = rvSongs;
    this.toolbar = toolbar;
    this.tvEnd = tvEnd;
    this.tvSep = tvSep;
    this.tvSortBy = tvSortBy;
    this.tvStart = tvStart;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityStatisticBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityStatisticBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_statistic, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityStatisticBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnFetch;
      AppCompatButton btnFetch = ViewBindings.findChildViewById(rootView, id);
      if (btnFetch == null) {
        break missingId;
      }

      id = R.id.chip30;
      Chip chip30 = ViewBindings.findChildViewById(rootView, id);
      if (chip30 == null) {
        break missingId;
      }

      id = R.id.chip7;
      Chip chip7 = ViewBindings.findChildViewById(rootView, id);
      if (chip7 == null) {
        break missingId;
      }

      id = R.id.chipAll;
      Chip chipAll = ViewBindings.findChildViewById(rootView, id);
      if (chipAll == null) {
        break missingId;
      }

      id = R.id.chipChoose;
      Chip chipChoose = ViewBindings.findChildViewById(rootView, id);
      if (chipChoose == null) {
        break missingId;
      }

      id = R.id.imgCalendar;
      ImageView imgCalendar = ViewBindings.findChildViewById(rootView, id);
      if (imgCalendar == null) {
        break missingId;
      }

      id = R.id.layoutInfo;
      ConstraintLayout layoutInfo = ViewBindings.findChildViewById(rootView, id);
      if (layoutInfo == null) {
        break missingId;
      }

      id = R.id.layoutRange;
      LinearLayout layoutRange = ViewBindings.findChildViewById(rootView, id);
      if (layoutRange == null) {
        break missingId;
      }

      id = R.id.progress;
      AVLoadingIndicatorView progress = ViewBindings.findChildViewById(rootView, id);
      if (progress == null) {
        break missingId;
      }

      id = R.id.rvSongs;
      RecyclerView rvSongs = ViewBindings.findChildViewById(rootView, id);
      if (rvSongs == null) {
        break missingId;
      }

      id = R.id.toolbar;
      Toolbar toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      id = R.id.tvEnd;
      TextView tvEnd = ViewBindings.findChildViewById(rootView, id);
      if (tvEnd == null) {
        break missingId;
      }

      id = R.id.tvSep;
      TextView tvSep = ViewBindings.findChildViewById(rootView, id);
      if (tvSep == null) {
        break missingId;
      }

      id = R.id.tvSortBy;
      TextView tvSortBy = ViewBindings.findChildViewById(rootView, id);
      if (tvSortBy == null) {
        break missingId;
      }

      id = R.id.tvStart;
      TextView tvStart = ViewBindings.findChildViewById(rootView, id);
      if (tvStart == null) {
        break missingId;
      }

      return new ActivityStatisticBinding((ConstraintLayout) rootView, btnFetch, chip30, chip7,
          chipAll, chipChoose, imgCalendar, layoutInfo, layoutRange, progress, rvSongs, toolbar,
          tvEnd, tvSep, tvSortBy, tvStart);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
