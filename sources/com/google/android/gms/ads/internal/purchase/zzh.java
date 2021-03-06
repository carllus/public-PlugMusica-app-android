package com.google.android.gms.ads.internal.purchase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.internal.zzkn;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@zziy
public class zzh {
    private static final Object zzakd = new Object();
    /* access modifiers changed from: private */
    public static final String zzccd = String.format(Locale.US, "CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s INTEGER)", new Object[]{"InAppPurchase", "purchase_id", "product_id", "developer_payload", "record_time"});
    private static zzh zzccf;
    private final zza zzcce;

    public class zza extends SQLiteOpenHelper {
        public zza(Context context, String str) {
            super(context, str, (SQLiteDatabase.CursorFactory) null, 4);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL(zzh.zzccd);
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            zzkn.zzde(new StringBuilder(64).append("Database updated from version ").append(i).append(" to version ").append(i2).toString());
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS InAppPurchase");
            onCreate(sQLiteDatabase);
        }
    }

    zzh(Context context) {
        this.zzcce = new zza(context, "google_inapp_purchase.db");
    }

    public static zzh zzs(Context context) {
        zzh zzh;
        synchronized (zzakd) {
            if (zzccf == null) {
                zzccf = new zzh(context);
            }
            zzh = zzccf;
        }
        return zzh;
    }

    public int getRecordCount() {
        Cursor cursor = null;
        int i = 0;
        synchronized (zzakd) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase != null) {
                try {
                    Cursor rawQuery = writableDatabase.rawQuery("select count(*) from InAppPurchase", (String[]) null);
                    if (rawQuery.moveToFirst()) {
                        i = rawQuery.getInt(0);
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                    } else if (rawQuery != null) {
                        rawQuery.close();
                    }
                } catch (SQLiteException e) {
                    String valueOf = String.valueOf(e.getMessage());
                    zzkn.zzdf(valueOf.length() != 0 ? "Error getting record count".concat(valueOf) : new String("Error getting record count"));
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
        return i;
    }

    public SQLiteDatabase getWritableDatabase() {
        try {
            return this.zzcce.getWritableDatabase();
        } catch (SQLiteException e) {
            zzkn.zzdf("Error opening writable conversion tracking database");
            return null;
        }
    }

    public zzf zza(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        return new zzf(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
    }

    public void zza(zzf zzf) {
        if (zzf != null) {
            synchronized (zzakd) {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                if (writableDatabase != null) {
                    writableDatabase.delete("InAppPurchase", String.format(Locale.US, "%s = %d", new Object[]{"purchase_id", Long.valueOf(zzf.zzcby)}), (String[]) null);
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzb(com.google.android.gms.ads.internal.purchase.zzf r7) {
        /*
            r6 = this;
            if (r7 != 0) goto L_0x0003
        L_0x0002:
            return
        L_0x0003:
            java.lang.Object r1 = zzakd
            monitor-enter(r1)
            android.database.sqlite.SQLiteDatabase r0 = r6.getWritableDatabase()     // Catch:{ all -> 0x000e }
            if (r0 != 0) goto L_0x0011
            monitor-exit(r1)     // Catch:{ all -> 0x000e }
            goto L_0x0002
        L_0x000e:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x000e }
            throw r0
        L_0x0011:
            android.content.ContentValues r2 = new android.content.ContentValues     // Catch:{ all -> 0x000e }
            r2.<init>()     // Catch:{ all -> 0x000e }
            java.lang.String r3 = "product_id"
            java.lang.String r4 = r7.zzcca     // Catch:{ all -> 0x000e }
            r2.put(r3, r4)     // Catch:{ all -> 0x000e }
            java.lang.String r3 = "developer_payload"
            java.lang.String r4 = r7.zzcbz     // Catch:{ all -> 0x000e }
            r2.put(r3, r4)     // Catch:{ all -> 0x000e }
            java.lang.String r3 = "record_time"
            long r4 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x000e }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x000e }
            r2.put(r3, r4)     // Catch:{ all -> 0x000e }
            java.lang.String r3 = "InAppPurchase"
            r4 = 0
            long r2 = r0.insert(r3, r4, r2)     // Catch:{ all -> 0x000e }
            r7.zzcby = r2     // Catch:{ all -> 0x000e }
            int r0 = r6.getRecordCount()     // Catch:{ all -> 0x000e }
            long r2 = (long) r0     // Catch:{ all -> 0x000e }
            r4 = 20000(0x4e20, double:9.8813E-320)
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x0048
            r6.zzqr()     // Catch:{ all -> 0x000e }
        L_0x0048:
            monitor-exit(r1)     // Catch:{ all -> 0x000e }
            goto L_0x0002
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.purchase.zzh.zzb(com.google.android.gms.ads.internal.purchase.zzf):void");
    }

    public List<zzf> zzg(long j) {
        Cursor cursor;
        synchronized (zzakd) {
            LinkedList linkedList = new LinkedList();
            if (j <= 0) {
                return linkedList;
            }
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase == null) {
                return linkedList;
            }
            try {
                cursor = writableDatabase.query("InAppPurchase", (String[]) null, (String) null, (String[]) null, (String) null, (String) null, "record_time ASC", String.valueOf(j));
                try {
                    if (cursor.moveToFirst()) {
                        do {
                            linkedList.add(zza(cursor));
                        } while (cursor.moveToNext());
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                } catch (SQLiteException e) {
                    e = e;
                    try {
                        String valueOf = String.valueOf(e.getMessage());
                        zzkn.zzdf(valueOf.length() != 0 ? "Error extracing purchase info: ".concat(valueOf) : new String("Error extracing purchase info: "));
                        if (cursor != null) {
                            cursor.close();
                        }
                        return linkedList;
                    } catch (Throwable th) {
                        th = th;
                    }
                }
            } catch (SQLiteException e2) {
                e = e2;
                cursor = null;
            } catch (Throwable th2) {
                th = th2;
                cursor = null;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
    }

    public void zzqr() {
        Cursor cursor;
        synchronized (zzakd) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase != null) {
                try {
                    cursor = writableDatabase.query("InAppPurchase", (String[]) null, (String) null, (String[]) null, (String) null, (String) null, "record_time ASC", "1");
                    if (cursor != null) {
                        try {
                            if (cursor.moveToFirst()) {
                                zza(zza(cursor));
                            }
                        } catch (SQLiteException e) {
                            e = e;
                            try {
                                String valueOf = String.valueOf(e.getMessage());
                                zzkn.zzdf(valueOf.length() != 0 ? "Error remove oldest record".concat(valueOf) : new String("Error remove oldest record"));
                                if (cursor != null) {
                                    cursor.close();
                                }
                                return;
                            } catch (Throwable th) {
                                th = th;
                            }
                        }
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                } catch (SQLiteException e2) {
                    e = e2;
                    cursor = null;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = null;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
        }
    }
}
