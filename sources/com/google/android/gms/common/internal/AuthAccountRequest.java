package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class AuthAccountRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<AuthAccountRequest> CREATOR = new zzd();

    /* renamed from: AW */
    final IBinder f246AW;

    /* renamed from: AX */
    final Scope[] f247AX;

    /* renamed from: AY */
    Integer f248AY;

    /* renamed from: AZ */
    Integer f249AZ;
    final int mVersionCode;

    AuthAccountRequest(int i, IBinder iBinder, Scope[] scopeArr, Integer num, Integer num2) {
        this.mVersionCode = i;
        this.f246AW = iBinder;
        this.f247AX = scopeArr;
        this.f248AY = num;
        this.f249AZ = num2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzd.zza(this, parcel, i);
    }
}
