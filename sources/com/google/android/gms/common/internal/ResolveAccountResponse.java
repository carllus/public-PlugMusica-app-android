package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzr;

public class ResolveAccountResponse extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ResolveAccountResponse> CREATOR = new zzae();

    /* renamed from: AW */
    IBinder f266AW;

    /* renamed from: CX */
    private boolean f267CX;
    final int mVersionCode;

    /* renamed from: vm */
    private ConnectionResult f268vm;

    /* renamed from: xz */
    private boolean f269xz;

    ResolveAccountResponse(int i, IBinder iBinder, ConnectionResult connectionResult, boolean z, boolean z2) {
        this.mVersionCode = i;
        this.f266AW = iBinder;
        this.f268vm = connectionResult;
        this.f269xz = z;
        this.f267CX = z2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResolveAccountResponse)) {
            return false;
        }
        ResolveAccountResponse resolveAccountResponse = (ResolveAccountResponse) obj;
        return this.f268vm.equals(resolveAccountResponse.f268vm) && zzavd().equals(resolveAccountResponse.zzavd());
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzae.zza(this, parcel, i);
    }

    public zzr zzavd() {
        return zzr.zza.zzdr(this.f266AW);
    }

    public ConnectionResult zzave() {
        return this.f268vm;
    }

    public boolean zzavf() {
        return this.f269xz;
    }

    public boolean zzavg() {
        return this.f267CX;
    }
}
