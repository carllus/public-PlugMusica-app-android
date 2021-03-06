package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzh;

public final class zzrx {
    public static final Api<Api.ApiOptions.NoOptions> API = new Api<>("Common.API", f855fb, f854fa);

    /* renamed from: Dh */
    public static final zzry f853Dh = new zzrz();

    /* renamed from: fa */
    public static final Api.zzf<zzsb> f854fa = new Api.zzf<>();

    /* renamed from: fb */
    private static final Api.zza<zzsb, Api.ApiOptions.NoOptions> f855fb = new Api.zza<zzsb, Api.ApiOptions.NoOptions>() {
        /* renamed from: zzf */
        public zzsb zza(Context context, Looper looper, zzh zzh, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            return new zzsb(context, looper, zzh, connectionCallbacks, onConnectionFailedListener);
        }
    };
}
