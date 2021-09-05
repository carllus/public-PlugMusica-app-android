package com.google.android.gms.clearcut;

import android.content.Context;

public class zza {

    /* renamed from: tF */
    private static int f86tF = -1;

    /* renamed from: tG */
    public static final zza f87tG = new zza();

    protected zza() {
    }

    public int zzbl(Context context) {
        if (f86tF < 0) {
            f86tF = context.getSharedPreferences("bootCount", 0).getInt("bootCount", 1);
        }
        return f86tF;
    }
}
