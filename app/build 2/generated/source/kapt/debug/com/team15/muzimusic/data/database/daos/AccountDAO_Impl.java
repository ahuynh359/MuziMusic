package com.team15.muzimusic.data.database.daos;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.team15.muzimusic.data.database.entities.AccountEntity;
import java.lang.Class;
import java.lang.Exception;
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
public final class AccountDAO_Impl implements AccountDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AccountEntity> __insertionAdapterOfAccountEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public AccountDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAccountEntity = new EntityInsertionAdapter<AccountEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `AccountEntity` (`idAccount`,`email`,`accountName`,`avatar`,`created`,`totalSong`,`totalLove`,`totalFollowers`,`totalFollowings`,`avatarPath`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AccountEntity value) {
        stmt.bindLong(1, value.getIdAccount());
        if (value.getEmail() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getEmail());
        }
        if (value.getAccountName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAccountName());
        }
        if (value.getAvatar() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getAvatar());
        }
        if (value.getCreated() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getCreated());
        }
        stmt.bindLong(6, value.getTotalSong());
        stmt.bindLong(7, value.getTotalLove());
        stmt.bindLong(8, value.getTotalFollowers());
        stmt.bindLong(9, value.getTotalFollowings());
        if (value.getAvatarPath() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getAvatarPath());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM AccountEntity";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final AccountEntity account, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAccountEntity.insert(account);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object insertAll(final List<AccountEntity> accounts,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAccountEntity.insert(accounts);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteAll(final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteAll.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object getAll(final Continuation<? super List<AccountEntity>> continuation) {
    final String _sql = "SELECT * FROM AccountEntity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AccountEntity>>() {
      @Override
      public List<AccountEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfIdAccount = CursorUtil.getColumnIndexOrThrow(_cursor, "idAccount");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfAccountName = CursorUtil.getColumnIndexOrThrow(_cursor, "accountName");
          final int _cursorIndexOfAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "avatar");
          final int _cursorIndexOfCreated = CursorUtil.getColumnIndexOrThrow(_cursor, "created");
          final int _cursorIndexOfTotalSong = CursorUtil.getColumnIndexOrThrow(_cursor, "totalSong");
          final int _cursorIndexOfTotalLove = CursorUtil.getColumnIndexOrThrow(_cursor, "totalLove");
          final int _cursorIndexOfTotalFollowers = CursorUtil.getColumnIndexOrThrow(_cursor, "totalFollowers");
          final int _cursorIndexOfTotalFollowings = CursorUtil.getColumnIndexOrThrow(_cursor, "totalFollowings");
          final int _cursorIndexOfAvatarPath = CursorUtil.getColumnIndexOrThrow(_cursor, "avatarPath");
          final List<AccountEntity> _result = new ArrayList<AccountEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final AccountEntity _item;
            final int _tmpIdAccount;
            _tmpIdAccount = _cursor.getInt(_cursorIndexOfIdAccount);
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpAccountName;
            if (_cursor.isNull(_cursorIndexOfAccountName)) {
              _tmpAccountName = null;
            } else {
              _tmpAccountName = _cursor.getString(_cursorIndexOfAccountName);
            }
            final String _tmpAvatar;
            if (_cursor.isNull(_cursorIndexOfAvatar)) {
              _tmpAvatar = null;
            } else {
              _tmpAvatar = _cursor.getString(_cursorIndexOfAvatar);
            }
            final String _tmpCreated;
            if (_cursor.isNull(_cursorIndexOfCreated)) {
              _tmpCreated = null;
            } else {
              _tmpCreated = _cursor.getString(_cursorIndexOfCreated);
            }
            final int _tmpTotalSong;
            _tmpTotalSong = _cursor.getInt(_cursorIndexOfTotalSong);
            final int _tmpTotalLove;
            _tmpTotalLove = _cursor.getInt(_cursorIndexOfTotalLove);
            final int _tmpTotalFollowers;
            _tmpTotalFollowers = _cursor.getInt(_cursorIndexOfTotalFollowers);
            final int _tmpTotalFollowings;
            _tmpTotalFollowings = _cursor.getInt(_cursorIndexOfTotalFollowings);
            final String _tmpAvatarPath;
            if (_cursor.isNull(_cursorIndexOfAvatarPath)) {
              _tmpAvatarPath = null;
            } else {
              _tmpAvatarPath = _cursor.getString(_cursorIndexOfAvatarPath);
            }
            _item = new AccountEntity(_tmpIdAccount,_tmpEmail,_tmpAccountName,_tmpAvatar,_tmpCreated,_tmpTotalSong,_tmpTotalLove,_tmpTotalFollowers,_tmpTotalFollowings,_tmpAvatarPath);
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

  @Override
  public Object getAccount(final int idAccount,
      final Continuation<? super AccountEntity> continuation) {
    final String _sql = "SELECT * FROM AccountEntity WHERE idAccount = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, idAccount);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<AccountEntity>() {
      @Override
      public AccountEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfIdAccount = CursorUtil.getColumnIndexOrThrow(_cursor, "idAccount");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfAccountName = CursorUtil.getColumnIndexOrThrow(_cursor, "accountName");
          final int _cursorIndexOfAvatar = CursorUtil.getColumnIndexOrThrow(_cursor, "avatar");
          final int _cursorIndexOfCreated = CursorUtil.getColumnIndexOrThrow(_cursor, "created");
          final int _cursorIndexOfTotalSong = CursorUtil.getColumnIndexOrThrow(_cursor, "totalSong");
          final int _cursorIndexOfTotalLove = CursorUtil.getColumnIndexOrThrow(_cursor, "totalLove");
          final int _cursorIndexOfTotalFollowers = CursorUtil.getColumnIndexOrThrow(_cursor, "totalFollowers");
          final int _cursorIndexOfTotalFollowings = CursorUtil.getColumnIndexOrThrow(_cursor, "totalFollowings");
          final int _cursorIndexOfAvatarPath = CursorUtil.getColumnIndexOrThrow(_cursor, "avatarPath");
          final AccountEntity _result;
          if(_cursor.moveToFirst()) {
            final int _tmpIdAccount;
            _tmpIdAccount = _cursor.getInt(_cursorIndexOfIdAccount);
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpAccountName;
            if (_cursor.isNull(_cursorIndexOfAccountName)) {
              _tmpAccountName = null;
            } else {
              _tmpAccountName = _cursor.getString(_cursorIndexOfAccountName);
            }
            final String _tmpAvatar;
            if (_cursor.isNull(_cursorIndexOfAvatar)) {
              _tmpAvatar = null;
            } else {
              _tmpAvatar = _cursor.getString(_cursorIndexOfAvatar);
            }
            final String _tmpCreated;
            if (_cursor.isNull(_cursorIndexOfCreated)) {
              _tmpCreated = null;
            } else {
              _tmpCreated = _cursor.getString(_cursorIndexOfCreated);
            }
            final int _tmpTotalSong;
            _tmpTotalSong = _cursor.getInt(_cursorIndexOfTotalSong);
            final int _tmpTotalLove;
            _tmpTotalLove = _cursor.getInt(_cursorIndexOfTotalLove);
            final int _tmpTotalFollowers;
            _tmpTotalFollowers = _cursor.getInt(_cursorIndexOfTotalFollowers);
            final int _tmpTotalFollowings;
            _tmpTotalFollowings = _cursor.getInt(_cursorIndexOfTotalFollowings);
            final String _tmpAvatarPath;
            if (_cursor.isNull(_cursorIndexOfAvatarPath)) {
              _tmpAvatarPath = null;
            } else {
              _tmpAvatarPath = _cursor.getString(_cursorIndexOfAvatarPath);
            }
            _result = new AccountEntity(_tmpIdAccount,_tmpEmail,_tmpAccountName,_tmpAvatar,_tmpCreated,_tmpTotalSong,_tmpTotalLove,_tmpTotalFollowers,_tmpTotalFollowings,_tmpAvatarPath);
          } else {
            _result = null;
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
