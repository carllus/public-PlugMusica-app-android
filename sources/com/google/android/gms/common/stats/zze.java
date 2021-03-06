package com.google.android.gms.common.stats;

import android.os.SystemClock;
import android.support.p000v4.util.SimpleArrayMap;
import android.util.Log;

public class zze {

    /* renamed from: Ev */
    private final long f469Ev;

    /* renamed from: Ew */
    private final int f470Ew;

    /* renamed from: Ex */
    private final SimpleArrayMap<String, Long> f471Ex;

    public zze() {
        this.f469Ev = 60000;
        this.f470Ew = 10;
        this.f471Ex = new SimpleArrayMap<>(10);
    }

    public zze(int i, long j) {
        this.f469Ev = j;
        this.f470Ew = i;
        this.f471Ex = new SimpleArrayMap<>();
    }

    private void zze(long j, long j2) {
        for (int size = this.f471Ex.size() - 1; size >= 0; size--) {
            if (j2 - this.f471Ex.valueAt(size).longValue() > j) {
                this.f471Ex.removeAt(size);
            }
        }
    }

    public Long zzif(String str) {
        Long put;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = this.f469Ev;
        synchronized (this) {
            while (this.f471Ex.size() >= this.f470Ew) {
                zze(j, elapsedRealtime);
                j /= 2;
                Log.w("ConnectionTracker", new StringBuilder(94).append("The max capacity ").append(this.f470Ew).append(" is not enough. Current durationThreshold is: ").append(j).toString());
            }
            put = this.f471Ex.put(str, Long.valueOf(elapsedRealtime));
        }
        return put;
    }

    public boolean zzig(String str) {
        boolean z;
        synchronized (this) {
            z = this.f471Ex.remove(str) != null;
        }
        return z;
    }
}
