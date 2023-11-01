package com.team15.muzimusic.data.database.daos;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.collection.LongSparseArray;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.team15.muzimusic.data.database.entities.AccountEntity;
import com.team15.muzimusic.data.database.entities.AlbumEntity;
import com.team15.muzimusic.data.database.entities.SongEntity;
import com.team15.muzimusic.data.database.entities.SongFullEntity;
import com.team15.muzimusic.data.database.entities.SongSingersEntity;
import com.team15.muzimusic.data.database.entities.TypeEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SongDAO_Impl implements SongDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SongEntity> __insertionAdapterOfSongEntity;

  private final EntityInsertionAdapter<SongSingersEntity> __insertionAdapterOfSongSingersEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteListTopSongs;

  private final SharedSQLiteStatement __preparedStmtOfUpdateSongImagePath;

  private final SharedSQLiteStatement __preparedStmtOfUpdateSongFilePath;

  public SongDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSongEntity = new EntityInsertionAdapter<SongEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `SongEntity` (`idSong`,`nameSong`,`created`,`description`,`link`,`listen`,`lyrics`,`loveStatus`,`imageSong`,`songStatus`,`totalLove`,`idAccount`,`idAlbum`,`downloaded`,`filePath`,`imagePath`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SongEntity value) {
        stmt.bindLong(1, value.getIdSong());
        if (value.getNameSong() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNameSong());
        }
        if (value.getCreated() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCreated());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDescription());
        }
        if (value.getLink() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getLink());
        }
        stmt.bindLong(6, value.getListen());
        if (value.getLyrics() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getLyrics());
        }
        final int _tmp = value.getLoveStatus() ? 1 : 0;
        stmt.bindLong(8, _tmp);
        if (value.getImageSong() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getImageSong());
        }
        stmt.bindLong(10, value.getSongStatus());
        stmt.bindLong(11, value.getTotalLove());
        stmt.bindLong(12, value.getIdAccount());
        stmt.bindLong(13, value.getIdAlbum());
        final int _tmp_1 = value.getDownloaded() ? 1 : 0;
        stmt.bindLong(14, _tmp_1);
        if (value.getFilePath() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.getFilePath());
        }
        if (value.getImagePath() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.getImagePath());
        }
      }
    };
    this.__insertionAdapterOfSongSingersEntity = new EntityInsertionAdapter<SongSingersEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `SongSingersEntity` (`idSong`,`idAccount`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SongSingersEntity value) {
        stmt.bindLong(1, value.getIdSong());
        stmt.bindLong(2, value.getIdAccount());
      }
    };
    this.__preparedStmtOfDeleteListTopSongs = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM SongEntity";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateSongImagePath = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE SongEntity SET imagePath = ? WHERE idSong = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateSongFilePath = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE SongEntity SET filePath = ? WHERE idSong = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertSong(final SongEntity songs, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSongEntity.insert(songs);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object insertSongSingers(final SongSingersEntity songSingers,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSongSingersEntity.insert(songSingers);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteListTopSongs(final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteListTopSongs.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeleteListTopSongs.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object updateSongImagePath(final int idSong, final String imagePath,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateSongImagePath.acquire();
        int _argIndex = 1;
        if (imagePath == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, imagePath);
        }
        _argIndex = 2;
        _stmt.bindLong(_argIndex, idSong);
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfUpdateSongImagePath.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object updateSongFilePath(final int idSong, final String filePath,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateSongFilePath.acquire();
        int _argIndex = 1;
        if (filePath == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, filePath);
        }
        _argIndex = 2;
        _stmt.bindLong(_argIndex, idSong);
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfUpdateSongFilePath.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object getListTopSongs(final Continuation<? super List<SongFullEntity>> continuation) {
    final String _sql = "SELECT * FROM SongEntity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<SongFullEntity>>() {
      @Override
      public List<SongFullEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
        try {
          final int _cursorIndexOfIdSong = CursorUtil.getColumnIndexOrThrow(_cursor, "idSong");
          final int _cursorIndexOfNameSong = CursorUtil.getColumnIndexOrThrow(_cursor, "nameSong");
          final int _cursorIndexOfCreated = CursorUtil.getColumnIndexOrThrow(_cursor, "created");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfLink = CursorUtil.getColumnIndexOrThrow(_cursor, "link");
          final int _cursorIndexOfListen = CursorUtil.getColumnIndexOrThrow(_cursor, "listen");
          final int _cursorIndexOfLyrics = CursorUtil.getColumnIndexOrThrow(_cursor, "lyrics");
          final int _cursorIndexOfLoveStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "loveStatus");
          final int _cursorIndexOfImageSong = CursorUtil.getColumnIndexOrThrow(_cursor, "imageSong");
          final int _cursorIndexOfSongStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "songStatus");
          final int _cursorIndexOfTotalLove = CursorUtil.getColumnIndexOrThrow(_cursor, "totalLove");
          final int _cursorIndexOfIdAccount = CursorUtil.getColumnIndexOrThrow(_cursor, "idAccount");
          final int _cursorIndexOfIdAlbum = CursorUtil.getColumnIndexOrThrow(_cursor, "idAlbum");
          final int _cursorIndexOfDownloaded = CursorUtil.getColumnIndexOrThrow(_cursor, "downloaded");
          final int _cursorIndexOfFilePath = CursorUtil.getColumnIndexOrThrow(_cursor, "filePath");
          final int _cursorIndexOfImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "imagePath");
          final LongSparseArray<AccountEntity> _collectionAccount = new LongSparseArray<AccountEntity>();
          final LongSparseArray<ArrayList<AccountEntity>> _collectionSingers = new LongSparseArray<ArrayList<AccountEntity>>();
          final LongSparseArray<ArrayList<TypeEntity>> _collectionTypes = new LongSparseArray<ArrayList<TypeEntity>>();
          final LongSparseArray<AlbumEntity> _collectionAlbum = new LongSparseArray<AlbumEntity>();
          while (_cursor.moveToNext()) {
            final long _tmpKey = _cursor.getLong(_cursorIndexOfIdAccount);
            _collectionAccount.put(_tmpKey, null);
            final long _tmpKey_1 = _cursor.getLong(_cursorIndexOfIdSong);
            ArrayList<AccountEntity> _tmpSingersCollection = _collectionSingers.get(_tmpKey_1);
            if (_tmpSingersCollection == null) {
              _tmpSingersCollection = new ArrayList<AccountEntity>();
              _collectionSingers.put(_tmpKey_1, _tmpSingersCollection);
            }
            final long _tmpKey_2 = _cursor.getLong(_cursorIndexOfIdSong);
            ArrayList<TypeEntity> _tmpTypesCollection = _collectionTypes.get(_tmpKey_2);
            if (_tmpTypesCollection == null) {
              _tmpTypesCollection = new ArrayList<TypeEntity>();
              _collectionTypes.put(_tmpKey_2, _tmpTypesCollection);
            }
            final long _tmpKey_3 = _cursor.getLong(_cursorIndexOfIdAlbum);
            _collectionAlbum.put(_tmpKey_3, null);
          }
          _cursor.moveToPosition(-1);
          __fetchRelationshipAccountEntityAscomTeam15MuzimusicDataDatabaseEntitiesAccountEntity(_collectionAccount);
          __fetchRelationshipAccountEntityAscomTeam15MuzimusicDataDatabaseEntitiesAccountEntity_1(_collectionSingers);
          __fetchRelationshipTypeEntityAscomTeam15MuzimusicDataDatabaseEntitiesTypeEntity(_collectionTypes);
          __fetchRelationshipAlbumEntityAscomTeam15MuzimusicDataDatabaseEntitiesAlbumEntity(_collectionAlbum);
          final List<SongFullEntity> _result = new ArrayList<SongFullEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final SongFullEntity _item;
            final SongEntity _tmpSong;
            if (! (_cursor.isNull(_cursorIndexOfIdSong) && _cursor.isNull(_cursorIndexOfNameSong) && _cursor.isNull(_cursorIndexOfCreated) && _cursor.isNull(_cursorIndexOfDescription) && _cursor.isNull(_cursorIndexOfLink) && _cursor.isNull(_cursorIndexOfListen) && _cursor.isNull(_cursorIndexOfLyrics) && _cursor.isNull(_cursorIndexOfLoveStatus) && _cursor.isNull(_cursorIndexOfImageSong) && _cursor.isNull(_cursorIndexOfSongStatus) && _cursor.isNull(_cursorIndexOfTotalLove) && _cursor.isNull(_cursorIndexOfIdAccount) && _cursor.isNull(_cursorIndexOfIdAlbum) && _cursor.isNull(_cursorIndexOfDownloaded) && _cursor.isNull(_cursorIndexOfFilePath) && _cursor.isNull(_cursorIndexOfImagePath))) {
              final int _tmpIdSong;
              _tmpIdSong = _cursor.getInt(_cursorIndexOfIdSong);
              final String _tmpNameSong;
              if (_cursor.isNull(_cursorIndexOfNameSong)) {
                _tmpNameSong = null;
              } else {
                _tmpNameSong = _cursor.getString(_cursorIndexOfNameSong);
              }
              final String _tmpCreated;
              if (_cursor.isNull(_cursorIndexOfCreated)) {
                _tmpCreated = null;
              } else {
                _tmpCreated = _cursor.getString(_cursorIndexOfCreated);
              }
              final String _tmpDescription;
              if (_cursor.isNull(_cursorIndexOfDescription)) {
                _tmpDescription = null;
              } else {
                _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
              }
              final String _tmpLink;
              if (_cursor.isNull(_cursorIndexOfLink)) {
                _tmpLink = null;
              } else {
                _tmpLink = _cursor.getString(_cursorIndexOfLink);
              }
              final int _tmpListen;
              _tmpListen = _cursor.getInt(_cursorIndexOfListen);
              final String _tmpLyrics;
              if (_cursor.isNull(_cursorIndexOfLyrics)) {
                _tmpLyrics = null;
              } else {
                _tmpLyrics = _cursor.getString(_cursorIndexOfLyrics);
              }
              final boolean _tmpLoveStatus;
              final int _tmp;
              _tmp = _cursor.getInt(_cursorIndexOfLoveStatus);
              _tmpLoveStatus = _tmp != 0;
              final String _tmpImageSong;
              if (_cursor.isNull(_cursorIndexOfImageSong)) {
                _tmpImageSong = null;
              } else {
                _tmpImageSong = _cursor.getString(_cursorIndexOfImageSong);
              }
              final int _tmpSongStatus;
              _tmpSongStatus = _cursor.getInt(_cursorIndexOfSongStatus);
              final int _tmpTotalLove;
              _tmpTotalLove = _cursor.getInt(_cursorIndexOfTotalLove);
              final int _tmpIdAccount;
              _tmpIdAccount = _cursor.getInt(_cursorIndexOfIdAccount);
              final int _tmpIdAlbum;
              _tmpIdAlbum = _cursor.getInt(_cursorIndexOfIdAlbum);
              final boolean _tmpDownloaded;
              final int _tmp_1;
              _tmp_1 = _cursor.getInt(_cursorIndexOfDownloaded);
              _tmpDownloaded = _tmp_1 != 0;
              final String _tmpFilePath;
              if (_cursor.isNull(_cursorIndexOfFilePath)) {
                _tmpFilePath = null;
              } else {
                _tmpFilePath = _cursor.getString(_cursorIndexOfFilePath);
              }
              final String _tmpImagePath;
              if (_cursor.isNull(_cursorIndexOfImagePath)) {
                _tmpImagePath = null;
              } else {
                _tmpImagePath = _cursor.getString(_cursorIndexOfImagePath);
              }
              _tmpSong = new SongEntity(_tmpIdSong,_tmpNameSong,_tmpCreated,_tmpDescription,_tmpLink,_tmpListen,_tmpLyrics,_tmpLoveStatus,_tmpImageSong,_tmpSongStatus,_tmpTotalLove,_tmpIdAccount,_tmpIdAlbum,_tmpDownloaded,_tmpFilePath,_tmpImagePath);
            }  else  {
              _tmpSong = null;
            }
            AccountEntity _tmpAccount = null;
            final long _tmpKey_4 = _cursor.getLong(_cursorIndexOfIdAccount);
            _tmpAccount = _collectionAccount.get(_tmpKey_4);
            ArrayList<AccountEntity> _tmpSingersCollection_1 = null;
            final long _tmpKey_5 = _cursor.getLong(_cursorIndexOfIdSong);
            _tmpSingersCollection_1 = _collectionSingers.get(_tmpKey_5);
            if (_tmpSingersCollection_1 == null) {
              _tmpSingersCollection_1 = new ArrayList<AccountEntity>();
            }
            ArrayList<TypeEntity> _tmpTypesCollection_1 = null;
            final long _tmpKey_6 = _cursor.getLong(_cursorIndexOfIdSong);
            _tmpTypesCollection_1 = _collectionTypes.get(_tmpKey_6);
            if (_tmpTypesCollection_1 == null) {
              _tmpTypesCollection_1 = new ArrayList<TypeEntity>();
            }
            AlbumEntity _tmpAlbum = null;
            final long _tmpKey_7 = _cursor.getLong(_cursorIndexOfIdAlbum);
            _tmpAlbum = _collectionAlbum.get(_tmpKey_7);
            _item = new SongFullEntity(_tmpSong,_tmpAccount,_tmpSingersCollection_1,_tmpTypesCollection_1,_tmpAlbum);
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

  private void __fetchRelationshipAccountEntityAscomTeam15MuzimusicDataDatabaseEntitiesAccountEntity(
      final LongSparseArray<AccountEntity> _map) {
    if (_map.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      LongSparseArray<AccountEntity> _tmpInnerMap = new LongSparseArray<AccountEntity>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _tmpIndex = 0;
      int _mapIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), null);
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipAccountEntityAscomTeam15MuzimusicDataDatabaseEntitiesAccountEntity(_tmpInnerMap);
          _map.putAll(_tmpInnerMap);
          _tmpInnerMap = new LongSparseArray<AccountEntity>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipAccountEntityAscomTeam15MuzimusicDataDatabaseEntitiesAccountEntity(_tmpInnerMap);
        _map.putAll(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `idAccount`,`email`,`accountName`,`avatar`,`created`,`totalSong`,`totalLove`,`totalFollowers`,`totalFollowings`,`avatarPath` FROM `AccountEntity` WHERE `idAccount` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex ++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "idAccount");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfIdAccount = 0;
      final int _cursorIndexOfEmail = 1;
      final int _cursorIndexOfAccountName = 2;
      final int _cursorIndexOfAvatar = 3;
      final int _cursorIndexOfCreated = 4;
      final int _cursorIndexOfTotalSong = 5;
      final int _cursorIndexOfTotalLove = 6;
      final int _cursorIndexOfTotalFollowers = 7;
      final int _cursorIndexOfTotalFollowings = 8;
      final int _cursorIndexOfAvatarPath = 9;
      while(_cursor.moveToNext()) {
        final long _tmpKey = _cursor.getLong(_itemKeyIndex);
        if (_map.containsKey(_tmpKey)) {
          final AccountEntity _item_1;
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
          _item_1 = new AccountEntity(_tmpIdAccount,_tmpEmail,_tmpAccountName,_tmpAvatar,_tmpCreated,_tmpTotalSong,_tmpTotalLove,_tmpTotalFollowers,_tmpTotalFollowings,_tmpAvatarPath);
          _map.put(_tmpKey, _item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }

  private void __fetchRelationshipAccountEntityAscomTeam15MuzimusicDataDatabaseEntitiesAccountEntity_1(
      final LongSparseArray<ArrayList<AccountEntity>> _map) {
    if (_map.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      LongSparseArray<ArrayList<AccountEntity>> _tmpInnerMap = new LongSparseArray<ArrayList<AccountEntity>>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _tmpIndex = 0;
      int _mapIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipAccountEntityAscomTeam15MuzimusicDataDatabaseEntitiesAccountEntity_1(_tmpInnerMap);
          _tmpInnerMap = new LongSparseArray<ArrayList<AccountEntity>>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipAccountEntityAscomTeam15MuzimusicDataDatabaseEntitiesAccountEntity_1(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `AccountEntity`.`idAccount` AS `idAccount`,`AccountEntity`.`email` AS `email`,`AccountEntity`.`accountName` AS `accountName`,`AccountEntity`.`avatar` AS `avatar`,`AccountEntity`.`created` AS `created`,`AccountEntity`.`totalSong` AS `totalSong`,`AccountEntity`.`totalLove` AS `totalLove`,`AccountEntity`.`totalFollowers` AS `totalFollowers`,`AccountEntity`.`totalFollowings` AS `totalFollowings`,`AccountEntity`.`avatarPath` AS `avatarPath`,_junction.`idSong` FROM `SongSingersEntity` AS _junction INNER JOIN `AccountEntity` ON (_junction.`idAccount` = `AccountEntity`.`idAccount`) WHERE _junction.`idSong` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex ++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = 10; // _junction.idSong;
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfIdAccount = 0;
      final int _cursorIndexOfEmail = 1;
      final int _cursorIndexOfAccountName = 2;
      final int _cursorIndexOfAvatar = 3;
      final int _cursorIndexOfCreated = 4;
      final int _cursorIndexOfTotalSong = 5;
      final int _cursorIndexOfTotalLove = 6;
      final int _cursorIndexOfTotalFollowers = 7;
      final int _cursorIndexOfTotalFollowings = 8;
      final int _cursorIndexOfAvatarPath = 9;
      while(_cursor.moveToNext()) {
        final long _tmpKey = _cursor.getLong(_itemKeyIndex);
        ArrayList<AccountEntity> _tmpRelation = _map.get(_tmpKey);
        if (_tmpRelation != null) {
          final AccountEntity _item_1;
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
          _item_1 = new AccountEntity(_tmpIdAccount,_tmpEmail,_tmpAccountName,_tmpAvatar,_tmpCreated,_tmpTotalSong,_tmpTotalLove,_tmpTotalFollowers,_tmpTotalFollowings,_tmpAvatarPath);
          _tmpRelation.add(_item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }

  private void __fetchRelationshipTypeEntityAscomTeam15MuzimusicDataDatabaseEntitiesTypeEntity(
      final LongSparseArray<ArrayList<TypeEntity>> _map) {
    if (_map.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      LongSparseArray<ArrayList<TypeEntity>> _tmpInnerMap = new LongSparseArray<ArrayList<TypeEntity>>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _tmpIndex = 0;
      int _mapIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipTypeEntityAscomTeam15MuzimusicDataDatabaseEntitiesTypeEntity(_tmpInnerMap);
          _tmpInnerMap = new LongSparseArray<ArrayList<TypeEntity>>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipTypeEntityAscomTeam15MuzimusicDataDatabaseEntitiesTypeEntity(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `TypeEntity`.`idType` AS `idType`,`TypeEntity`.`nameType` AS `nameType`,`TypeEntity`.`description` AS `description`,_junction.`idSong` FROM `SongTypeEntity` AS _junction INNER JOIN `TypeEntity` ON (_junction.`idType` = `TypeEntity`.`idType`) WHERE _junction.`idSong` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex ++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = 3; // _junction.idSong;
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfIdType = 0;
      final int _cursorIndexOfNameType = 1;
      final int _cursorIndexOfDescription = 2;
      while(_cursor.moveToNext()) {
        final long _tmpKey = _cursor.getLong(_itemKeyIndex);
        ArrayList<TypeEntity> _tmpRelation = _map.get(_tmpKey);
        if (_tmpRelation != null) {
          final TypeEntity _item_1;
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
          _item_1 = new TypeEntity(_tmpIdType,_tmpNameType,_tmpDescription);
          _tmpRelation.add(_item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }

  private void __fetchRelationshipAlbumEntityAscomTeam15MuzimusicDataDatabaseEntitiesAlbumEntity(
      final LongSparseArray<AlbumEntity> _map) {
    if (_map.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      LongSparseArray<AlbumEntity> _tmpInnerMap = new LongSparseArray<AlbumEntity>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _tmpIndex = 0;
      int _mapIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), null);
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipAlbumEntityAscomTeam15MuzimusicDataDatabaseEntitiesAlbumEntity(_tmpInnerMap);
          _map.putAll(_tmpInnerMap);
          _tmpInnerMap = new LongSparseArray<AlbumEntity>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipAlbumEntityAscomTeam15MuzimusicDataDatabaseEntitiesAlbumEntity(_tmpInnerMap);
        _map.putAll(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `idAlbum`,`nameAlbum`,`created`,`idAccount` FROM `AlbumEntity` WHERE `idAlbum` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex ++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "idAlbum");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfIdAlbum = 0;
      final int _cursorIndexOfNameAlbum = 1;
      final int _cursorIndexOfCreated = 2;
      final int _cursorIndexOfIdAccount = 3;
      while(_cursor.moveToNext()) {
        final long _tmpKey = _cursor.getLong(_itemKeyIndex);
        if (_map.containsKey(_tmpKey)) {
          final AlbumEntity _item_1;
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
          _item_1 = new AlbumEntity(_tmpIdAlbum,_tmpNameAlbum,_tmpCreated,_tmpIdAccount);
          _map.put(_tmpKey, _item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
