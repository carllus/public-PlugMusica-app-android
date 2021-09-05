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
import com.google.tagmanager.SimpleNetworkDispatcher;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.http.impl.client.DefaultHttpClient;

class PersistentHitStore implements HitStore {
    /* access modifiers changed from: private */
    public static final String CREATE_HITS_TABLE = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL,'%s' INTEGER NOT NULL);", new Object[]{HITS_TABLE, HIT_ID, HIT_TIME, HIT_URL, HIT_FIRST_DISPATCH_TIME});
    private static final String DATABASE_FILENAME = "gtm_urls.db";
    @VisibleForTesting
    static final String HITS_TABLE = "gtm_hits";
    static final long HIT_DISPATCH_RETRY_WINDOW = 14400000;
    @VisibleForTesting
    static final String HIT_FIRST_DISPATCH_TIME = "hit_first_send_time";
    @VisibleForTesting
    static final String HIT_ID = "hit_id";
    private static final String HIT_ID_WHERE_CLAUSE = "hit_id=?";
    @VisibleForTesting
    static final String HIT_TIME = "hit_time";
    @VisibleForTesting
    static final String HIT_URL = "hit_url";
    /* access modifiers changed from: private */
    public Clock mClock;
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final String mDatabaseName;
    private final UrlDatabaseHelper mDbHelper;
    private volatile Dispatcher mDispatcher;
    private long mLastDeleteStaleHitsTime;
    private final HitStoreStateListener mListener;

    PersistentHitStore(HitStoreStateListener listener, Context ctx) {
        this(listener, ctx, DATABASE_FILENAME);
    }

    @VisibleForTesting
    PersistentHitStore(HitStoreStateListener listener, Context ctx, String databaseName) {
        this.mContext = ctx.getApplicationContext();
        this.mDatabaseName = databaseName;
        this.mListener = listener;
        this.mClock = new Clock() {
            public long currentTimeMillis() {
                return System.currentTimeMillis();
            }
        };
        this.mDbHelper = new UrlDatabaseHelper(this.mContext, this.mDatabaseName);
        this.mDispatcher = new SimpleNetworkDispatcher(new DefaultHttpClient(), this.mContext, new StoreDispatchListener());
        this.mLastDeleteStaleHitsTime = 0;
    }

    @VisibleForTesting
    public void setClock(Clock clock) {
        this.mClock = clock;
    }

    @VisibleForTesting
    public UrlDatabaseHelper getDbHelper() {
        return this.mDbHelper;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void setDispatcher(Dispatcher dispatcher) {
        this.mDispatcher = dispatcher;
    }

    public void putHit(long hitTimeInMilliseconds, String path) {
        deleteStaleHits();
        removeOldHitIfFull();
        writeHitToDatabase(hitTimeInMilliseconds, path);
    }

    private void removeOldHitIfFull() {
        int hitsOverLimit = (getNumStoredHits() - 2000) + 1;
        if (hitsOverLimit > 0) {
            List<String> hitsToDelete = peekHitIds(hitsOverLimit);
            Log.m179v("Store full, deleting " + hitsToDelete.size() + " hits to make room.");
            deleteHits((String[]) hitsToDelete.toArray(new String[0]));
        }
    }

    private void writeHitToDatabase(long hitTimeInMilliseconds, String path) {
        SQLiteDatabase db = getWritableDatabase("Error opening database for putHit");
        if (db != null) {
            ContentValues content = new ContentValues();
            content.put(HIT_TIME, Long.valueOf(hitTimeInMilliseconds));
            content.put(HIT_URL, path);
            content.put(HIT_FIRST_DISPATCH_TIME, 0);
            try {
                db.insert(HITS_TABLE, (String) null, content);
                this.mListener.reportStoreIsEmpty(false);
            } catch (SQLiteException e) {
                Log.m181w("Error storing hit");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public List<String> peekHitIds(int maxHits) {
        List<String> hitIds = new ArrayList<>();
        if (maxHits <= 0) {
            Log.m181w("Invalid maxHits specified. Skipping");
        } else {
            SQLiteDatabase db = getWritableDatabase("Error opening database for peekHitIds.");
            if (db != null) {
                Cursor cursor = null;
                try {
                    Cursor cursor2 = db.query(HITS_TABLE, new String[]{HIT_ID}, (String) null, (String[]) null, (String) null, (String) null, String.format("%s ASC", new Object[]{HIT_ID}), Integer.toString(maxHits));
                    if (cursor2.moveToFirst()) {
                        do {
                            hitIds.add(String.valueOf(cursor2.getLong(0)));
                        } while (cursor2.moveToNext());
                    }
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                } catch (SQLiteException e) {
                    Log.m181w("Error in peekHits fetching hitIds: " + e.getMessage());
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
        return hitIds;
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00ed  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00f7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.google.tagmanager.Hit> peekHits(int r24) {
        /*
            r23 = this;
            java.util.ArrayList r18 = new java.util.ArrayList
            r18.<init>()
            java.lang.String r4 = "Error opening database for peekHits"
            r0 = r23
            android.database.sqlite.SQLiteDatabase r2 = r0.getWritableDatabase(r4)
            if (r2 != 0) goto L_0x0012
            r19 = r18
        L_0x0011:
            return r19
        L_0x0012:
            r14 = 0
            java.lang.String r3 = "gtm_hits"
            r4 = 3
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x00d0 }
            r5 = 0
            java.lang.String r6 = "hit_id"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x00d0 }
            r5 = 1
            java.lang.String r6 = "hit_time"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x00d0 }
            r5 = 2
            java.lang.String r6 = "hit_first_send_time"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x00d0 }
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            java.lang.String r9 = "%s ASC"
            r10 = 1
            java.lang.Object[] r10 = new java.lang.Object[r10]     // Catch:{ SQLiteException -> 0x00d0 }
            r11 = 0
            java.lang.String r12 = "hit_id"
            r10[r11] = r12     // Catch:{ SQLiteException -> 0x00d0 }
            java.lang.String r9 = java.lang.String.format(r9, r10)     // Catch:{ SQLiteException -> 0x00d0 }
            java.lang.String r10 = java.lang.Integer.toString(r24)     // Catch:{ SQLiteException -> 0x00d0 }
            android.database.Cursor r14 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x00d0 }
            java.util.ArrayList r19 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x00d0 }
            r19.<init>()     // Catch:{ SQLiteException -> 0x00d0 }
            boolean r4 = r14.moveToFirst()     // Catch:{ SQLiteException -> 0x0178, all -> 0x0173 }
            if (r4 == 0) goto L_0x006b
        L_0x004c:
            com.google.tagmanager.Hit r3 = new com.google.tagmanager.Hit     // Catch:{ SQLiteException -> 0x0178, all -> 0x0173 }
            r4 = 0
            long r4 = r14.getLong(r4)     // Catch:{ SQLiteException -> 0x0178, all -> 0x0173 }
            r6 = 1
            long r6 = r14.getLong(r6)     // Catch:{ SQLiteException -> 0x0178, all -> 0x0173 }
            r8 = 2
            long r8 = r14.getLong(r8)     // Catch:{ SQLiteException -> 0x0178, all -> 0x0173 }
            r3.<init>(r4, r6, r8)     // Catch:{ SQLiteException -> 0x0178, all -> 0x0173 }
            r0 = r19
            r0.add(r3)     // Catch:{ SQLiteException -> 0x0178, all -> 0x0173 }
            boolean r4 = r14.moveToNext()     // Catch:{ SQLiteException -> 0x0178, all -> 0x0173 }
            if (r4 != 0) goto L_0x004c
        L_0x006b:
            if (r14 == 0) goto L_0x0070
            r14.close()
        L_0x0070:
            r13 = 0
            java.lang.String r5 = "gtm_hits"
            r4 = 2
            java.lang.String[] r6 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x011b }
            r4 = 0
            java.lang.String r7 = "hit_id"
            r6[r4] = r7     // Catch:{ SQLiteException -> 0x011b }
            r4 = 1
            java.lang.String r7 = "hit_url"
            r6[r4] = r7     // Catch:{ SQLiteException -> 0x011b }
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            java.lang.String r4 = "%s ASC"
            r11 = 1
            java.lang.Object[] r11 = new java.lang.Object[r11]     // Catch:{ SQLiteException -> 0x011b }
            r12 = 0
            java.lang.String r22 = "hit_id"
            r11[r12] = r22     // Catch:{ SQLiteException -> 0x011b }
            java.lang.String r11 = java.lang.String.format(r4, r11)     // Catch:{ SQLiteException -> 0x011b }
            java.lang.String r12 = java.lang.Integer.toString(r24)     // Catch:{ SQLiteException -> 0x011b }
            r4 = r2
            android.database.Cursor r14 = r4.query(r5, r6, r7, r8, r9, r10, r11, r12)     // Catch:{ SQLiteException -> 0x011b }
            boolean r4 = r14.moveToFirst()     // Catch:{ SQLiteException -> 0x011b }
            if (r4 == 0) goto L_0x00c7
        L_0x00a1:
            r0 = r14
            android.database.sqlite.SQLiteCursor r0 = (android.database.sqlite.SQLiteCursor) r0     // Catch:{ SQLiteException -> 0x011b }
            r4 = r0
            android.database.CursorWindow r15 = r4.getWindow()     // Catch:{ SQLiteException -> 0x011b }
            int r4 = r15.getNumRows()     // Catch:{ SQLiteException -> 0x011b }
            if (r4 <= 0) goto L_0x00fb
            r0 = r19
            java.lang.Object r4 = r0.get(r13)     // Catch:{ SQLiteException -> 0x011b }
            com.google.tagmanager.Hit r4 = (com.google.tagmanager.Hit) r4     // Catch:{ SQLiteException -> 0x011b }
            r5 = 1
            java.lang.String r5 = r14.getString(r5)     // Catch:{ SQLiteException -> 0x011b }
            r4.setHitUrl(r5)     // Catch:{ SQLiteException -> 0x011b }
        L_0x00bf:
            int r13 = r13 + 1
            boolean r4 = r14.moveToNext()     // Catch:{ SQLiteException -> 0x011b }
            if (r4 != 0) goto L_0x00a1
        L_0x00c7:
            if (r14 == 0) goto L_0x00cc
            r14.close()
        L_0x00cc:
            r18 = r19
            goto L_0x0011
        L_0x00d0:
            r16 = move-exception
        L_0x00d1:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f4 }
            r4.<init>()     // Catch:{ all -> 0x00f4 }
            java.lang.String r5 = "Error in peekHits fetching hitIds: "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x00f4 }
            java.lang.String r5 = r16.getMessage()     // Catch:{ all -> 0x00f4 }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x00f4 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x00f4 }
            com.google.tagmanager.Log.m181w(r4)     // Catch:{ all -> 0x00f4 }
            if (r14 == 0) goto L_0x00f0
            r14.close()
        L_0x00f0:
            r19 = r18
            goto L_0x0011
        L_0x00f4:
            r4 = move-exception
        L_0x00f5:
            if (r14 == 0) goto L_0x00fa
            r14.close()
        L_0x00fa:
            throw r4
        L_0x00fb:
            java.lang.String r5 = "HitString for hitId %d too large.  Hit will be deleted."
            r4 = 1
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch:{ SQLiteException -> 0x011b }
            r7 = 0
            r0 = r19
            java.lang.Object r4 = r0.get(r13)     // Catch:{ SQLiteException -> 0x011b }
            com.google.tagmanager.Hit r4 = (com.google.tagmanager.Hit) r4     // Catch:{ SQLiteException -> 0x011b }
            long r8 = r4.getHitId()     // Catch:{ SQLiteException -> 0x011b }
            java.lang.Long r4 = java.lang.Long.valueOf(r8)     // Catch:{ SQLiteException -> 0x011b }
            r6[r7] = r4     // Catch:{ SQLiteException -> 0x011b }
            java.lang.String r4 = java.lang.String.format(r5, r6)     // Catch:{ SQLiteException -> 0x011b }
            com.google.tagmanager.Log.m181w(r4)     // Catch:{ SQLiteException -> 0x011b }
            goto L_0x00bf
        L_0x011b:
            r16 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x016c }
            r4.<init>()     // Catch:{ all -> 0x016c }
            java.lang.String r5 = "Error in peekHits fetching hit url: "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x016c }
            java.lang.String r5 = r16.getMessage()     // Catch:{ all -> 0x016c }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x016c }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x016c }
            com.google.tagmanager.Log.m181w(r4)     // Catch:{ all -> 0x016c }
            java.util.ArrayList r21 = new java.util.ArrayList     // Catch:{ all -> 0x016c }
            r21.<init>()     // Catch:{ all -> 0x016c }
            r17 = 0
            java.util.Iterator r20 = r19.iterator()     // Catch:{ all -> 0x016c }
        L_0x0141:
            boolean r4 = r20.hasNext()     // Catch:{ all -> 0x016c }
            if (r4 == 0) goto L_0x0159
            java.lang.Object r3 = r20.next()     // Catch:{ all -> 0x016c }
            com.google.tagmanager.Hit r3 = (com.google.tagmanager.Hit) r3     // Catch:{ all -> 0x016c }
            java.lang.String r4 = r3.getHitUrl()     // Catch:{ all -> 0x016c }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x016c }
            if (r4 == 0) goto L_0x0166
            if (r17 == 0) goto L_0x0164
        L_0x0159:
            if (r14 == 0) goto L_0x015e
            r14.close()
        L_0x015e:
            r18 = r19
            r19 = r21
            goto L_0x0011
        L_0x0164:
            r17 = 1
        L_0x0166:
            r0 = r21
            r0.add(r3)     // Catch:{ all -> 0x016c }
            goto L_0x0141
        L_0x016c:
            r4 = move-exception
            if (r14 == 0) goto L_0x0172
            r14.close()
        L_0x0172:
            throw r4
        L_0x0173:
            r4 = move-exception
            r18 = r19
            goto L_0x00f5
        L_0x0178:
            r16 = move-exception
            r18 = r19
            goto L_0x00d1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.tagmanager.PersistentHitStore.peekHits(int):java.util.List");
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void setLastDeleteStaleHitsTime(long timeInMilliseconds) {
        this.mLastDeleteStaleHitsTime = timeInMilliseconds;
    }

    /* access modifiers changed from: package-private */
    public int deleteStaleHits() {
        boolean z = true;
        long now = this.mClock.currentTimeMillis();
        if (now <= this.mLastDeleteStaleHitsTime + 86400000) {
            return 0;
        }
        this.mLastDeleteStaleHitsTime = now;
        SQLiteDatabase db = getWritableDatabase("Error opening database for deleteStaleHits.");
        if (db == null) {
            return 0;
        }
        int rslt = db.delete(HITS_TABLE, "HIT_TIME < ?", new String[]{Long.toString(this.mClock.currentTimeMillis() - 2592000000L)});
        HitStoreStateListener hitStoreStateListener = this.mListener;
        if (getNumStoredHits() != 0) {
            z = false;
        }
        hitStoreStateListener.reportStoreIsEmpty(z);
        return rslt;
    }

    /* access modifiers changed from: package-private */
    public void deleteHits(String[] hitIds) {
        SQLiteDatabase db;
        boolean z = true;
        if (hitIds != null && hitIds.length != 0 && (db = getWritableDatabase("Error opening database for deleteHits.")) != null) {
            try {
                db.delete(HITS_TABLE, String.format("HIT_ID in (%s)", new Object[]{TextUtils.join(",", Collections.nCopies(hitIds.length, "?"))}), hitIds);
                HitStoreStateListener hitStoreStateListener = this.mListener;
                if (getNumStoredHits() != 0) {
                    z = false;
                }
                hitStoreStateListener.reportStoreIsEmpty(z);
            } catch (SQLiteException e) {
                Log.m181w("Error deleting hits");
            }
        }
    }

    /* access modifiers changed from: private */
    public void deleteHit(long hitId) {
        deleteHits(new String[]{String.valueOf(hitId)});
    }

    /* access modifiers changed from: private */
    public void setHitFirstDispatchTime(long hitId, long firstDispatchTime) {
        SQLiteDatabase db = getWritableDatabase("Error opening database for getNumStoredHits.");
        if (db != null) {
            ContentValues cv = new ContentValues();
            cv.put(HIT_FIRST_DISPATCH_TIME, Long.valueOf(firstDispatchTime));
            try {
                db.update(HITS_TABLE, cv, HIT_ID_WHERE_CLAUSE, new String[]{String.valueOf(hitId)});
            } catch (SQLiteException e) {
                Log.m181w("Error setting HIT_FIRST_DISPATCH_TIME for hitId: " + hitId);
                deleteHit(hitId);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int getNumStoredHits() {
        int numStoredHits = 0;
        SQLiteDatabase db = getWritableDatabase("Error opening database for getNumStoredHits.");
        if (db == null) {
            return 0;
        }
        Cursor cursor = null;
        try {
            Cursor cursor2 = db.rawQuery("SELECT COUNT(*) from gtm_hits", (String[]) null);
            if (cursor2.moveToFirst()) {
                numStoredHits = (int) cursor2.getLong(0);
            }
            if (cursor2 != null) {
                cursor2.close();
            }
        } catch (SQLiteException e) {
            Log.m181w("Error getting numStoredHits");
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return numStoredHits;
    }

    /* access modifiers changed from: package-private */
    public int getNumStoredUntriedHits() {
        int numStoredHits = 0;
        SQLiteDatabase db = getWritableDatabase("Error opening database for getNumStoredHits.");
        if (db == null) {
            return 0;
        }
        Cursor cursor = null;
        try {
            cursor = db.query(HITS_TABLE, new String[]{HIT_ID, HIT_FIRST_DISPATCH_TIME}, "hit_first_send_time=0", (String[]) null, (String) null, (String) null, (String) null);
            numStoredHits = cursor.getCount();
            if (cursor != null) {
                cursor.close();
            }
        } catch (SQLiteException e) {
            Log.m181w("Error getting num untried hits");
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return numStoredHits;
    }

    public void dispatch() {
        Log.m179v("GTM Dispatch running...");
        if (this.mDispatcher.okToDispatch()) {
            List<Hit> hits = peekHits(40);
            if (hits.isEmpty()) {
                Log.m179v("...nothing to dispatch");
                this.mListener.reportStoreIsEmpty(true);
                return;
            }
            this.mDispatcher.dispatchHits(hits);
            if (getNumStoredUntriedHits() > 0) {
                ServiceManagerImpl.getInstance().dispatch();
            }
        }
    }

    @VisibleForTesting
    class StoreDispatchListener implements SimpleNetworkDispatcher.DispatchListener {
        StoreDispatchListener() {
        }

        public void onHitDispatched(Hit hit) {
            PersistentHitStore.this.deleteHit(hit.getHitId());
        }

        public void onHitPermanentDispatchFailure(Hit hit) {
            PersistentHitStore.this.deleteHit(hit.getHitId());
            Log.m179v("Permanent failure dispatching hitId: " + hit.getHitId());
        }

        public void onHitTransientDispatchFailure(Hit hit) {
            long firstDispatchTime = hit.getHitFirstDispatchTime();
            if (firstDispatchTime == 0) {
                PersistentHitStore.this.setHitFirstDispatchTime(hit.getHitId(), PersistentHitStore.this.mClock.currentTimeMillis());
            } else if (PersistentHitStore.HIT_DISPATCH_RETRY_WINDOW + firstDispatchTime < PersistentHitStore.this.mClock.currentTimeMillis()) {
                PersistentHitStore.this.deleteHit(hit.getHitId());
                Log.m179v("Giving up on failed hitId: " + hit.getHitId());
            }
        }
    }

    public Dispatcher getDispatcher() {
        return this.mDispatcher;
    }

    public void close() {
        try {
            this.mDbHelper.getWritableDatabase().close();
            this.mDispatcher.close();
        } catch (SQLiteException e) {
            Log.m181w("Error opening database for close");
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public UrlDatabaseHelper getHelper() {
        return this.mDbHelper;
    }

    private SQLiteDatabase getWritableDatabase(String errorMessage) {
        try {
            return this.mDbHelper.getWritableDatabase();
        } catch (SQLiteException e) {
            Log.m181w(errorMessage);
            return null;
        }
    }

    @VisibleForTesting
    class UrlDatabaseHelper extends SQLiteOpenHelper {
        private boolean mBadDatabase;
        private long mLastDatabaseCheckTime = 0;

        /* access modifiers changed from: package-private */
        public boolean isBadDatabase() {
            return this.mBadDatabase;
        }

        /* access modifiers changed from: package-private */
        public void setBadDatabase(boolean badDatabase) {
            this.mBadDatabase = badDatabase;
        }

        UrlDatabaseHelper(Context context, String databaseName) {
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
            if (!this.mBadDatabase || this.mLastDatabaseCheckTime + 3600000 <= PersistentHitStore.this.mClock.currentTimeMillis()) {
                SQLiteDatabase db = null;
                this.mBadDatabase = true;
                this.mLastDatabaseCheckTime = PersistentHitStore.this.mClock.currentTimeMillis();
                try {
                    db = super.getWritableDatabase();
                } catch (SQLiteException e) {
                    PersistentHitStore.this.mContext.getDatabasePath(PersistentHitStore.this.mDatabaseName).delete();
                }
                if (db == null) {
                    db = super.getWritableDatabase();
                }
                this.mBadDatabase = false;
                return db;
            }
            throw new SQLiteException("Database creation failed");
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
            if (!tablePresent(PersistentHitStore.HITS_TABLE, db)) {
                db.execSQL(PersistentHitStore.CREATE_HITS_TABLE);
            } else {
                validateColumnsPresent(db);
            }
        }

        /* JADX INFO: finally extract failed */
        private void validateColumnsPresent(SQLiteDatabase db) {
            Cursor c = db.rawQuery("SELECT * FROM gtm_hits WHERE 0", (String[]) null);
            Set<String> columns = new HashSet<>();
            try {
                String[] columnNames = c.getColumnNames();
                for (String add : columnNames) {
                    columns.add(add);
                }
                c.close();
                if (!columns.remove(PersistentHitStore.HIT_ID) || !columns.remove(PersistentHitStore.HIT_URL) || !columns.remove(PersistentHitStore.HIT_TIME) || !columns.remove(PersistentHitStore.HIT_FIRST_DISPATCH_TIME)) {
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
}
