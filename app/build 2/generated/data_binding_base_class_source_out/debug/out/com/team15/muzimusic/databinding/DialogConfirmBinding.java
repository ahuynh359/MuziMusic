// Generated by view binder compiler. Do not edit!
package com.team15.muzimusic.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.team15.muzimusic.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DialogConfirmBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatButton btnNegative;

  @NonNull
  public final AppCompatButton btnPositive;

  @NonNull
  public final AppCompatTextView tvContent;

  @NonNull
  public final AppCompatTextView tvTitle;

  private DialogConfirmBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppCompatButton btnNegative, @NonNull AppCompatButton btnPositive,
      @NonNull AppCompatTextView tvContent, @NonNull AppCompatTextView tvTitle) {
    this.rootView = rootView;
    this.btnNegative = btnNegative;
    this.btnPositive = btnPositive;
    this.tvContent = tvContent;
    this.tvTitle = tvTitle;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogConfirmBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogConfirmBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_confirm, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogConfirmBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_negative;
      AppCompatButton btnNegative = ViewBindings.findChildViewById(rootView, id);
      if (btnNegative == null) {
        break missingId;
      }

      id = R.id.btn_positive;
      AppCompatButton btnPositive = ViewBindings.findChildViewById(rootView, id);
      if (btnPositive == null) {
        break missingId;
      }

      id = R.id.tv_content;
      AppCompatTextView tvContent = ViewBindings.findChildViewById(rootView, id);
      if (tvContent == null) {
        break missingId;
      }

      id = R.id.tv_title;
      AppCompatTextView tvTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvTitle == null) {
        break missingId;
      }

      return new DialogConfirmBinding((ConstraintLayout) rootView, btnNegative, btnPositive,
          tvContent, tvTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
