package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable;

public final class ConnectionEvent extends StatsEvent {
    public static final Parcelable.Creator<ConnectionEvent> CREATOR = new zza();

    /* renamed from: DM */
    private final long f418DM;

    /* renamed from: DN */
    private int f419DN;

    /* renamed from: DO */
    private final String f420DO;

    /* renamed from: DP */
    private final String f421DP;

    /* renamed from: DQ */
    private final String f422DQ;

    /* renamed from: DR */
    private final String f423DR;

    /* renamed from: DS */
    private final String f424DS;

    /* renamed from: DT */
    private final String f425DT;

    /* renamed from: DU */
    private final long f426DU;

    /* renamed from: DV */
    private final long f427DV;

    /* renamed from: DW */
    private long f428DW;
    final int mVersionCode;

    ConnectionEvent(int i, long j, int i2, String str, String str2, String str3, String str4, String str5, String str6, long j2, long j3) {
        this.mVersionCode = i;
        this.f418DM = j;
        this.f419DN = i2;
        this.f420DO = str;
        this.f421DP = str2;
        this.f422DQ = str3;
        this.f423DR = str4;
        this.f428DW = -1;
        this.f424DS = str5;
        this.f425DT = str6;
        this.f426DU = j2;
        this.f427DV = j3;
    }

    public ConnectionEvent(long j, int i, String str, String str2, String str3, String str4, String str5, String str6, long j2, long j3) {
        this(1, j, i, str, str2, str3, str4, str5, str6, j2, j3);
    }

    public int getEventType() {
        return this.f419DN;
    }

    public long getTimeMillis() {
        return this.f418DM;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.zza(this, parcel, i);
    }

    public String zzawk() {
        return this.f420DO;
    }

    public String zzawl() {
        return this.f421DP;
    }

    public String zzawm() {
        return this.f422DQ;
    }

    public String zzawn() {
        return this.f423DR;
    }

    public String zzawo() {
        return this.f424DS;
    }

    public String zzawp() {
        return this.f425DT;
    }

    public long zzawq() {
        return this.f428DW;
    }

    public long zzawr() {
        return this.f427DV;
    }

    public long zzaws() {
        return this.f426DU;
    }

    public String zzawt() {
        String valueOf = String.valueOf("\t");
        String valueOf2 = String.valueOf(zzawk());
        String valueOf3 = String.valueOf(zzawl());
        String valueOf4 = String.valueOf("\t");
        String valueOf5 = String.valueOf(zzawm());
        String valueOf6 = String.valueOf(zzawn());
        String valueOf7 = String.valueOf("\t");
        String str = this.f424DS == null ? "" : this.f424DS;
        String valueOf8 = String.valueOf("\t");
        return new StringBuilder(String.valueOf(valueOf).length() + 22 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length() + String.valueOf(valueOf4).length() + String.valueOf(valueOf5).length() + String.valueOf(valueOf6).length() + String.valueOf(valueOf7).length() + String.valueOf(str).length() + String.valueOf(valueOf8).length()).append(valueOf).append(valueOf2).append("/").append(valueOf3).append(valueOf4).append(valueOf5).append("/").append(valueOf6).append(valueOf7).append(str).append(valueOf8).append(zzawr()).toString();
    }
}
