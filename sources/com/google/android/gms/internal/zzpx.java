package com.google.android.gms.internal;

public class zzpx {
    public static long zzd(long j, long j2) {
        return j >= 0 ? j % j2 : (((Long.MAX_VALUE % j2) + 1) + ((j & Long.MAX_VALUE) % j2)) % j2;
    }
}
