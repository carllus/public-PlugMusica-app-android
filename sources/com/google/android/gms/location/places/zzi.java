package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzi implements Parcelable.Creator<PlaceReport> {
    static void zza(PlaceReport placeReport, Parcel parcel, int i) {
        int zzcr = zzb.zzcr(parcel);
        zzb.zzc(parcel, 1, placeReport.mVersionCode);
        zzb.zza(parcel, 2, placeReport.getPlaceId(), false);
        zzb.zza(parcel, 3, placeReport.getTag(), false);
        zzb.zza(parcel, 4, placeReport.getSource(), false);
        zzb.zzaj(parcel, zzcr);
    }

    /* renamed from: zzny */
    public PlaceReport createFromParcel(Parcel parcel) {
        String str = null;
        int zzcq = zza.zzcq(parcel);
        int i = 0;
        String str2 = null;
        String str3 = null;
        while (parcel.dataPosition() < zzcq) {
            int zzcp = zza.zzcp(parcel);
            switch (zza.zzgv(zzcp)) {
                case 1:
                    i = zza.zzg(parcel, zzcp);
                    break;
                case 2:
                    str3 = zza.zzq(parcel, zzcp);
                    break;
                case 3:
                    str2 = zza.zzq(parcel, zzcp);
                    break;
                case 4:
                    str = zza.zzq(parcel, zzcp);
                    break;
                default:
                    zza.zzb(parcel, zzcp);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcq) {
            return new PlaceReport(i, str3, str2, str);
        }
        throw new zza.C1096zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
    }

    /* renamed from: zzvb */
    public PlaceReport[] newArray(int i) {
        return new PlaceReport[i];
    }
}
