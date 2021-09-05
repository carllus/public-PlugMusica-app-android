package com.google.android.gms.common.internal;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

final class zzo extends zzn implements Handler.Callback {
    /* access modifiers changed from: private */

    /* renamed from: CB */
    public final HashMap<zza, zzb> f370CB = new HashMap<>();
    /* access modifiers changed from: private */

    /* renamed from: CC */
    public final com.google.android.gms.common.stats.zzb f371CC;

    /* renamed from: CD */
    private final long f372CD;
    private final Handler mHandler;
    /* access modifiers changed from: private */
    public final Context zzask;

    private static final class zza {

        /* renamed from: CE */
        private final String f373CE;

        /* renamed from: CF */
        private final ComponentName f374CF;

        /* renamed from: V */
        private final String f375V;

        public zza(ComponentName componentName) {
            this.f375V = null;
            this.f373CE = null;
            this.f374CF = (ComponentName) zzac.zzy(componentName);
        }

        public zza(String str, String str2) {
            this.f375V = zzac.zzhz(str);
            this.f373CE = zzac.zzhz(str2);
            this.f374CF = null;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            return zzab.equal(this.f375V, zza.f375V) && zzab.equal(this.f374CF, zza.f374CF);
        }

        public int hashCode() {
            return zzab.hashCode(this.f375V, this.f374CF);
        }

        public String toString() {
            return this.f375V == null ? this.f374CF.flattenToString() : this.f375V;
        }

        public Intent zzauv() {
            return this.f375V != null ? new Intent(this.f375V).setPackage(this.f373CE) : new Intent().setComponent(this.f374CF);
        }
    }

    private final class zzb {
        /* access modifiers changed from: private */

        /* renamed from: Bz */
        public IBinder f376Bz;
        /* access modifiers changed from: private */

        /* renamed from: CF */
        public ComponentName f377CF;

        /* renamed from: CG */
        private final zza f378CG = new zza();
        /* access modifiers changed from: private */

        /* renamed from: CH */
        public final Set<ServiceConnection> f379CH = new HashSet();

        /* renamed from: CI */
        private boolean f380CI;
        /* access modifiers changed from: private */

        /* renamed from: CJ */
        public final zza f381CJ;
        /* access modifiers changed from: private */
        public int mState = 2;

        public class zza implements ServiceConnection {
            public zza() {
            }

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                synchronized (zzo.this.f370CB) {
                    IBinder unused = zzb.this.f376Bz = iBinder;
                    ComponentName unused2 = zzb.this.f377CF = componentName;
                    for (ServiceConnection onServiceConnected : zzb.this.f379CH) {
                        onServiceConnected.onServiceConnected(componentName, iBinder);
                    }
                    int unused3 = zzb.this.mState = 1;
                }
            }

            public void onServiceDisconnected(ComponentName componentName) {
                synchronized (zzo.this.f370CB) {
                    IBinder unused = zzb.this.f376Bz = null;
                    ComponentName unused2 = zzb.this.f377CF = componentName;
                    for (ServiceConnection onServiceDisconnected : zzb.this.f379CH) {
                        onServiceDisconnected.onServiceDisconnected(componentName);
                    }
                    int unused3 = zzb.this.mState = 2;
                }
            }
        }

        public zzb(zza zza2) {
            this.f381CJ = zza2;
        }

        public IBinder getBinder() {
            return this.f376Bz;
        }

        public ComponentName getComponentName() {
            return this.f377CF;
        }

        public int getState() {
            return this.mState;
        }

        public boolean isBound() {
            return this.f380CI;
        }

        public void zza(ServiceConnection serviceConnection, String str) {
            zzo.this.f371CC.zza(zzo.this.zzask, serviceConnection, str, this.f381CJ.zzauv());
            this.f379CH.add(serviceConnection);
        }

        public boolean zza(ServiceConnection serviceConnection) {
            return this.f379CH.contains(serviceConnection);
        }

        public boolean zzauw() {
            return this.f379CH.isEmpty();
        }

