// Generated by Dagger (https://dagger.dev).
package com.team15.muzimusic.ui.comment.reply;

import com.team15.muzimusic.data.repositories.CommentRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class CommentReplyViewModel_Factory implements Factory<CommentReplyViewModel> {
  private final Provider<CommentRepository> commentRepositoryProvider;

  public CommentReplyViewModel_Factory(Provider<CommentRepository> commentRepositoryProvider) {
    this.commentRepositoryProvider = commentRepositoryProvider;
  }

  @Override
  public CommentReplyViewModel get() {
    return newInstance(commentRepositoryProvider.get());
  }

  public static CommentReplyViewModel_Factory create(
      Provider<CommentRepository> commentRepositoryProvider) {
    return new CommentReplyViewModel_Factory(commentRepositoryProvider);
  }

  public static CommentReplyViewModel newInstance(CommentRepository commentRepository) {
    return new CommentReplyViewModel(commentRepository);
  }
}
