package com.google.android.gms.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.zzd;
import com.google.android.gms.internal.zzqc;

public class zzqu<O extends Api.ApiOptions> extends zzql {

    /* renamed from: yN */
    private final zzd<O> f775yN;

    public zzqu(zzd<O> zzd) {
        super("Method is not supported by connectionless client. APIs supporting connectionless client must not call this method.");
        this.f775yN = zzd;
    }

    public Looper getLooper() {
        return this.f775yN.getLooper();
    }

    public void zza(zzrp zzrp) {
        this.f775yN.zzapu();
    }

    public void zzb(zzrp zzrp) {
        this.f775yN.zzapv();
    }

    public <A extends Api.zzb, R extends Result, T extends zzqc.zza<R, A>> T zzc(@NonNull T t) {
        return this.f775yN.zza(t);
    }

    public <A extends Api.zzb, T extends zzqc.zza<? extends Result, A>> T zzd(@NonNull T t) {
        return this.f775yN.zzb(t);
    }
}
