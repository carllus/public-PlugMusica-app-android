package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzh implements Parcelable.Creator<Status> {
    static void zza(Status status, Parcel parcel, int i) {
        int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, status.getStatusCode());
        zzb.zza(parcel, 2, status.getStatusMessage(), false);
        zzb.zza(parcel, 3, (Parcelable) status.zzaqh(), i, false);
        zzb.zzc(parcel, 1000, status.getVersionCode());
        zzb.zzaj(parcel, zzcr);
    }

    /* renamed from: zzce */
    public Status createFromParcel(Parcel parcel) {
        PendingIntent pendingIntent = null;
        int i = 0;
        int zzcq = zza.zzcq(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzcq) {
            int zzcp = zza.zzcp(parcel);
            switch (zza.zzgv(zzcp)) {
                case 1:
                    i = zza.zzg(parcel, zzcp);
                    break;
                case 2:
                    str = zza.zzq(parcel, zzcp);
                    break;
                case 3:
                    pendingIntent = (PendingIntent) zza.zza(parcel, zzcp, PendingIntent.CREATOR);
                    break;
                case 1000:
                    i2 = zza.zzg(parcel, zzcp);
                    break;
                default:
                    zza.zzb(parcel, zzcp);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcq) {
            return new Status(i2, i, str, pendingIntent);
        }
        throw new zza.C1096zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
    }

    /* renamed from: zzfp */
    public Status[] newArray(int i) {
        return new Status[i];
    }
}
