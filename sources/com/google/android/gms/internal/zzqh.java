package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzh;
import com.google.android.gms.common.zzc;
import com.google.android.gms.internal.zzqc;
import com.google.android.gms.internal.zzqy;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

final class zzqh implements zzqy {
    private final Context mContext;

    /* renamed from: wV */
    private final zzqp f628wV;
    /* access modifiers changed from: private */

    /* renamed from: wW */
    public final zzqr f629wW;
    /* access modifiers changed from: private */

    /* renamed from: wX */
    public final zzqr f630wX;

    /* renamed from: wY */
    private final Map<Api.zzc<?>, zzqr> f631wY;

    /* renamed from: wZ */
    private final Set<zzrl> f632wZ = Collections.newSetFromMap(new WeakHashMap());

    /* renamed from: xa */
    private final Api.zze f633xa;

    /* renamed from: xb */
    private Bundle f634xb;
    /* access modifiers changed from: private */

    /* renamed from: xc */
    public ConnectionResult f635xc = null;
    /* access modifiers changed from: private */

    /* renamed from: xd */
    public ConnectionResult f636xd = null;
    /* access modifiers changed from: private */

    /* renamed from: xe */
    public boolean f637xe = false;
    /* access modifiers changed from: private */

    /* renamed from: xf */
    public final Lock f638xf;

    /* renamed from: xg */
    private int f639xg = 0;
    private final Looper zzajn;

    private class zza implements zzqy.zza {
        private zza() {
        }

        public void zzc(int i, boolean z) {
            zzqh.this.f638xf.lock();
            try {
                if (zzqh.this.f637xe || zzqh.this.f636xd == null || !zzqh.this.f636xd.isSuccess()) {
                    boolean unused = zzqh.this.f637xe = false;
                    zzqh.this.zzb(i, z);
                    return;
                }
                boolean unused2 = zzqh.this.f637xe = true;
                zzqh.this.f630wX.onConnectionSuspended(i);
                zzqh.this.f638xf.unlock();
            } finally {
                zzqh.this.f638xf.unlock();
            }
        }

        public void zzd(@NonNull ConnectionResult connectionResult) {
            zzqh.this.f638xf.lock();
            try {
                ConnectionResult unused = zzqh.this.f635xc = connectionResult;
                zzqh.this.zzarb();
            } finally {
                zzqh.this.f638xf.unlock();
            }
        }

        public void zzn(@Nullable Bundle bundle) {
            zzqh.this.f638xf.lock();
            try {
                zzqh.this.zzm(bundle);
                ConnectionResult unused = zzqh.this.f635xc = ConnectionResult.f117uJ;
                zzqh.this.zzarb();
            } finally {
                zzqh.this.f638xf.unlock();
            }
        }
    }

    private class zzb implements zzqy.zza {
        private zzb() {
        }

        public void zzc(int i, boolean z) {
            zzqh.this.f638xf.lock();
            try {
                if (zzqh.this.f637xe) {
                    boolean unused = zzqh.this.f637xe = false;
                    zzqh.this.zzb(i, z);
                    return;
                }
                boolean unused2 = zzqh.this.f637xe = true;
                zzqh.this.f629wW.onConnectionSuspended(i);
                zzqh.this.f638xf.unlock();
            } finally {
                zzqh.this.f638xf.unlock();
            }
        }

        public void zzd(@NonNull ConnectionResult connectionResult) {
            zzqh.this.f638xf.lock();
            try {
                ConnectionResult unused = zzqh.this.f636xd = connectionResult;
                zzqh.this.zzarb();
            } finally {
                zzqh.this.f638xf.unlock();
            }
        }

        public void zzn(@Nullable Bundle bundle) {
            zzqh.this.f638xf.lock();
            try {
                ConnectionResult unused = zzqh.this.f636xd = ConnectionResult.f117uJ;
                zzqh.this.zzarb();
            } finally {
                zzqh.this.f638xf.unlock();
            }
        }
    }

