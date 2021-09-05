package com.google.android.gms.internal;

import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

public abstract class zzqk implements Releasable, Result {

    /* renamed from: fp */
    protected final Status f644fp;

    /* renamed from: xi */
    protected final DataHolder f645xi;

    protected zzqk(DataHolder dataHolder, Status status) {
        this.f644fp = status;
        this.f645xi = dataHolder;
    }

    public Status getStatus() {
        return this.f644fp;
    }

    public void release() {
        if (this.f645xi != null) {
            this.f645xi.close();
        }
    }
}
