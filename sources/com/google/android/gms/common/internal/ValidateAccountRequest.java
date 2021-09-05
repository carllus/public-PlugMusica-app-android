package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@Deprecated
public class ValidateAccountRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ValidateAccountRequest> CREATOR = new zzak();

    /* renamed from: AW */
    final IBinder f273AW;

    /* renamed from: AX */
    private final Scope[] f274AX;

    /* renamed from: De */
    private final int f275De;

    /* renamed from: Df */
    private final Bundle f276Df;

    /* renamed from: Dg */
    private final String f277Dg;
    final int mVersionCode;

    ValidateAccountRequest(int i, int i2, IBinder iBinder, Scope[] scopeArr, Bundle bundle, String str) {
        this.mVersionCode = i;
        this.f275De = i2;
        this.f273AW = iBinder;
        this.f274AX = scopeArr;
        this.f276Df = bundle;
        this.f277Dg = str;
    }

    public String getCallingPackage() {
        return this.f277Dg;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzak.zza(this, parcel, i);
    }

    public Scope[] zzavj() {
        return this.f274AX;
    }

    public int zzavl() {
        return this.f275De;
    }

    public Bundle zzavm() {
        return this.f276Df;
    }
}
