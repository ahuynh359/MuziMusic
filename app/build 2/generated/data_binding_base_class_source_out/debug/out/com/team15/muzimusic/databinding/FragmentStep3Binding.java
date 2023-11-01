// Generated by view binder compiler. Do not edit!
package com.team15.muzimusic.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.team15.muzimusic.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentStep3Binding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ChipGroup chipGroup;

  @NonNull
  public final EditText edSearchSingers;

  @NonNull
  public final CircularProgressIndicator pbLoading;

  @NonNull
  public final RecyclerView recyclerSingers;

  private FragmentStep3Binding(@NonNull LinearLayout rootView, @NonNull ChipGroup chipGroup,
      @NonNull EditText edSearchSingers, @NonNull CircularProgressIndicator pbLoading,
      @NonNull RecyclerView recyclerSingers) {
    this.rootView = rootView;
    this.chipGroup = chipGroup;
    this.edSearchSingers = edSearchSingers;
    this.pbLoading = pbLoading;
    this.recyclerSingers = recyclerSingers;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentStep3Binding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentStep3Binding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_step3, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentStep3Binding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.chipGroup;
      ChipGroup chipGroup = ViewBindings.findChildViewById(rootView, id);
      if (chipGroup == null) {
        break missingId;
      }

      id = R.id.edSearchSingers;
      EditText edSearchSingers = ViewBindings.findChildViewById(rootView, id);
      if (edSearchSingers == null) {
        break missingId;
      }

      id = R.id.pbLoading;
      CircularProgressIndicator pbLoading = ViewBindings.findChildViewById(rootView, id);
      if (pbLoading == null) {
        break missingId;
      }

      id = R.id.recyclerSingers;
      RecyclerView recyclerSingers = ViewBindings.findChildViewById(rootView, id);
      if (recyclerSingers == null) {
        break missingId;
      }

      return new FragmentStep3Binding((LinearLayout) rootView, chipGroup, edSearchSingers,
          pbLoading, recyclerSingers);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
