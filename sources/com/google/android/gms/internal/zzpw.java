package com.google.android.gms.internal;

import android.content.ContentResolver;
import android.content.Context;
import android.util.Log;
import com.google.android.gms.clearcut.zzb;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzac;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class zzpw implements zzb.C1094zzb {
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    /* renamed from: uC */
    static Boolean f573uC = null;

    /* renamed from: uD */
    final zza f574uD;

    static class zza {
        final ContentResolver mContentResolver;

        zza(Context context) {
            if (context == null || !zzbn(context)) {
                this.mContentResolver = null;
                return;
            }
            this.mContentResolver = context.getContentResolver();
            zzafz.zzb(this.mContentResolver, "gms:playlog:service:sampling_");
        }

        private static boolean zzbn(Context context) {
            if (zzpw.f573uC == null) {
                zzpw.f573uC = Boolean.valueOf(context.checkCallingOrSelfPermission("com.google.android.providers.gsf.permission.READ_GSERVICES") == 0);
            }
            return zzpw.f573uC.booleanValue();
        }

        /* access modifiers changed from: package-private */
        public long zzapb() {
            if (this.mContentResolver == null) {
                return 0;
            }
            return zzafz.getLong(this.mContentResolver, "android_id", 0);
        }

        /* access modifiers changed from: package-private */
        public String zzhc(String str) {
            if (this.mContentResolver == null) {
                return null;
            }
            ContentResolver contentResolver = this.mContentResolver;
            String valueOf = String.valueOf("gms:playlog:service:sampling_");
            String valueOf2 = String.valueOf(str);
            return zzafz.zza(contentResolver, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), (String) null);
        }
    }

    static class zzb {

        /* renamed from: uE */
        public final String f575uE;

        /* renamed from: uF */
        public final long f576uF;

        /* renamed from: uG */
        public final long f577uG;

        public zzb(String str, long j, long j2) {
            this.f575uE = str;
            this.f576uF = j;
            this.f577uG = j2;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzb)) {
                return false;
            }
            zzb zzb = (zzb) obj;
            return zzab.equal(this.f575uE, zzb.f575uE) && zzab.equal(Long.valueOf(this.f576uF), Long.valueOf(zzb.f576uF)) && zzab.equal(Long.valueOf(this.f577uG), Long.valueOf(zzb.f577uG));
        }

        public int hashCode() {
            return zzab.hashCode(this.f575uE, Long.valueOf(this.f576uF), Long.valueOf(this.f577uG));
        }
    }

    public zzpw() {
        this(new zza((Context) null));
    }

    public zzpw(Context context) {
        this(new zza(context));
    }

    zzpw(zza zza2) {
        this.f574uD = (zza) zzac.zzy(zza2);
    }

    static boolean zza(long j, long j2, long j3) {
        if (j2 >= 0 && j3 >= 0) {
            return j3 > 0 && zzpx.zzd(j, j3) < j2;
        }
        throw new IllegalArgumentException(new StringBuilder(72).append("negative values not supported: ").append(j2).append("/").append(j3).toString());
    }

    static long zzai(long j) {
        return zzpt.zzm(ByteBuffer.allocate(8).putLong(j).array());
    }

    static long zzd(String str, long j) {
        if (str == null || str.isEmpty()) {
            return zzai(j);
        }
        byte[] bytes = str.getBytes(UTF_8);
        ByteBuffer allocate = ByteBuffer.allocate(bytes.length + 8);
        allocate.put(bytes);
        allocate.putLong(j);
        return zzpt.zzm(allocate.array());
    }

    static zzb zzhb(String str) {
        int i = 0;
        if (str == null) {
            return null;
        }
        String str2 = "";
        int indexOf = str.indexOf(44);
        if (indexOf >= 0) {
            str2 = str.substring(0, indexOf);
            i = indexOf + 1;
        }
        int indexOf2 = str.indexOf(47, i);
        if (indexOf2 <= 0) {
            String valueOf = String.valueOf(str);
            Log.e("LogSamplerImpl", valueOf.length() != 0 ? "Failed to parse the rule: ".concat(valueOf) : new String("Failed to parse the rule: "));
            return null;
        }
        try {
            long parseLong = Long.parseLong(str.substring(i, indexOf2));
            long parseLong2 = Long.parseLong(str.substring(indexOf2 + 1));
            if (parseLong >= 0 && parseLong2 >= 0) {
                return new zzb(str2, parseLong, parseLong2);
            }
            Log.e("LogSamplerImpl", new StringBuilder(72).append("negative values not supported: ").append(parseLong).append("/").append(parseLong2).toString());
            return null;
        } catch (NumberFormatException e) {
            NumberFormatException numberFormatException = e;
            String valueOf2 = String.valueOf(str);
            Log.e("LogSamplerImpl", valueOf2.length() != 0 ? "parseLong() failed while parsing: ".concat(valueOf2) : new String("parseLong() failed while parsing: "), numberFormatException);
            return null;
        }
    }

    public boolean zzh(String str, int i) {
        if (str == null || str.isEmpty()) {
            str = i >= 0 ? String.valueOf(i) : null;
        }
        if (str == null) {
            return true;
        }
        long zzapb = this.f574uD.zzapb();
        zzb zzhb = zzhb(this.f574uD.zzhc(str));
        if (zzhb != null) {
            return zza(zzd(zzhb.f575uE, zzapb), zzhb.f576uF, zzhb.f577uG);
        }
        return true;
    }
}
