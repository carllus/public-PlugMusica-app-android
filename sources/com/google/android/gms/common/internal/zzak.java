package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzak implements Parcelable.Creator<ValidateAccountRequest> {
    static void zza(ValidateAccountRequest validateAccountRequest, Parcel parcel, int i) {
        int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, validateAccountRequest.mVersionCode);
        zzb.zzc(parcel, 2, validateAccountRequest.zzavl());
        zzb.zza(parcel, 3, validateAccountRequest.f273AW, false);
        zzb.zza(parcel, 4, (T[]) validateAccountRequest.zzavj(), i, false);
        zzb.zza(parcel, 5, validateAccountRequest.zzavm(), false);
        zzb.zza(parcel, 6, validateAccountRequest.getCallingPackage(), false);
        zzb.zzaj(parcel, zzcr);
    }

    /* renamed from: zzco */
    public ValidateAccountRequest createFromParcel(Parcel parcel) {
        int i = 0;
        String str = null;
        int zzcq = zza.zzcq(parcel);
        Bundle bundle = null;
        Scope[] scopeArr = null;
        IBinder iBinder = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzcq) {
            int zzcp = zza.zzcp(parcel);
            switch (zza.zzgv(zzcp)) {
                case 1:
                    i2 = zza.zzg(parcel, zzcp);
                    break;
                case 2:
                    i = zza.zzg(parcel, zzcp);
                    break;
                case 3:
                    iBinder = zza.zzr(parcel, zzcp);
                    break;
                case 4:
                    scopeArr = (Scope[]) zza.zzb(parcel, zzcp, Scope.CREATOR);
                    break;
                case 5:
                    bundle = zza.zzs(parcel, zzcp);
                    break;
                case 6:
                    str = zza.zzq(parcel, zzcp);
                    break;
                default:
                    zza.zzb(parcel, zzcp);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcq) {
            return new ValidateAccountRequest(i2, i, iBinder, scopeArr, bundle, str);
        }
        throw new zza.C1096zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
    }

    /* renamed from: zzgu */
    public ValidateAccountRequest[] newArray(int i) {
        return new ValidateAccountRequest[i];
    }
}
