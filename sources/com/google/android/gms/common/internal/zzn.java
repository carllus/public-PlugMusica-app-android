package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;

public abstract class zzn {

    /* renamed from: CA */
    private static zzn f368CA;

    /* renamed from: Cz */
    private static final Object f369Cz = new Object();

    public static zzn zzcf(Context context) {
        synchronized (f369Cz) {
            if (f368CA == null) {
                f368CA = new zzo(context.getApplicationContext());
            }
        }
        return f368CA;
    }

    public abstract boolean zza(ComponentName componentName, ServiceConnection serviceConnection, String str);

    public abstract boolean zza(String str, String str2, ServiceConnection serviceConnection, String str3);

    public abstract void zzb(ComponentName componentName, ServiceConnection serviceConnection, String str);

    public abstract void zzb(String str, String str2, ServiceConnection serviceConnection, String str3);
}
