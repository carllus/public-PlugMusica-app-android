package com.google.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.text.TextUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.tagmanager.DataLayer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class DataLayerPersistentStoreImpl implements DataLayer.PersistentStore {
    /* access modifiers changed from: private */
    public static final String CREATE_MAPS_TABLE = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' STRING NOT NULL, '%s' BLOB NOT NULL, '%s' INTEGER NOT NULL);", new Object[]{MAPS_TABLE, ID_FIELD, KEY_FIELD, VALUE_FIELD, EXPIRE_FIELD});
    private static final String DATABASE_NAME = "google_tagmanager.db";
    private static final String EXPIRE_FIELD = "expires";
    private static final String ID_FIELD = "ID";
    private static final String KEY_FIELD = "key";
    private static final String MAPS_TABLE = "datalayer";
    private static final int MAX_NUM_STORED_ITEMS = 2000;
    private static final String VALUE_FIELD = "value";
    private Clock mClock;
    /* access modifiers changed from: private */
    public final Context mContext;
    private DatabaseHelper mDbHelper;
    private final Executor mExecutor;
    private int mMaxNumStoredItems;

    public DataLayerPersistentStoreImpl(Context context) {
        this(context, new Clock() {
            public long currentTimeMillis() {
                return System.currentTimeMillis();
            }
        }, DATABASE_NAME, MAX_NUM_STORED_ITEMS, Executors.newSingleThreadExecutor());
    }

    @VisibleForTesting
    DataLayerPersistentStoreImpl(Context context, Clock clock, String databaseName, int maxNumStoredItems, Executor executor) {
        this.mContext = context;
        this.mClock = clock;
        this.mMaxNumStoredItems = maxNumStoredItems;
        this.mExecutor = executor;
        this.mDbHelper = new DatabaseHelper(this.mContext, databaseName);
    }

    public void saveKeyValues(List<DataLayer.KeyValue> keysAndValues, final long lifetimeInMillis) {
        final List<KeyAndSerialized> serializedKeysAndValues = serializeValues(keysAndValues);
        this.mExecutor.execute(new Runnable() {
            public void run() {
                DataLayerPersistentStoreImpl.this.saveSingleThreaded(serializedKeysAndValues, lifetimeInMillis);
            }
        });
    }

    public void loadSaved(final DataLayer.PersistentStore.Callback callback) {
        this.mExecutor.execute(new Runnable() {
            public void run() {
                callback.onKeyValuesLoaded(DataLayerPersistentStoreImpl.this.loadSingleThreaded());
            }
        });
    }

    public void clearKeysWithPrefix(final String keyPrefix) {
        this.mExecutor.execute(new Runnable() {
            public void run() {
                DataLayerPersistentStoreImpl.this.clearKeysWithPrefixSingleThreaded(keyPrefix);
            }
        });
    }

    /* access modifiers changed from: private */
    public List<DataLayer.KeyValue> loadSingleThreaded() {
        try {
            deleteEntriesOlderThan(this.mClock.currentTimeMillis());
            return unserializeValues(loadSerialized());
        } finally {
            closeDatabaseConnection();
        }
    }

    private List<DataLayer.KeyValue> unserializeValues(List<KeyAndSerialized> serialized) {
        List<DataLayer.KeyValue> result = new ArrayList<>();
        for (KeyAndSerialized keyAndSerialized : serialized) {
            result.add(new DataLayer.KeyValue(keyAndSerialized.mKey, unserialize(keyAndSerialized.mSerialized)));
        }
        return result;
    }

    private List<KeyAndSerialized> serializeValues(List<DataLayer.KeyValue> keysAndValues) {
        List<KeyAndSerialized> result = new ArrayList<>();
        for (DataLayer.KeyValue keyAndValue : keysAndValues) {
            result.add(new KeyAndSerialized(keyAndValue.mKey, serialize(keyAndValue.mValue)));
        }
        return result;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0029 A[SYNTHETIC, Splitter:B:19:0x0029] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0035 A[SYNTHETIC, Splitter:B:25:0x0035] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.Object unserialize(byte[] r7) {
        /*
            r6 = this;
            r4 = 0
            java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream
            r0.<init>(r7)
            r2 = 0
            java.io.ObjectInputStream r3 = new java.io.ObjectInputStream     // Catch:{ IOException -> 0x001a, ClassNotFoundException -> 0x0026, all -> 0x0032 }
            r3.<init>(r0)     // Catch:{ IOException -> 0x001a, ClassNotFoundException -> 0x0026, all -> 0x0032 }
            java.lang.Object r4 = r3.readObject()     // Catch:{ IOException -> 0x0044, ClassNotFoundException -> 0x0041, all -> 0x003e }
            if (r3 == 0) goto L_0x0015
            r3.close()     // Catch:{ IOException -> 0x0047 }
        L_0x0015:
            r0.close()     // Catch:{ IOException -> 0x0047 }
        L_0x0018:
            r2 = r3
        L_0x0019:
            return r4
        L_0x001a:
            r1 = move-exception
        L_0x001b:
            if (r2 == 0) goto L_0x0020
            r2.close()     // Catch:{ IOException -> 0x0024 }
        L_0x0020:
            r0.close()     // Catch:{ IOException -> 0x0024 }
            goto L_0x0019
        L_0x0024:
            r5 = move-exception
            goto L_0x0019
        L_0x0026:
            r1 = move-exception
        L_0x0027:
            if (r2 == 0) goto L_0x002c
            r2.close()     // Catch:{ IOException -> 0x0030 }
        L_0x002c:
            r0.close()     // Catch:{ IOException -> 0x0030 }
            goto L_0x0019
        L_0x0030:
            r5 = move-exception
            goto L_0x0019
        L_0x0032:
            r4 = move-exception
        L_0x0033:
            if (r2 == 0) goto L_0x0038
            r2.close()     // Catch:{ IOException -> 0x003c }
        L_0x0038:
            r0.close()     // Catch:{ IOException -> 0x003c }
        L_0x003b:
            throw r4
        L_0x003c:
            r5 = move-exception
            goto L_0x003b
        L_0x003e:
            r4 = move-exception
            r2 = r3
            goto L_0x0033
        L_0x0041:
            r1 = move-exception
            r2 = r3
            goto L_0x0027
        L_0x0044:
            r1 = move-exception
            r2 = r3
            goto L_0x001b
        L_0x0047:
            r5 = move-exception
            goto L_0x0018
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.tagmanager.DataLayerPersistentStoreImpl.unserialize(byte[]):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x002c A[SYNTHETIC, Splitter:B:20:0x002c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private byte[] serialize(java.lang.Object r7) {
        /*
            r6 = this;
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            r2 = 0
            java.io.ObjectOutputStream r3 = new java.io.ObjectOutputStream     // Catch:{ IOException -> 0x001c, all -> 0x0029 }
            r3.<init>(r0)     // Catch:{ IOException -> 0x001c, all -> 0x0029 }
            r3.writeObject(r7)     // Catch:{ IOException -> 0x0038, all -> 0x0035 }
            byte[] r4 = r0.toByteArray()     // Catch:{ IOException -> 0x0038, all -> 0x0035 }
            if (r3 == 0) goto L_0x0017
            r3.close()     // Catch:{ IOException -> 0x003b }
        L_0x0017:
            r0.close()     // Catch:{ IOException -> 0x003b }
        L_0x001a:
            r2 = r3
        L_0x001b:
            return r4
        L_0x001c:
            r1 = move-exception
        L_0x001d:
            r4 = 0
            if (r2 == 0) goto L_0x0023
            r2.close()     // Catch:{ IOException -> 0x0027 }
        L_0x0023:
            r0.close()     // Catch:{ IOException -> 0x0027 }
            goto L_0x001b
        L_0x0027:
            r5 = move-exception
            goto L_0x001b
        L_0x0029:
            r4 = move-exception
        L_0x002a:
            if (r2 == 0) goto L_0x002f
            r2.close()     // Catch:{ IOException -> 0x0033 }
        L_0x002f:
            r0.close()     // Catch:{ IOException -> 0x0033 }
        L_0x0032:
            throw r4
        L_0x0033:
            r5 = move-exception
            goto L_0x0032
        L_0x0035:
            r4 = move-exception
            r2 = r3
            goto L_0x002a
        L_0x0038:
            r1 = move-exception
            r2 = r3
            goto L_0x001d
        L_0x003b:
            r5 = move-exception
            goto L_0x001a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.tagmanager.DataLayerPersistentStoreImpl.serialize(java.lang.Object):byte[]");
    }

    /* access modifiers changed from: private */
    public synchronized void saveSingleThreaded(List<KeyAndSerialized> keysAndValues, long lifetimeInMillis) {
        try {
            long now = this.mClock.currentTimeMillis();
            deleteEntriesOlderThan(now);
            makeRoomForEntries(keysAndValues.size());
            writeEntriesToDatabase(keysAndValues, now + lifetimeInMillis);
            closeDatabaseConnection();
        } catch (Throwable th) {
            closeDatabaseConnection();
            throw th;
        }
    }

    private List<KeyAndSerialized> loadSerialized() {
        SQLiteDatabase db = getWritableDatabase("Error opening database for loadSerialized.");
        List<KeyAndSerialized> list = new ArrayList<>();
        if (db != null) {
            Cursor results = db.query(MAPS_TABLE, new String[]{KEY_FIELD, VALUE_FIELD}, (String) null, (String[]) null, (String) null, (String) null, ID_FIELD, (String) null);
            while (results.moveToNext()) {
                try {
                    list.add(new KeyAndSerialized(results.getString(0), results.getBlob(1)));
                } finally {
                    results.close();
                }
            }
        }
        return list;
    }

    private void writeEntriesToDatabase(List<KeyAndSerialized> keysAndValues, long expireTime) {
        SQLiteDatabase db = getWritableDatabase("Error opening database for writeEntryToDatabase.");
        if (db != null) {
            for (KeyAndSerialized keyAndValue : keysAndValues) {
                ContentValues values = new ContentValues();
                values.put(EXPIRE_FIELD, Long.valueOf(expireTime));
                values.put(KEY_FIELD, keyAndValue.mKey);
                values.put(VALUE_FIELD, keyAndValue.mSerialized);
                db.insert(MAPS_TABLE, (String) null, values);
            }
        }
    }

    private void makeRoomForEntries(int count) {
        int entrysOverLimit = (getNumStoredEntries() - this.mMaxNumStoredItems) + count;
        if (entrysOverLimit > 0) {
            List<String> entrysToDelete = peekEntryIds(entrysOverLimit);
            Log.m177i("DataLayer store full, deleting " + entrysToDelete.size() + " entries to make room.");
            deleteEntries((String[]) entrysToDelete.toArray(new String[0]));
        }
    }

    /* access modifiers changed from: private */
    public void clearKeysWithPrefixSingleThreaded(String keyPrefix) {
        SQLiteDatabase db = getWritableDatabase("Error opening database for clearKeysWithPrefix.");
        if (db != null) {
            try {
                Log.m179v("Cleared " + db.delete(MAPS_TABLE, "key = ? OR key LIKE ?", new String[]{keyPrefix, keyPrefix + ".%"}) + " items");
            } catch (SQLiteException e) {
                Log.m181w("Error deleting entries with key prefix: " + keyPrefix + " (" + e + ").");
            } finally {
                closeDatabaseConnection();
            }
        }
    }

    private void deleteEntriesOlderThan(long timeInMillis) {
        SQLiteDatabase db = getWritableDatabase("Error opening database for deleteOlderThan.");
        if (db != null) {
            try {
                Log.m179v("Deleted " + db.delete(MAPS_TABLE, "expires <= ?", new String[]{Long.toString(timeInMillis)}) + " expired items");
            } catch (SQLiteException e) {
                Log.m181w("Error deleting old entries.");
            }
        }
    }

    private void deleteEntries(String[] entryIds) {
        SQLiteDatabase db;
        if (entryIds != null && entryIds.length != 0 && (db = getWritableDatabase("Error opening database for deleteEntries.")) != null) {
            try {
                db.delete(MAPS_TABLE, String.format("%s in (%s)", new Object[]{ID_FIELD, TextUtils.join(",", Collections.nCopies(entryIds.length, "?"))}), entryIds);
            } catch (SQLiteException e) {
                Log.m181w("Error deleting entries " + Arrays.toString(entryIds));
            }
        }
    }

    private List<String> peekEntryIds(int maxEntries) {
        List<String> entryIds = new ArrayList<>();
        if (maxEntries <= 0) {
            Log.m181w("Invalid maxEntries specified. Skipping.");
        } else {
            SQLiteDatabase db = getWritableDatabase("Error opening database for peekEntryIds.");
            if (db != null) {
                Cursor cursor = null;
                try {
                    Cursor cursor2 = db.query(MAPS_TABLE, new String[]{ID_FIELD}, (String) null, (String[]) null, (String) null, (String) null, String.format("%s ASC", new Object[]{ID_FIELD}), Integer.toString(maxEntries));
                    if (cursor2.moveToFirst()) {
                        do {
                            entryIds.add(String.valueOf(cursor2.getLong(0)));
                        } while (cursor2.moveToNext());
                    }
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                } catch (SQLiteException e) {
                    Log.m181w("Error in peekEntries fetching entryIds: " + e.getMessage());
                    if (cursor != null) {
                        cursor.close();
                    }
                } catch (Throwable th) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
        }
        return entryIds;
    }

    private int getNumStoredEntries() {
        int numStoredEntries = 0;
        SQLiteDatabase db = getWritableDatabase("Error opening database for getNumStoredEntries.");
        if (db == null) {
            return 0;
        }
        Cursor cursor = null;
        try {
            Cursor cursor2 = db.rawQuery("SELECT COUNT(*) from datalayer", (String[]) null);
            if (cursor2.moveToFirst()) {
                numStoredEntries = (int) cursor2.getLong(0);
            }
            if (cursor2 != null) {
                cursor2.close();
            }
        } catch (SQLiteException e) {
            Log.m181w("Error getting numStoredEntries");
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return numStoredEntries;
    }

    private SQLiteDatabase getWritableDatabase(String errorMessage) {
        try {
            return this.mDbHelper.getWritableDatabase();
        } catch (SQLiteException e) {
            Log.m181w(errorMessage);
            return null;
        }
    }

    private void closeDatabaseConnection() {
        try {
            this.mDbHelper.close();
        } catch (SQLiteException e) {
        }
    }

    @VisibleForTesting
    class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context, String databaseName) {
            super(context, databaseName, (SQLiteDatabase.CursorFactory) null, 1);
        }

        private boolean tablePresent(String table, SQLiteDatabase db) {
            Cursor cursor = null;
            try {
                cursor = db.query("SQLITE_MASTER", new String[]{"name"}, "name=?", new String[]{table}, (String) null, (String) null, (String) null);
                boolean moveToFirst = cursor.moveToFirst();
                if (cursor == null) {
                    return moveToFirst;
                }
                cursor.close();
                return moveToFirst;
            } catch (SQLiteException e) {
                Log.m181w("Error querying for table " + table);
                if (cursor != null) {
                    cursor.close();
                }
                return false;
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }

        public SQLiteDatabase getWritableDatabase() {
            SQLiteDatabase db = null;
            try {
                db = super.getWritableDatabase();
            } catch (SQLiteException e) {
                DataLayerPersistentStoreImpl.this.mContext.getDatabasePath(DataLayerPersistentStoreImpl.DATABASE_NAME).delete();
            }
            if (db == null) {
                return super.getWritableDatabase();
            }
            return db;
        }

        public void onOpen(SQLiteDatabase db) {
            if (Build.VERSION.SDK_INT < 15) {
                Cursor cursor = db.rawQuery("PRAGMA journal_mode=memory", (String[]) null);
                try {
                    cursor.moveToFirst();
                } finally {
                    cursor.close();
                }
            }
            if (!tablePresent(DataLayerPersistentStoreImpl.MAPS_TABLE, db)) {
                db.execSQL(DataLayerPersistentStoreImpl.CREATE_MAPS_TABLE);
            } else {
                validateColumnsPresent(db);
            }
        }

        /* JADX INFO: finally extract failed */
        private void validateColumnsPresent(SQLiteDatabase db) {
            Cursor c = db.rawQuery("SELECT * FROM datalayer WHERE 0", (String[]) null);
            Set<String> columns = new HashSet<>();
            try {
                String[] columnNames = c.getColumnNames();
                for (String add : columnNames) {
                    columns.add(add);
                }
                c.close();
                if (!columns.remove(DataLayerPersistentStoreImpl.KEY_FIELD) || !columns.remove(DataLayerPersistentStoreImpl.VALUE_FIELD) || !columns.remove(DataLayerPersistentStoreImpl.ID_FIELD) || !columns.remove(DataLayerPersistentStoreImpl.EXPIRE_FIELD)) {
                    throw new SQLiteException("Database column missing");
                } else if (!columns.isEmpty()) {
                    throw new SQLiteException("Database has extra columns");
                }
            } catch (Throwable th) {
                c.close();
                throw th;
            }
        }

        public void onCreate(SQLiteDatabase db) {
            FutureApis.setOwnerOnlyReadWrite(db.getPath());
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

    private static class KeyAndSerialized {
        final String mKey;
        final byte[] mSerialized;

        KeyAndSerialized(String key, byte[] serialized) {
            this.mKey = key;
            this.mSerialized = serialized;
        }

        public String toString() {
            return "KeyAndSerialized: key = " + this.mKey + " serialized hash = " + Arrays.hashCode(this.mSerialized);
        }
    }
}
