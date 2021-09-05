package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzs;
import com.google.android.gms.internal.zzrq;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public abstract class zzqe<R extends Result> extends PendingResult<R> {

    /* renamed from: wF */
    static final ThreadLocal<Boolean> f610wF = new ThreadLocal<Boolean>() {
        /* access modifiers changed from: protected */
        /* renamed from: zzaqv */
        public Boolean initialValue() {
            return false;
        }
    };
    /* access modifiers changed from: private */

    /* renamed from: vU */
    public R f611vU;

    /* renamed from: wG */
    private final Object f612wG;

    /* renamed from: wH */
    protected final zza<R> f613wH;

    /* renamed from: wI */
    protected final WeakReference<GoogleApiClient> f614wI;

    /* renamed from: wJ */
    private final ArrayList<PendingResult.zza> f615wJ;

    /* renamed from: wK */
    private ResultCallback<? super R> f616wK;

    /* renamed from: wL */
    private final AtomicReference<zzrq.zzb> f617wL;

    /* renamed from: wM */
    private zzb f618wM;

    /* renamed from: wN */
    private volatile boolean f619wN;

    /* renamed from: wO */
    private boolean f620wO;

    /* renamed from: wP */
    private zzs f621wP;

    /* renamed from: wQ */
    private volatile zzrp<R> f622wQ;

    /* renamed from: wR */
    private boolean f623wR;
    private boolean zzak;
    private final CountDownLatch zzamx;

    public static class zza<R extends Result> extends Handler {
        public zza() {
            this(Looper.getMainLooper());
        }

        public zza(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Pair pair = (Pair) message.obj;
                    zzb((ResultCallback) pair.first, (Result) pair.second);
                    return;
                case 2:
                    ((zzqe) message.obj).zzaa(Status.f166wb);
                    return;
                default:
                    Log.wtf("BasePendingResult", new StringBuilder(45).append("Don't know how to handle message: ").append(message.what).toString(), new Exception());
                    return;
            }
        }

        public void zza(ResultCallback<? super R> resultCallback, R r) {
            sendMessage(obtainMessage(1, new Pair(resultCallback, r)));
        }

        public void zza(zzqe<R> zzqe, long j) {
            sendMessageDelayed(obtainMessage(2, zzqe), j);
        }

        public void zzaqw() {
            removeMessages(2);
        }

        /* access modifiers changed from: protected */
        public void zzb(ResultCallback<? super R> resultCallback, R r) {
            try {
                resultCallback.onResult(r);
            } catch (RuntimeException e) {
                zzqe.zze(r);
                throw e;
            }
        }
    }

    private final class zzb {
        private zzb() {
        }

        /* access modifiers changed from: protected */
        public void finalize() throws Throwable {
            zzqe.zze(zzqe.this.f611vU);
            super.finalize();
        }
    }

    @Deprecated
    zzqe() {
        this.f612wG = new Object();
        this.zzamx = new CountDownLatch(1);
        this.f615wJ = new ArrayList<>();
        this.f617wL = new AtomicReference<>();
        this.f623wR = false;
        this.f613wH = new zza<>(Looper.getMainLooper());
        this.f614wI = new WeakReference<>((Object) null);
    }

    @Deprecated
    protected zzqe(Looper looper) {
        this.f612wG = new Object();
        this.zzamx = new CountDownLatch(1);
        this.f615wJ = new ArrayList<>();
        this.f617wL = new AtomicReference<>();
        this.f623wR = false;
        this.f613wH = new zza<>(looper);
        this.f614wI = new WeakReference<>((Object) null);
    }

    protected zzqe(GoogleApiClient googleApiClient) {
        this.f612wG = new Object();
        this.zzamx = new CountDownLatch(1);
        this.f615wJ = new ArrayList<>();
        this.f617wL = new AtomicReference<>();
        this.f623wR = false;
        this.f613wH = new zza<>(googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
        this.f614wI = new WeakReference<>(googleApiClient);
    }

    private R get() {
        R r;
        boolean z = true;
        synchronized (this.f612wG) {
            if (this.f619wN) {
                z = false;
            }
            zzac.zza(z, (Object) "Result has already been consumed.");
            zzac.zza(isReady(), (Object) "Result is not ready.");
            r = this.f611vU;
            this.f611vU = null;
            this.f616wK = null;
            this.f619wN = true;
        }
        zzaqr();
        return r;
    }

    private void zzaqr() {
        zzrq.zzb andSet = this.f617wL.getAndSet((Object) null);
        if (andSet != null) {
            andSet.zzc(this);
        }
    }

    private void zzd(R r) {
        this.f611vU = r;
        this.f621wP = null;
        this.zzamx.countDown();
        Status status = this.f611vU.getStatus();
        if (this.zzak) {
            this.f616wK = null;
        } else if (this.f616wK != null) {
            this.f613wH.zzaqw();
            this.f613wH.zza(this.f616wK, get());
        } else if (this.f611vU instanceof Releasable) {
            this.f618wM = new zzb();
        }
        Iterator<PendingResult.zza> it = this.f615wJ.iterator();
        while (it.hasNext()) {
            it.next().zzv(status);
        }
        this.f615wJ.clear();
    }

    public static void zze(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e) {
                String valueOf = String.valueOf(result);
                Log.w("BasePendingResult", new StringBuilder(String.valueOf(valueOf).length() + 18).append("Unable to release ").append(valueOf).toString(), e);
            }
        }
    }

    public final R await() {
        boolean z = true;
        zzac.zza(Looper.myLooper() != Looper.getMainLooper(), (Object) "await must not be called on the UI thread");
        zzac.zza(!this.f619wN, (Object) "Result has already been consumed");
        if (this.f622wQ != null) {
            z = false;
        }
        zzac.zza(z, (Object) "Cannot await if then() has been called.");
        try {
            this.zzamx.await();
        } catch (InterruptedException e) {
            zzaa(Status.f164vZ);
        }
        zzac.zza(isReady(), (Object) "Result is not ready.");
        return get();
    }

    public final R await(long j, TimeUnit timeUnit) {
        boolean z = true;
        zzac.zza(j <= 0 || Looper.myLooper() != Looper.getMainLooper(), (Object) "await must not be called on the UI thread when time is greater than zero.");
        zzac.zza(!this.f619wN, (Object) "Result has already been consumed.");
        if (this.f622wQ != null) {
            z = false;
        }
        zzac.zza(z, (Object) "Cannot await if then() has been called.");
        try {
            if (!this.zzamx.await(j, timeUnit)) {
                zzaa(Status.f166wb);
            }
        } catch (InterruptedException e) {
            zzaa(Status.f164vZ);
        }
        zzac.zza(isReady(), (Object) "Result is not ready.");
        return get();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void cancel() {
        /*
            r2 = this;
            java.lang.Object r1 = r2.f612wG
            monitor-enter(r1)
            boolean r0 = r2.zzak     // Catch:{ all -> 0x0029 }
            if (r0 != 0) goto L_0x000b
            boolean r0 = r2.f619wN     // Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x000d
        L_0x000b:
            monitor-exit(r1)     // Catch:{ all -> 0x0029 }
        L_0x000c:
            return
        L_0x000d:
            com.google.android.gms.common.internal.zzs r0 = r2.f621wP     // Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x0016
            com.google.android.gms.common.internal.zzs r0 = r2.f621wP     // Catch:{ RemoteException -> 0x002c }
            r0.cancel()     // Catch:{ RemoteException -> 0x002c }
        L_0x0016:
            R r0 = r2.f611vU     // Catch:{ all -> 0x0029 }
            zze(r0)     // Catch:{ all -> 0x0029 }
            r0 = 1
            r2.zzak = r0     // Catch:{ all -> 0x0029 }
            com.google.android.gms.common.api.Status r0 = com.google.android.gms.common.api.Status.f167wc     // Catch:{ all -> 0x0029 }
            com.google.android.gms.common.api.Result r0 = r2.zzc((com.google.android.gms.common.api.Status) r0)     // Catch:{ all -> 0x0029 }
            r2.zzd(r0)     // Catch:{ all -> 0x0029 }
            monitor-exit(r1)     // Catch:{ all -> 0x0029 }
            goto L_0x000c
        L_0x0029:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0029 }
            throw r0
        L_0x002c:
            r0 = move-exception
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzqe.cancel():void");
    }

    public boolean isCanceled() {
        boolean z;
        synchronized (this.f612wG) {
            z = this.zzak;
        }
        return z;
    }

    public final boolean isReady() {
        return this.zzamx.getCount() == 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setResultCallback(com.google.android.gms.common.api.ResultCallback<? super R> r6) {
        /*
            r5 = this;
            r0 = 1
            r1 = 0
            java.lang.Object r3 = r5.f612wG
            monitor-enter(r3)
            if (r6 != 0) goto L_0x000c
            r0 = 0
            r5.f616wK = r0     // Catch:{ all -> 0x0027 }
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
        L_0x000b:
            return
        L_0x000c:
            boolean r2 = r5.f619wN     // Catch:{ all -> 0x0027 }
            if (r2 != 0) goto L_0x002a
            r2 = r0
        L_0x0011:
            java.lang.String r4 = "Result has already been consumed."
            com.google.android.gms.common.internal.zzac.zza((boolean) r2, (java.lang.Object) r4)     // Catch:{ all -> 0x0027 }
            com.google.android.gms.internal.zzrp<R> r2 = r5.f622wQ     // Catch:{ all -> 0x0027 }
            if (r2 != 0) goto L_0x002c
        L_0x001a:
            java.lang.String r1 = "Cannot set callbacks if then() has been called."
            com.google.android.gms.common.internal.zzac.zza((boolean) r0, (java.lang.Object) r1)     // Catch:{ all -> 0x0027 }
            boolean r0 = r5.isCanceled()     // Catch:{ all -> 0x0027 }
            if (r0 == 0) goto L_0x002e
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            goto L_0x000b
        L_0x0027:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            throw r0
        L_0x002a:
            r2 = r1
            goto L_0x0011
        L_0x002c:
            r0 = r1
            goto L_0x001a
        L_0x002e:
            boolean r0 = r5.isReady()     // Catch:{ all -> 0x0027 }
            if (r0 == 0) goto L_0x003f
            com.google.android.gms.internal.zzqe$zza<R> r0 = r5.f613wH     // Catch:{ all -> 0x0027 }
            com.google.android.gms.common.api.Result r1 = r5.get()     // Catch:{ all -> 0x0027 }
            r0.zza(r6, r1)     // Catch:{ all -> 0x0027 }
        L_0x003d:
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            goto L_0x000b
        L_0x003f:
            r5.f616wK = r6     // Catch:{ all -> 0x0027 }
            goto L_0x003d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzqe.setResultCallback(com.google.android.gms.common.api.ResultCallback):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setResultCallback(com.google.android.gms.common.api.ResultCallback<? super R> r7, long r8, java.util.concurrent.TimeUnit r10) {
        /*
            r6 = this;
            r0 = 1
            r1 = 0
            java.lang.Object r3 = r6.f612wG
            monitor-enter(r3)
            if (r7 != 0) goto L_0x000c
            r0 = 0
            r6.f616wK = r0     // Catch:{ all -> 0x0027 }
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
        L_0x000b:
            return
        L_0x000c:
            boolean r2 = r6.f619wN     // Catch:{ all -> 0x0027 }
            if (r2 != 0) goto L_0x002a
            r2 = r0
        L_0x0011:
            java.lang.String r4 = "Result has already been consumed."
            com.google.android.gms.common.internal.zzac.zza((boolean) r2, (java.lang.Object) r4)     // Catch:{ all -> 0x0027 }
            com.google.android.gms.internal.zzrp<R> r2 = r6.f622wQ     // Catch:{ all -> 0x0027 }
            if (r2 != 0) goto L_0x002c
        L_0x001a:
            java.lang.String r1 = "Cannot set callbacks if then() has been called."
            com.google.android.gms.common.internal.zzac.zza((boolean) r0, (java.lang.Object) r1)     // Catch:{ all -> 0x0027 }
            boolean r0 = r6.isCanceled()     // Catch:{ all -> 0x0027 }
            if (r0 == 0) goto L_0x002e
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            goto L_0x000b
        L_0x0027:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            throw r0
        L_0x002a:
            r2 = r1
            goto L_0x0011
        L_0x002c:
            r0 = r1
            goto L_0x001a
        L_0x002e:
            boolean r0 = r6.isReady()     // Catch:{ all -> 0x0027 }
            if (r0 == 0) goto L_0x003f
            com.google.android.gms.internal.zzqe$zza<R> r0 = r6.f613wH     // Catch:{ all -> 0x0027 }
            com.google.android.gms.common.api.Result r1 = r6.get()     // Catch:{ all -> 0x0027 }
            r0.zza(r7, r1)     // Catch:{ all -> 0x0027 }
        L_0x003d:
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            goto L_0x000b
        L_0x003f:
            r6.f616wK = r7     // Catch:{ all -> 0x0027 }
            com.google.android.gms.internal.zzqe$zza<R> r0 = r6.f613wH     // Catch:{ all -> 0x0027 }
            long r4 = r10.toMillis(r8)     // Catch:{ all -> 0x0027 }
            r0.zza(r6, (long) r4)     // Catch:{ all -> 0x0027 }
            goto L_0x003d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzqe.setResultCallback(com.google.android.gms.common.api.ResultCallback, long, java.util.concurrent.TimeUnit):void");
    }

    public <S extends Result> TransformedResult<S> then(ResultTransform<? super R, ? extends S> resultTransform) {
        TransformedResult<S> then;
        boolean z = true;
        zzac.zza(!this.f619wN, (Object) "Result has already been consumed.");
        synchronized (this.f612wG) {
            zzac.zza(this.f622wQ == null, (Object) "Cannot call then() twice.");
            if (this.f616wK != null) {
                z = false;
            }
            zzac.zza(z, (Object) "Cannot call then() if callbacks are set.");
            this.f623wR = true;
            this.f622wQ = new zzrp<>(this.f614wI);
            then = this.f622wQ.then(resultTransform);
            if (isReady()) {
                this.f613wH.zza(this.f622wQ, get());
            } else {
                this.f616wK = this.f622wQ;
            }
        }
        return then;
    }

    public final void zza(PendingResult.zza zza2) {
        boolean z = true;
        zzac.zza(!this.f619wN, (Object) "Result has already been consumed.");
        if (zza2 == null) {
            z = false;
        }
        zzac.zzb(z, (Object) "Callback cannot be null.");
        synchronized (this.f612wG) {
            if (isReady()) {
                zza2.zzv(this.f611vU.getStatus());
            } else {
                this.f615wJ.add(zza2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zza(zzs zzs) {
        synchronized (this.f612wG) {
            this.f621wP = zzs;
        }
    }

    public void zza(zzrq.zzb zzb2) {
        this.f617wL.set(zzb2);
    }

    public final void zzaa(Status status) {
        synchronized (this.f612wG) {
            if (!isReady()) {
                zzc(zzc(status));
                this.f620wO = true;
            }
        }
    }

    public Integer zzaqf() {
        return null;
    }

    public boolean zzaqq() {
        boolean isCanceled;
        synchronized (this.f612wG) {
            if (((GoogleApiClient) this.f614wI.get()) == null || !this.f623wR) {
                cancel();
            }
            isCanceled = isCanceled();
        }
        return isCanceled;
    }

    public void zzaqs() {
        setResultCallback((ResultCallback) null);
    }

    public void zzaqt() {
        this.f623wR = this.f623wR || f610wF.get().booleanValue();
    }

    /* access modifiers changed from: package-private */
    public boolean zzaqu() {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract R zzc(Status status);

    public final void zzc(R r) {
        boolean z = true;
        synchronized (this.f612wG) {
            if (this.f620wO || this.zzak || (isReady() && zzaqu())) {
                zze(r);
                return;
            }
            zzac.zza(!isReady(), (Object) "Results have already been set");
            if (this.f619wN) {
                z = false;
            }
            zzac.zza(z, (Object) "Result has already been consumed");
            zzd(r);
        }
    }
}