        public void zzb(ServiceConnection serviceConnection, String str) {
            zzo.this.f371CC.zzb(zzo.this.zzask, serviceConnection);
            this.f379CH.remove(serviceConnection);
        }

        @TargetApi(14)
        public void zzhu(String str) {
            this.mState = 3;
            this.f380CI = zzo.this.f371CC.zza(zzo.this.zzask, str, this.f381CJ.zzauv(), (ServiceConnection) this.f378CG, 129);
            if (!this.f380CI) {
                this.mState = 2;
                try {
                    zzo.this.f371CC.zza(zzo.this.zzask, (ServiceConnection) this.f378CG);
                } catch (IllegalArgumentException e) {
                }
            }
        }

        public void zzhv(String str) {
            zzo.this.f371CC.zza(zzo.this.zzask, (ServiceConnection) this.f378CG);
            this.f380CI = false;
            this.mState = 2;
        }
    }

    zzo(Context context) {
        this.zzask = context.getApplicationContext();
        this.mHandler = new Handler(context.getMainLooper(), this);
        this.f371CC = com.google.android.gms.common.stats.zzb.zzawu();
        this.f372CD = 5000;
    }

    private boolean zza(zza zza2, ServiceConnection serviceConnection, String str) {
        boolean isBound;
        zzac.zzb(serviceConnection, (Object) "ServiceConnection must not be null");
        synchronized (this.f370CB) {
            zzb zzb2 = this.f370CB.get(zza2);
            if (zzb2 != null) {
                this.mHandler.removeMessages(0, zzb2);
                if (!zzb2.zza(serviceConnection)) {
                    zzb2.zza(serviceConnection, str);
                    switch (zzb2.getState()) {
                        case 1:
                            serviceConnection.onServiceConnected(zzb2.getComponentName(), zzb2.getBinder());
                            break;
                        case 2:
                            zzb2.zzhu(str);
                            break;
                    }
                } else {
                    String valueOf = String.valueOf(zza2);
                    throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 81).append("Trying to bind a GmsServiceConnection that was already connected before.  config=").append(valueOf).toString());
                }
            } else {
                zzb2 = new zzb(zza2);
                zzb2.zza(serviceConnection, str);
                zzb2.zzhu(str);
                this.f370CB.put(zza2, zzb2);
            }
            isBound = zzb2.isBound();
        }
        return isBound;
    }

    private void zzb(zza zza2, ServiceConnection serviceConnection, String str) {
        zzac.zzb(serviceConnection, (Object) "ServiceConnection must not be null");
        synchronized (this.f370CB) {
            zzb zzb2 = this.f370CB.get(zza2);
            if (zzb2 == null) {
                String valueOf = String.valueOf(zza2);
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 50).append("Nonexistent connection status for service config: ").append(valueOf).toString());
            } else if (!zzb2.zza(serviceConnection)) {
                String valueOf2 = String.valueOf(zza2);
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf2).length() + 76).append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=").append(valueOf2).toString());
            } else {
                zzb2.zzb(serviceConnection, str);
                if (zzb2.zzauw()) {
                    this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0, zzb2), this.f372CD);
                }
            }
        }
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                zzb zzb2 = (zzb) message.obj;
                synchronized (this.f370CB) {
                    if (zzb2.zzauw()) {
                        if (zzb2.isBound()) {
                            zzb2.zzhv("GmsClientSupervisor");
                        }
                        this.f370CB.remove(zzb2.f381CJ);
                    }
                }
                return true;
            default:
                return false;
        }
    }

    public boolean zza(ComponentName componentName, ServiceConnection serviceConnection, String str) {
        return zza(new zza(componentName), serviceConnection, str);
    }

    public boolean zza(String str, String str2, ServiceConnection serviceConnection, String str3) {
        return zza(new zza(str, str2), serviceConnection, str3);
    }

    public void zzb(ComponentName componentName, ServiceConnection serviceConnection, String str) {
        zzb(new zza(componentName), serviceConnection, str);
    }

    public void zzb(String str, String str2, ServiceConnection serviceConnection, String str3) {
        zzb(new zza(str, str2), serviceConnection, str3);
    }
}
