package com.google.android.gms.common.util;

import com.google.android.gms.common.internal.zzg;
import java.util.regex.Pattern;

public class zzw {

    /* renamed from: EZ */
    private static final Pattern f490EZ = Pattern.compile("\\$\\{(.*?)\\}");

    public static boolean zzij(String str) {
        return str == null || zzg.f320BB.zzb(str);
    }
}