    private zzqh(Context context, zzqp zzqp, Lock lock, Looper looper, zzc zzc, Map<Api.zzc<?>, Api.zze> map, Map<Api.zzc<?>, Api.zze> map2, zzh zzh, Api.zza<? extends zzwz, zzxa> zza2, Api.zze zze, ArrayList<zzqf> arrayList, ArrayList<zzqf> arrayList2, Map<Api<?>, Integer> map3, Map<Api<?>, Integer> map4) {
        this.mContext = context;
        this.f628wV = zzqp;
        this.f638xf = lock;
        this.zzajn = looper;
        this.f633xa = zze;
        this.f629wW = new zzqr(context, this.f628wV, lock, looper, zzc, map2, (zzh) null, map4, (Api.zza<? extends zzwz, zzxa>) null, arrayList2, new zza());
        this.f630wX = new zzqr(context, this.f628wV, lock, looper, zzc, map, zzh, map3, zza2, arrayList, new zzb());
        ArrayMap arrayMap = new ArrayMap();
        for (Api.zzc<?> put : map2.keySet()) {
            arrayMap.put(put, this.f629wW);
        }
        for (Api.zzc<?> put2 : map.keySet()) {
            arrayMap.put(put2, this.f630wX);
        }
        this.f631wY = Collections.unmodifiableMap(arrayMap);
    }

    public static zzqh zza(Context context, zzqp zzqp, Lock lock, Looper looper, zzc zzc, Map<Api.zzc<?>, Api.zze> map, zzh zzh, Map<Api<?>, Integer> map2, Api.zza<? extends zzwz, zzxa> zza2, ArrayList<zzqf> arrayList) {
        Api.zze zze = null;
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        for (Map.Entry next : map.entrySet()) {
            Api.zze zze2 = (Api.zze) next.getValue();
            if (zze2.zzahs()) {
                zze = zze2;
            }
            if (zze2.zzahd()) {
                arrayMap.put((Api.zzc) next.getKey(), zze2);
            } else {
                arrayMap2.put((Api.zzc) next.getKey(), zze2);
            }
        }
        zzac.zza(!arrayMap.isEmpty(), (Object) "CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
        ArrayMap arrayMap3 = new ArrayMap();
        ArrayMap arrayMap4 = new ArrayMap();
        for (Api next2 : map2.keySet()) {
            Api.zzc<?> zzapp = next2.zzapp();
            if (arrayMap.containsKey(zzapp)) {
                arrayMap3.put(next2, map2.get(next2));
            } else if (arrayMap2.containsKey(zzapp)) {
                arrayMap4.put(next2, map2.get(next2));
            } else {
                throw new IllegalStateException("Each API in the apiTypeMap must have a corresponding client in the clients map.");
            }
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Iterator<zzqf> it = arrayList.iterator();
        while (it.hasNext()) {
            zzqf next3 = it.next();
            if (arrayMap3.containsKey(next3.f625tv)) {
                arrayList2.add(next3);
            } else if (arrayMap4.containsKey(next3.f625tv)) {
                arrayList3.add(next3);
            } else {
                throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the apiTypeMap");
            }
        }
        return new zzqh(context, zzqp, lock, looper, zzc, arrayMap, arrayMap2, zzh, zza2, zze, arrayList2, arrayList3, arrayMap3, arrayMap4);
    }

    private void zzara() {
        this.f636xd = null;
        this.f635xc = null;
        this.f629wW.connect();
        this.f630wX.connect();
    }

    /* access modifiers changed from: private */
    public void zzarb() {
        if (zzc(this.f635xc)) {
            if (zzc(this.f636xd) || zzare()) {
                zzarc();
            } else if (this.f636xd == null) {
            } else {
                if (this.f639xg == 1) {
                    zzard();
                    return;
                }
                zzb(this.f636xd);
                this.f629wW.disconnect();
            }
        } else if (this.f635xc != null && zzc(this.f636xd)) {
            this.f630wX.disconnect();
            zzb(this.f635xc);
        } else if (this.f635xc != null && this.f636xd != null) {
            ConnectionResult connectionResult = this.f635xc;
            if (this.f630wX.f736yo < this.f629wW.f736yo) {
                connectionResult = this.f636xd;
            }
            zzb(connectionResult);
        }
    }

    private void zzarc() {
        switch (this.f639xg) {
            case 1:
                break;
            case 2:
                this.f628wV.zzn(this.f634xb);
                break;
            default:
                Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new AssertionError());
                break;
        }
        zzard();
        this.f639xg = 0;
    }

    private void zzard() {
        for (zzrl zzahr : this.f632wZ) {
            zzahr.zzahr();
        }
        this.f632wZ.clear();
    }

    private boolean zzare() {
        return this.f636xd != null && this.f636xd.getErrorCode() == 4;
    }

    @Nullable
    private PendingIntent zzarf() {
        if (this.f633xa == null) {
            return null;
        }
        return PendingIntent.getActivity(this.mContext, this.f628wV.getSessionId(), this.f633xa.zzaht(), 134217728);
    }

    /* access modifiers changed from: private */
    public void zzb(int i, boolean z) {
        this.f628wV.zzc(i, z);
        this.f636xd = null;
        this.f635xc = null;
    }

    private void zzb(ConnectionResult connectionResult) {
        switch (this.f639xg) {
            case 1:
                break;
            case 2:
                this.f628wV.zzd(connectionResult);
                break;
            default:
                Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new Exception());
                break;
        }
        zzard();
        this.f639xg = 0;
    }

