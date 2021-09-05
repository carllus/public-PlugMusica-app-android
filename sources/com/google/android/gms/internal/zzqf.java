package com.google.android.gms.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzac;

public class zzqf implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    /* renamed from: tv */
    public final Api<?> f625tv;

    /* renamed from: wT */
    private final int f626wT;

    /* renamed from: wU */
    private zzqg f627wU;

    public zzqf(Api<?> api, int i) {
        this.f625tv = api;
        this.f626wT = i;
    }

    private void zzaqx() {
        zzac.zzb(this.f627wU, (Object) "Callbacks must be attached to a ClientConnectionHelper instance before connecting the client.");
    }

    public void onConnected(@Nullable Bundle bundle) {
        zzaqx();
        this.f627wU.onConnected(bundle);
    }

    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        zzaqx();
        this.f627wU.zza(connectionResult, this.f625tv, this.f626wT);
    }

    public void onConnectionSuspended(int i) {
        zzaqx();
        this.f627wU.onConnectionSuspended(i);
    }

    public void zza(zzqg zzqg) {
        this.f627wU = zzqg;
    }
}
