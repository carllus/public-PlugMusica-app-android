package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.internal.zzqc;
import java.util.Collections;

public class zzqo implements zzqq {

    /* renamed from: xk */
    private final zzqr f689xk;

    public zzqo(zzqr zzqr) {
        this.f689xk = zzqr;
    }

    public void begin() {
        this.f689xk.zzary();
        this.f689xk.f725wV.f704xX = Collections.emptySet();
    }

    public void connect() {
        this.f689xk.zzarw();
    }

    public boolean disconnect() {
        return true;
    }

    public void onConnected(Bundle bundle) {
    }

    public void onConnectionSuspended(int i) {
    }

    public void zza(ConnectionResult connectionResult, Api<?> api, int i) {
    }

    public <A extends Api.zzb, R extends Result, T extends zzqc.zza<R, A>> T zzc(T t) {
        this.f689xk.f725wV.f697xQ.add(t);
        return t;
    }

    public <A extends Api.zzb, T extends zzqc.zza<? extends Result, A>> T zzd(T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
}
