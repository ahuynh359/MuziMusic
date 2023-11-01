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

public final class ItemTypeManageBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatButton btnDeleteType;

  @NonNull
  public final AppCompatTextView tvTypeName;

  private ItemTypeManageBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppCompatButton btnDeleteType, @NonNull AppCompatTextView tvTypeName) {
    this.rootView = rootView;
    this.btnDeleteType = btnDeleteType;
    this.tvTypeName = tvTypeName;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemTypeManageBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemTypeManageBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_type_manage, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemTypeManageBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_delete_type;
      AppCompatButton btnDeleteType = ViewBindings.findChildViewById(rootView, id);
      if (btnDeleteType == null) {
        break missingId;
      }

      id = R.id.tv_type_name;
      AppCompatTextView tvTypeName = ViewBindings.findChildViewById(rootView, id);
      if (tvTypeName == null) {
        break missingId;
      }

      return new ItemTypeManageBinding((ConstraintLayout) rootView, btnDeleteType, tvTypeName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}