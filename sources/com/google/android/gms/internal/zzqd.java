package com.google.android.gms.internal;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.internal.zzqv;

public abstract class zzqd extends zzra implements DialogInterface.OnCancelListener {
    protected boolean mStarted;

    /* renamed from: vP */
    protected final GoogleApiAvailability f602vP;
    /* access modifiers changed from: private */

    /* renamed from: wA */
    public int f603wA;

    /* renamed from: wB */
    private final Handler f604wB;

    /* renamed from: wy */
    protected boolean f605wy;
    /* access modifiers changed from: private */

    /* renamed from: wz */
    public ConnectionResult f606wz;

    private class zza implements Runnable {
        private zza() {
        }

        @MainThread
        public void run() {
            if (zzqd.this.mStarted) {
                if (zzqd.this.f606wz.hasResolution()) {
                    zzqd.this.f786yY.startActivityForResult(GoogleApiActivity.zzb(zzqd.this.getActivity(), zzqd.this.f606wz.getResolution(), zzqd.this.f603wA, false), 1);
                } else if (zzqd.this.f602vP.isUserResolvableError(zzqd.this.f606wz.getErrorCode())) {
                    zzqd.this.f602vP.zza(zzqd.this.getActivity(), zzqd.this.f786yY, zzqd.this.f606wz.getErrorCode(), 2, zzqd.this);
                } else if (zzqd.this.f606wz.getErrorCode() == 18) {
                    final Dialog zza = zzqd.this.f602vP.zza(zzqd.this.getActivity(), (DialogInterface.OnCancelListener) zzqd.this);
                    zzqd.this.f602vP.zza(zzqd.this.getActivity().getApplicationContext(), (zzqv.zza) new zzqv.zza() {
                        public void zzaqp() {
                            zzqd.this.zzaqo();
                            if (zza.isShowing()) {
                                zza.dismiss();
                            }
                        }
                    });
                } else {
                    zzqd.this.zza(zzqd.this.f606wz, zzqd.this.f603wA);
                }
            }
        }
    }

    protected zzqd(zzrb zzrb) {
        this(zzrb, GoogleApiAvailability.getInstance());
    }

    zzqd(zzrb zzrb, GoogleApiAvailability googleApiAvailability) {
        super(zzrb);
        this.f603wA = -1;
        this.f604wB = new Handler(Looper.getMainLooper());
        this.f602vP = googleApiAvailability;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onActivityResult(int r6, int r7, android.content.Intent r8) {
        /*
            r5 = this;
            r4 = 18
            r2 = 13
            r0 = 1
            r1 = 0
            switch(r6) {
                case 1: goto L_0x0027;
                case 2: goto L_0x0010;
                default: goto L_0x0009;
            }
        L_0x0009:
            r0 = r1
        L_0x000a:
            if (r0 == 0) goto L_0x003d
            r5.zzaqo()
        L_0x000f:
            return
        L_0x0010:
            com.google.android.gms.common.GoogleApiAvailability r2 = r5.f602vP
            android.app.Activity r3 = r5.getActivity()
            int r2 = r2.isGooglePlayServicesAvailable(r3)
            if (r2 != 0) goto L_0x0047
        L_0x001c:
            com.google.android.gms.common.ConnectionResult r1 = r5.f606wz
            int r1 = r1.getErrorCode()
            if (r1 != r4) goto L_0x000a
            if (r2 != r4) goto L_0x000a
            goto L_0x000f
        L_0x0027:
            r3 = -1
            if (r7 == r3) goto L_0x000a
            if (r7 != 0) goto L_0x0009
            if (r8 == 0) goto L_0x0045
            java.lang.String r0 = "<<ResolutionFailureErrorDetail>>"
            int r0 = r8.getIntExtra(r0, r2)
        L_0x0034:
            com.google.android.gms.common.ConnectionResult r2 = new com.google.android.gms.common.ConnectionResult
            r3 = 0
            r2.<init>(r0, r3)
            r5.f606wz = r2
            goto L_0x0009
        L_0x003d:
            com.google.android.gms.common.ConnectionResult r0 = r5.f606wz
            int r1 = r5.f603wA
            r5.zza(r0, r1)
            goto L_0x000f
        L_0x0045:
            r0 = r2
            goto L_0x0034
        L_0x0047:
            r0 = r1
            goto L_0x001c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzqd.onActivityResult(int, int, android.content.Intent):void");
    }

    public void onCancel(DialogInterface dialogInterface) {
        zza(new ConnectionResult(13, (PendingIntent) null), this.f603wA);
        zzaqo();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.f605wy = bundle.getBoolean("resolving_error", false);
            if (this.f605wy) {
                this.f603wA = bundle.getInt("failed_client_id", -1);
                this.f606wz = new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution"));
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("resolving_error", this.f605wy);
        if (this.f605wy) {
            bundle.putInt("failed_client_id", this.f603wA);
            bundle.putInt("failed_status", this.f606wz.getErrorCode());
            bundle.putParcelable("failed_resolution", this.f606wz.getResolution());
        }
    }

    public void onStart() {
        super.onStart();
        this.mStarted = true;
    }

    public void onStop() {
        super.onStop();
        this.mStarted = false;
    }

    /* access modifiers changed from: protected */
    public abstract void zza(ConnectionResult connectionResult, int i);

    /* access modifiers changed from: protected */
    public abstract void zzaqk();

    /* access modifiers changed from: protected */
    public void zzaqo() {
        this.f603wA = -1;
        this.f605wy = false;
        this.f606wz = null;
        zzaqk();
    }

    public void zzb(ConnectionResult connectionResult, int i) {
        if (!this.f605wy) {
            this.f605wy = true;
            this.f603wA = i;
            this.f606wz = connectionResult;
            this.f604wB.post(new zza());
        }
    }
}
