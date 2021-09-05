package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

public final class zzi {

    /* renamed from: EL */
    private static Boolean f476EL;

    /* renamed from: EM */
    private static Boolean f477EM;

    /* renamed from: EN */
    private static Boolean f478EN;

    /* renamed from: EO */
    private static Boolean f479EO;

    public static boolean zzb(Resources resources) {
        boolean z = false;
        if (resources == null) {
            return false;
        }
        if (f476EL == null) {
            boolean z2 = (resources.getConfiguration().screenLayout & 15) > 3;
            if ((zzs.zzaxk() && z2) || zzc(resources)) {
                z = true;
            }
            f476EL = Boolean.valueOf(z);
        }
        return f476EL.booleanValue();
    }

    @TargetApi(13)
    private static boolean zzc(Resources resources) {
        if (f477EM == null) {
            Configuration configuration = resources.getConfiguration();
            f477EM = Boolean.valueOf(zzs.zzaxm() && (configuration.screenLayout & 15) <= 3 && configuration.smallestScreenWidthDp >= 600);
        }
        return f477EM.booleanValue();
    }

    @TargetApi(20)
    public static boolean zzcl(Context context) {
        if (f478EN == null) {
            f478EN = Boolean.valueOf(zzs.zzaxs() && context.getPackageManager().hasSystemFeature("android.hardware.type.watch"));
        }
        return f478EN.booleanValue();
    }

    @TargetApi(21)
    public static boolean zzcm(Context context) {
        if (f479EO == null) {
            f479EO = Boolean.valueOf(zzs.zzaxu() && context.getPackageManager().hasSystemFeature("cn.google"));
        }
        return f479EO.booleanValue();
    }
}
