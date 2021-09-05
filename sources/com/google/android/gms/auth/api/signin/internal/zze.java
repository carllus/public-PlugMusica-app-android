package com.google.android.gms.auth.api.signin.internal;

public class zze {

    /* renamed from: hy */
    static int f70hy = 31;

    /* renamed from: hz */
    private int f71hz = 1;

    public int zzahv() {
        return this.f71hz;
    }

    public zze zzbd(boolean z) {
        this.f71hz = (z ? 1 : 0) + (this.f71hz * f70hy);
        return this;
    }

    public zze zzq(Object obj) {
        this.f71hz = (obj == null ? 0 : obj.hashCode()) + (this.f71hz * f70hy);
        return this;
    }
}
