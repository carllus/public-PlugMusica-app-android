package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.DeadObjectException;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.p000v4.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzqc;
import com.google.android.gms.internal.zzrd;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.FirebaseException;
import java.util.Map;

public abstract class zzpy {

    /* renamed from: lN */
    public final int f578lN;

    /* renamed from: wf */
    public final int f579wf;

    private static abstract class zza extends zzpy {

        /* renamed from: wg */
        protected final SparseArray<Map<zzrd.zzb<?>, zzri>> f580wg;

        /* renamed from: wh */
        protected final TaskCompletionSource<Void> f581wh;

        public zza(int i, int i2, TaskCompletionSource<Void> taskCompletionSource, SparseArray<Map<zzrd.zzb<?>, zzri>> sparseArray) {
            super(i, i2);
            this.f580wg = sparseArray;
            this.f581wh = taskCompletionSource;
        }

        private void zza(RemoteException remoteException) {
            zzx(new Status(8, remoteException.getLocalizedMessage(), (PendingIntent) null));
        }

        public boolean cancel() {
            this.f581wh.setException(new com.google.android.gms.common.api.zza(Status.f167wc));
            return true;
        }

        public void zza(SparseArray<zzrq> sparseArray) {
        }

        /* access modifiers changed from: protected */
        public abstract void zza(Api.zzb zzb) throws RemoteException;

        public final void zzb(Api.zzb zzb) throws DeadObjectException {
            try {
                zza(zzb);
            } catch (DeadObjectException e) {
                zza((RemoteException) e);
                throw e;
            } catch (RemoteException e2) {
                zza(e2);
            }
        }

        public void zzx(@NonNull Status status) {
            this.f581wh.setException(new com.google.android.gms.common.api.zza(status));
        }
    }

    public static class zzb<A extends zzqc.zza<? extends Result, Api.zzb>> extends zzpy {

        /* renamed from: wi */
        protected final A f582wi;

        public zzb(int i, int i2, A a) {
            super(i, i2);
            this.f582wi = a;
        }

        public boolean cancel() {
            return this.f582wi.zzaqq();
        }

        public void zza(SparseArray<zzrq> sparseArray) {
            zzrq zzrq = sparseArray.get(this.f579wf);
            if (zzrq != null) {
                zzrq.zzb((zzqe<? extends Result>) this.f582wi);
            }
        }

        public void zzb(Api.zzb zzb) throws DeadObjectException {
            this.f582wi.zzb(zzb);
        }

        public void zzx(@NonNull Status status) {
            this.f582wi.zzz(status);
        }
    }

    public static final class zzc extends zza {

        /* renamed from: wj */
        public final zzrh<Api.zzb> f583wj;

        /* renamed from: wk */
        public final zzrr<Api.zzb> f584wk;

        public zzc(int i, zzri zzri, TaskCompletionSource<Void> taskCompletionSource, SparseArray<Map<zzrd.zzb<?>, zzri>> sparseArray) {
            super(i, 3, taskCompletionSource, sparseArray);
            this.f583wj = zzri.f799wj;
            this.f584wk = zzri.f800wk;
        }

        public /* bridge */ /* synthetic */ boolean cancel() {
            return super.cancel();
        }

        public /* bridge */ /* synthetic */ void zza(SparseArray sparseArray) {
            super.zza((SparseArray<zzrq>) sparseArray);
        }

        public void zza(Api.zzb zzb) throws DeadObjectException {
            this.f583wj.zza(zzb, this.f581wh);
            Map map = (Map) this.f580wg.get(this.f579wf);
            if (map == null) {
                map = new ArrayMap(1);
                this.f580wg.put(this.f579wf, map);
            }
            String valueOf = String.valueOf(this.f583wj.zzasr());
            Log.d("reg", new StringBuilder(String.valueOf(valueOf).length() + 12).append("registered: ").append(valueOf).toString());
            if (this.f583wj.zzasr() != null) {
                map.put(this.f583wj.zzasr(), new zzri(this.f583wj, this.f584wk));
            }
        }

        public /* bridge */ /* synthetic */ void zzx(@NonNull Status status) {
            super.zzx(status);
        }
    }

    public static final class zzd<TResult> extends zzpy {

        /* renamed from: wm */
        private static final Status f585wm = new Status(8, "Connection to Google Play services was lost while executing the API call.");

        /* renamed from: wh */
        private final TaskCompletionSource<TResult> f586wh;

        /* renamed from: wl */
        private final zzro<Api.zzb, TResult> f587wl;

        public zzd(int i, int i2, zzro<Api.zzb, TResult> zzro, TaskCompletionSource<TResult> taskCompletionSource) {
            super(i, i2);
            this.f586wh = taskCompletionSource;
            this.f587wl = zzro;
        }

        public void zzb(Api.zzb zzb) throws DeadObjectException {
            try {
                this.f587wl.zzb(zzb, this.f586wh);
            } catch (DeadObjectException e) {
                zzx(f585wm);
                throw e;
            } catch (RemoteException e2) {
                zzx(f585wm);
            }
        }

        public void zzx(@NonNull Status status) {
            if (status.getStatusCode() == 8) {
                this.f586wh.setException(new FirebaseException(status.getStatusMessage()));
            } else {
                this.f586wh.setException(new FirebaseApiNotAvailableException(status.getStatusMessage()));
            }
        }
    }

    public static final class zze extends zza {

        /* renamed from: wn */
        public final zzrr<Api.zzb> f588wn;

        public zze(int i, zzrr<Api.zzb> zzrr, TaskCompletionSource<Void> taskCompletionSource, SparseArray<Map<zzrd.zzb<?>, zzri>> sparseArray) {
            super(i, 4, taskCompletionSource, sparseArray);
            this.f588wn = zzrr;
        }

        public /* bridge */ /* synthetic */ boolean cancel() {
            return super.cancel();
        }

        public /* bridge */ /* synthetic */ void zza(SparseArray sparseArray) {
            super.zza((SparseArray<zzrq>) sparseArray);
        }

        public void zza(Api.zzb zzb) throws DeadObjectException {
            Map map = (Map) this.f580wg.get(this.f579wf);
            if (map == null || this.f588wn.zzasr() == null) {
                Log.wtf("UnregisterListenerTask", "Received call to unregister a listener without a matching registration call.", new Exception());
                this.f581wh.setException(new com.google.android.gms.common.api.zza(Status.f165wa));
                return;
            }
            map.remove(this.f588wn.zzasr());
            this.f588wn.zzc(zzb, this.f581wh);
        }

        public /* bridge */ /* synthetic */ void zzx(@NonNull Status status) {
            super.zzx(status);
        }
    }

    public zzpy(int i, int i2) {
        this.f579wf = i;
        this.f578lN = i2;
    }

    public boolean cancel() {
        return true;
    }

    public void zza(SparseArray<zzrq> sparseArray) {
    }

    public abstract void zzb(Api.zzb zzb2) throws DeadObjectException;

    public abstract void zzx(@NonNull Status status);
}
