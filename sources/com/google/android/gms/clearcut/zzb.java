package com.google.android.gms.clearcut;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzh;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.internal.zzarp;
import com.google.android.gms.internal.zzpr;
import com.google.android.gms.internal.zzps;
import com.google.android.gms.internal.zzpw;
import com.google.android.gms.playlog.internal.PlayLoggerContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TimeZone;

public final class zzb {
    public static final Api<Api.ApiOptions.NoOptions> API = new Api<>("ClearcutLogger.API", f89fb, f88fa);

    /* renamed from: fa */
    public static final Api.zzf<zzps> f88fa = new Api.zzf<>();

    /* renamed from: fb */
    public static final Api.zza<zzps, Api.ApiOptions.NoOptions> f89fb = new Api.zza<zzps, Api.ApiOptions.NoOptions>() {
        /* renamed from: zze */
        public zzps zza(Context context, Looper looper, zzh zzh, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            return new zzps(context, looper, zzh, connectionCallbacks, onConnectionFailedListener);
        }
    };

    /* renamed from: tH */
    public static final zzc f90tH = new zzpr();
    /* access modifiers changed from: private */

    /* renamed from: ed */
    public final String f91ed;
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */

    /* renamed from: tI */
    public final int f92tI;
    /* access modifiers changed from: private */

    /* renamed from: tJ */
    public String f93tJ;
    /* access modifiers changed from: private */

    /* renamed from: tK */
    public int f94tK;
    /* access modifiers changed from: private */

    /* renamed from: tL */
    public String f95tL;
    /* access modifiers changed from: private */

    /* renamed from: tM */
    public String f96tM;
    /* access modifiers changed from: private */

    /* renamed from: tN */
    public final boolean f97tN;

    /* renamed from: tO */
    private int f98tO;
    /* access modifiers changed from: private */

    /* renamed from: tP */
    public final zzc f99tP;
    /* access modifiers changed from: private */

    /* renamed from: tQ */
    public final zza f100tQ;
    /* access modifiers changed from: private */

    /* renamed from: tR */
    public zzd f101tR;
    /* access modifiers changed from: private */

    /* renamed from: tS */
    public final C1094zzb f102tS;
    /* access modifiers changed from: private */
    public final zze zzapy;

    public class zza {

        /* renamed from: tJ */
        private String f103tJ;

        /* renamed from: tK */
        private int f104tK;

        /* renamed from: tL */
        private String f105tL;

        /* renamed from: tM */
        private String f106tM;

        /* renamed from: tO */
        private int f107tO;

        /* renamed from: tT */
        private final zzc f108tT;

        /* renamed from: tU */
        private ArrayList<Integer> f109tU;

        /* renamed from: tV */
        private ArrayList<String> f110tV;

        /* renamed from: tW */
        private ArrayList<Integer> f111tW;

        /* renamed from: tX */
        private ArrayList<byte[]> f112tX;

        /* renamed from: tY */
        private boolean f113tY;

        /* renamed from: tZ */
        private final zzarp.zzd f114tZ;

        /* renamed from: ua */
        private boolean f115ua;

        private zza(zzb zzb, byte[] bArr) {
            this(bArr, (zzc) null);
        }

        private zza(byte[] bArr, zzc zzc) {
            this.f104tK = zzb.this.f94tK;
            this.f103tJ = zzb.this.f93tJ;
            this.f105tL = zzb.this.f95tL;
            this.f106tM = zzb.this.f96tM;
            this.f107tO = zzb.zze(zzb.this);
            this.f109tU = null;
            this.f110tV = null;
            this.f111tW = null;
            this.f112tX = null;
            this.f113tY = true;
            this.f114tZ = new zzarp.zzd();
            this.f115ua = false;
            this.f105tL = zzb.this.f95tL;
            this.f106tM = zzb.this.f96tM;
            this.f114tZ.bra = zzb.this.zzapy.currentTimeMillis();
            this.f114tZ.brb = zzb.this.zzapy.elapsedRealtime();
            this.f114tZ.brs = (long) zzb.this.f100tQ.zzbl(zzb.this.mContext);
            this.f114tZ.brm = zzb.this.f101tR.zzag(this.f114tZ.bra);
            if (bArr != null) {
                this.f114tZ.brh = bArr;
            }
            this.f108tT = zzc;
        }

