package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.List;

public final class WakeLockEvent extends StatsEvent {
    public static final Parcelable.Creator<WakeLockEvent> CREATOR = new zzg();

    /* renamed from: DM */
    private final long f429DM;

    /* renamed from: DN */
    private int f430DN;

    /* renamed from: DU */
    private final long f431DU;

    /* renamed from: DW */
    private long f432DW;

    /* renamed from: EA */
    private final String f433EA;

    /* renamed from: EB */
    private final int f434EB;

    /* renamed from: EC */
    private final List<String> f435EC;

    /* renamed from: ED */
    private final String f436ED;

    /* renamed from: EE */
    private int f437EE;

    /* renamed from: EF */
    private final String f438EF;

    /* renamed from: EG */
    private final float f439EG;

    /* renamed from: Ey */
    private final String f440Ey;

    /* renamed from: Ez */
    private final String f441Ez;
    private final long mTimeout;
    final int mVersionCode;

    WakeLockEvent(int i, long j, int i2, String str, int i3, List<String> list, String str2, long j2, int i4, String str3, String str4, float f, long j3, String str5) {
        this.mVersionCode = i;
        this.f429DM = j;
        this.f430DN = i2;
        this.f440Ey = str;
        this.f441Ez = str3;
        this.f433EA = str5;
        this.f434EB = i3;
        this.f432DW = -1;
        this.f435EC = list;
        this.f436ED = str2;
        this.f431DU = j2;
        this.f437EE = i4;
        this.f438EF = str4;
        this.f439EG = f;
        this.mTimeout = j3;
    }

    public WakeLockEvent(long j, int i, String str, int i2, List<String> list, String str2, long j2, int i3, String str3, String str4, float f, long j3, String str5) {
        this(2, j, i, str, i2, list, str2, j2, i3, str3, str4, f, j3, str5);
    }

    public int getEventType() {
        return this.f430DN;
    }

    public long getTimeMillis() {
        return this.f429DM;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzg.zza(this, parcel, i);
    }

    public String zzawp() {
        return this.f436ED;
    }

    public long zzawq() {
        return this.f432DW;
    }

    public long zzaws() {
        return this.f431DU;
    }

    public String zzawt() {
        String valueOf = String.valueOf("\t");
        String valueOf2 = String.valueOf(zzaww());
        String valueOf3 = String.valueOf("\t");
        int zzawz = zzawz();
        String valueOf4 = String.valueOf("\t");
        String join = zzaxa() == null ? "" : TextUtils.join(",", zzaxa());
        String valueOf5 = String.valueOf("\t");
        int zzaxb = zzaxb();
        String valueOf6 = String.valueOf("\t");
        String zzawx = zzawx() == null ? "" : zzawx();
        String valueOf7 = String.valueOf("\t");
        String zzaxc = zzaxc() == null ? "" : zzaxc();
        String valueOf8 = String.valueOf("\t");
        float zzaxd = zzaxd();
        String valueOf9 = String.valueOf("\t");
        String zzawy = zzawy() == null ? "" : zzawy();
        return new StringBuilder(String.valueOf(valueOf).length() + 37 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length() + String.valueOf(valueOf4).length() + String.valueOf(join).length() + String.valueOf(valueOf5).length() + String.valueOf(valueOf6).length() + String.valueOf(zzawx).length() + String.valueOf(valueOf7).length() + String.valueOf(zzaxc).length() + String.valueOf(valueOf8).length() + String.valueOf(valueOf9).length() + String.valueOf(zzawy).length()).append(valueOf).append(valueOf2).append(valueOf3).append(zzawz).append(valueOf4).append(join).append(valueOf5).append(zzaxb).append(valueOf6).append(zzawx).append(valueOf7).append(zzaxc).append(valueOf8).append(zzaxd).append(valueOf9).append(zzawy).toString();
    }

    public String zzaww() {
        return this.f440Ey;
    }

    public String zzawx() {
        return this.f441Ez;
    }

    public String zzawy() {
        return this.f433EA;
    }

    public int zzawz() {
        return this.f434EB;
    }

    public List<String> zzaxa() {
        return this.f435EC;
    }

    public int zzaxb() {
        return this.f437EE;
    }

    public String zzaxc() {
        return this.f438EF;
    }

    public float zzaxd() {
        return this.f439EG;
    }

    public long zzaxe() {
        return this.mTimeout;
    }
}
