// Generated by view binder compiler. Do not edit!
package com.team15.muzimusic.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.team15.muzimusic.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentAlbumDialogBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView btnGoToAddAlbum;

  @NonNull
  public final RecyclerView recyclerAlbum;

  private FragmentAlbumDialogBinding(@NonNull LinearLayout rootView,
      @NonNull TextView btnGoToAddAlbum, @NonNull RecyclerView recyclerAlbum) {
    this.rootView = rootView;
    this.btnGoToAddAlbum = btnGoToAddAlbum;
    this.recyclerAlbum = recyclerAlbum;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentAlbumDialogBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentAlbumDialogBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_album_dialog, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentAlbumDialogBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnGoToAddAlbum;
      TextView btnGoToAddAlbum = ViewBindings.findChildViewById(rootView, id);
      if (btnGoToAddAlbum == null) {
        break missingId;
      }

      id = R.id.recyclerAlbum;
      RecyclerView recyclerAlbum = ViewBindings.findChildViewById(rootView, id);
      if (recyclerAlbum == null) {
        break missingId;
      }

      return new FragmentAlbumDialogBinding((LinearLayout) rootView, btnGoToAddAlbum,
          recyclerAlbum);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}