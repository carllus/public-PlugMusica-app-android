package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzab;

public final class Status extends AbstractSafeParcelable implements Result, ReflectedParcelable {
    public static final Parcelable.Creator<Status> CREATOR = new zzh();

    /* renamed from: vY */
    public static final Status f163vY = new Status(0);

    /* renamed from: vZ */
    public static final Status f164vZ = new Status(14);

    /* renamed from: wa */
    public static final Status f165wa = new Status(8);

    /* renamed from: wb */
    public static final Status f166wb = new Status(15);

    /* renamed from: wc */
    public static final Status f167wc = new Status(16);

    /* renamed from: wd */
    public static final Status f168wd = new Status(17);

    /* renamed from: we */
    public static final Status f169we = new Status(18);
    private final PendingIntent mPendingIntent;
    private final int mVersionCode;

    /* renamed from: rR */
    private final int f170rR;

    /* renamed from: uK */
    private final String f171uK;

    public Status(int i) {
        this(i, (String) null);
    }

    Status(int i, int i2, String str, PendingIntent pendingIntent) {
        this.mVersionCode = i;
        this.f170rR = i2;
        this.f171uK = str;
        this.mPendingIntent = pendingIntent;
    }

    public Status(int i, String str) {
        this(1, i, str, (PendingIntent) null);
    }

    public Status(int i, String str, PendingIntent pendingIntent) {
        this(1, i, str, pendingIntent);
    }

    private String zzaqi() {
        return this.f171uK != null ? this.f171uK : CommonStatusCodes.getStatusCodeString(this.f170rR);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Status)) {
            return false;
        }
        Status status = (Status) obj;
        return this.mVersionCode == status.mVersionCode && this.f170rR == status.f170rR && zzab.equal(this.f171uK, status.f171uK) && zzab.equal(this.mPendingIntent, status.mPendingIntent);
    }

    public PendingIntent getResolution() {
        return this.mPendingIntent;
    }

    public Status getStatus() {
        return this;
    }

    public int getStatusCode() {
        return this.f170rR;
    }

    @Nullable
    public String getStatusMessage() {
        return this.f171uK;
    }

    /* access modifiers changed from: package-private */
    public int getVersionCode() {
        return this.mVersionCode;
    }

    public boolean hasResolution() {
        return this.mPendingIntent != null;
    }

    public int hashCode() {
        return zzab.hashCode(Integer.valueOf(this.mVersionCode), Integer.valueOf(this.f170rR), this.f171uK, this.mPendingIntent);
    }

    public boolean isCanceled() {
        return this.f170rR == 16;
    }

    public boolean isInterrupted() {
        return this.f170rR == 14;
    }

    public boolean isSuccess() {
        return this.f170rR <= 0;
    }

    public void startResolutionForResult(Activity activity, int i) throws IntentSender.SendIntentException {
        if (hasResolution()) {
            activity.startIntentSenderForResult(this.mPendingIntent.getIntentSender(), i, (Intent) null, 0, 0, 0);
        }
    }

    public String toString() {
        return zzab.zzx(this).zzg("statusCode", zzaqi()).zzg("resolution", this.mPendingIntent).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzh.zza(this, parcel, i);
    }

    /* access modifiers changed from: package-private */
    public PendingIntent zzaqh() {
        return this.mPendingIntent;
    }
}
