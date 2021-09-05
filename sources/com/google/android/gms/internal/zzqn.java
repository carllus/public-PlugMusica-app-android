package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zze;
import com.google.android.gms.common.internal.zzh;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzqc;
import com.google.android.gms.internal.zzqr;
import com.google.android.gms.signin.internal.SignInResponse;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;

public class zzqn implements zzqq {
    /* access modifiers changed from: private */
    public final Context mContext;

    /* renamed from: vQ */
    private final Api.zza<? extends zzwz, zzxa> f651vQ;

    /* renamed from: xA */
    private boolean f652xA;

    /* renamed from: xB */
    private final zzh f653xB;

    /* renamed from: xC */
    private final Map<Api<?>, Integer> f654xC;

    /* renamed from: xD */
    private ArrayList<Future<?>> f655xD = new ArrayList<>();
    /* access modifiers changed from: private */

    /* renamed from: xf */
    public final Lock f656xf;
    /* access modifiers changed from: private */

    /* renamed from: xk */
    public final zzqr f657xk;
    /* access modifiers changed from: private */

    /* renamed from: xn */
    public final com.google.android.gms.common.zzc f658xn;

    /* renamed from: xo */
    private ConnectionResult f659xo;

    /* renamed from: xp */
    private int f660xp;

    /* renamed from: xq */
    private int f661xq = 0;

    /* renamed from: xr */
    private int f662xr;

    /* renamed from: xs */
    private final Bundle f663xs = new Bundle();

    /* renamed from: xt */
    private final Set<Api.zzc> f664xt = new HashSet();
    /* access modifiers changed from: private */

    /* renamed from: xu */
    public zzwz f665xu;

    /* renamed from: xv */
    private int f666xv;
    /* access modifiers changed from: private */

    /* renamed from: xw */
    public boolean f667xw;

    /* renamed from: xx */
    private boolean f668xx;
    /* access modifiers changed from: private */

    /* renamed from: xy */
    public zzr f669xy;

    /* renamed from: xz */
    private boolean f670xz;

    private static class zza implements zze.zzf {

        /* renamed from: tv */
        private final Api<?> f672tv;
        /* access modifiers changed from: private */

        /* renamed from: wT */
        public final int f673wT;

        /* renamed from: xF */
        private final WeakReference<zzqn> f674xF;

        public zza(zzqn zzqn, Api<?> api, int i) {
            this.f674xF = new WeakReference<>(zzqn);
            this.f672tv = api;
            this.f673wT = i;
        }

        public void zzh(@NonNull ConnectionResult connectionResult) {
            boolean z = false;
            zzqn zzqn = (zzqn) this.f674xF.get();
            if (zzqn != null) {
                if (Looper.myLooper() == zzqn.f657xk.f725wV.getLooper()) {
                    z = true;
                }
                zzac.zza(z, (Object) "onReportServiceBinding must be called on the GoogleApiClient handler thread");
                zzqn.f656xf.lock();
                try {
                    if (zzqn.zzfr(0)) {
                        if (!connectionResult.isSuccess()) {
                            zzqn.zzb(connectionResult, this.f672tv, this.f673wT);
                        }
                        if (zzqn.zzarj()) {
                            zzqn.zzark();
                        }
                        zzqn.f656xf.unlock();
                    }
                } finally {
                    zzqn.f656xf.unlock();
                }
            }
        }
    }

    private class zzb extends zzf {

        /* renamed from: xG */
        private final Map<Api.zze, zza> f676xG;

        public zzb(Map<Api.zze, zza> map) {
            super();
            this.f676xG = map;
        }

