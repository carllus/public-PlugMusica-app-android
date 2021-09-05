package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzai;
import com.google.android.gms.internal.zzqc;
import com.google.android.gms.internal.zzqr;

public class zzqm implements zzqq {
    /* access modifiers changed from: private */

    /* renamed from: xk */
    public final zzqr f647xk;

    /* renamed from: xl */
    private boolean f648xl = false;

    public zzqm(zzqr zzqr) {
        this.f647xk = zzqr;
    }

    private <A extends Api.zzb> void zzf(zzqc.zza<? extends Result, A> zza) throws DeadObjectException {
        this.f647xk.f725wV.f710yc.zzb((zzqe<? extends Result>) zza);
        A zzb = this.f647xk.f725wV.zzb((Api.zzc<?>) zza.zzapp());
        if (zzb.isConnected() || !this.f647xk.f733yl.containsKey(zza.zzapp())) {
            if (zzb instanceof zzai) {
                zzb = ((zzai) zzb).zzavk();
            }
            zza.zzb(zzb);
            return;
        }
        zza.zzz(new Status(17));
    }

    public void begin() {
    }

    public void connect() {
        if (this.f648xl) {
            this.f648xl = false;
            this.f647xk.zza((zzqr.zza) new zzqr.zza(this) {
                public void zzari() {
                    zzqm.this.f647xk.f737yp.zzn((Bundle) null);
                }
            });
        }
    }

    public boolean disconnect() {
        if (this.f648xl) {
            return false;
        }
        if (this.f647xk.f725wV.zzaru()) {
            this.f648xl = true;
            for (zzrp zzasu : this.f647xk.f725wV.f709yb) {
                zzasu.zzasu();
            }
            return false;
        }
        this.f647xk.zzi((ConnectionResult) null);
        return true;
    }

    public void onConnected(Bundle bundle) {
    }

    public void onConnectionSuspended(int i) {
        this.f647xk.zzi((ConnectionResult) null);
        this.f647xk.f737yp.zzc(i, this.f648xl);
    }

    public void zza(ConnectionResult connectionResult, Api<?> api, int i) {
    }

    /* access modifiers changed from: package-private */
    public void zzarh() {
        if (this.f648xl) {
            this.f648xl = false;
            this.f647xk.f725wV.f710yc.release();
            disconnect();
        }
    }

    public <A extends Api.zzb, R extends Result, T extends zzqc.zza<R, A>> T zzc(T t) {
        return zzd(t);
    }

    public <A extends Api.zzb, T extends zzqc.zza<? extends Result, A>> T zzd(T t) {
        try {
            zzf(t);
        } catch (DeadObjectException e) {
            this.f647xk.zza((zzqr.zza) new zzqr.zza(this) {
                public void zzari() {
                    zzqm.this.onConnectionSuspended(1);
                }
            });
        }
        return t;
    }
}
