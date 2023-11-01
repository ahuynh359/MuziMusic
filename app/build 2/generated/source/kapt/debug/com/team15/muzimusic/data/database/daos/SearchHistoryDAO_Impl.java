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
import com.team15.muzimusic.data.database.Converters;
import com.team15.muzimusic.data.database.entities.SearchHistoryEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SearchHistoryDAO_Impl implements SearchHistoryDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SearchHistoryEntity> __insertionAdapterOfSearchHistoryEntity;

  private final Converters __converters = new Converters();

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public SearchHistoryDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSearchHistoryEntity = new EntityInsertionAdapter<SearchHistoryEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `SearchHistoryEntity` (`keyword`,`time`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SearchHistoryEntity value) {
        if (value.getKeyword() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getKeyword());
        }
        final Long _tmp = __converters.dateToTimestamp(value.getTime());
        if (_tmp == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, _tmp);
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
  public Object insert(final SearchHistoryEntity searchHistory,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSearchHistoryEntity.insert(searchHistory);
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
  public Object getAllSearchHistory(
      final Continuation<? super List<SearchHistoryEntity>> continuation) {
    final String _sql = "SELECT * FROM SearchHistoryEntity ORDER BY time DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<SearchHistoryEntity>>() {
      @Override
      public List<SearchHistoryEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfKeyword = CursorUtil.getColumnIndexOrThrow(_cursor, "keyword");
          final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
          final List<SearchHistoryEntity> _result = new ArrayList<SearchHistoryEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final SearchHistoryEntity _item;
            final String _tmpKeyword;
            if (_cursor.isNull(_cursorIndexOfKeyword)) {
              _tmpKeyword = null;
            } else {
              _tmpKeyword = _cursor.getString(_cursorIndexOfKeyword);
            }
            final Date _tmpTime;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfTime)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfTime);
            }
            _tmpTime = __converters.fromTimestamp(_tmp);
            _item = new SearchHistoryEntity(_tmpKeyword,_tmpTime);
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
