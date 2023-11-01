package com.team15.muzimusic.data.database.daos;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.team15.muzimusic.data.database.entities.NotificationEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class NotificationDAO_Impl implements NotificationDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<NotificationEntity> __insertionAdapterOfNotificationEntity;

  private final EntityDeletionOrUpdateAdapter<NotificationEntity> __deletionAdapterOfNotificationEntity;

  private final EntityDeletionOrUpdateAdapter<NotificationEntity> __updateAdapterOfNotificationEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteListNotifications;

  private final SharedSQLiteStatement __preparedStmtOfReadAllNotification;

  public NotificationDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNotificationEntity = new EntityInsertionAdapter<NotificationEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `NotificationEntity` (`idNotification`,`content`,`action`,`notificationStatus`,`notificationTime`,`idAccount`,`accountName`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NotificationEntity value) {
        stmt.bindLong(1, value.getIdNotification());
        if (value.getContent() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getContent());
        }
        if (value.getAction() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAction());
        }
        stmt.bindLong(4, value.getNotificationStatus());
        if (value.getNotificationTime() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getNotificationTime());
        }
        if (value.getIdAccount() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindLong(6, value.getIdAccount());
        }
        if (value.getAccountName() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getAccountName());
        }
      }
    };
    this.__deletionAdapterOfNotificationEntity = new EntityDeletionOrUpdateAdapter<NotificationEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `NotificationEntity` WHERE `idNotification` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NotificationEntity value) {
        stmt.bindLong(1, value.getIdNotification());
      }
    };
    this.__updateAdapterOfNotificationEntity = new EntityDeletionOrUpdateAdapter<NotificationEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `NotificationEntity` SET `idNotification` = ?,`content` = ?,`action` = ?,`notificationStatus` = ?,`notificationTime` = ?,`idAccount` = ?,`accountName` = ? WHERE `idNotification` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NotificationEntity value) {
        stmt.bindLong(1, value.getIdNotification());
        if (value.getContent() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getContent());
        }
        if (value.getAction() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAction());
        }
        stmt.bindLong(4, value.getNotificationStatus());
        if (value.getNotificationTime() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getNotificationTime());
        }
        if (value.getIdAccount() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindLong(6, value.getIdAccount());
        }
        if (value.getAccountName() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getAccountName());
        }
        stmt.bindLong(8, value.getIdNotification());
      }
    };
    this.__preparedStmtOfDeleteListNotifications = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM NotificationEntity";
        return _query;
      }
    };
    this.__preparedStmtOfReadAllNotification = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE NotificationEntity SET notificationStatus = 1";
        return _query;
      }
    };
  }

  @Override
  public Object insertListNotification(final List<NotificationEntity> notifications,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfNotificationEntity.insert(notifications);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteNotification(final NotificationEntity notification,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfNotificationEntity.handle(notification);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object updateNotification(final NotificationEntity notification,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfNotificationEntity.handle(notification);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteListNotifications(final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteListNotifications.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteListNotifications.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object readAllNotification(final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfReadAllNotification.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfReadAllNotification.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object getListNotifications(
      final Continuation<? super List<NotificationEntity>> continuation) {
    final String _sql = "SELECT * FROM NotificationEntity ORDER BY idNotification DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<NotificationEntity>>() {
      @Override
      public List<NotificationEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfIdNotification = CursorUtil.getColumnIndexOrThrow(_cursor, "idNotification");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfAction = CursorUtil.getColumnIndexOrThrow(_cursor, "action");
          final int _cursorIndexOfNotificationStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "notificationStatus");
          final int _cursorIndexOfNotificationTime = CursorUtil.getColumnIndexOrThrow(_cursor, "notificationTime");
          final int _cursorIndexOfIdAccount = CursorUtil.getColumnIndexOrThrow(_cursor, "idAccount");
          final int _cursorIndexOfAccountName = CursorUtil.getColumnIndexOrThrow(_cursor, "accountName");
          final List<NotificationEntity> _result = new ArrayList<NotificationEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final NotificationEntity _item;
            final int _tmpIdNotification;
            _tmpIdNotification = _cursor.getInt(_cursorIndexOfIdNotification);
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            final String _tmpAction;
            if (_cursor.isNull(_cursorIndexOfAction)) {
              _tmpAction = null;
            } else {
              _tmpAction = _cursor.getString(_cursorIndexOfAction);
            }
            final int _tmpNotificationStatus;
            _tmpNotificationStatus = _cursor.getInt(_cursorIndexOfNotificationStatus);
            final String _tmpNotificationTime;
            if (_cursor.isNull(_cursorIndexOfNotificationTime)) {
              _tmpNotificationTime = null;
            } else {
              _tmpNotificationTime = _cursor.getString(_cursorIndexOfNotificationTime);
            }
            final Integer _tmpIdAccount;
            if (_cursor.isNull(_cursorIndexOfIdAccount)) {
              _tmpIdAccount = null;
            } else {
              _tmpIdAccount = _cursor.getInt(_cursorIndexOfIdAccount);
            }
            final String _tmpAccountName;
            if (_cursor.isNull(_cursorIndexOfAccountName)) {
              _tmpAccountName = null;
            } else {
              _tmpAccountName = _cursor.getString(_cursorIndexOfAccountName);
            }
            _item = new NotificationEntity(_tmpIdNotification,_tmpContent,_tmpAction,_tmpNotificationStatus,_tmpNotificationTime,_tmpIdAccount,_tmpAccountName);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
