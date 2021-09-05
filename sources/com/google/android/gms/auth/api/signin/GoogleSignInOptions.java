package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.zze;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzac;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleSignInOptions extends AbstractSafeParcelable implements Api.ApiOptions.Optional, ReflectedParcelable {
    public static final Parcelable.Creator<GoogleSignInOptions> CREATOR = new zzb();
    public static final GoogleSignInOptions DEFAULT_SIGN_IN = new Builder().requestId().requestProfile().build();

    /* renamed from: hc */
    private static Comparator<Scope> f52hc = new Comparator<Scope>() {
        /* renamed from: zza */
        public int compare(Scope scope, Scope scope2) {
            return scope.zzaqg().compareTo(scope2.zzaqg());
        }
    };

    /* renamed from: hd */
    public static final Scope f53hd = new Scope(Scopes.PROFILE);

    /* renamed from: he */
    public static final Scope f54he = new Scope("email");

    /* renamed from: hf */
    public static final Scope f55hf = new Scope("openid");
    /* access modifiers changed from: private */

    /* renamed from: ec */
    public Account f56ec;
    /* access modifiers changed from: private */

    /* renamed from: hg */
    public final ArrayList<Scope> f57hg;
    /* access modifiers changed from: private */

    /* renamed from: hh */
    public boolean f58hh;
    /* access modifiers changed from: private */

    /* renamed from: hi */
    public final boolean f59hi;
    /* access modifiers changed from: private */

    /* renamed from: hj */
    public final boolean f60hj;
    /* access modifiers changed from: private */

    /* renamed from: hk */
    public String f61hk;
    /* access modifiers changed from: private */

    /* renamed from: hl */
    public String f62hl;
    final int versionCode;

    public static final class Builder {

        /* renamed from: ec */
        private Account f63ec;

        /* renamed from: hh */
        private boolean f64hh;

        /* renamed from: hi */
        private boolean f65hi;

        /* renamed from: hj */
        private boolean f66hj;

        /* renamed from: hk */
        private String f67hk;

        /* renamed from: hl */
        private String f68hl;

        /* renamed from: hm */
        private Set<Scope> f69hm = new HashSet();

        public Builder() {
        }

        public Builder(@NonNull GoogleSignInOptions googleSignInOptions) {
            zzac.zzy(googleSignInOptions);
            this.f69hm = new HashSet(googleSignInOptions.f57hg);
            this.f65hi = googleSignInOptions.f59hi;
            this.f66hj = googleSignInOptions.f60hj;
            this.f64hh = googleSignInOptions.f58hh;
            this.f67hk = googleSignInOptions.f61hk;
            this.f63ec = googleSignInOptions.f56ec;
            this.f68hl = googleSignInOptions.f62hl;
        }

        private String zzfz(String str) {
            zzac.zzhz(str);
            zzac.zzb(this.f67hk == null || this.f67hk.equals(str), (Object) "two different server client ids provided");
            return str;
        }

        public GoogleSignInOptions build() {
            if (this.f64hh && (this.f63ec == null || !this.f69hm.isEmpty())) {
                requestId();
            }
            return new GoogleSignInOptions((Set) this.f69hm, this.f63ec, this.f64hh, this.f65hi, this.f66hj, this.f67hk, this.f68hl);
        }

        public Builder requestEmail() {
            this.f69hm.add(GoogleSignInOptions.f54he);
            return this;
        }

        public Builder requestId() {
            this.f69hm.add(GoogleSignInOptions.f55hf);
            return this;
        }

        public Builder requestIdToken(String str) {
            this.f64hh = true;
            this.f67hk = zzfz(str);
            return this;
        }

        public Builder requestProfile() {
            this.f69hm.add(GoogleSignInOptions.f53hd);
            return this;
        }

        public Builder requestScopes(Scope scope, Scope... scopeArr) {
            this.f69hm.add(scope);
            this.f69hm.addAll(Arrays.asList(scopeArr));
            return this;
        }

        public Builder requestServerAuthCode(String str) {
            return requestServerAuthCode(str, false);
        }

        public Builder requestServerAuthCode(String str, boolean z) {
            this.f65hi = true;
            this.f67hk = zzfz(str);
            this.f66hj = z;
            return this;
        }

        public Builder setAccountName(String str) {
            this.f63ec = new Account(zzac.zzhz(str), "com.google");
            return this;
        }

        public Builder setHostedDomain(String str) {
            this.f68hl = zzac.zzhz(str);
            return this;
        }
    }

    GoogleSignInOptions(int i, ArrayList<Scope> arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2) {
        this.versionCode = i;
        this.f57hg = arrayList;
        this.f56ec = account;
        this.f58hh = z;
        this.f59hi = z2;
        this.f60hj = z3;
        this.f61hk = str;
        this.f62hl = str2;
    }

    private GoogleSignInOptions(Set<Scope> set, Account account, boolean z, boolean z2, boolean z3, String str, String str2) {
        this(2, (ArrayList<Scope>) new ArrayList(set), account, z, z2, z3, str, str2);
    }

    private JSONObject zzahi() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            Collections.sort(this.f57hg, f52hc);
            Iterator<Scope> it = this.f57hg.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().zzaqg());
            }
            jSONObject.put("scopes", jSONArray);
            if (this.f56ec != null) {
                jSONObject.put("accountName", this.f56ec.name);
            }
            jSONObject.put("idTokenRequested", this.f58hh);
            jSONObject.put("forceCodeForRefreshToken", this.f60hj);
            jSONObject.put("serverAuthRequested", this.f59hi);
            if (!TextUtils.isEmpty(this.f61hk)) {
                jSONObject.put("serverClientId", this.f61hk);
            }
            if (!TextUtils.isEmpty(this.f62hl)) {
                jSONObject.put("hostedDomain", this.f62hl);
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    public static GoogleSignInOptions zzfy(@Nullable String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("scopes");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            hashSet.add(new Scope(jSONArray.getString(i)));
        }
        String optString = jSONObject.optString("accountName", (String) null);
        return new GoogleSignInOptions(hashSet, !TextUtils.isEmpty(optString) ? new Account(optString, "com.google") : null, jSONObject.getBoolean("idTokenRequested"), jSONObject.getBoolean("serverAuthRequested"), jSONObject.getBoolean("forceCodeForRefreshToken"), jSONObject.optString("serverClientId", (String) null), jSONObject.optString("hostedDomain", (String) null));
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            GoogleSignInOptions googleSignInOptions = (GoogleSignInOptions) obj;
            if (this.f57hg.size() != googleSignInOptions.zzahj().size() || !this.f57hg.containsAll(googleSignInOptions.zzahj())) {
                return false;
            }
            if (this.f56ec == null) {
                if (googleSignInOptions.getAccount() != null) {
                    return false;
                }
            } else if (!this.f56ec.equals(googleSignInOptions.getAccount())) {
                return false;
            }
            if (TextUtils.isEmpty(this.f61hk)) {
                if (!TextUtils.isEmpty(googleSignInOptions.zzahn())) {
                    return false;
                }
            } else if (!this.f61hk.equals(googleSignInOptions.zzahn())) {
                return false;
            }
            return this.f60hj == googleSignInOptions.zzahm() && this.f58hh == googleSignInOptions.zzahk() && this.f59hi == googleSignInOptions.zzahl();
        } catch (ClassCastException e) {
            return false;
        }
    }

    public Account getAccount() {
        return this.f56ec;
    }

    public Scope[] getScopeArray() {
        return (Scope[]) this.f57hg.toArray(new Scope[this.f57hg.size()]);
    }

    public int hashCode() {
        ArrayList arrayList = new ArrayList();
        Iterator<Scope> it = this.f57hg.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().zzaqg());
        }
        Collections.sort(arrayList);
        return new zze().zzq(arrayList).zzq(this.f56ec).zzq(this.f61hk).zzbd(this.f60hj).zzbd(this.f58hh).zzbd(this.f59hi).zzahv();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }

    public String zzahg() {
        return zzahi().toString();
    }

    public ArrayList<Scope> zzahj() {
        return new ArrayList<>(this.f57hg);
    }

    public boolean zzahk() {
        return this.f58hh;
    }

    public boolean zzahl() {
        return this.f59hi;
    }

    public boolean zzahm() {
        return this.f60hj;
    }

    public String zzahn() {
        return this.f61hk;
    }

    public String zzaho() {
        return this.f62hl;
    }
}
