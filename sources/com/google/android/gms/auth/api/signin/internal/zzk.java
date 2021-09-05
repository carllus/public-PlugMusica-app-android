package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.zzac;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;

public class zzk {

    /* renamed from: hI */
    private static final Lock f72hI = new ReentrantLock();

    /* renamed from: hJ */
    private static zzk f73hJ;

    /* renamed from: hK */
    private final Lock f74hK = new ReentrantLock();

    /* renamed from: hL */
    private final SharedPreferences f75hL;

    zzk(Context context) {
        this.f75hL = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }

    public static zzk zzbd(Context context) {
        zzac.zzy(context);
        f72hI.lock();
        try {
            if (f73hJ == null) {
                f73hJ = new zzk(context.getApplicationContext());
            }
            return f73hJ;
        } finally {
            f72hI.unlock();
        }
    }

    private String zzy(String str, String str2) {
        String valueOf = String.valueOf(":");
        return new StringBuilder(String.valueOf(str).length() + 0 + String.valueOf(valueOf).length() + String.valueOf(str2).length()).append(str).append(valueOf).append(str2).toString();
    }

    /* access modifiers changed from: package-private */
    public void zza(GoogleSignInAccount googleSignInAccount, GoogleSignInOptions googleSignInOptions) {
        zzac.zzy(googleSignInAccount);
        zzac.zzy(googleSignInOptions);
        String zzahf = googleSignInAccount.zzahf();
        zzx(zzy("googleSignInAccount", zzahf), googleSignInAccount.zzahh());
        zzx(zzy("googleSignInOptions", zzahf), googleSignInOptions.zzahg());
    }

    public GoogleSignInAccount zzaic() {
        return zzga(zzgc("defaultGoogleSignInAccount"));
    }

    public GoogleSignInOptions zzaid() {
        return zzgb(zzgc("defaultGoogleSignInAccount"));
    }

    public void zzaie() {
        String zzgc = zzgc("defaultGoogleSignInAccount");
        zzge("defaultGoogleSignInAccount");
        zzgd(zzgc);
    }

    public void zzb(GoogleSignInAccount googleSignInAccount, GoogleSignInOptions googleSignInOptions) {
        zzac.zzy(googleSignInAccount);
        zzac.zzy(googleSignInOptions);
        zzx("defaultGoogleSignInAccount", googleSignInAccount.zzahf());
        zza(googleSignInAccount, googleSignInOptions);
    }

    /* access modifiers changed from: package-private */
    public GoogleSignInAccount zzga(String str) {
        String zzgc;
        if (TextUtils.isEmpty(str) || (zzgc = zzgc(zzy("googleSignInAccount", str))) == null) {
            return null;
        }
        try {
            return GoogleSignInAccount.zzfw(zzgc);
        } catch (JSONException e) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public GoogleSignInOptions zzgb(String str) {
        String zzgc;
        if (TextUtils.isEmpty(str) || (zzgc = zzgc(zzy("googleSignInOptions", str))) == null) {
            return null;
        }
        try {
            return GoogleSignInOptions.zzfy(zzgc);
        } catch (JSONException e) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public String zzgc(String str) {
        this.f74hK.lock();
        try {
            return this.f75hL.getString(str, (String) null);
        } finally {
            this.f74hK.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public void zzgd(String str) {
        if (!TextUtils.isEmpty(str)) {
            zzge(zzy("googleSignInAccount", str));
            zzge(zzy("googleSignInOptions", str));
        }
    }

    /* access modifiers changed from: protected */
    public void zzge(String str) {
        this.f74hK.lock();
        try {
            this.f75hL.edit().remove(str).apply();
        } finally {
            this.f74hK.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public void zzx(String str, String str2) {
        this.f74hK.lock();
        try {
            this.f75hL.edit().putString(str, str2).apply();
        } finally {
            this.f74hK.unlock();
        }
    }
}
