package com.google.android.gms.common.data;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzac;

public abstract class zzc {

    /* renamed from: xi */
    protected final DataHolder f209xi;

    /* renamed from: zK */
    protected int f210zK;

    /* renamed from: zL */
    private int f211zL;

    public zzc(DataHolder dataHolder, int i) {
        this.f209xi = (DataHolder) zzac.zzy(dataHolder);
        zzfz(i);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof zzc)) {
            return false;
        }
        zzc zzc = (zzc) obj;
        return zzab.equal(Integer.valueOf(zzc.f210zK), Integer.valueOf(this.f210zK)) && zzab.equal(Integer.valueOf(zzc.f211zL), Integer.valueOf(this.f211zL)) && zzc.f209xi == this.f209xi;
    }

    /* access modifiers changed from: protected */
    public boolean getBoolean(String str) {
        return this.f209xi.zze(str, this.f210zK, this.f211zL);
    }

    /* access modifiers changed from: protected */
    public byte[] getByteArray(String str) {
        return this.f209xi.zzg(str, this.f210zK, this.f211zL);
    }

    /* access modifiers changed from: protected */
    public float getFloat(String str) {
        return this.f209xi.zzf(str, this.f210zK, this.f211zL);
    }

    /* access modifiers changed from: protected */
    public int getInteger(String str) {
        return this.f209xi.zzc(str, this.f210zK, this.f211zL);
    }

    /* access modifiers changed from: protected */
    public long getLong(String str) {
        return this.f209xi.zzb(str, this.f210zK, this.f211zL);
    }

    /* access modifiers changed from: protected */
    public String getString(String str) {
        return this.f209xi.zzd(str, this.f210zK, this.f211zL);
    }

    public int hashCode() {
        return zzab.hashCode(Integer.valueOf(this.f210zK), Integer.valueOf(this.f211zL), this.f209xi);
    }

    public boolean isDataValid() {
        return !this.f209xi.isClosed();
    }

    /* access modifiers changed from: protected */
    public void zza(String str, CharArrayBuffer charArrayBuffer) {
        this.f209xi.zza(str, this.f210zK, this.f211zL, charArrayBuffer);
    }

    /* access modifiers changed from: protected */
    public int zzatc() {
        return this.f210zK;
    }

    /* access modifiers changed from: protected */
    public void zzfz(int i) {
        zzac.zzbr(i >= 0 && i < this.f209xi.getCount());
        this.f210zK = i;
        this.f211zL = this.f209xi.zzgb(this.f210zK);
    }

    public boolean zzhm(String str) {
        return this.f209xi.zzhm(str);
    }

    /* access modifiers changed from: protected */
    public Uri zzhn(String str) {
        return this.f209xi.zzh(str, this.f210zK, this.f211zL);
    }

    /* access modifiers changed from: protected */
    public boolean zzho(String str) {
        return this.f209xi.zzi(str, this.f210zK, this.f211zL);
    }
}
