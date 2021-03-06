package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;

public class ConverterWrapper extends AbstractSafeParcelable {
    public static final zza CREATOR = new zza();

    /* renamed from: Dn */
    private final StringToIntConverter f392Dn;
    private final int mVersionCode;

    ConverterWrapper(int i, StringToIntConverter stringToIntConverter) {
        this.mVersionCode = i;
        this.f392Dn = stringToIntConverter;
    }

    private ConverterWrapper(StringToIntConverter stringToIntConverter) {
        this.mVersionCode = 1;
        this.f392Dn = stringToIntConverter;
    }

    public static ConverterWrapper zza(FastJsonResponse.zza<?, ?> zza) {
        if (zza instanceof StringToIntConverter) {
            return new ConverterWrapper((StringToIntConverter) zza);
        }
        throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
    }

    /* access modifiers changed from: package-private */
    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza zza = CREATOR;
        zza.zza(this, parcel, i);
    }

    /* access modifiers changed from: package-private */
    public StringToIntConverter zzavn() {
        return this.f392Dn;
    }

    public FastJsonResponse.zza<?, ?> zzavo() {
        if (this.f392Dn != null) {
            return this.f392Dn;
        }
        throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
    }
}
