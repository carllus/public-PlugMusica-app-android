package com.google.android.gms.internal;

import android.content.Context;

public class zzsi {

    /* renamed from: Fj */
    private static zzsi f864Fj = new zzsi();

    /* renamed from: Fi */
    private zzsh f865Fi = null;

    public static zzsh zzcr(Context context) {
        return f864Fj.zzcq(context);
    }

    public synchronized zzsh zzcq(Context context) {
        if (this.f865Fi == null) {
            if (context.getApplicationContext() != null) {
                context = context.getApplicationContext();
            }
            this.f865Fi = new zzsh(context);
        }
        return this.f865Fi;
    }
}
