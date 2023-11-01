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
import com.team15.muzimusic.data.database.entities.AlbumEntity;
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
public final class AlbumDao_Impl implements AlbumDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AlbumEntity> __insertionAdapterOfAlbumEntity;

  private final EntityDeletionOrUpdateAdapter<AlbumEntity> __deletionAdapterOfAlbumEntity;

  private final EntityDeletionOrUpdateAdapter<AlbumEntity> __updateAdapterOfAlbumEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAlbumById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllAlbum;

  public AlbumDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAlbumEntity = new EntityInsertionAdapter<AlbumEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `AlbumEntity` (`idAlbum`,`nameAlbum`,`created`,`idAccount`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AlbumEntity value) {
        stmt.bindLong(1, value.getIdAlbum());
        if (value.getNameAlbum() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNameAlbum());
        }
        if (value.getCreated() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCreated());
        }
        stmt.bindLong(4, value.getIdAccount());
      }
    };
    this.__deletionAdapterOfAlbumEntity = new EntityDeletionOrUpdateAdapter<AlbumEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `AlbumEntity` WHERE `idAlbum` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AlbumEntity value) {
        stmt.bindLong(1, value.getIdAlbum());
      }
    };
    this.__updateAdapterOfAlbumEntity = new EntityDeletionOrUpdateAdapter<AlbumEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `AlbumEntity` SET `idAlbum` = ?,`nameAlbum` = ?,`created` = ?,`idAccount` = ? WHERE `idAlbum` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AlbumEntity value) {
        stmt.bindLong(1, value.getIdAlbum());
        if (value.getNameAlbum() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNameAlbum());
        }
        if (value.getCreated() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCreated());
        }
        stmt.bindLong(4, value.getIdAccount());
        stmt.bindLong(5, value.getIdAlbum());
      }
    };
    this.__preparedStmtOfDeleteAlbumById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM AlbumEntity WHERE idAlbum = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllAlbum = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM AlbumEntity";
        return _query;
      }
    };
  }

  @Override
  public Object insertAlbum(final AlbumEntity albumEntity,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAlbumEntity.insert(albumEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteAlbum(final AlbumEntity albumEntity,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfAlbumEntity.handle(albumEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object updateAlbum(final AlbumEntity albumEntity,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfAlbumEntity.handle(albumEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteAlbumById(final int idAlbum, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAlbumById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, idAlbum);
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteAlbumById.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteAllAlbum(final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllAlbum.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteAllAlbum.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object getAllAlbum(final Continuation<? super List<AlbumEntity>> continuation) {
    final String _sql = "SELECT * FROM AlbumEntity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AlbumEntity>>() {
      @Override
      public List<AlbumEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfIdAlbum = CursorUtil.getColumnIndexOrThrow(_cursor, "idAlbum");
          final int _cursorIndexOfNameAlbum = CursorUtil.getColumnIndexOrThrow(_cursor, "nameAlbum");
          final int _cursorIndexOfCreated = CursorUtil.getColumnIndexOrThrow(_cursor, "created");
          final int _cursorIndexOfIdAccount = CursorUtil.getColumnIndexOrThrow(_cursor, "idAccount");
          final List<AlbumEntity> _result = new ArrayList<AlbumEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final AlbumEntity _item;
            final int _tmpIdAlbum;
            _tmpIdAlbum = _cursor.getInt(_cursorIndexOfIdAlbum);
            final String _tmpNameAlbum;
            if (_cursor.isNull(_cursorIndexOfNameAlbum)) {
              _tmpNameAlbum = null;
            } else {
              _tmpNameAlbum = _cursor.getString(_cursorIndexOfNameAlbum);
            }
            final String _tmpCreated;
            if (_cursor.isNull(_cursorIndexOfCreated)) {
              _tmpCreated = null;
            } else {
              _tmpCreated = _cursor.getString(_cursorIndexOfCreated);
            }
            final int _tmpIdAccount;
            _tmpIdAccount = _cursor.getInt(_cursorIndexOfIdAccount);
            _item = new AlbumEntity(_tmpIdAlbum,_tmpNameAlbum,_tmpCreated,_tmpIdAccount);
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
