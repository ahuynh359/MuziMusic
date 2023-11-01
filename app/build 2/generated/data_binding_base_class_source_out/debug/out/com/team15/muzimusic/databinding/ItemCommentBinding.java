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
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.team15.muzimusic.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemCommentBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextView btnDeleteComment;

  @NonNull
  public final TextView btnEditComment;

  @NonNull
  public final TextView btnReplyComment;

  @NonNull
  public final CardView cardViewCmtAvatar;

  @NonNull
  public final TextView dot;

  @NonNull
  public final TextView dot1;

  @NonNull
  public final TextView dot2;

  @NonNull
  public final ImageView imgAvatar;

  @NonNull
  public final LinearLayout layoutAction;

  @NonNull
  public final RelativeLayout layoutCommentContent;

  @NonNull
  public final TextView tvAccountName;

  @NonNull
  public final TextView tvCommentContent;

  @NonNull
  public final TextView tvCommentTime;

  private ItemCommentBinding(@NonNull RelativeLayout rootView, @NonNull TextView btnDeleteComment,
      @NonNull TextView btnEditComment, @NonNull TextView btnReplyComment,
      @NonNull CardView cardViewCmtAvatar, @NonNull TextView dot, @NonNull TextView dot1,
      @NonNull TextView dot2, @NonNull ImageView imgAvatar, @NonNull LinearLayout layoutAction,
      @NonNull RelativeLayout layoutCommentContent, @NonNull TextView tvAccountName,
      @NonNull TextView tvCommentContent, @NonNull TextView tvCommentTime) {
    this.rootView = rootView;
    this.btnDeleteComment = btnDeleteComment;
    this.btnEditComment = btnEditComment;
    this.btnReplyComment = btnReplyComment;
    this.cardViewCmtAvatar = cardViewCmtAvatar;
    this.dot = dot;
    this.dot1 = dot1;
    this.dot2 = dot2;
    this.imgAvatar = imgAvatar;
    this.layoutAction = layoutAction;
    this.layoutCommentContent = layoutCommentContent;
    this.tvAccountName = tvAccountName;
    this.tvCommentContent = tvCommentContent;
    this.tvCommentTime = tvCommentTime;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemCommentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemCommentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_comment, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemCommentBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnDeleteComment;
      TextView btnDeleteComment = ViewBindings.findChildViewById(rootView, id);
      if (btnDeleteComment == null) {
        break missingId;
      }

      id = R.id.btnEditComment;
      TextView btnEditComment = ViewBindings.findChildViewById(rootView, id);
      if (btnEditComment == null) {
        break missingId;
      }

      id = R.id.btnReplyComment;
      TextView btnReplyComment = ViewBindings.findChildViewById(rootView, id);
      if (btnReplyComment == null) {
        break missingId;
      }

      id = R.id.cardViewCmtAvatar;
      CardView cardViewCmtAvatar = ViewBindings.findChildViewById(rootView, id);
      if (cardViewCmtAvatar == null) {
        break missingId;
      }

      id = R.id.dot;
      TextView dot = ViewBindings.findChildViewById(rootView, id);
      if (dot == null) {
        break missingId;
      }

      id = R.id.dot1;
      TextView dot1 = ViewBindings.findChildViewById(rootView, id);
      if (dot1 == null) {
        break missingId;
      }

      id = R.id.dot2;
      TextView dot2 = ViewBindings.findChildViewById(rootView, id);
      if (dot2 == null) {
        break missingId;
      }

      id = R.id.imgAvatar;
      ImageView imgAvatar = ViewBindings.findChildViewById(rootView, id);
      if (imgAvatar == null) {
        break missingId;
      }

      id = R.id.layoutAction;
      LinearLayout layoutAction = ViewBindings.findChildViewById(rootView, id);
      if (layoutAction == null) {
        break missingId;
      }

      id = R.id.layoutCommentContent;
      RelativeLayout layoutCommentContent = ViewBindings.findChildViewById(rootView, id);
      if (layoutCommentContent == null) {
        break missingId;
      }

      id = R.id.tvAccountName;
      TextView tvAccountName = ViewBindings.findChildViewById(rootView, id);
      if (tvAccountName == null) {
        break missingId;
      }

      id = R.id.tvCommentContent;
      TextView tvCommentContent = ViewBindings.findChildViewById(rootView, id);
      if (tvCommentContent == null) {
        break missingId;
      }

      id = R.id.tvCommentTime;
      TextView tvCommentTime = ViewBindings.findChildViewById(rootView, id);
      if (tvCommentTime == null) {
        break missingId;
      }

      return new ItemCommentBinding((RelativeLayout) rootView, btnDeleteComment, btnEditComment,
          btnReplyComment, cardViewCmtAvatar, dot, dot1, dot2, imgAvatar, layoutAction,
          layoutCommentContent, tvAccountName, tvCommentContent, tvCommentTime);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}