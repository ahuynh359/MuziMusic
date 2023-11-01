// Generated by view binder compiler. Do not edit!
package com.team15.muzimusic.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.team15.muzimusic.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLoginBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatButton btnLogIn;

  @NonNull
  public final TextInputEditText edtEmail;

  @NonNull
  public final TextInputEditText edtPassword;

  @NonNull
  public final AppCompatImageView imvIcon;

  @NonNull
  public final CircularProgressIndicator pbLoading;

  @NonNull
  public final TextInputLayout tilEmail;

  @NonNull
  public final TextInputLayout tilPassword;

  @NonNull
  public final AppCompatTextView tvDontHaveAccount;

  @NonNull
  public final AppCompatTextView tvForgotPassword;

  @NonNull
  public final AppCompatTextView tvSignUp;

  @NonNull
  public final AppCompatTextView tvTitle;

  private ActivityLoginBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppCompatButton btnLogIn, @NonNull TextInputEditText edtEmail,
      @NonNull TextInputEditText edtPassword, @NonNull AppCompatImageView imvIcon,
      @NonNull CircularProgressIndicator pbLoading, @NonNull TextInputLayout tilEmail,
      @NonNull TextInputLayout tilPassword, @NonNull AppCompatTextView tvDontHaveAccount,
      @NonNull AppCompatTextView tvForgotPassword, @NonNull AppCompatTextView tvSignUp,
      @NonNull AppCompatTextView tvTitle) {
    this.rootView = rootView;
    this.btnLogIn = btnLogIn;
    this.edtEmail = edtEmail;
    this.edtPassword = edtPassword;
    this.imvIcon = imvIcon;
    this.pbLoading = pbLoading;
    this.tilEmail = tilEmail;
    this.tilPassword = tilPassword;
    this.tvDontHaveAccount = tvDontHaveAccount;
    this.tvForgotPassword = tvForgotPassword;
    this.tvSignUp = tvSignUp;
    this.tvTitle = tvTitle;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_log_in;
      AppCompatButton btnLogIn = ViewBindings.findChildViewById(rootView, id);
      if (btnLogIn == null) {
        break missingId;
      }

      id = R.id.edt_email;
      TextInputEditText edtEmail = ViewBindings.findChildViewById(rootView, id);
      if (edtEmail == null) {
        break missingId;
      }

      id = R.id.edt_password;
      TextInputEditText edtPassword = ViewBindings.findChildViewById(rootView, id);
      if (edtPassword == null) {
        break missingId;
      }

      id = R.id.imv_icon;
      AppCompatImageView imvIcon = ViewBindings.findChildViewById(rootView, id);
      if (imvIcon == null) {
        break missingId;
      }

      id = R.id.pbLoading;
      CircularProgressIndicator pbLoading = ViewBindings.findChildViewById(rootView, id);
      if (pbLoading == null) {
        break missingId;
      }

      id = R.id.til_email;
      TextInputLayout tilEmail = ViewBindings.findChildViewById(rootView, id);
      if (tilEmail == null) {
        break missingId;
      }

      id = R.id.til_password;
      TextInputLayout tilPassword = ViewBindings.findChildViewById(rootView, id);
      if (tilPassword == null) {
        break missingId;
      }

      id = R.id.tv_dont_have_account;
      AppCompatTextView tvDontHaveAccount = ViewBindings.findChildViewById(rootView, id);
      if (tvDontHaveAccount == null) {
        break missingId;
      }

      id = R.id.tv_forgot_password;
      AppCompatTextView tvForgotPassword = ViewBindings.findChildViewById(rootView, id);
      if (tvForgotPassword == null) {
        break missingId;
      }

      id = R.id.tv_sign_up;
      AppCompatTextView tvSignUp = ViewBindings.findChildViewById(rootView, id);
      if (tvSignUp == null) {
        break missingId;
      }

      id = R.id.tv_title;
      AppCompatTextView tvTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvTitle == null) {
        break missingId;
      }

      return new ActivityLoginBinding((ConstraintLayout) rootView, btnLogIn, edtEmail, edtPassword,
          imvIcon, pbLoading, tilEmail, tilPassword, tvDontHaveAccount, tvForgotPassword, tvSignUp,
          tvTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}