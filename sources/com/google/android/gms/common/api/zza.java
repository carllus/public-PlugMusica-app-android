package com.google.android.gms.common.api;

import android.support.annotation.NonNull;

public class zza extends Exception {

    /* renamed from: fp */
    protected final Status f172fp;

    public zza(@NonNull Status status) {
        super(status.getStatusMessage());
        this.f172fp = status;
    }
}
