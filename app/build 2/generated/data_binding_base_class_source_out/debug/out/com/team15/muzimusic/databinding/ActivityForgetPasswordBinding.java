// Generated by view binder compiler. Do not edit!
package com.team15.muzimusic.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.aceinteract.android.stepper.StepperNavigationView;
import com.team15.muzimusic.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityForgetPasswordBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatImageButton btnBack;

  @NonNull
  public final AppCompatImageView imvLogo;

  @NonNull
  public final StepperNavigationView stepper;

  @NonNull
  public final AppCompatTextView tvTitle;

  private ActivityForgetPasswordBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppCompatImageButton btnBack, @NonNull AppCompatImageView imvLogo,
      @NonNull StepperNavigationView stepper, @NonNull AppCompatTextView tvTitle) {
    this.rootView = rootView;
    this.btnBack = btnBack;
    this.imvLogo = imvLogo;
    this.stepper = stepper;
    this.tvTitle = tvTitle;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityForgetPasswordBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityForgetPasswordBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_forget_password, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityForgetPasswordBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_back;
      AppCompatImageButton btnBack = ViewBindings.findChildViewById(rootView, id);
      if (btnBack == null) {
        break missingId;
      }

      id = R.id.imv_logo;
      AppCompatImageView imvLogo = ViewBindings.findChildViewById(rootView, id);
      if (imvLogo == null) {
        break missingId;
      }

      id = R.id.stepper;
      StepperNavigationView stepper = ViewBindings.findChildViewById(rootView, id);
      if (stepper == null) {
        break missingId;
      }

      id = R.id.tv_title;
      AppCompatTextView tvTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvTitle == null) {
        break missingId;
      }

      return new ActivityForgetPasswordBinding((ConstraintLayout) rootView, btnBack, imvLogo,
          stepper, tvTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
