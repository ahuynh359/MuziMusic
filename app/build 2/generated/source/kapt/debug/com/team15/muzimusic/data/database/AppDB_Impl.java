package com.team15.muzimusic.data.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.team15.muzimusic.data.database.daos.AccountDAO;
import com.team15.muzimusic.data.database.daos.AccountDAO_Impl;
import com.team15.muzimusic.data.database.daos.AlbumDao;
import com.team15.muzimusic.data.database.daos.AlbumDao_Impl;
import com.team15.muzimusic.data.database.daos.NotificationDAO;
import com.team15.muzimusic.data.database.daos.NotificationDAO_Impl;
import com.team15.muzimusic.data.database.daos.SearchHistoryDAO;
import com.team15.muzimusic.data.database.daos.SearchHistoryDAO_Impl;
import com.team15.muzimusic.data.database.daos.SongDAO;
import com.team15.muzimusic.data.database.daos.SongDAO_Impl;
import com.team15.muzimusic.data.database.daos.TypeDao;
import com.team15.muzimusic.data.database.daos.TypeDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDB_Impl extends AppDB {
  private volatile SongDAO _songDAO;

  private volatile AccountDAO _accountDAO;

  private volatile NotificationDAO _notificationDAO;

  private volatile SearchHistoryDAO _searchHistoryDAO;

  private volatile AlbumDao _albumDao;

  private volatile TypeDao _typeDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `SongEntity` (`idSong` INTEGER NOT NULL, `nameSong` TEXT NOT NULL, `created` TEXT NOT NULL, `description` TEXT NOT NULL, `link` TEXT NOT NULL, `listen` INTEGER NOT NULL, `lyrics` TEXT NOT NULL, `loveStatus` INTEGER NOT NULL, `imageSong` TEXT NOT NULL, `songStatus` INTEGER NOT NULL, `totalLove` INTEGER NOT NULL, `idAccount` INTEGER NOT NULL, `idAlbum` INTEGER NOT NULL, `downloaded` INTEGER NOT NULL, `filePath` TEXT NOT NULL, `imagePath` TEXT NOT NULL, PRIMARY KEY(`idSong`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `AccountEntity` (`idAccount` INTEGER NOT NULL, `email` TEXT NOT NULL, `accountName` TEXT NOT NULL, `avatar` TEXT NOT NULL, `created` TEXT NOT NULL, `totalSong` INTEGER NOT NULL, `totalLove` INTEGER NOT NULL, `totalFollowers` INTEGER NOT NULL, `totalFollowings` INTEGER NOT NULL, `avatarPath` TEXT NOT NULL, PRIMARY KEY(`idAccount`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `SongSingersEntity` (`idSong` INTEGER NOT NULL, `idAccount` INTEGER NOT NULL, PRIMARY KEY(`idSong`, `idAccount`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `AlbumEntity` (`idAlbum` INTEGER NOT NULL, `nameAlbum` TEXT NOT NULL, `created` TEXT NOT NULL, `idAccount` INTEGER NOT NULL, PRIMARY KEY(`idAlbum`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `TypeEntity` (`idType` INTEGER NOT NULL, `nameType` TEXT NOT NULL, `description` TEXT NOT NULL, PRIMARY KEY(`idType`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `SongTypeEntity` (`idSong` INTEGER NOT NULL, `idType` INTEGER NOT NULL, PRIMARY KEY(`idSong`, `idType`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `NotificationEntity` (`idNotification` INTEGER NOT NULL, `content` TEXT NOT NULL, `action` TEXT NOT NULL, `notificationStatus` INTEGER NOT NULL, `notificationTime` TEXT NOT NULL, `idAccount` INTEGER, `accountName` TEXT, PRIMARY KEY(`idNotification`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `SearchHistoryEntity` (`keyword` TEXT NOT NULL, `time` INTEGER NOT NULL, PRIMARY KEY(`keyword`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'caf2c61b478aef1e34104bb6fecc5822')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `SongEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `AccountEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `SongSingersEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `AlbumEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `TypeEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `SongTypeEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `NotificationEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `SearchHistoryEntity`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsSongEntity = new HashMap<String, TableInfo.Column>(16);
        _columnsSongEntity.put("idSong", new TableInfo.Column("idSong", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongEntity.put("nameSong", new TableInfo.Column("nameSong", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongEntity.put("created", new TableInfo.Column("created", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongEntity.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongEntity.put("link", new TableInfo.Column("link", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongEntity.put("listen", new TableInfo.Column("listen", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongEntity.put("lyrics", new TableInfo.Column("lyrics", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongEntity.put("loveStatus", new TableInfo.Column("loveStatus", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongEntity.put("imageSong", new TableInfo.Column("imageSong", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongEntity.put("songStatus", new TableInfo.Column("songStatus", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongEntity.put("totalLove", new TableInfo.Column("totalLove", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongEntity.put("idAccount", new TableInfo.Column("idAccount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongEntity.put("idAlbum", new TableInfo.Column("idAlbum", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongEntity.put("downloaded", new TableInfo.Column("downloaded", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongEntity.put("filePath", new TableInfo.Column("filePath", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongEntity.put("imagePath", new TableInfo.Column("imagePath", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSongEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSongEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSongEntity = new TableInfo("SongEntity", _columnsSongEntity, _foreignKeysSongEntity, _indicesSongEntity);
        final TableInfo _existingSongEntity = TableInfo.read(_db, "SongEntity");
        if (! _infoSongEntity.equals(_existingSongEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "SongEntity(com.team15.muzimusic.data.database.entities.SongEntity).\n"
                  + " Expected:\n" + _infoSongEntity + "\n"
                  + " Found:\n" + _existingSongEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsAccountEntity = new HashMap<String, TableInfo.Column>(10);
        _columnsAccountEntity.put("idAccount", new TableInfo.Column("idAccount", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccountEntity.put("email", new TableInfo.Column("email", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccountEntity.put("accountName", new TableInfo.Column("accountName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccountEntity.put("avatar", new TableInfo.Column("avatar", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccountEntity.put("created", new TableInfo.Column("created", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccountEntity.put("totalSong", new TableInfo.Column("totalSong", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccountEntity.put("totalLove", new TableInfo.Column("totalLove", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccountEntity.put("totalFollowers", new TableInfo.Column("totalFollowers", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccountEntity.put("totalFollowings", new TableInfo.Column("totalFollowings", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccountEntity.put("avatarPath", new TableInfo.Column("avatarPath", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAccountEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAccountEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAccountEntity = new TableInfo("AccountEntity", _columnsAccountEntity, _foreignKeysAccountEntity, _indicesAccountEntity);
        final TableInfo _existingAccountEntity = TableInfo.read(_db, "AccountEntity");
        if (! _infoAccountEntity.equals(_existingAccountEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "AccountEntity(com.team15.muzimusic.data.database.entities.AccountEntity).\n"
                  + " Expected:\n" + _infoAccountEntity + "\n"
                  + " Found:\n" + _existingAccountEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsSongSingersEntity = new HashMap<String, TableInfo.Column>(2);
        _columnsSongSingersEntity.put("idSong", new TableInfo.Column("idSong", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongSingersEntity.put("idAccount", new TableInfo.Column("idAccount", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSongSingersEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSongSingersEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSongSingersEntity = new TableInfo("SongSingersEntity", _columnsSongSingersEntity, _foreignKeysSongSingersEntity, _indicesSongSingersEntity);
        final TableInfo _existingSongSingersEntity = TableInfo.read(_db, "SongSingersEntity");
        if (! _infoSongSingersEntity.equals(_existingSongSingersEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "SongSingersEntity(com.team15.muzimusic.data.database.entities.SongSingersEntity).\n"
                  + " Expected:\n" + _infoSongSingersEntity + "\n"
                  + " Found:\n" + _existingSongSingersEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsAlbumEntity = new HashMap<String, TableInfo.Column>(4);
        _columnsAlbumEntity.put("idAlbum", new TableInfo.Column("idAlbum", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlbumEntity.put("nameAlbum", new TableInfo.Column("nameAlbum", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlbumEntity.put("created", new TableInfo.Column("created", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAlbumEntity.put("idAccount", new TableInfo.Column("idAccount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAlbumEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAlbumEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAlbumEntity = new TableInfo("AlbumEntity", _columnsAlbumEntity, _foreignKeysAlbumEntity, _indicesAlbumEntity);
        final TableInfo _existingAlbumEntity = TableInfo.read(_db, "AlbumEntity");
        if (! _infoAlbumEntity.equals(_existingAlbumEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "AlbumEntity(com.team15.muzimusic.data.database.entities.AlbumEntity).\n"
                  + " Expected:\n" + _infoAlbumEntity + "\n"
                  + " Found:\n" + _existingAlbumEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsTypeEntity = new HashMap<String, TableInfo.Column>(3);
        _columnsTypeEntity.put("idType", new TableInfo.Column("idType", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTypeEntity.put("nameType", new TableInfo.Column("nameType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTypeEntity.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTypeEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTypeEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTypeEntity = new TableInfo("TypeEntity", _columnsTypeEntity, _foreignKeysTypeEntity, _indicesTypeEntity);
        final TableInfo _existingTypeEntity = TableInfo.read(_db, "TypeEntity");
        if (! _infoTypeEntity.equals(_existingTypeEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "TypeEntity(com.team15.muzimusic.data.database.entities.TypeEntity).\n"
                  + " Expected:\n" + _infoTypeEntity + "\n"
                  + " Found:\n" + _existingTypeEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsSongTypeEntity = new HashMap<String, TableInfo.Column>(2);
        _columnsSongTypeEntity.put("idSong", new TableInfo.Column("idSong", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSongTypeEntity.put("idType", new TableInfo.Column("idType", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSongTypeEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSongTypeEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSongTypeEntity = new TableInfo("SongTypeEntity", _columnsSongTypeEntity, _foreignKeysSongTypeEntity, _indicesSongTypeEntity);
        final TableInfo _existingSongTypeEntity = TableInfo.read(_db, "SongTypeEntity");
        if (! _infoSongTypeEntity.equals(_existingSongTypeEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "SongTypeEntity(com.team15.muzimusic.data.database.entities.SongTypeEntity).\n"
                  + " Expected:\n" + _infoSongTypeEntity + "\n"
                  + " Found:\n" + _existingSongTypeEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsNotificationEntity = new HashMap<String, TableInfo.Column>(7);
        _columnsNotificationEntity.put("idNotification", new TableInfo.Column("idNotification", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotificationEntity.put("content", new TableInfo.Column("content", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotificationEntity.put("action", new TableInfo.Column("action", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotificationEntity.put("notificationStatus", new TableInfo.Column("notificationStatus", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotificationEntity.put("notificationTime", new TableInfo.Column("notificationTime", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotificationEntity.put("idAccount", new TableInfo.Column("idAccount", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotificationEntity.put("accountName", new TableInfo.Column("accountName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysNotificationEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesNotificationEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoNotificationEntity = new TableInfo("NotificationEntity", _columnsNotificationEntity, _foreignKeysNotificationEntity, _indicesNotificationEntity);
        final TableInfo _existingNotificationEntity = TableInfo.read(_db, "NotificationEntity");
        if (! _infoNotificationEntity.equals(_existingNotificationEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "NotificationEntity(com.team15.muzimusic.data.database.entities.NotificationEntity).\n"
                  + " Expected:\n" + _infoNotificationEntity + "\n"
                  + " Found:\n" + _existingNotificationEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsSearchHistoryEntity = new HashMap<String, TableInfo.Column>(2);
        _columnsSearchHistoryEntity.put("keyword", new TableInfo.Column("keyword", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSearchHistoryEntity.put("time", new TableInfo.Column("time", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSearchHistoryEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSearchHistoryEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSearchHistoryEntity = new TableInfo("SearchHistoryEntity", _columnsSearchHistoryEntity, _foreignKeysSearchHistoryEntity, _indicesSearchHistoryEntity);
        final TableInfo _existingSearchHistoryEntity = TableInfo.read(_db, "SearchHistoryEntity");
        if (! _infoSearchHistoryEntity.equals(_existingSearchHistoryEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "SearchHistoryEntity(com.team15.muzimusic.data.database.entities.SearchHistoryEntity).\n"
                  + " Expected:\n" + _infoSearchHistoryEntity + "\n"
                  + " Found:\n" + _existingSearchHistoryEntity);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "caf2c61b478aef1e34104bb6fecc5822", "c83a8c6da59d9efeed3b4a7ecb2a5c77");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "SongEntity","AccountEntity","SongSingersEntity","AlbumEntity","TypeEntity","SongTypeEntity","NotificationEntity","SearchHistoryEntity");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `SongEntity`");
      _db.execSQL("DELETE FROM `AccountEntity`");
      _db.execSQL("DELETE FROM `SongSingersEntity`");
      _db.execSQL("DELETE FROM `AlbumEntity`");
      _db.execSQL("DELETE FROM `TypeEntity`");
      _db.execSQL("DELETE FROM `SongTypeEntity`");
      _db.execSQL("DELETE FROM `NotificationEntity`");
      _db.execSQL("DELETE FROM `SearchHistoryEntity`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(SongDAO.class, SongDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(AccountDAO.class, AccountDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(NotificationDAO.class, NotificationDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(SearchHistoryDAO.class, SearchHistoryDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(AlbumDao.class, AlbumDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TypeDao.class, TypeDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public SongDAO songDao() {
    if (_songDAO != null) {
      return _songDAO;
    } else {
      synchronized(this) {
        if(_songDAO == null) {
          _songDAO = new SongDAO_Impl(this);
        }
        return _songDAO;
      }
    }
  }

  @Override
  public AccountDAO accountDao() {
    if (_accountDAO != null) {
      return _accountDAO;
    } else {
      synchronized(this) {
        if(_accountDAO == null) {
          _accountDAO = new AccountDAO_Impl(this);
        }
        return _accountDAO;
      }
    }
  }

  @Override
  public NotificationDAO notificationDao() {
    if (_notificationDAO != null) {
      return _notificationDAO;
    } else {
      synchronized(this) {
        if(_notificationDAO == null) {
          _notificationDAO = new NotificationDAO_Impl(this);
        }
        return _notificationDAO;
      }
    }
  }

  @Override
  public SearchHistoryDAO searchHistoryDao() {
    if (_searchHistoryDAO != null) {
      return _searchHistoryDAO;
    } else {
      synchronized(this) {
        if(_searchHistoryDAO == null) {
          _searchHistoryDAO = new SearchHistoryDAO_Impl(this);
        }
        return _searchHistoryDAO;
      }
    }
  }

  @Override
  public AlbumDao albumDao() {
    if (_albumDao != null) {
      return _albumDao;
    } else {
      synchronized(this) {
        if(_albumDao == null) {
          _albumDao = new AlbumDao_Impl(this);
        }
        return _albumDao;
      }
    }
  }

  @Override
  public TypeDao typeDao() {
    if (_typeDao != null) {
      return _typeDao;
    } else {
      synchronized(this) {
        if(_typeDao == null) {
          _typeDao = new TypeDao_Impl(this);
        }
        return _typeDao;
      }
    }
  }
}
