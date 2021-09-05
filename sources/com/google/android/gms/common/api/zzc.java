package com.google.android.gms.common.api;

import android.support.p000v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.internal.zzpz;

public class zzc implements Result {

    /* renamed from: fp */
    private final Status f174fp;

    /* renamed from: vn */
    private final ArrayMap<zzpz<?>, ConnectionResult> f175vn;

    public zzc(Status status, ArrayMap<zzpz<?>, ConnectionResult> arrayMap) {
        this.f174fp = status;
        this.f175vn = arrayMap;
    }

    public Status getStatus() {
        return this.f174fp;
    }
}
