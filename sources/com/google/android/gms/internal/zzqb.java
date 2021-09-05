package com.google.android.gms.internal;

import android.support.p000v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zzb;
import com.google.android.gms.common.api.zzc;
import java.util.Set;

public final class zzqb extends zzqe<zzc> {

    /* renamed from: wv */
    private int f598wv;

    /* renamed from: ww */
    private boolean f599ww;

    private void zza(ConnectionResult connectionResult) {
        ArrayMap arrayMap = null;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < arrayMap.size()) {
                zza((zzpz) arrayMap.keyAt(i2), connectionResult);
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public void zza(zzpz<?> zzpz, ConnectionResult connectionResult) {
        synchronized (0) {
            ArrayMap arrayMap = null;
            try {
                arrayMap.put(zzpz, connectionResult);
                this.f598wv--;
                if (!connectionResult.isSuccess()) {
                    this.f599ww = true;
                }
                if (this.f598wv == 0) {
                    Status status = this.f599ww ? new Status(13) : Status.f163vY;
                    ArrayMap arrayMap2 = null;
                    zzc(arrayMap2.size() == 1 ? new zzb(status, (ArrayMap<zzpz<?>, ConnectionResult>) null) : new zzc(status, (ArrayMap<zzpz<?>, ConnectionResult>) null));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public Set<zzpz<?>> zzaqm() {
        ArrayMap arrayMap = null;
        return arrayMap.keySet();
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    /* renamed from: zzy */
    public zzc zzc(Status status) {
        zzc zzb;
        synchronized (0) {
            try {
                zza(new ConnectionResult(8));
                ArrayMap arrayMap = null;
                zzb = arrayMap.size() == 1 ? new zzb(status, (ArrayMap<zzpz<?>, ConnectionResult>) null) : new zzc(status, (ArrayMap<zzpz<?>, ConnectionResult>) null);
            } catch (Throwable th) {
                throw th;
            }
        }
        return zzb;
    }
}
