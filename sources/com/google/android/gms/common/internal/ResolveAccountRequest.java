package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class ResolveAccountRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ResolveAccountRequest> CREATOR = new zzad();

    /* renamed from: CV */
    private final int f263CV;

    /* renamed from: CW */
    private final GoogleSignInAccount f264CW;

    /* renamed from: ec */
    private final Account f265ec;
    final int mVersionCode;

    ResolveAccountRequest(int i, Account account, int i2, GoogleSignInAccount googleSignInAccount) {
        this.mVersionCode = i;
        this.f265ec = account;
        this.f263CV = i2;
        this.f264CW = googleSignInAccount;
    }

    public ResolveAccountRequest(Account account, int i, GoogleSignInAccount googleSignInAccount) {
        this(2, account, i, googleSignInAccount);
    }

    public Account getAccount() {
        return this.f265ec;
    }

    public int getSessionId() {
        return this.f263CV;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzad.zza(this, parcel, i);
    }

    @Nullable
    public GoogleSignInAccount zzavc() {
        return this.f264CW;
    }
}
