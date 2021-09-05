package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.gms.C0417R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzaj;

@Deprecated
public final class zzqw {

    /* renamed from: yP */
    private static zzqw f777yP;
    private static Object zzaok = new Object();

    /* renamed from: yQ */
    private final String f778yQ;

    /* renamed from: yR */
    private final Status f779yR;

    /* renamed from: yS */
    private final String f780yS;

    /* renamed from: yT */
    private final String f781yT;

    /* renamed from: yU */
    private final String f782yU;

    /* renamed from: yV */
    private final boolean f783yV;

    /* renamed from: yW */
    private final boolean f784yW;
    private final String zzcpe;

    zzqw(Context context) {
        boolean z = true;
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("google_app_measurement_enable", "integer", resources.getResourcePackageName(C0417R.string.common_google_play_services_unknown_issue));
        if (identifier != 0) {
            boolean z2 = resources.getInteger(identifier) != 0;
            this.f784yW = z2 ? false : z;
            z = z2;
        } else {
            this.f784yW = false;
        }
        this.f783yV = z;
        zzaj zzaj = new zzaj(context);
        this.f780yS = zzaj.getString("firebase_database_url");
        this.f782yU = zzaj.getString("google_storage_bucket");
        this.f781yT = zzaj.getString("gcm_defaultSenderId");
        this.f778yQ = zzaj.getString("google_api_key");
        String zzcg = zzaa.zzcg(context);
        zzcg = zzcg == null ? zzaj.getString("google_app_id") : zzcg;
        if (TextUtils.isEmpty(zzcg)) {
            this.f779yR = new Status(10, "Missing google app id value from from string resources with name google_app_id.");
            this.zzcpe = null;
            return;
        }
        this.zzcpe = zzcg;
        this.f779yR = Status.f163vY;
    }

    zzqw(String str, boolean z) {
        this(str, z, (String) null, (String) null, (String) null);
    }

    zzqw(String str, boolean z, String str2, String str3, String str4) {
        this.zzcpe = str;
        this.f778yQ = null;
        this.f779yR = Status.f163vY;
        this.f783yV = z;
        this.f784yW = !z;
        this.f780yS = str2;
        this.f781yT = str4;
        this.f782yU = str3;
    }

    public static String zzasl() {
        return zzhf("getGoogleAppId").zzcpe;
    }

    public static boolean zzasm() {
        return zzhf("isMeasurementExplicitlyDisabled").f784yW;
    }

    public static Status zzb(Context context, String str, boolean z) {
        Status status;
        zzac.zzb(context, (Object) "Context must not be null.");
        zzac.zzh(str, "App ID must be nonempty.");
        synchronized (zzaok) {
            if (f777yP != null) {
                status = f777yP.zzhe(str);
            } else {
                f777yP = new zzqw(str, z);
                status = f777yP.f779yR;
            }
        }
        return status;
    }

    public static Status zzcb(Context context) {
        Status status;
        zzac.zzb(context, (Object) "Context must not be null.");
        synchronized (zzaok) {
            if (f777yP == null) {
                f777yP = new zzqw(context);
            }
            status = f777yP.f779yR;
        }
        return status;
    }

    private static zzqw zzhf(String str) {
        zzqw zzqw;
        synchronized (zzaok) {
            if (f777yP == null) {
                throw new IllegalStateException(new StringBuilder(String.valueOf(str).length() + 34).append("Initialize must be called before ").append(str).append(".").toString());
            }
            zzqw = f777yP;
        }
        return zzqw;
    }

    /* access modifiers changed from: package-private */
    public Status zzhe(String str) {
        if (this.zzcpe == null || this.zzcpe.equals(str)) {
            return Status.f163vY;
        }
        String str2 = this.zzcpe;
        return new Status(10, new StringBuilder(String.valueOf(str2).length() + 97).append("Initialize was called with two different Google App IDs.  Only the first app ID will be used: '").append(str2).append("'.").toString());
    }
}