        @WorkerThread
        public void zzari() {
            boolean z;
            boolean z2;
            boolean z3;
            boolean z4 = true;
            int i = 0;
            Iterator<Api.zze> it = this.f676xG.keySet().iterator();
            boolean z5 = true;
            boolean z6 = false;
            while (true) {
                if (!it.hasNext()) {
                    z4 = z6;
                    z = false;
                    break;
                }
                Api.zze next = it.next();
                if (!next.zzapr()) {
                    z3 = false;
                    z2 = z6;
                } else if (this.f676xG.get(next).f673wT == 0) {
                    z = true;
                    break;
                } else {
                    z3 = z5;
                    z2 = true;
                }
                z6 = z2;
                z5 = z3;
            }
            if (z4) {
                i = zzqn.this.f658xn.isGooglePlayServicesAvailable(zzqn.this.mContext);
            }
            if (i == 0 || (!z && !z5)) {
                if (zzqn.this.f667xw) {
                    zzqn.this.f665xu.connect();
                }
                for (Api.zze next2 : this.f676xG.keySet()) {
                    final zze.zzf zzf = this.f676xG.get(next2);
                    if (!next2.zzapr() || i == 0) {
                        next2.zza(zzf);
                    } else {
                        zzqn.this.f657xk.zza((zzqr.zza) new zzqr.zza(zzqn.this) {
                            public void zzari() {
                                zzf.zzh(new ConnectionResult(16, (PendingIntent) null));
                            }
                        });
                    }
                }
                return;
            }
            final ConnectionResult connectionResult = new ConnectionResult(i, (PendingIntent) null);
            zzqn.this.f657xk.zza((zzqr.zza) new zzqr.zza(zzqn.this) {
                public void zzari() {
                    zzqn.this.zzg(connectionResult);
                }
            });
        }
    }

    private class zzc extends zzf {

        /* renamed from: xK */
        private final ArrayList<Api.zze> f682xK;

        public zzc(ArrayList<Api.zze> arrayList) {
            super();
            this.f682xK = arrayList;
        }

        @WorkerThread
        public void zzari() {
            zzqn.this.f657xk.f725wV.f704xX = zzqn.this.zzarp();
            Iterator<Api.zze> it = this.f682xK.iterator();
            while (it.hasNext()) {
                it.next().zza(zzqn.this.f669xy, zzqn.this.f657xk.f725wV.f704xX);
            }
        }
    }

    private static class zzd extends com.google.android.gms.signin.internal.zzb {

        /* renamed from: xF */
        private final WeakReference<zzqn> f683xF;

        zzd(zzqn zzqn) {
            this.f683xF = new WeakReference<>(zzqn);
        }

        @BinderThread
        public void zzb(final SignInResponse signInResponse) {
            final zzqn zzqn = (zzqn) this.f683xF.get();
            if (zzqn != null) {
                zzqn.f657xk.zza((zzqr.zza) new zzqr.zza(zzqn) {
                    public void zzari() {
                        zzqn.zza(signInResponse);
                    }
                });
            }
        }
    }

    private class zze implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
        private zze() {
        }

