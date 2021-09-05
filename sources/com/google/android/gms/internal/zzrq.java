package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zzf;
import com.google.android.gms.internal.zzqc;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public class zzrq {

    /* renamed from: zt */
    private static final zzqe<?>[] f820zt = new zzqe[0];

    /* renamed from: vC */
    private final Api.zze f821vC;

    /* renamed from: xW */
    private final Map<Api.zzc<?>, Api.zze> f822xW;

    /* renamed from: zu */
    final Set<zzqe<?>> f823zu;

    /* renamed from: zv */
    private final zzb f824zv;
    /* access modifiers changed from: private */

    /* renamed from: zw */
    public zzc f825zw;

    private static class zza implements IBinder.DeathRecipient, zzb {

        /* renamed from: zA */
        private final WeakReference<IBinder> f827zA;

        /* renamed from: zy */
        private final WeakReference<zzqe<?>> f828zy;

        /* renamed from: zz */
        private final WeakReference<zzf> f829zz;

        private zza(zzqe<?> zzqe, zzf zzf, IBinder iBinder) {
            this.f829zz = new WeakReference<>(zzf);
            this.f828zy = new WeakReference<>(zzqe);
            this.f827zA = new WeakReference<>(iBinder);
        }

        private void zzasd() {
            zzqe zzqe = (zzqe) this.f828zy.get();
            zzf zzf = (zzf) this.f829zz.get();
            if (!(zzf == null || zzqe == null)) {
                zzf.remove(zzqe.zzaqf().intValue());
            }
            IBinder iBinder = (IBinder) this.f827zA.get();
            if (iBinder != null) {
                iBinder.unlinkToDeath(this, 0);
            }
        }

        public void binderDied() {
            zzasd();
        }

        public void zzc(zzqe<?> zzqe) {
            zzasd();
        }
    }

    interface zzb {
        void zzc(zzqe<?> zzqe);
    }

    interface zzc {
        void zzask();
    }

    public zzrq(Api.zze zze) {
        this.f823zu = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));
        this.f824zv = new zzb() {
            public void zzc(zzqe<?> zzqe) {
                zzrq.this.f823zu.remove(zzqe);
                if (!(zzqe.zzaqf() == null || zzrq.zza(zzrq.this) == null)) {
                    zzrq.zza(zzrq.this).remove(zzqe.zzaqf().intValue());
                }
                if (zzrq.this.f825zw != null && zzrq.this.f823zu.isEmpty()) {
                    zzrq.this.f825zw.zzask();
                }
            }
        };
        this.f825zw = null;
        this.f822xW = null;
        this.f821vC = zze;
    }

    public zzrq(Map<Api.zzc<?>, Api.zze> map) {
        this.f823zu = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));
        this.f824zv = new zzb() {
            public void zzc(zzqe<?> zzqe) {
                zzrq.this.f823zu.remove(zzqe);
                if (!(zzqe.zzaqf() == null || zzrq.zza(zzrq.this) == null)) {
                    zzrq.zza(zzrq.this).remove(zzqe.zzaqf().intValue());
                }
                if (zzrq.this.f825zw != null && zzrq.this.f823zu.isEmpty()) {
                    zzrq.this.f825zw.zzask();
                }
            }
        };
        this.f825zw = null;
        this.f822xW = map;
        this.f821vC = null;
    }

    static /* synthetic */ zzf zza(zzrq zzrq) {
        return null;
    }

    private static void zza(zzqe<?> zzqe, zzf zzf, IBinder iBinder) {
        if (zzqe.isReady()) {
            zzqe.zza((zzb) new zza(zzqe, zzf, iBinder));
        } else if (iBinder == null || !iBinder.isBinderAlive()) {
            zzqe.zza((zzb) null);
            zzqe.cancel();
            zzf.remove(zzqe.zzaqf().intValue());
        } else {
            zza zza2 = new zza(zzqe, zzf, iBinder);
            zzqe.zza((zzb) zza2);
            try {
                iBinder.linkToDeath(zza2, 0);
            } catch (RemoteException e) {
                zzqe.cancel();
                zzf.remove(zzqe.zzaqf().intValue());
            }
        }
    }

    public void dump(PrintWriter printWriter) {
        printWriter.append(" mUnconsumedApiCalls.size()=").println(this.f823zu.size());
    }

    public void release() {
        IBinder iBinder;
        for (zzqe zzqe : (zzqe[]) this.f823zu.toArray(f820zt)) {
            zzqe.zza((zzb) null);
            if (zzqe.zzaqf() != null) {
                zzqe.zzaqs();
                if (this.f821vC != null) {
                    iBinder = this.f821vC.zzaps();
                } else if (this.f822xW != null) {
                    iBinder = this.f822xW.get(((zzqc.zza) zzqe).zzapp()).zzaps();
                } else {
                    Log.wtf("UnconsumedApiCalls", "Could not get service broker binder", new Exception());
                    iBinder = null;
                }
                zza(zzqe, (zzf) null, iBinder);
                this.f823zu.remove(zzqe);
            } else if (zzqe.zzaqq()) {
                this.f823zu.remove(zzqe);
            }
        }
    }

    public void zza(zzc zzc2) {
        if (this.f823zu.isEmpty()) {
            zzc2.zzask();
        }
        this.f825zw = zzc2;
    }

    public void zzasw() {
        for (zzqe zzaa : (zzqe[]) this.f823zu.toArray(f820zt)) {
            zzaa.zzaa(new Status(8, "The connection to Google Play services was lost"));
        }
    }

    public boolean zzasx() {
        for (zzqe isReady : (zzqe[]) this.f823zu.toArray(f820zt)) {
            if (!isReady.isReady()) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void zzb(zzqe<? extends Result> zzqe) {
        this.f823zu.add(zzqe);
        zzqe.zza(this.f824zv);
    }
}
