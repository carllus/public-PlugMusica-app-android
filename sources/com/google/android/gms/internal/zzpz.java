package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.internal.zzab;

public final class zzpz<O extends Api.ApiOptions> {

    /* renamed from: tv */
    private final Api<O> f589tv;

    /* renamed from: vw */
    private final O f590vw;

    /* renamed from: wo */
    private final boolean f591wo = false;

    /* renamed from: wp */
    private final int f592wp;

    private zzpz(Api<O> api, O o) {
        this.f589tv = api;
        this.f590vw = o;
        this.f592wp = zzab.hashCode(this.f589tv, this.f590vw);
    }

    public static <O extends Api.ApiOptions> zzpz<O> zza(Api<O> api, O o) {
        return new zzpz<>(api, o);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzpz)) {
            return false;
        }
        zzpz zzpz = (zzpz) obj;
        return zzab.equal(this.f589tv, zzpz.f589tv) && zzab.equal(this.f590vw, zzpz.f590vw);
    }

    public int hashCode() {
        return this.f592wp;
    }

    public String zzaqj() {
        return this.f589tv.getName();
    }
}
