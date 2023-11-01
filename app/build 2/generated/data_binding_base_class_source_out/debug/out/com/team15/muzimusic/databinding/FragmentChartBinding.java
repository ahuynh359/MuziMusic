// Generated by view binder compiler. Do not edit!
package com.team15.muzimusic.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.github.mikephil.charting.charts.LineChart;
import com.team15.muzimusic.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentChartBinding implements ViewBinding {
  @NonNull
  private final SwipeRefreshLayout rootView;

  @NonNull
  public final LineChart chart;

  @NonNull
  public final RecyclerView recyclerSong;

  @NonNull
  public final ShimmerFrameLayout shimmerTopSong;

  @NonNull
  public final SwipeRefreshLayout swipeRefresh;

  private FragmentChartBinding(@NonNull SwipeRefreshLayout rootView, @NonNull LineChart chart,
      @NonNull RecyclerView recyclerSong, @NonNull ShimmerFrameLayout shimmerTopSong,
      @NonNull SwipeRefreshLayout swipeRefresh) {
    this.rootView = rootView;
    this.chart = chart;
    this.recyclerSong = recyclerSong;
    this.shimmerTopSong = shimmerTopSong;
    this.swipeRefresh = swipeRefresh;
  }

  @Override
  @NonNull
  public SwipeRefreshLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentChartBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentChartBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_chart, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentChartBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.chart;
      LineChart chart = ViewBindings.findChildViewById(rootView, id);
      if (chart == null) {
        break missingId;
      }

      id = R.id.recyclerSong;
      RecyclerView recyclerSong = ViewBindings.findChildViewById(rootView, id);
      if (recyclerSong == null) {
        break missingId;
      }

      id = R.id.shimmerTopSong;
      ShimmerFrameLayout shimmerTopSong = ViewBindings.findChildViewById(rootView, id);
      if (shimmerTopSong == null) {
        break missingId;
      }

      SwipeRefreshLayout swipeRefresh = (SwipeRefreshLayout) rootView;

      return new FragmentChartBinding((SwipeRefreshLayout) rootView, chart, recyclerSong,
          shimmerTopSong, swipeRefresh);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}