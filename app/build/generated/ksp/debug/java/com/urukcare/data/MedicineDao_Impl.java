package com.urukcare.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
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
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class MedicineDao_Impl implements MedicineDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Medicine> __insertionAdapterOfMedicine;

  private final EntityDeletionOrUpdateAdapter<Medicine> __updateAdapterOfMedicine;

  public MedicineDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMedicine = new EntityInsertionAdapter<Medicine>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `medicines` (`id`,`name`,`price`,`description`,`sideEffects`,`prescriptionRequired`,`manufacturer`,`category`,`imageUrl`,`isFavorite`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Medicine entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getPrice());
        statement.bindString(4, entity.getDescription());
        statement.bindString(5, entity.getSideEffects());
        final int _tmp = entity.getPrescriptionRequired() ? 1 : 0;
        statement.bindLong(6, _tmp);
        statement.bindString(7, entity.getManufacturer());
        statement.bindString(8, entity.getCategory());
        if (entity.getImageUrl() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getImageUrl());
        }
        final int _tmp_1 = entity.isFavorite() ? 1 : 0;
        statement.bindLong(10, _tmp_1);
      }
    };
    this.__updateAdapterOfMedicine = new EntityDeletionOrUpdateAdapter<Medicine>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `medicines` SET `id` = ?,`name` = ?,`price` = ?,`description` = ?,`sideEffects` = ?,`prescriptionRequired` = ?,`manufacturer` = ?,`category` = ?,`imageUrl` = ?,`isFavorite` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Medicine entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getPrice());
        statement.bindString(4, entity.getDescription());
        statement.bindString(5, entity.getSideEffects());
        final int _tmp = entity.getPrescriptionRequired() ? 1 : 0;
        statement.bindLong(6, _tmp);
        statement.bindString(7, entity.getManufacturer());
        statement.bindString(8, entity.getCategory());
        if (entity.getImageUrl() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getImageUrl());
        }
        final int _tmp_1 = entity.isFavorite() ? 1 : 0;
        statement.bindLong(10, _tmp_1);
        statement.bindLong(11, entity.getId());
      }
    };
  }

  @Override
  public Object insertAll(final List<Medicine> medicines,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMedicine.insert(medicines);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final Medicine medicine, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfMedicine.handle(medicine);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Medicine>> getAllMedicines() {
    final String _sql = "SELECT * FROM medicines";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"medicines"}, new Callable<List<Medicine>>() {
      @Override
      @NonNull
      public List<Medicine> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfSideEffects = CursorUtil.getColumnIndexOrThrow(_cursor, "sideEffects");
          final int _cursorIndexOfPrescriptionRequired = CursorUtil.getColumnIndexOrThrow(_cursor, "prescriptionRequired");
          final int _cursorIndexOfManufacturer = CursorUtil.getColumnIndexOrThrow(_cursor, "manufacturer");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final List<Medicine> _result = new ArrayList<Medicine>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Medicine _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpPrice;
            _tmpPrice = _cursor.getString(_cursorIndexOfPrice);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpSideEffects;
            _tmpSideEffects = _cursor.getString(_cursorIndexOfSideEffects);
            final boolean _tmpPrescriptionRequired;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfPrescriptionRequired);
            _tmpPrescriptionRequired = _tmp != 0;
            final String _tmpManufacturer;
            _tmpManufacturer = _cursor.getString(_cursorIndexOfManufacturer);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final boolean _tmpIsFavorite;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_1 != 0;
            _item = new Medicine(_tmpId,_tmpName,_tmpPrice,_tmpDescription,_tmpSideEffects,_tmpPrescriptionRequired,_tmpManufacturer,_tmpCategory,_tmpImageUrl,_tmpIsFavorite);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getMedicineById(final int id, final Continuation<? super Medicine> $completion) {
    final String _sql = "SELECT * FROM medicines WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Medicine>() {
      @Override
      @Nullable
      public Medicine call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfSideEffects = CursorUtil.getColumnIndexOrThrow(_cursor, "sideEffects");
          final int _cursorIndexOfPrescriptionRequired = CursorUtil.getColumnIndexOrThrow(_cursor, "prescriptionRequired");
          final int _cursorIndexOfManufacturer = CursorUtil.getColumnIndexOrThrow(_cursor, "manufacturer");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final Medicine _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpPrice;
            _tmpPrice = _cursor.getString(_cursorIndexOfPrice);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpSideEffects;
            _tmpSideEffects = _cursor.getString(_cursorIndexOfSideEffects);
            final boolean _tmpPrescriptionRequired;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfPrescriptionRequired);
            _tmpPrescriptionRequired = _tmp != 0;
            final String _tmpManufacturer;
            _tmpManufacturer = _cursor.getString(_cursorIndexOfManufacturer);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final boolean _tmpIsFavorite;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_1 != 0;
            _result = new Medicine(_tmpId,_tmpName,_tmpPrice,_tmpDescription,_tmpSideEffects,_tmpPrescriptionRequired,_tmpManufacturer,_tmpCategory,_tmpImageUrl,_tmpIsFavorite);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Medicine>> searchMedicines(final String query) {
    final String _sql = "SELECT * FROM medicines WHERE LOWER(name) LIKE '%' || LOWER(?) || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"medicines"}, new Callable<List<Medicine>>() {
      @Override
      @NonNull
      public List<Medicine> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfSideEffects = CursorUtil.getColumnIndexOrThrow(_cursor, "sideEffects");
          final int _cursorIndexOfPrescriptionRequired = CursorUtil.getColumnIndexOrThrow(_cursor, "prescriptionRequired");
          final int _cursorIndexOfManufacturer = CursorUtil.getColumnIndexOrThrow(_cursor, "manufacturer");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final List<Medicine> _result = new ArrayList<Medicine>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Medicine _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpPrice;
            _tmpPrice = _cursor.getString(_cursorIndexOfPrice);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpSideEffects;
            _tmpSideEffects = _cursor.getString(_cursorIndexOfSideEffects);
            final boolean _tmpPrescriptionRequired;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfPrescriptionRequired);
            _tmpPrescriptionRequired = _tmp != 0;
            final String _tmpManufacturer;
            _tmpManufacturer = _cursor.getString(_cursorIndexOfManufacturer);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final boolean _tmpIsFavorite;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_1 != 0;
            _item = new Medicine(_tmpId,_tmpName,_tmpPrice,_tmpDescription,_tmpSideEffects,_tmpPrescriptionRequired,_tmpManufacturer,_tmpCategory,_tmpImageUrl,_tmpIsFavorite);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Medicine>> getMedicinesByCategory(final String category) {
    final String _sql = "SELECT * FROM medicines WHERE category = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, category);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"medicines"}, new Callable<List<Medicine>>() {
      @Override
      @NonNull
      public List<Medicine> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfSideEffects = CursorUtil.getColumnIndexOrThrow(_cursor, "sideEffects");
          final int _cursorIndexOfPrescriptionRequired = CursorUtil.getColumnIndexOrThrow(_cursor, "prescriptionRequired");
          final int _cursorIndexOfManufacturer = CursorUtil.getColumnIndexOrThrow(_cursor, "manufacturer");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final List<Medicine> _result = new ArrayList<Medicine>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Medicine _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpPrice;
            _tmpPrice = _cursor.getString(_cursorIndexOfPrice);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpSideEffects;
            _tmpSideEffects = _cursor.getString(_cursorIndexOfSideEffects);
            final boolean _tmpPrescriptionRequired;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfPrescriptionRequired);
            _tmpPrescriptionRequired = _tmp != 0;
            final String _tmpManufacturer;
            _tmpManufacturer = _cursor.getString(_cursorIndexOfManufacturer);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final boolean _tmpIsFavorite;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_1 != 0;
            _item = new Medicine(_tmpId,_tmpName,_tmpPrice,_tmpDescription,_tmpSideEffects,_tmpPrescriptionRequired,_tmpManufacturer,_tmpCategory,_tmpImageUrl,_tmpIsFavorite);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<String>> getAllCategories() {
    final String _sql = "SELECT DISTINCT category FROM medicines";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"medicines"}, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            _item = _cursor.getString(0);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Medicine>> getFavoriteMedicines() {
    final String _sql = "SELECT * FROM medicines WHERE isFavorite = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"medicines"}, new Callable<List<Medicine>>() {
      @Override
      @NonNull
      public List<Medicine> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfSideEffects = CursorUtil.getColumnIndexOrThrow(_cursor, "sideEffects");
          final int _cursorIndexOfPrescriptionRequired = CursorUtil.getColumnIndexOrThrow(_cursor, "prescriptionRequired");
          final int _cursorIndexOfManufacturer = CursorUtil.getColumnIndexOrThrow(_cursor, "manufacturer");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final List<Medicine> _result = new ArrayList<Medicine>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Medicine _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpPrice;
            _tmpPrice = _cursor.getString(_cursorIndexOfPrice);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpSideEffects;
            _tmpSideEffects = _cursor.getString(_cursorIndexOfSideEffects);
            final boolean _tmpPrescriptionRequired;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfPrescriptionRequired);
            _tmpPrescriptionRequired = _tmp != 0;
            final String _tmpManufacturer;
            _tmpManufacturer = _cursor.getString(_cursorIndexOfManufacturer);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final boolean _tmpIsFavorite;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_1 != 0;
            _item = new Medicine(_tmpId,_tmpName,_tmpPrice,_tmpDescription,_tmpSideEffects,_tmpPrescriptionRequired,_tmpManufacturer,_tmpCategory,_tmpImageUrl,_tmpIsFavorite);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM medicines";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