    private static boolean zzc(ConnectionResult connectionResult) {
        return connectionResult != null && connectionResult.isSuccess();
    }

    private boolean zze(zzqc.zza<? extends Result, ? extends Api.zzb> zza2) {
        Api.zzc<? extends Api.zzb> zzapp = zza2.zzapp();
        zzac.zzb(this.f631wY.containsKey(zzapp), (Object) "GoogleApiClient is not configured to use the API required for this call.");
        return this.f631wY.get(zzapp).equals(this.f630wX);
    }

    /* access modifiers changed from: private */
    public void zzm(Bundle bundle) {
        if (this.f634xb == null) {
            this.f634xb = bundle;
        } else if (bundle != null) {
            this.f634xb.putAll(bundle);
        }
    }

    public ConnectionResult blockingConnect() {
        throw new UnsupportedOperationException();
    }

    public ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public void connect() {
        this.f639xg = 2;
        this.f637xe = false;
        zzara();
    }

    public void disconnect() {
        this.f636xd = null;
        this.f635xc = null;
        this.f639xg = 0;
        this.f629wW.disconnect();
        this.f630wX.disconnect();
        zzard();
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append(str).append("authClient").println(":");
        this.f630wX.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
        printWriter.append(str).append("anonClient").println(":");
        this.f629wW.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
    }

    @Nullable
    public ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        return this.f631wY.get(api.zzapp()).equals(this.f630wX) ? zzare() ? new ConnectionResult(4, zzarf()) : this.f630wX.getConnectionResult(api) : this.f629wW.getConnectionResult(api);
    }

    public boolean isConnected() {
        boolean z = true;
        this.f638xf.lock();
        try {
            if (!this.f629wW.isConnected() || (!zzaqz() && !zzare() && this.f639xg != 1)) {
                z = false;
            }
            return z;
        } finally {
            this.f638xf.unlock();
        }
    }

    public boolean isConnecting() {
        this.f638xf.lock();
        try {
            return this.f639xg == 2;
        } finally {
            this.f638xf.unlock();
        }
    }

    public boolean zza(zzrl zzrl) {
        this.f638xf.lock();
        try {
            if ((isConnecting() || isConnected()) && !zzaqz()) {
                this.f632wZ.add(zzrl);
                if (this.f639xg == 0) {
                    this.f639xg = 1;
                }
                this.f636xd = null;
                this.f630wX.connect();
                return true;
            }
            this.f638xf.unlock();
            return false;
        } finally {
            this.f638xf.unlock();
        }
    }

    public void zzaqb() {
        this.f638xf.lock();
        try {
            boolean isConnecting = isConnecting();
            this.f630wX.disconnect();
            this.f636xd = new ConnectionResult(4);
            if (isConnecting) {
                new Handler(this.zzajn).post(new Runnable() {
                    public void run() {
                        zzqh.this.f638xf.lock();
                        try {
                            zzqh.this.zzarb();
                        } finally {
                            zzqh.this.f638xf.unlock();
                        }
                    }
                });
            } else {
                zzard();
            }
        } finally {
            this.f638xf.unlock();
        }
    }

    public void zzaqy() {
        this.f629wW.zzaqy();
        this.f630wX.zzaqy();
    }

    public boolean zzaqz() {
        return this.f630wX.isConnected();
    }

    public <A extends Api.zzb, R extends Result, T extends zzqc.zza<R, A>> T zzc(@NonNull T t) {
        if (!zze((zzqc.zza<? extends Result, ? extends Api.zzb>) t)) {
            return this.f629wW.zzc(t);
        }
        if (!zzare()) {
            return this.f630wX.zzc(t);
        }
        t.zzz(new Status(4, (String) null, zzarf()));
        return t;
    }

    public <A extends Api.zzb, T extends zzqc.zza<? extends Result, A>> T zzd(@NonNull T t) {
        if (!zze((zzqc.zza<? extends Result, ? extends Api.zzb>) t)) {
            return this.f629wW.zzd(t);
        }
        if (!zzare()) {
            return this.f630wX.zzd(t);
        }
        t.zzz(new Status(4, (String) null, zzarf()));
        return t;
    }
}
