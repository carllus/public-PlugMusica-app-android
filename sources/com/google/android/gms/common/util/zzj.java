package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.os.SystemClock;

public final class zzj {

    /* renamed from: EP */
    private static IntentFilter f480EP = new IntentFilter("android.intent.action.BATTERY_CHANGED");

    /* renamed from: EQ */
    private static long f481EQ;

    /* renamed from: ER */
    private static float f482ER = Float.NaN;

    @TargetApi(20)
    public static boolean zzb(PowerManager powerManager) {
        return zzs.zzaxs() ? powerManager.isInteractive() : powerManager.isScreenOn();
    }

    @TargetApi(20)
    public static int zzcn(Context context) {
        int i = 1;
        if (context == null || context.getApplicationContext() == null) {
            return -1;
        }
        Intent registerReceiver = context.getApplicationContext().registerReceiver((BroadcastReceiver) null, f480EP);
        boolean z = ((registerReceiver == null ? 0 : registerReceiver.getIntExtra("plugged", 0)) & 7) != 0;
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager == null) {
            return -1;
        }
        int i2 = (zzb(powerManager) ? 1 : 0) << 1;
        if (!z) {
            i = 0;
        }
        return i2 | i;
    }

    public static synchronized float zzco(Context context) {
        float f;
        synchronized (zzj.class) {
            if (SystemClock.elapsedRealtime() - f481EQ >= 60000 || Float.isNaN(f482ER)) {
                Intent registerReceiver = context.getApplicationContext().registerReceiver((BroadcastReceiver) null, f480EP);
                if (registerReceiver != null) {
                    f482ER = ((float) registerReceiver.getIntExtra("level", -1)) / ((float) registerReceiver.getIntExtra("scale", -1));
                }
                f481EQ = SystemClock.elapsedRealtime();
                f = f482ER;
            } else {
                f = f482ER;
            }
        }
        return f;
    }
}
