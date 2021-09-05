package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzac;

public final class Scope extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<Scope> CREATOR = new zzg();
    final int mVersionCode;

    /* renamed from: vX */
    private final String f162vX;

    Scope(int i, String str) {
        zzac.zzh(str, "scopeUri must not be null or empty");
        this.mVersionCode = i;
        this.f162vX = str;
    }

    public Scope(String str) {
        this(1, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Scope)) {
            return false;
        }
        return this.f162vX.equals(((Scope) obj).f162vX);
    }

    public int hashCode() {
        return this.f162vX.hashCode();
    }

    public String toString() {
        return this.f162vX;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzg.zza(this, parcel, i);
    }

    public String zzaqg() {
        return this.f162vX;
    }
}
