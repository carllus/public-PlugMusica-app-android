package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.util.SparseArray;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.server.response.FastJsonResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public final class StringToIntConverter extends AbstractSafeParcelable implements FastJsonResponse.zza<String, Integer> {
    public static final zzb CREATOR = new zzb();

    /* renamed from: Do */
    private final HashMap<String, Integer> f393Do;

    /* renamed from: Dp */
    private final SparseArray<String> f394Dp;

    /* renamed from: Dq */
    private final ArrayList<Entry> f395Dq;
    private final int mVersionCode;

    public static final class Entry extends AbstractSafeParcelable {
        public static final zzc CREATOR = new zzc();

        /* renamed from: Dr */
        final String f396Dr;

        /* renamed from: Ds */
        final int f397Ds;
        final int versionCode;

        Entry(int i, String str, int i2) {
            this.versionCode = i;
            this.f396Dr = str;
            this.f397Ds = i2;
        }

        Entry(String str, int i) {
            this.versionCode = 1;
            this.f396Dr = str;
            this.f397Ds = i;
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzc zzc = CREATOR;
            zzc.zza(this, parcel, i);
        }
    }

    public StringToIntConverter() {
        this.mVersionCode = 1;
        this.f393Do = new HashMap<>();
        this.f394Dp = new SparseArray<>();
        this.f395Dq = null;
    }

    StringToIntConverter(int i, ArrayList<Entry> arrayList) {
        this.mVersionCode = i;
        this.f393Do = new HashMap<>();
        this.f394Dp = new SparseArray<>();
        this.f395Dq = null;
        zzh(arrayList);
    }

    private void zzh(ArrayList<Entry> arrayList) {
        Iterator<Entry> it = arrayList.iterator();
        while (it.hasNext()) {
            Entry next = it.next();
            zzj(next.f396Dr, next.f397Ds);
        }
    }

    /* access modifiers changed from: package-private */
    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb zzb = CREATOR;
        zzb.zza(this, parcel, i);
    }

    /* access modifiers changed from: package-private */
    public ArrayList<Entry> zzavp() {
        ArrayList<Entry> arrayList = new ArrayList<>();
        for (String next : this.f393Do.keySet()) {
            arrayList.add(new Entry(next, this.f393Do.get(next).intValue()));
        }
        return arrayList;
    }

    public int zzavq() {
        return 7;
    }

    public int zzavr() {
        return 0;
    }

    /* renamed from: zzd */
    public String convertBack(Integer num) {
        String str = this.f394Dp.get(num.intValue());
        return (str != null || !this.f393Do.containsKey("gms_unknown")) ? str : "gms_unknown";
    }

    public StringToIntConverter zzj(String str, int i) {
        this.f393Do.put(str, Integer.valueOf(i));
        this.f394Dp.put(i, str);
        return this;
    }
}
