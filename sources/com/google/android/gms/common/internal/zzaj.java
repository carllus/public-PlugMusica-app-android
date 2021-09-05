package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.C0417R;

public class zzaj {

    /* renamed from: Dc */
    private final Resources f284Dc;

    /* renamed from: Dd */
    private final String f285Dd = this.f284Dc.getResourcePackageName(C0417R.string.common_google_play_services_unknown_issue);

    public zzaj(Context context) {
        zzac.zzy(context);
        this.f284Dc = context.getResources();
    }

    public String getString(String str) {
        int identifier = this.f284Dc.getIdentifier(str, "string", this.f285Dd);
        if (identifier == 0) {
            return null;
        }
        return this.f284Dc.getString(identifier);
    }
}
