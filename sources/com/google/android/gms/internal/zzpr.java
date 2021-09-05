package com.google.android.gms.internal;

import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.clearcut.LogEventParcelable;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.internal.zzpu;
import com.google.android.gms.internal.zzqc;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class zzpr implements com.google.android.gms.clearcut.zzc {

    /* renamed from: um */
    private static final Object f552um = new Object();

    /* renamed from: un */
    private static ScheduledExecutorService f553un;
    /* access modifiers changed from: private */

    /* renamed from: uo */
    public static final zze f554uo = new zze();

    /* renamed from: up */
    private static final long f555up = TimeUnit.MILLISECONDS.convert(2, TimeUnit.MINUTES);
    /* access modifiers changed from: private */

    /* renamed from: kv */
    public GoogleApiClient f556kv;

    /* renamed from: uq */
    private final zza f557uq;
    /* access modifiers changed from: private */

    /* renamed from: ur */
    public final Object f558ur;

    /* renamed from: us */
    private long f559us;

    /* renamed from: ut */
    private final long f560ut;

    /* renamed from: uu */
    private ScheduledFuture<?> f561uu;

    /* renamed from: uv */
    private final Runnable f562uv;
    /* access modifiers changed from: private */
    public final com.google.android.gms.common.util.zze zzapy;

    public interface zza {
    }

    public static class zzb implements zza {
    }

    static abstract class zzc<R extends Result> extends zzqc.zza<R, zzps> {
        public zzc(GoogleApiClient googleApiClient) {
            super((Api<?>) com.google.android.gms.clearcut.zzb.API, googleApiClient);
        }
    }

    static final class zzd extends zzc<Status> {

        /* renamed from: uA */
        private final LogEventParcelable f571uA;

        zzd(LogEventParcelable logEventParcelable, GoogleApiClient googleApiClient) {
            super(googleApiClient);
            this.f571uA = logEventParcelable;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof zzd)) {
                return false;
            }
            return this.f571uA.equals(((zzd) obj).f571uA);
        }

        public String toString() {
            String valueOf = String.valueOf(this.f571uA);
            return new StringBuilder(String.valueOf(valueOf).length() + 12).append("MethodImpl(").append(valueOf).append(")").toString();
        }

        /* access modifiers changed from: protected */
        public void zza(zzps zzps) throws RemoteException {
            C08581 r0 = new zzpu.zza() {
                public void zzw(Status status) {
                    zzd.this.zzc(status);
                }
            };
            try {
                zzpr.zza(this.f571uA);
                zzps.zza(r0, this.f571uA);
            } catch (RuntimeException e) {
                Log.e("ClearcutLoggerApiImpl", "derived ClearcutLogger.MessageProducer ", e);
                zzz(new Status(10, "MessageProducer"));
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: zzb */
        public Status zzc(Status status) {
            return status;
        }
    }

    private static final class zze {
        private int mSize;

        private zze() {
            this.mSize = 0;
        }

        public synchronized void decrement() {
            if (this.mSize == 0) {
                throw new RuntimeException("too many decrements");
            }
            this.mSize--;
            if (this.mSize == 0) {
                notifyAll();
            }
        }

        public synchronized void increment() {
            this.mSize++;
        }
    }

    public zzpr() {
        this(new zzh(), f555up, new zzb());
    }

    public zzpr(com.google.android.gms.common.util.zze zze2, long j, zza zza2) {
        this.f558ur = new Object();
        this.f559us = 0;
        this.f561uu = null;
        this.f556kv = null;
        this.f562uv = new Runnable() {
            public void run() {
                synchronized (zzpr.this.f558ur) {
                    if (zzpr.zzb(zzpr.this) <= zzpr.this.zzapy.elapsedRealtime() && zzpr.this.f556kv != null) {
                        Log.i("ClearcutLoggerApiImpl", "disconnect managed GoogleApiClient");
                        zzpr.this.f556kv.disconnect();
                        GoogleApiClient unused = zzpr.this.f556kv = null;
                    }
                }
            }
        };
        this.zzapy = zze2;
        this.f560ut = j;
        this.f557uq = zza2;
    }

    private PendingResult<Status> zza(final GoogleApiClient googleApiClient, final zzc<Status> zzc2) {
        zzaoz().execute(new Runnable() {
            public void run() {
                googleApiClient.zzc(zzc2);
            }
        });
        return zzc2;
    }

    /* access modifiers changed from: private */
    public static void zza(LogEventParcelable logEventParcelable) {
        if (logEventParcelable.f84uk != null && logEventParcelable.f83uj.brh.length == 0) {
            logEventParcelable.f83uj.brh = logEventParcelable.f84uk.zzaoy();
        }
        if (logEventParcelable.f85ul != null && logEventParcelable.f83uj.bro.length == 0) {
            logEventParcelable.f83uj.bro = logEventParcelable.f85ul.zzaoy();
        }
        logEventParcelable.f77ud = zzark.zzf(logEventParcelable.f83uj);
    }

    private ScheduledExecutorService zzaoz() {
        synchronized (f552um) {
            if (f553un == null) {
                f553un = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
                    public Thread newThread(final Runnable runnable) {
                        return new Thread(new Runnable() {
                            public void run() {
                                Process.setThreadPriority(10);
                                runnable.run();
                            }
                        }, "ClearcutLoggerApiImpl");
                    }
                });
            }
        }
        return f553un;
    }

    static /* synthetic */ long zzb(zzpr zzpr) {
        return 0;
    }

    private zzd zzb(GoogleApiClient googleApiClient, LogEventParcelable logEventParcelable) {
        f554uo.increment();
        zzd zzd2 = new zzd(logEventParcelable, googleApiClient);
        zzd2.zza((PendingResult.zza) new PendingResult.zza() {
            public void zzv(Status status) {
                zzpr.f554uo.decrement();
            }
        });
        return zzd2;
    }

    public PendingResult<Status> zza(GoogleApiClient googleApiClient, LogEventParcelable logEventParcelable) {
        return zza(googleApiClient, (zzc<Status>) zzb(googleApiClient, logEventParcelable));
    }
}
