package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

public class zzai<T extends IInterface> extends zzl<T> {

    /* renamed from: Db */
    private final Api.zzg<T> f283Db;

    public zzai(Context context, Looper looper, int i, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, zzh zzh, Api.zzg<T> zzg) {
        super(context, looper, i, zzh, connectionCallbacks, onConnectionFailedListener);
        this.f283Db = zzg;
    }

    public Api.zzg<T> zzavk() {
        return this.f283Db;
    }

    /* access modifiers changed from: protected */
    public void zzc(int i, T t) {
        this.f283Db.zza(i, t);
    }

    /* access modifiers changed from: protected */
    public T zzh(IBinder iBinder) {
        return this.f283Db.zzh(iBinder);
    }

    /* access modifiers changed from: protected */
    public String zzix() {
        return this.f283Db.zzix();
    }

    /* access modifiers changed from: protected */
    public String zziy() {
        return this.f283Db.zziy();
    }
}
