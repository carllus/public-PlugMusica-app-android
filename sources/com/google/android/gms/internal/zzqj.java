package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.zzrd;

public abstract class zzqj<L> implements zzrd.zzc<L> {

    /* renamed from: xi */
    private final DataHolder f643xi;

    protected zzqj(DataHolder dataHolder) {
        this.f643xi = dataHolder;
    }

    /* access modifiers changed from: protected */
    public abstract void zza(L l, DataHolder dataHolder);

    public void zzarg() {
        if (this.f643xi != null) {
            this.f643xi.close();
        }
    }

    public final void zzt(L l) {
        zza(l, this.f643xi);
    }
}