        public LogEventParcelable zzaox() {
            return new LogEventParcelable(new PlayLoggerContext(zzb.this.f91ed, zzb.this.f92tI, this.f104tK, this.f103tJ, this.f105tL, this.f106tM, zzb.this.f97tN, this.f107tO), this.f114tZ, this.f108tT, (zzc) null, zzb.zzb((ArrayList<Integer>) null), zzb.zzc((ArrayList<String>) null), zzb.zzb((ArrayList<Integer>) null), zzb.zzd((ArrayList<byte[]>) null), this.f113tY);
        }

        public PendingResult<Status> zze(GoogleApiClient googleApiClient) {
            if (this.f115ua) {
                throw new IllegalStateException("do not reuse LogEventBuilder");
            }
            this.f115ua = true;
            PlayLoggerContext playLoggerContext = zzaox().f76uc;
            return zzb.this.f102tS.zzh(playLoggerContext.axz, playLoggerContext.axv) ? zzb.this.f99tP.zza(googleApiClient, zzaox()) : PendingResults.immediatePendingResult(Status.f163vY);
        }

        public zza zzfh(int i) {
            this.f114tZ.brd = i;
            return this;
        }

        public zza zzfi(int i) {
            this.f114tZ.zzajd = i;
            return this;
        }
    }

    /* renamed from: com.google.android.gms.clearcut.zzb$zzb  reason: collision with other inner class name */
    public interface C1094zzb {
        boolean zzh(String str, int i);
    }

    public interface zzc {
        byte[] zzaoy();
    }

    public static class zzd {
        public long zzag(long j) {
            return (long) (TimeZone.getDefault().getOffset(j) / 1000);
        }
    }

    public zzb(Context context, int i, String str, String str2, String str3, boolean z, zzc zzc2, zze zze, zzd zzd2, zza zza2, C1094zzb zzb) {
        this.f94tK = -1;
        this.f98tO = 0;
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext == null ? context : applicationContext;
        this.f91ed = context.getPackageName();
        this.f92tI = zzbm(context);
        this.f94tK = i;
        this.f93tJ = str;
        this.f95tL = str2;
        this.f96tM = str3;
        this.f97tN = z;
        this.f99tP = zzc2;
        this.zzapy = zze;
        this.f101tR = zzd2 == null ? new zzd() : zzd2;
        this.f100tQ = zza2;
        this.f98tO = 0;
        this.f102tS = zzb;
        if (this.f97tN) {
            zzac.zzb(this.f95tL == null, (Object) "can't be anonymous with an upload account");
        }
    }

    public zzb(Context context, String str, String str2) {
        this(context, -1, str, str2, (String) null, false, f90tH, com.google.android.gms.common.util.zzh.zzaxj(), (zzd) null, zza.f87tG, new zzpw(context));
    }

    /* access modifiers changed from: private */
    public static int[] zzb(ArrayList<Integer> arrayList) {
        if (arrayList == null) {
            return null;
        }
        int[] iArr = new int[arrayList.size()];
        int i = 0;
        Iterator<Integer> it = arrayList.iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return iArr;
            }
            i = i2 + 1;
            iArr[i2] = it.next().intValue();
        }
    }

    private int zzbm(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.wtf("ClearcutLogger", "This can't happen.");
            return 0;
        }
    }

    /* access modifiers changed from: private */
    public static String[] zzc(ArrayList<String> arrayList) {
        if (arrayList == null) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    /* access modifiers changed from: private */
    public static byte[][] zzd(ArrayList<byte[]> arrayList) {
        if (arrayList == null) {
            return null;
        }
        return (byte[][]) arrayList.toArray(new byte[0][]);
    }

    static /* synthetic */ int zze(zzb zzb) {
        return 0;
    }

    public zza zzl(byte[] bArr) {
        return new zza(bArr);
    }
}
