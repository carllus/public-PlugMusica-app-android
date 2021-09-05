package com.google.android.gms.common.api;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Pair;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzai;
import com.google.android.gms.common.internal.zzh;
import com.google.android.gms.internal.zzpz;
import com.google.android.gms.internal.zzqc;
import com.google.android.gms.internal.zzqt;
import com.google.android.gms.internal.zzqu;
import com.google.android.gms.internal.zzre;
import com.google.android.gms.internal.zzro;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class zzd<O extends Api.ApiOptions> {
    private final Context mContext;
    private final int mId;

    /* renamed from: tv */
    private final Api<O> f176tv;

    /* renamed from: vA */
    private final AtomicBoolean f177vA;

    /* renamed from: vB */
    private final AtomicInteger f178vB;

    /* renamed from: vC */
    private Api.zze f179vC;

    /* renamed from: vv */
    private final zzre f180vv;

    /* renamed from: vw */
    private final O f181vw;

    /* renamed from: vx */
    private final zzpz<O> f182vx;

    /* renamed from: vy */
    private final zzqt f183vy;

    /* renamed from: vz */
    private final GoogleApiClient f184vz;
    private final Looper zzajn;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public zzd(@NonNull Context context, Api<O> api, O o) {
        this(context, api, o, Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper());
    }

    public zzd(@NonNull Context context, Api<O> api, O o, Looper looper) {
        this.f177vA = new AtomicBoolean(false);
        this.f178vB = new AtomicInteger(0);
        zzac.zzb(context, (Object) "Null context is not permitted.");
        zzac.zzb(api, (Object) "Api must not be null.");
        zzac.zzb(looper, (Object) "Looper must not be null.");
        this.mContext = context.getApplicationContext();
        this.f176tv = api;
        this.f181vw = o;
        this.zzajn = looper;
        this.f180vv = new zzre();
        this.f182vx = zzpz.zza(this.f176tv, this.f181vw);
        this.f184vz = new zzqu(this);
        Pair<zzqt, Integer> zza = zzqt.zza(this.mContext, (zzd<?>) this);
        this.f183vy = (zzqt) zza.first;
        this.mId = ((Integer) zza.second).intValue();
    }

    private <A extends Api.zzb, T extends zzqc.zza<? extends Result, A>> T zza(int i, @NonNull T t) {
        t.zzaqt();
        this.f183vy.zza(this, i, (zzqc.zza<? extends Result, Api.zzb>) t);
        return t;
    }

    private <TResult, A extends Api.zzb> Task<TResult> zza(int i, @NonNull zzro<A, TResult> zzro) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.f183vy.zza(this, i, zzro, taskCompletionSource);
        return taskCompletionSource.getTask();
    }

    public int getInstanceId() {
        return this.mId;
    }

    public Looper getLooper() {
        return this.zzajn;
    }

    public void release() {
        boolean z = true;
        if (!this.f177vA.getAndSet(true)) {
            this.f180vv.release();
            zzqt zzqt = this.f183vy;
            int i = this.mId;
            if (this.f178vB.get() <= 0) {
                z = false;
            }
            zzqt.zzd(i, z);
        }
    }

    @WorkerThread
    public Api.zze zza(Looper looper, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        if (!zzapw()) {
            if (this.f176tv.zzapq()) {
                Api.zzh zzapo = this.f176tv.zzapo();
                this.f179vC = new zzai(this.mContext, looper, zzapo.zzapt(), connectionCallbacks, onConnectionFailedListener, zzh.zzcd(this.mContext), zzapo.zzr(this.f181vw));
            } else {
                this.f179vC = this.f176tv.zzapn().zza(this.mContext, looper, zzh.zzcd(this.mContext), this.f181vw, connectionCallbacks, onConnectionFailedListener);
            }
        }
        return this.f179vC;
    }

    public <A extends Api.zzb, T extends zzqc.zza<? extends Result, A>> T zza(@NonNull T t) {
        return zza(0, t);
    }

    public <TResult, A extends Api.zzb> Task<TResult> zza(zzro<A, TResult> zzro) {
        return zza(0, zzro);
    }

    public void zzapu() {
        this.f178vB.incrementAndGet();
    }

    public void zzapv() {
        if (this.f178vB.decrementAndGet() == 0 && this.f177vA.get()) {
            this.f183vy.zzd(this.mId, false);
        }
    }

    public boolean zzapw() {
        return this.f179vC != null;
    }

    public zzpz<O> zzapx() {
        return this.f182vx;
    }

    public GoogleApiClient zzapy() {
        return this.f184vz;
    }

    public <A extends Api.zzb, T extends zzqc.zza<? extends Result, A>> T zzb(@NonNull T t) {
        return zza(1, t);
    }

    public <TResult, A extends Api.zzb> Task<TResult> zzb(zzro<A, TResult> zzro) {
        return zza(1, zzro);
    }
}
