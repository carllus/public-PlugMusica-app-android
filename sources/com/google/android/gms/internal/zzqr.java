package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzh;
import com.google.android.gms.common.zzc;
import com.google.android.gms.internal.zzqc;
import com.google.android.gms.internal.zzqy;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class zzqr implements zzqg, zzqy {
    private final Context mContext;

    /* renamed from: vQ */
    final Api.zza<? extends zzwz, zzxa> f724vQ;

    /* renamed from: wV */
    final zzqp f725wV;

    /* renamed from: xB */
    final zzh f726xB;

    /* renamed from: xC */
    final Map<Api<?>, Integer> f727xC;

    /* renamed from: xW */
    final Map<Api.zzc<?>, Api.zze> f728xW;
    /* access modifiers changed from: private */

    /* renamed from: xf */
    public final Lock f729xf;

    /* renamed from: xn */
    private final zzc f730xn;

    /* renamed from: yj */
    private final Condition f731yj;

    /* renamed from: yk */
    private final zzb f732yk;

    /* renamed from: yl */
    final Map<Api.zzc<?>, ConnectionResult> f733yl = new HashMap();
    /* access modifiers changed from: private */

    /* renamed from: ym */
    public volatile zzqq f734ym;

    /* renamed from: yn */
    private ConnectionResult f735yn = null;

    /* renamed from: yo */
    int f736yo;

    /* renamed from: yp */
    final zzqy.zza f737yp;

    static abstract class zza {

        /* renamed from: yq */
        private final zzqq f738yq;

        protected zza(zzqq zzqq) {
            this.f738yq = zzqq;
        }

        /* access modifiers changed from: protected */
        public abstract void zzari();

        public final void zzc(zzqr zzqr) {
            zzqr.f729xf.lock();
            try {
                if (zzqr.f734ym == this.f738yq) {
                    zzari();
                    zzqr.f729xf.unlock();
                }
            } finally {
                zzqr.f729xf.unlock();
            }
        }
    }

    final class zzb extends Handler {
        zzb(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    ((zza) message.obj).zzc(zzqr.this);
                    return;
                case 2:
                    throw ((RuntimeException) message.obj);
                default:
                    Log.w("GACStateManager", new StringBuilder(31).append("Unknown message id: ").append(message.what).toString());
                    return;
            }
        }
    }

    public zzqr(Context context, zzqp zzqp, Lock lock, Looper looper, zzc zzc, Map<Api.zzc<?>, Api.zze> map, zzh zzh, Map<Api<?>, Integer> map2, Api.zza<? extends zzwz, zzxa> zza2, ArrayList<zzqf> arrayList, zzqy.zza zza3) {
        this.mContext = context;
        this.f729xf = lock;
        this.f730xn = zzc;
        this.f728xW = map;
        this.f726xB = zzh;
        this.f727xC = map2;
        this.f724vQ = zza2;
        this.f725wV = zzqp;
        this.f737yp = zza3;
        Iterator<zzqf> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().zza(this);
        }
        this.f732yk = new zzb(looper);
        this.f731yj = lock.newCondition();
        this.f734ym = new zzqo(this);
    }

    public ConnectionResult blockingConnect() {
        connect();
        while (isConnecting()) {
            try {
                this.f731yj.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, (PendingIntent) null);
            }
        }
        return isConnected() ? ConnectionResult.f117uJ : this.f735yn != null ? this.f735yn : new ConnectionResult(13, (PendingIntent) null);
    }

    public ConnectionResult blockingConnect(long j, TimeUnit timeUnit) {
        connect();
        long nanos = timeUnit.toNanos(j);
        while (isConnecting()) {
            if (nanos <= 0) {
                try {
                    disconnect();
                    return new ConnectionResult(14, (PendingIntent) null);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return new ConnectionResult(15, (PendingIntent) null);
                }
            } else {
                nanos = this.f731yj.awaitNanos(nanos);
            }
        }
        return isConnected() ? ConnectionResult.f117uJ : this.f735yn != null ? this.f735yn : new ConnectionResult(13, (PendingIntent) null);
    }

    public void connect() {
        this.f734ym.connect();
    }

    public void disconnect() {
        if (this.f734ym.disconnect()) {
            this.f733yl.clear();
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String concat = String.valueOf(str).concat("  ");
        printWriter.append(str).append("mState=").println(this.f734ym);
        for (Api next : this.f727xC.keySet()) {
            printWriter.append(str).append(next.getName()).println(":");
            this.f728xW.get(next.zzapp()).dump(concat, fileDescriptor, printWriter, strArr);
        }
    }

    @Nullable
    public ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        Api.zzc<?> zzapp = api.zzapp();
        if (this.f728xW.containsKey(zzapp)) {
            if (this.f728xW.get(zzapp).isConnected()) {
                return ConnectionResult.f117uJ;
            }
            if (this.f733yl.containsKey(zzapp)) {
                return this.f733yl.get(zzapp);
            }
        }
        return null;
    }

    public boolean isConnected() {
        return this.f734ym instanceof zzqm;
    }

    public boolean isConnecting() {
        return this.f734ym instanceof zzqn;
    }

    public void onConnected(@Nullable Bundle bundle) {
        this.f729xf.lock();
        try {
            this.f734ym.onConnected(bundle);
        } finally {
            this.f729xf.unlock();
        }
    }

    public void onConnectionSuspended(int i) {
        this.f729xf.lock();
        try {
            this.f734ym.onConnectionSuspended(i);
        } finally {
            this.f729xf.unlock();
        }
    }

    public void zza(@NonNull ConnectionResult connectionResult, @NonNull Api<?> api, int i) {
        this.f729xf.lock();
        try {
            this.f734ym.zza(connectionResult, api, i);
        } finally {
            this.f729xf.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(zza zza2) {
        this.f732yk.sendMessage(this.f732yk.obtainMessage(1, zza2));
    }

    /* access modifiers changed from: package-private */
    public void zza(RuntimeException runtimeException) {
        this.f732yk.sendMessage(this.f732yk.obtainMessage(2, runtimeException));
    }

    public boolean zza(zzrl zzrl) {
        return false;
    }

    public void zzaqb() {
    }

    public void zzaqy() {
        if (isConnected()) {
            ((zzqm) this.f734ym).zzarh();
        }
    }

    /* access modifiers changed from: package-private */
    public void zzarw() {
        this.f729xf.lock();
        try {
            this.f734ym = new zzqn(this, this.f726xB, this.f727xC, this.f730xn, this.f724vQ, this.f729xf, this.mContext);
            this.f734ym.begin();
            this.f731yj.signalAll();
        } finally {
            this.f729xf.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public void zzarx() {
        this.f729xf.lock();
        try {
            this.f725wV.zzart();
            this.f734ym = new zzqm(this);
            this.f734ym.begin();
            this.f731yj.signalAll();
        } finally {
            this.f729xf.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public void zzary() {
        for (Api.zze disconnect : this.f728xW.values()) {
            disconnect.disconnect();
        }
    }

    public <A extends Api.zzb, R extends Result, T extends zzqc.zza<R, A>> T zzc(@NonNull T t) {
        t.zzaqt();
        return this.f734ym.zzc(t);
    }

    public <A extends Api.zzb, T extends zzqc.zza<? extends Result, A>> T zzd(@NonNull T t) {
        t.zzaqt();
        return this.f734ym.zzd(t);
    }

    /* access modifiers changed from: package-private */
    public void zzi(ConnectionResult connectionResult) {
        this.f729xf.lock();
        try {
            this.f735yn = connectionResult;
            this.f734ym = new zzqo(this);
            this.f734ym.begin();
            this.f731yj.signalAll();
        } finally {
            this.f729xf.unlock();
        }
    }
}
