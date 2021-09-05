package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.view.View;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzxa;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class zzh {

    /* renamed from: BX */
    private final Set<Scope> f342BX;

    /* renamed from: BY */
    private final Map<Api<?>, zza> f343BY;

    /* renamed from: BZ */
    private final zzxa f344BZ;

    /* renamed from: Ca */
    private Integer f345Ca;

    /* renamed from: ec */
    private final Account f346ec;

    /* renamed from: fo */
    private final String f347fo;

    /* renamed from: vF */
    private final Set<Scope> f348vF;

    /* renamed from: vH */
    private final int f349vH;

    /* renamed from: vI */
    private final View f350vI;

    /* renamed from: vJ */
    private final String f351vJ;

    public static final class zza {

        /* renamed from: Cb */
        public final boolean f352Cb;

        /* renamed from: hm */
        public final Set<Scope> f353hm;

        public zza(Set<Scope> set, boolean z) {
            zzac.zzy(set);
            this.f353hm = Collections.unmodifiableSet(set);
            this.f352Cb = z;
        }
    }

    public zzh(Account account, Set<Scope> set, Map<Api<?>, zza> map, int i, View view, String str, String str2, zzxa zzxa) {
        this.f346ec = account;
        this.f348vF = set == null ? Collections.EMPTY_SET : Collections.unmodifiableSet(set);
        this.f343BY = map == null ? Collections.EMPTY_MAP : map;
        this.f350vI = view;
        this.f349vH = i;
        this.f347fo = str;
        this.f351vJ = str2;
        this.f344BZ = zzxa;
        HashSet hashSet = new HashSet(this.f348vF);
        for (zza zza2 : this.f343BY.values()) {
            hashSet.addAll(zza2.f353hm);
        }
        this.f342BX = Collections.unmodifiableSet(hashSet);
    }

    public static zzh zzcd(Context context) {
        return new GoogleApiClient.Builder(context).zzaqd();
    }

    public Account getAccount() {
        return this.f346ec;
    }

    @Deprecated
    public String getAccountName() {
        if (this.f346ec != null) {
            return this.f346ec.name;
        }
        return null;
    }

    public Account zzatv() {
        return this.f346ec != null ? this.f346ec : new Account("<<default account>>", "com.google");
    }

    public int zzauf() {
        return this.f349vH;
    }

    public Set<Scope> zzaug() {
        return this.f348vF;
    }

    public Set<Scope> zzauh() {
        return this.f342BX;
    }

    public Map<Api<?>, zza> zzaui() {
        return this.f343BY;
    }

    public String zzauj() {
        return this.f347fo;
    }

    public String zzauk() {
        return this.f351vJ;
    }

    public View zzaul() {
        return this.f350vI;
    }

    public zzxa zzaum() {
        return this.f344BZ;
    }

    public Integer zzaun() {
        return this.f345Ca;
    }

    public Set<Scope> zzb(Api<?> api) {
        zza zza2 = this.f343BY.get(api);
        if (zza2 == null || zza2.f353hm.isEmpty()) {
            return this.f348vF;
        }
        HashSet hashSet = new HashSet(this.f348vF);
        hashSet.addAll(zza2.f353hm);
        return hashSet;
    }

    public void zzc(Integer num) {
        this.f345Ca = num;
    }
}