        public void onConnected(Bundle bundle) {
            zzqn.this.f665xu.zza(new zzd(zzqn.this));
        }

        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            zzqn.this.f656xf.lock();
            try {
                if (zzqn.this.zzf(connectionResult)) {
                    zzqn.this.zzarn();
                    zzqn.this.zzark();
                } else {
                    zzqn.this.zzg(connectionResult);
                }
            } finally {
                zzqn.this.f656xf.unlock();
            }
        }

        public void onConnectionSuspended(int i) {
        }
    }

    private abstract class zzf implements Runnable {
        private zzf() {
        }

        @WorkerThread
        public void run() {
            zzqn.this.f656xf.lock();
            try {
                if (!Thread.interrupted()) {
                    zzari();
                    zzqn.this.f656xf.unlock();
                }
            } catch (RuntimeException e) {
                zzqn.this.f657xk.zza(e);
            } finally {
                zzqn.this.f656xf.unlock();
            }
        }

        /* access modifiers changed from: protected */
        @WorkerThread
        public abstract void zzari();
    }

    public zzqn(zzqr zzqr, zzh zzh, Map<Api<?>, Integer> map, com.google.android.gms.common.zzc zzc2, Api.zza<? extends zzwz, zzxa> zza2, Lock lock, Context context) {
        this.f657xk = zzqr;
        this.f653xB = zzh;
        this.f654xC = map;
        this.f658xn = zzc2;
        this.f651vQ = zza2;
        this.f656xf = lock;
        this.mContext = context;
    }

    /* access modifiers changed from: private */
    public void zza(SignInResponse signInResponse) {
        if (zzfr(0)) {
            ConnectionResult zzave = signInResponse.zzave();
            if (zzave.isSuccess()) {
                ResolveAccountResponse zzcdl = signInResponse.zzcdl();
                ConnectionResult zzave2 = zzcdl.zzave();
                if (!zzave2.isSuccess()) {
                    String valueOf = String.valueOf(zzave2);
                    Log.wtf("GoogleApiClientConnecting", new StringBuilder(String.valueOf(valueOf).length() + 48).append("Sign-in succeeded with resolve account failure: ").append(valueOf).toString(), new Exception());
                    zzg(zzave2);
                    return;
                }
                this.f668xx = true;
                this.f669xy = zzcdl.zzavd();
                this.f670xz = zzcdl.zzavf();
                this.f652xA = zzcdl.zzavg();
                zzark();
            } else if (zzf(zzave)) {
                zzarn();
                zzark();
            } else {
                zzg(zzave);
            }
        }
    }

    private boolean zza(int i, int i2, ConnectionResult connectionResult) {
        if (i2 != 1 || zze(connectionResult)) {
            return this.f659xo == null || i < this.f660xp;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public boolean zzarj() {
        this.f662xr--;
        if (this.f662xr > 0) {
            return false;
        }
        if (this.f662xr < 0) {
            Log.w("GoogleApiClientConnecting", this.f657xk.f725wV.zzarv());
            Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
            zzg(new ConnectionResult(8, (PendingIntent) null));
            return false;
        } else if (this.f659xo == null) {
            return true;
        } else {
            this.f657xk.f736yo = this.f660xp;
            zzg(this.f659xo);
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void zzark() {
        if (this.f662xr == 0) {
            if (!this.f667xw || this.f668xx) {
                zzarl();
            }
        }
    }

    private void zzarl() {
        ArrayList arrayList = new ArrayList();
        this.f661xq = 1;
        this.f662xr = this.f657xk.f728xW.size();
        for (Api.zzc next : this.f657xk.f728xW.keySet()) {
            if (!this.f657xk.f733yl.containsKey(next)) {
                arrayList.add(this.f657xk.f728xW.get(next));
            } else if (zzarj()) {
                zzarm();
            }
        }
        if (!arrayList.isEmpty()) {
            this.f655xD.add(zzqs.zzarz().submit(new zzc(arrayList)));
        }
    }

    private void zzarm() {
        this.f657xk.zzarx();
        zzqs.zzarz().execute(new Runnable() {
            public void run() {
                zzqn.this.f658xn.zzbq(zzqn.this.mContext);
            }
        });
        if (this.f665xu != null) {
            if (this.f670xz) {
                this.f665xu.zza(this.f669xy, this.f652xA);
            }
            zzbq(false);
        }
        for (Api.zzc<?> zzc2 : this.f657xk.f733yl.keySet()) {
            this.f657xk.f728xW.get(zzc2).disconnect();
        }
        this.f657xk.f737yp.zzn(this.f663xs.isEmpty() ? null : this.f663xs);
    }

    /* access modifiers changed from: private */
    public void zzarn() {
        this.f667xw = false;
        this.f657xk.f725wV.f704xX = Collections.emptySet();
        for (Api.zzc next : this.f664xt) {
            if (!this.f657xk.f733yl.containsKey(next)) {
                this.f657xk.f733yl.put(next, new ConnectionResult(17, (PendingIntent) null));
            }
        }
    }

    private void zzaro() {
        Iterator<Future<?>> it = this.f655xD.iterator();
        while (it.hasNext()) {
            it.next().cancel(true);
        }
        this.f655xD.clear();
    }

    /* access modifiers changed from: private */
    public Set<Scope> zzarp() {
        if (this.f653xB == null) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet(this.f653xB.zzaug());
        Map<Api<?>, zzh.zza> zzaui = this.f653xB.zzaui();
        for (Api next : zzaui.keySet()) {
            if (!this.f657xk.f733yl.containsKey(next.zzapp())) {
                hashSet.addAll(zzaui.get(next).f353hm);
            }
        }
        return hashSet;
    }

    /* access modifiers changed from: private */
    public void zzb(ConnectionResult connectionResult, Api<?> api, int i) {
        if (i != 2) {
            int priority = api.zzapm().getPriority();
            if (zza(priority, i, connectionResult)) {
                this.f659xo = connectionResult;
                this.f660xp = priority;
            }
        }
        this.f657xk.f733yl.put(api.zzapp(), connectionResult);
    }

    private void zzbq(boolean z) {
        if (this.f665xu != null) {
            if (this.f665xu.isConnected() && z) {
                this.f665xu.zzcda();
            }
            this.f665xu.disconnect();
            this.f669xy = null;
        }
    }

    private boolean zze(ConnectionResult connectionResult) {
        return connectionResult.hasResolution() || this.f658xn.zzfl(connectionResult.getErrorCode()) != null;
    }

    /* access modifiers changed from: private */
    public boolean zzf(ConnectionResult connectionResult) {
        if (this.f666xv != 2) {
            return this.f666xv == 1 && !connectionResult.hasResolution();
        }
        return true;
    }

    /* access modifiers changed from: private */
    public boolean zzfr(int i) {
        if (this.f661xq == i) {
            return true;
        }
        Log.w("GoogleApiClientConnecting", this.f657xk.f725wV.zzarv());
        String valueOf = String.valueOf(this);
        Log.w("GoogleApiClientConnecting", new StringBuilder(String.valueOf(valueOf).length() + 23).append("Unexpected callback in ").append(valueOf).toString());
        Log.w("GoogleApiClientConnecting", new StringBuilder(33).append("mRemainingConnections=").append(this.f662xr).toString());
        String valueOf2 = String.valueOf(zzfs(this.f661xq));
        String valueOf3 = String.valueOf(zzfs(i));
        Log.wtf("GoogleApiClientConnecting", new StringBuilder(String.valueOf(valueOf2).length() + 70 + String.valueOf(valueOf3).length()).append("GoogleApiClient connecting is in step ").append(valueOf2).append(" but received callback for step ").append(valueOf3).toString(), new Exception());
        zzg(new ConnectionResult(8, (PendingIntent) null));
        return false;
    }

    private String zzfs(int i) {
        switch (i) {
            case 0:
                return "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
            case 1:
                return "STEP_GETTING_REMOTE_SERVICE";
            default:
                return "UNKNOWN";
        }
    }

    /* access modifiers changed from: private */
    public void zzg(ConnectionResult connectionResult) {
        zzaro();
        zzbq(!connectionResult.hasResolution());
        this.f657xk.zzi(connectionResult);
        this.f657xk.f737yp.zzd(connectionResult);
    }

    public void begin() {
        this.f657xk.f733yl.clear();
        this.f667xw = false;
        this.f659xo = null;
        this.f661xq = 0;
        this.f666xv = 2;
        this.f668xx = false;
        this.f670xz = false;
        HashMap hashMap = new HashMap();
        boolean z = false;
        for (Api next : this.f654xC.keySet()) {
            Api.zze zze2 = this.f657xk.f728xW.get(next.zzapp());
            int intValue = this.f654xC.get(next).intValue();
            boolean z2 = (next.zzapm().getPriority() == 1) | z;
            if (zze2.zzahd()) {
                this.f667xw = true;
                if (intValue < this.f666xv) {
                    this.f666xv = intValue;
                }
                if (intValue != 0) {
                    this.f664xt.add(next.zzapp());
                }
            }
            hashMap.put(zze2, new zza(this, next, intValue));
            z = z2;
        }
        if (z) {
            this.f667xw = false;
        }
        if (this.f667xw) {
            this.f653xB.zzc(Integer.valueOf(this.f657xk.f725wV.getSessionId()));
            zze zze3 = new zze();
            this.f665xu = (zzwz) this.f651vQ.zza(this.mContext, this.f657xk.f725wV.getLooper(), this.f653xB, this.f653xB.zzaum(), zze3, zze3);
        }
        this.f662xr = this.f657xk.f728xW.size();
        this.f655xD.add(zzqs.zzarz().submit(new zzb(hashMap)));
    }

    public void connect() {
    }

    public boolean disconnect() {
        zzaro();
        zzbq(true);
        this.f657xk.zzi((ConnectionResult) null);
        return true;
    }

    public void onConnected(Bundle bundle) {
        if (zzfr(1)) {
            if (bundle != null) {
                this.f663xs.putAll(bundle);
            }
            if (zzarj()) {
                zzarm();
            }
        }
    }

    public void onConnectionSuspended(int i) {
        zzg(new ConnectionResult(8, (PendingIntent) null));
    }

    public void zza(ConnectionResult connectionResult, Api<?> api, int i) {
        if (zzfr(1)) {
            zzb(connectionResult, api, i);
            if (zzarj()) {
                zzarm();
            }
        }
    }

    public <A extends Api.zzb, R extends Result, T extends zzqc.zza<R, A>> T zzc(T t) {
        this.f657xk.f725wV.f697xQ.add(t);
        return t;
    }

    public <A extends Api.zzb, T extends zzqc.zza<? extends Result, A>> T zzd(T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
}
