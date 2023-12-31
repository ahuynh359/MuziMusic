// Generated by view binder compiler. Do not edit!
package com.team15.muzimusic.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.aceinteract.android.stepper.StepperNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.team15.muzimusic.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityFormSongBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final FloatingActionButton fabNext;

  @NonNull
  public final FloatingActionButton fabPrevious;

  @NonNull
  public final StepperNavigationView stepper;

  @NonNull
  public final Toolbar toolbar;

  private ActivityFormSongBinding(@NonNull RelativeLayout rootView,
      @NonNull FloatingActionButton fabNext, @NonNull FloatingActionButton fabPrevious,
      @NonNull StepperNavigationView stepper, @NonNull Toolbar toolbar) {
    this.rootView = rootView;
    this.fabNext = fabNext;
    this.fabPrevious = fabPrevious;
    this.stepper = stepper;
    this.toolbar = toolbar;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityFormSongBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityFormSongBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_form_song, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityFormSongBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.fabNext;
      FloatingActionButton fabNext = ViewBindings.findChildViewById(rootView, id);
      if (fabNext == null) {
        break missingId;
      }

      id = R.id.fabPrevious;
      FloatingActionButton fabPrevious = ViewBindings.findChildViewById(rootView, id);
      if (fabPrevious == null) {
        break missingId;
      }

      id = R.id.stepper;
      StepperNavigationView stepper = ViewBindings.findChildViewById(rootView, id);
      if (stepper == null) {
        break missingId;
      }

      id = R.id.toolbar;
      Toolbar toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      return new ActivityFormSongBinding((RelativeLayout) rootView, fabNext, fabPrevious, stepper,
          toolbar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
