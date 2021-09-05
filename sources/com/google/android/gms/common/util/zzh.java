package com.google.android.gms.common.util;

import android.os.SystemClock;

public final class zzh implements zze {

    /* renamed from: EK */
    private static zzh f475EK;

    public static synchronized zze zzaxj() {
        zzh zzh;
        synchronized (zzh.class) {
            if (f475EK == null) {
                f475EK = new zzh();
            }
            zzh = f475EK;
        }
        return zzh;
    }

    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public long elapsedRealtime() {
        return SystemClock.elapsedRealtime();
    }

    public long nanoTime() {
        return System.nanoTime();
    }
}
