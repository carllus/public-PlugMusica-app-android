package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.p000v4.app.FragmentActivity;
import android.util.Log;
import com.google.android.gms.auth.api.signin.internal.zzk;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzh;
import com.google.android.gms.common.internal.zzm;
import com.google.android.gms.internal.zzqc;
import com.google.android.gms.internal.zzqv;
import com.google.android.gms.internal.zzqy;
import com.google.android.gms.internal.zzrq;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;

public final class zzqp extends GoogleApiClient implements zzqy.zza {
    /* access modifiers changed from: private */
    public final Context mContext;

    /* renamed from: vN */
    private final int f690vN;

    /* renamed from: vP */
    private final GoogleApiAvailability f691vP;

    /* renamed from: vQ */
    final Api.zza<? extends zzwz, zzxa> f692vQ;

    /* renamed from: xB */
    final zzh f693xB;

    /* renamed from: xC */
    final Map<Api<?>, Integer> f694xC;

    /* renamed from: xO */
    private final zzm f695xO;

    /* renamed from: xP */
    private zzqy f696xP = null;

    /* renamed from: xQ */
    final Queue<zzqc.zza<?, ?>> f697xQ = new LinkedList();

    /* renamed from: xR */
    private volatile boolean f698xR;

    /* renamed from: xS */
    private long f699xS = 120000;

    /* renamed from: xT */
    private long f700xT = 5000;

    /* renamed from: xU */
    private final zza f701xU;

    /* renamed from: xV */
    zzqv f702xV;

    /* renamed from: xW */
    final Map<Api.zzc<?>, Api.zze> f703xW;

    /* renamed from: xX */
    Set<Scope> f704xX = new HashSet();

    /* renamed from: xY */
    private final zzre f705xY = new zzre();

    /* renamed from: xZ */
    private final ArrayList<zzqf> f706xZ;

    /* renamed from: xf */
    private final Lock f707xf;

    /* renamed from: ya */
    private Integer f708ya = null;

    /* renamed from: yb */
    Set<zzrp> f709yb = null;

    /* renamed from: yc */
    final zzrq f710yc;

    /* renamed from: yd */
    private final zzm.zza f711yd = new zzm.zza() {
        public boolean isConnected() {
            return zzqp.this.isConnected();
        }

        public Bundle zzaoe() {
            return null;
        }
    };
    private final Looper zzajn;

