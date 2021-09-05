package com.google.android.gms.clearcut;

import android.os.Parcel;
import com.google.android.gms.clearcut.zzb;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzarp;
import com.google.android.gms.playlog.internal.PlayLoggerContext;
import java.util.Arrays;

public class LogEventParcelable extends AbstractSafeParcelable {
    public static final zzd CREATOR = new zzd();

    /* renamed from: uc */
    public PlayLoggerContext f76uc;

    /* renamed from: ud */
    public byte[] f77ud;

    /* renamed from: ue */
    public int[] f78ue;

    /* renamed from: uf */
    public String[] f79uf;

    /* renamed from: ug */
    public int[] f80ug;

    /* renamed from: uh */
    public byte[][] f81uh;

    /* renamed from: ui */
    public boolean f82ui;

    /* renamed from: uj */
    public final zzarp.zzd f83uj;

    /* renamed from: uk */
    public final zzb.zzc f84uk;

    /* renamed from: ul */
    public final zzb.zzc f85ul;
    public final int versionCode;

    LogEventParcelable(int i, PlayLoggerContext playLoggerContext, byte[] bArr, int[] iArr, String[] strArr, int[] iArr2, byte[][] bArr2, boolean z) {
        this.versionCode = i;
        this.f76uc = playLoggerContext;
        this.f77ud = bArr;
        this.f78ue = iArr;
        this.f79uf = strArr;
        this.f83uj = null;
        this.f84uk = null;
        this.f85ul = null;
        this.f80ug = iArr2;
        this.f81uh = bArr2;
        this.f82ui = z;
    }

    public LogEventParcelable(PlayLoggerContext playLoggerContext, zzarp.zzd zzd, zzb.zzc zzc, zzb.zzc zzc2, int[] iArr, String[] strArr, int[] iArr2, byte[][] bArr, boolean z) {
        this.versionCode = 1;
        this.f76uc = playLoggerContext;
        this.f83uj = zzd;
        this.f84uk = zzc;
        this.f85ul = zzc2;
        this.f78ue = iArr;
        this.f79uf = strArr;
        this.f80ug = iArr2;
        this.f81uh = bArr;
        this.f82ui = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LogEventParcelable)) {
            return false;
        }
        LogEventParcelable logEventParcelable = (LogEventParcelable) obj;
        return this.versionCode == logEventParcelable.versionCode && zzab.equal(this.f76uc, logEventParcelable.f76uc) && Arrays.equals(this.f77ud, logEventParcelable.f77ud) && Arrays.equals(this.f78ue, logEventParcelable.f78ue) && Arrays.equals(this.f79uf, logEventParcelable.f79uf) && zzab.equal(this.f83uj, logEventParcelable.f83uj) && zzab.equal(this.f84uk, logEventParcelable.f84uk) && zzab.equal(this.f85ul, logEventParcelable.f85ul) && Arrays.equals(this.f80ug, logEventParcelable.f80ug) && Arrays.deepEquals(this.f81uh, logEventParcelable.f81uh) && this.f82ui == logEventParcelable.f82ui;
    }

    public int hashCode() {
        return zzab.hashCode(Integer.valueOf(this.versionCode), this.f76uc, this.f77ud, this.f78ue, this.f79uf, this.f83uj, this.f84uk, this.f85ul, this.f80ug, this.f81uh, Boolean.valueOf(this.f82ui));
    }

    public String toString() {
        return "LogEventParcelable[" + this.versionCode + ", " + this.f76uc + ", " + "LogEventBytes: " + (this.f77ud == null ? null : new String(this.f77ud)) + ", " + "TestCodes: " + Arrays.toString(this.f78ue) + ", " + "MendelPackages: " + Arrays.toString(this.f79uf) + ", " + "LogEvent: " + this.f83uj + ", " + "ExtensionProducer: " + this.f84uk + ", " + "VeProducer: " + this.f85ul + ", " + "ExperimentIDs: " + Arrays.toString(this.f80ug) + ", " + "ExperimentTokens: " + Arrays.toString(this.f81uh) + ", " + "AddPhenotypeExperimentTokens: " + this.f82ui + "]";
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzd.zza(this, parcel, i);
    }
}
