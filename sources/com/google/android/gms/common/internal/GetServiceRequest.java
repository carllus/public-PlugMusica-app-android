package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.zzc;
import java.util.Collection;

public class GetServiceRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GetServiceRequest> CREATOR = new zzk();

    /* renamed from: Ci */
    final int f255Ci;

    /* renamed from: Cj */
    int f256Cj;

    /* renamed from: Ck */
    String f257Ck;

    /* renamed from: Cl */
    IBinder f258Cl;

    /* renamed from: Cm */
    Scope[] f259Cm;

    /* renamed from: Cn */
    Bundle f260Cn;

    /* renamed from: Co */
    Account f261Co;

    /* renamed from: Cp */
    long f262Cp;
    final int version;

    public GetServiceRequest(int i) {
        this.version = 3;
        this.f256Cj = zzc.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        this.f255Ci = i;
    }

    GetServiceRequest(int i, int i2, int i3, String str, IBinder iBinder, Scope[] scopeArr, Bundle bundle, Account account, long j) {
        this.version = i;
        this.f255Ci = i2;
        this.f256Cj = i3;
        this.f257Ck = str;
        if (i < 2) {
            this.f261Co = zzdq(iBinder);
        } else {
            this.f258Cl = iBinder;
            this.f261Co = account;
        }
        this.f259Cm = scopeArr;
        this.f260Cn = bundle;
        this.f262Cp = j;
    }

    private Account zzdq(IBinder iBinder) {
        if (iBinder != null) {
            return zza.zza(zzr.zza.zzdr(iBinder));
        }
        return null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzk.zza(this, parcel, i);
    }

    public GetServiceRequest zzb(zzr zzr) {
        if (zzr != null) {
            this.f258Cl = zzr.asBinder();
        }
        return this;
    }

    public GetServiceRequest zzd(Account account) {
        this.f261Co = account;
        return this;
    }

    public GetServiceRequest zzf(Collection<Scope> collection) {
        this.f259Cm = (Scope[]) collection.toArray(new Scope[collection.size()]);
        return this;
    }

    public GetServiceRequest zzht(String str) {
        this.f257Ck = str;
        return this;
    }

    public GetServiceRequest zzo(Bundle bundle) {
        this.f260Cn = bundle;
        return this;
    }
}