    final class zza extends Handler {
        zza(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    zzqp.this.zzarr();
                    return;
                case 2:
                    zzqp.this.resume();
                    return;
                default:
                    Log.w("GoogleApiClientImpl", new StringBuilder(31).append("Unknown message id: ").append(message.what).toString());
                    return;
            }
        }
    }

    static class zzb extends zzqv.zza {

        /* renamed from: yi */
        private WeakReference<zzqp> f723yi;

        zzb(zzqp zzqp) {
            this.f723yi = new WeakReference<>(zzqp);
        }

        public void zzaqp() {
            zzqp zzqp = (zzqp) this.f723yi.get();
            if (zzqp != null) {
                zzqp.resume();
            }
        }
    }

    public zzqp(Context context, Lock lock, Looper looper, zzh zzh, GoogleApiAvailability googleApiAvailability, Api.zza<? extends zzwz, zzxa> zza2, Map<Api<?>, Integer> map, List<GoogleApiClient.ConnectionCallbacks> list, List<GoogleApiClient.OnConnectionFailedListener> list2, Map<Api.zzc<?>, Api.zze> map2, int i, int i2, ArrayList<zzqf> arrayList) {
        this.mContext = context;
        this.f707xf = lock;
        this.f695xO = new zzm(looper, this.f711yd);
        this.zzajn = looper;
        this.f701xU = new zza(looper);
        this.f691vP = googleApiAvailability;
        this.f690vN = i;
        if (this.f690vN >= 0) {
            this.f708ya = Integer.valueOf(i2);
        }
        this.f694xC = map;
        this.f703xW = map2;
        this.f706xZ = arrayList;
        this.f710yc = new zzrq(this.f703xW);
        for (GoogleApiClient.ConnectionCallbacks registerConnectionCallbacks : list) {
            this.f695xO.registerConnectionCallbacks(registerConnectionCallbacks);
        }
        for (GoogleApiClient.OnConnectionFailedListener registerConnectionFailedListener : list2) {
            this.f695xO.registerConnectionFailedListener(registerConnectionFailedListener);
        }
        this.f693xB = zzh;
        this.f692vQ = zza2;
    }

    /* access modifiers changed from: private */
    public void resume() {
        this.f707xf.lock();
        try {
            if (isResuming()) {
                zzarq();
            }
        } finally {
            this.f707xf.unlock();
        }
    }

    public static int zza(Iterable<Api.zze> iterable, boolean z) {
        boolean z2 = false;
        boolean z3 = false;
        for (Api.zze next : iterable) {
            if (next.zzahd()) {
                z3 = true;
            }
            z2 = next.zzahs() ? true : z2;
        }
        if (z3) {
            return (!z2 || !z) ? 1 : 2;
        }
        return 3;
    }

    /* access modifiers changed from: private */
    public void zza(final GoogleApiClient googleApiClient, final zzrm zzrm, final boolean z) {
        zzrx.f853Dh.zzg(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            /* renamed from: zzp */
            public void onResult(@NonNull Status status) {
                zzk.zzbd(zzqp.this.mContext).zzaie();
                if (status.isSuccess() && zzqp.this.isConnected()) {
                    zzqp.this.reconnect();
                }
                zzrm.zzc(status);
                if (z) {
                    googleApiClient.disconnect();
                }
            }
        });
    }

    private void zzarq() {
        this.f695xO.zzauu();
        this.f696xP.connect();
    }

    /* access modifiers changed from: private */
    public void zzarr() {
        this.f707xf.lock();
        try {
            if (zzart()) {
                zzarq();
            }
        } finally {
            this.f707xf.unlock();
        }
    }

    private void zzb(@NonNull zzqz zzqz) {
        if (this.f690vN >= 0) {
            zzqa.zza(zzqz).zzfq(this.f690vN);
            return;
        }
        throw new IllegalStateException("Called stopAutoManage but automatic lifecycle management is not enabled.");
    }

    private void zzft(int i) {
        if (this.f708ya == null) {
            this.f708ya = Integer.valueOf(i);
        } else if (this.f708ya.intValue() != i) {
            String valueOf = String.valueOf(zzfu(i));
            String valueOf2 = String.valueOf(zzfu(this.f708ya.intValue()));
            throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 51 + String.valueOf(valueOf2).length()).append("Cannot use sign-in mode: ").append(valueOf).append(". Mode was already set to ").append(valueOf2).toString());
        }
        if (this.f696xP == null) {
            boolean z = false;
            boolean z2 = false;
            for (Api.zze next : this.f703xW.values()) {
                if (next.zzahd()) {
                    z2 = true;
                }
                z = next.zzahs() ? true : z;
            }
            switch (this.f708ya.intValue()) {
                case 1:
                    if (!z2) {
                        throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
                    } else if (z) {
                        throw new IllegalStateException("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead.");
                    }
                    break;
                case 2:
                    if (z2) {
                        this.f696xP = zzqh.zza(this.mContext, this, this.f707xf, this.zzajn, this.f691vP, this.f703xW, this.f693xB, this.f694xC, this.f692vQ, this.f706xZ);
                        return;
                    }
                    break;
            }
            this.f696xP = new zzqr(this.mContext, this, this.f707xf, this.zzajn, this.f691vP, this.f703xW, this.f693xB, this.f694xC, this.f692vQ, this.f706xZ, this);
        }
    }

    static String zzfu(int i) {
        switch (i) {
            case 1:
                return "SIGN_IN_MODE_REQUIRED";
            case 2:
                return "SIGN_IN_MODE_OPTIONAL";
            case 3:
                return "SIGN_IN_MODE_NONE";
            default:
                return "UNKNOWN";
        }
    }

    public ConnectionResult blockingConnect() {
        boolean z = true;
        zzac.zza(Looper.myLooper() != Looper.getMainLooper(), (Object) "blockingConnect must not be called on the UI thread");
        this.f707xf.lock();
        try {
            if (this.f690vN >= 0) {
                if (this.f708ya == null) {
                    z = false;
                }
                zzac.zza(z, (Object) "Sign-in mode should have been set explicitly by auto-manage.");
            } else if (this.f708ya == null) {
                this.f708ya = Integer.valueOf(zza(this.f703xW.values(), false));
            } else if (this.f708ya.intValue() == 2) {
                throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            zzft(this.f708ya.intValue());
            this.f695xO.zzauu();
            return this.f696xP.blockingConnect();
        } finally {
            this.f707xf.unlock();
        }
    }

    public ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit) {
        boolean z = false;
        if (Looper.myLooper() != Looper.getMainLooper()) {
            z = true;
        }
        zzac.zza(z, (Object) "blockingConnect must not be called on the UI thread");
        zzac.zzb(timeUnit, (Object) "TimeUnit must not be null");
        this.f707xf.lock();
        try {
            if (this.f708ya == null) {
                this.f708ya = Integer.valueOf(zza(this.f703xW.values(), false));
            } else if (this.f708ya.intValue() == 2) {
                throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            zzft(this.f708ya.intValue());
            this.f695xO.zzauu();
            return this.f696xP.blockingConnect(j, timeUnit);
        } finally {
            this.f707xf.unlock();
        }
    }

    public PendingResult<Status> clearDefaultAccountAndReconnect() {
        zzac.zza(isConnected(), (Object) "GoogleApiClient is not connected yet.");
        zzac.zza(this.f708ya.intValue() != 2, (Object) "Cannot use clearDefaultAccountAndReconnect with GOOGLE_SIGN_IN_API");
        final zzrm zzrm = new zzrm((GoogleApiClient) this);
        if (this.f703xW.containsKey(zzrx.f854fa)) {
            zza(this, zzrm, false);
        } else {
            final AtomicReference atomicReference = new AtomicReference();
            GoogleApiClient build = new GoogleApiClient.Builder(this.mContext).addApi(zzrx.API).addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                public void onConnected(Bundle bundle) {
                    zzqp.this.zza((GoogleApiClient) atomicReference.get(), zzrm, true);
                }

                public void onConnectionSuspended(int i) {
                }
            }).addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                    zzrm.zzc(new Status(8));
                }
            }).setHandler(this.f701xU).build();
            atomicReference.set(build);
            build.connect();
        }
        return zzrm;
    }

    public void connect() {
        boolean z = false;
        this.f707xf.lock();
        try {
            if (this.f690vN >= 0) {
                if (this.f708ya != null) {
                    z = true;
                }
                zzac.zza(z, (Object) "Sign-in mode should have been set explicitly by auto-manage.");
            } else if (this.f708ya == null) {
                this.f708ya = Integer.valueOf(zza(this.f703xW.values(), false));
            } else if (this.f708ya.intValue() == 2) {
                throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            connect(this.f708ya.intValue());
        } finally {
            this.f707xf.unlock();
        }
    }

    public void connect(int i) {
        boolean z = true;
        this.f707xf.lock();
        if (!(i == 3 || i == 1 || i == 2)) {
            z = false;
        }
        try {
            zzac.zzb(z, (Object) new StringBuilder(33).append("Illegal sign-in mode: ").append(i).toString());
            zzft(i);
            zzarq();
        } finally {
            this.f707xf.unlock();
        }
    }

    public void disconnect() {
        this.f707xf.lock();
        try {
            this.f710yc.release();
            if (this.f696xP != null) {
                this.f696xP.disconnect();
            }
            this.f705xY.release();
            for (zzqc.zza zza2 : this.f697xQ) {
                zza2.zza((zzrq.zzb) null);
                zza2.cancel();
            }
            this.f697xQ.clear();
            if (this.f696xP != null) {
                zzart();
                this.f695xO.zzaut();
                this.f707xf.unlock();
            }
        } finally {
            this.f707xf.unlock();
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append(str).append("mContext=").println(this.mContext);
        printWriter.append(str).append("mResuming=").print(this.f698xR);
        printWriter.append(" mWorkQueue.size()=").print(this.f697xQ.size());
        this.f710yc.dump(printWriter);
        if (this.f696xP != null) {
            this.f696xP.dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    @NonNull
    public ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        this.f707xf.lock();
        try {
            if (!isConnected() && !isResuming()) {
                throw new IllegalStateException("Cannot invoke getConnectionResult unless GoogleApiClient is connected");
            } else if (this.f703xW.containsKey(api.zzapp())) {
                ConnectionResult connectionResult = this.f696xP.getConnectionResult(api);
                if (connectionResult != null) {
                    this.f707xf.unlock();
                } else if (isResuming()) {
                    connectionResult = ConnectionResult.f117uJ;
                } else {
                    Log.w("GoogleApiClientImpl", zzarv());
                    Log.wtf("GoogleApiClientImpl", String.valueOf(api.getName()).concat(" requested in getConnectionResult is not connected but is not present in the failed  connections map"), new Exception());
                    connectionResult = new ConnectionResult(8, (PendingIntent) null);
                    this.f707xf.unlock();
                }
                return connectionResult;
            } else {
                throw new IllegalArgumentException(String.valueOf(api.getName()).concat(" was never registered with GoogleApiClient"));
            }
        } finally {
            this.f707xf.unlock();
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    public Looper getLooper() {
        return this.zzajn;
    }

    public int getSessionId() {
        return System.identityHashCode(this);
    }

    public boolean hasConnectedApi(@NonNull Api<?> api) {
        Api.zze zze = this.f703xW.get(api.zzapp());
        return zze != null && zze.isConnected();
    }

    public boolean isConnected() {
        return this.f696xP != null && this.f696xP.isConnected();
    }

    public boolean isConnecting() {
        return this.f696xP != null && this.f696xP.isConnecting();
    }

    public boolean isConnectionCallbacksRegistered(@NonNull GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        return this.f695xO.isConnectionCallbacksRegistered(connectionCallbacks);
    }

    public boolean isConnectionFailedListenerRegistered(@NonNull GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return this.f695xO.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }

    /* access modifiers changed from: package-private */
    public boolean isResuming() {
        return this.f698xR;
    }

    public void reconnect() {
        disconnect();
        connect();
    }

    public void registerConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.f695xO.registerConnectionCallbacks(connectionCallbacks);
    }

    public void registerConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.f695xO.registerConnectionFailedListener(onConnectionFailedListener);
    }

    public void stopAutoManage(@NonNull FragmentActivity fragmentActivity) {
        zzb(new zzqz(fragmentActivity));
    }

    public void unregisterConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.f695xO.unregisterConnectionCallbacks(connectionCallbacks);
    }

    public void unregisterConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.f695xO.unregisterConnectionFailedListener(onConnectionFailedListener);
    }

    @NonNull
    public <C extends Api.zze> C zza(@NonNull Api.zzc<C> zzc) {
        C c = (Api.zze) this.f703xW.get(zzc);
        zzac.zzb(c, (Object) "Appropriate Api was not requested.");
        return c;
    }

    public void zza(zzrp zzrp) {
        this.f707xf.lock();
        try {
            if (this.f709yb == null) {
                this.f709yb = new HashSet();
            }
            this.f709yb.add(zzrp);
        } finally {
            this.f707xf.unlock();
        }
    }

    public boolean zza(@NonNull Api<?> api) {
        return this.f703xW.containsKey(api.zzapp());
    }

    public boolean zza(zzrl zzrl) {
        return this.f696xP != null && this.f696xP.zza(zzrl);
    }

    public void zzaqb() {
        if (this.f696xP != null) {
            this.f696xP.zzaqb();
        }
    }

    /* access modifiers changed from: package-private */
    public void zzars() {
        if (!isResuming()) {
            this.f698xR = true;
            if (this.f702xV == null) {
                this.f702xV = this.f691vP.zza(this.mContext.getApplicationContext(), (zzqv.zza) new zzb(this));
            }
            this.f701xU.sendMessageDelayed(this.f701xU.obtainMessage(1), this.f699xS);
            this.f701xU.sendMessageDelayed(this.f701xU.obtainMessage(2), this.f700xT);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zzart() {
        if (!isResuming()) {
            return false;
        }
        this.f698xR = false;
        this.f701xU.removeMessages(2);
        this.f701xU.removeMessages(1);
        if (this.f702xV != null) {
            this.f702xV.unregister();
            this.f702xV = null;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean zzaru() {
        boolean z = false;
        this.f707xf.lock();
        try {
            if (this.f709yb != null) {
                if (!this.f709yb.isEmpty()) {
                    z = true;
                }
                this.f707xf.unlock();
            }
            return z;
        } finally {
            this.f707xf.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public String zzarv() {
        StringWriter stringWriter = new StringWriter();
        dump("", (FileDescriptor) null, new PrintWriter(stringWriter), (String[]) null);
        return stringWriter.toString();
    }

    /* access modifiers changed from: package-private */
    public <C extends Api.zze> C zzb(Api.zzc<?> zzc) {
        C c = (Api.zze) this.f703xW.get(zzc);
        zzac.zzb(c, (Object) "Appropriate Api was not requested.");
        return c;
    }

    public void zzb(zzrp zzrp) {
        this.f707xf.lock();
        try {
            if (this.f709yb == null) {
                Log.wtf("GoogleApiClientImpl", "Attempted to remove pending transform when no transforms are registered.", new Exception());
            } else if (!this.f709yb.remove(zzrp)) {
                Log.wtf("GoogleApiClientImpl", "Failed to remove pending transform - this may lead to memory leaks!", new Exception());
            } else if (!zzaru()) {
                this.f696xP.zzaqy();
            }
        } finally {
            this.f707xf.unlock();
        }
    }

    public <A extends Api.zzb, R extends Result, T extends zzqc.zza<R, A>> T zzc(@NonNull T t) {
        zzac.zzb(t.zzapp() != null, (Object) "This task can not be enqueued (it's probably a Batch or malformed)");
        boolean containsKey = this.f703xW.containsKey(t.zzapp());
        String name = t.zzaqn() != null ? t.zzaqn().getName() : "the API";
        zzac.zzb(containsKey, (Object) new StringBuilder(String.valueOf(name).length() + 65).append("GoogleApiClient is not configured to use ").append(name).append(" required for this call.").toString());
        this.f707xf.lock();
        try {
            if (this.f696xP == null) {
                this.f697xQ.add(t);
            } else {
                t = this.f696xP.zzc(t);
                this.f707xf.unlock();
            }
            return t;
        } finally {
            this.f707xf.unlock();
        }
    }

    public void zzc(int i, boolean z) {
        if (i == 1 && !z) {
            zzars();
        }
        this.f710yc.zzasw();
        this.f695xO.zzgo(i);
        this.f695xO.zzaut();
        if (i == 2) {
            zzarq();
        }
    }

    public <A extends Api.zzb, T extends zzqc.zza<? extends Result, A>> T zzd(@NonNull T t) {
        zzac.zzb(t.zzapp() != null, (Object) "This task can not be executed (it's probably a Batch or malformed)");
        boolean containsKey = this.f703xW.containsKey(t.zzapp());
        String name = t.zzaqn() != null ? t.zzaqn().getName() : "the API";
        zzac.zzb(containsKey, (Object) new StringBuilder(String.valueOf(name).length() + 65).append("GoogleApiClient is not configured to use ").append(name).append(" required for this call.").toString());
        this.f707xf.lock();
        try {
            if (this.f696xP == null) {
                throw new IllegalStateException("GoogleApiClient is not connected yet.");
            }
            if (isResuming()) {
                this.f697xQ.add(t);
                while (!this.f697xQ.isEmpty()) {
                    zzqc.zza remove = this.f697xQ.remove();
                    this.f710yc.zzb((zzqe<? extends Result>) remove);
                    remove.zzz(Status.f165wa);
                }
            } else {
                t = this.f696xP.zzd(t);
                this.f707xf.unlock();
            }
            return t;
        } finally {
            this.f707xf.unlock();
        }
    }

    public void zzd(ConnectionResult connectionResult) {
        if (!this.f691vP.zzd(this.mContext, connectionResult.getErrorCode())) {
            zzart();
        }
        if (!isResuming()) {
            this.f695xO.zzn(connectionResult);
            this.f695xO.zzaut();
        }
    }

    public void zzn(Bundle bundle) {
        while (!this.f697xQ.isEmpty()) {
            zzd(this.f697xQ.remove());
        }
        this.f695xO.zzp(bundle);
    }

    public <L> zzrd<L> zzs(@NonNull L l) {
        this.f707xf.lock();
        try {
            return this.f705xY.zzb(l, this.zzajn);
        } finally {
            this.f707xf.unlock();
        }
    }
}
