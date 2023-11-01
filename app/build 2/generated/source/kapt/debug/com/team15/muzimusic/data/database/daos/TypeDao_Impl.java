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
import com.team15.muzimusic.data.database.entities.SongTypeEntity;
import com.team15.muzimusic.data.database.entities.TypeEntity;
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
public final class TypeDao_Impl implements TypeDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TypeEntity> __insertionAdapterOfTypeEntity;

  private final EntityInsertionAdapter<SongTypeEntity> __insertionAdapterOfSongTypeEntity;

  private final EntityDeletionOrUpdateAdapter<TypeEntity> __deletionAdapterOfTypeEntity;

  private final EntityDeletionOrUpdateAdapter<TypeEntity> __updateAdapterOfTypeEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllType;

  public TypeDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTypeEntity = new EntityInsertionAdapter<TypeEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `TypeEntity` (`idType`,`nameType`,`description`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TypeEntity value) {
        stmt.bindLong(1, value.getIdType());
        if (value.getNameType() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNameType());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
      }
    };
    this.__insertionAdapterOfSongTypeEntity = new EntityInsertionAdapter<SongTypeEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `SongTypeEntity` (`idSong`,`idType`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SongTypeEntity value) {
        stmt.bindLong(1, value.getIdSong());
        stmt.bindLong(2, value.getIdType());
      }
    };
    this.__deletionAdapterOfTypeEntity = new EntityDeletionOrUpdateAdapter<TypeEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `TypeEntity` WHERE `idType` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TypeEntity value) {
        stmt.bindLong(1, value.getIdType());
      }
    };
    this.__updateAdapterOfTypeEntity = new EntityDeletionOrUpdateAdapter<TypeEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `TypeEntity` SET `idType` = ?,`nameType` = ?,`description` = ? WHERE `idType` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TypeEntity value) {
        stmt.bindLong(1, value.getIdType());
        if (value.getNameType() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNameType());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        stmt.bindLong(4, value.getIdType());
      }
    };
    this.__preparedStmtOfDeleteAllType = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM TypeEntity";
        return _query;
      }
    };
  }

  @Override
  public Object insertType(final TypeEntity typeEntity,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTypeEntity.insert(typeEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object insertSongType(final SongTypeEntity songTypeEntity,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSongTypeEntity.insert(songTypeEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object insertListType(final List<TypeEntity> types,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTypeEntity.insert(types);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteType(final TypeEntity typeEntity,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfTypeEntity.handle(typeEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object updateType(final TypeEntity typeEntity,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfTypeEntity.handle(typeEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteAllType(final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllType.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteAllType.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object getAllType(final Continuation<? super List<TypeEntity>> continuation) {
    final String _sql = "SELECT * FROM TypeEntity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<TypeEntity>>() {
      @Override
      public List<TypeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfIdType = CursorUtil.getColumnIndexOrThrow(_cursor, "idType");
          final int _cursorIndexOfNameType = CursorUtil.getColumnIndexOrThrow(_cursor, "nameType");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final List<TypeEntity> _result = new ArrayList<TypeEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final TypeEntity _item;
            final int _tmpIdType;
            _tmpIdType = _cursor.getInt(_cursorIndexOfIdType);
            final String _tmpNameType;
            if (_cursor.isNull(_cursorIndexOfNameType)) {
              _tmpNameType = null;
            } else {
              _tmpNameType = _cursor.getString(_cursorIndexOfNameType);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            _item = new TypeEntity(_tmpIdType,_tmpNameType,_tmpDescription);
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
