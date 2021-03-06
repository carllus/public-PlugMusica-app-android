package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.app.FragmentActivity;
import android.support.p000v4.util.ArrayMap;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzai;
import com.google.android.gms.common.internal.zzh;
import com.google.android.gms.internal.zzqa;
import com.google.android.gms.internal.zzqc;
import com.google.android.gms.internal.zzqf;
import com.google.android.gms.internal.zzqp;
import com.google.android.gms.internal.zzqz;
import com.google.android.gms.internal.zzrd;
import com.google.android.gms.internal.zzrl;
import com.google.android.gms.internal.zzrp;
import com.google.android.gms.internal.zzwy;
import com.google.android.gms.internal.zzwz;
import com.google.android.gms.internal.zzxa;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public abstract class GoogleApiClient {
    public static final int SIGN_IN_MODE_OPTIONAL = 2;
    public static final int SIGN_IN_MODE_REQUIRED = 1;
    /* access modifiers changed from: private */

    /* renamed from: vE */
    public static final Set<GoogleApiClient> f142vE = Collections.newSetFromMap(new WeakHashMap());

    public static final class Builder {

        /* renamed from: ec */
        private Account f143ec;

        /* renamed from: fo */
        private String f144fo;
        private final Context mContext;

        /* renamed from: vF */
        private final Set<Scope> f145vF;

        /* renamed from: vG */
        private final Set<Scope> f146vG;

        /* renamed from: vH */
        private int f147vH;

        /* renamed from: vI */
        private View f148vI;

        /* renamed from: vJ */
        private String f149vJ;

        /* renamed from: vK */
        private final Map<Api<?>, zzh.zza> f150vK;

        /* renamed from: vL */
        private final Map<Api<?>, Api.ApiOptions> f151vL;

        /* renamed from: vM */
        private zzqz f152vM;

        /* renamed from: vN */
        private int f153vN;

        /* renamed from: vO */
        private OnConnectionFailedListener f154vO;

        /* renamed from: vP */
        private GoogleApiAvailability f155vP;

        /* renamed from: vQ */
        private Api.zza<? extends zzwz, zzxa> f156vQ;

        /* renamed from: vR */
        private final ArrayList<ConnectionCallbacks> f157vR;

        /* renamed from: vS */
        private final ArrayList<OnConnectionFailedListener> f158vS;
        private Looper zzajn;

        public Builder(@NonNull Context context) {
            this.f145vF = new HashSet();
            this.f146vG = new HashSet();
            this.f150vK = new ArrayMap();
            this.f151vL = new ArrayMap();
            this.f153vN = -1;
            this.f155vP = GoogleApiAvailability.getInstance();
            this.f156vQ = zzwy.f884fb;
            this.f157vR = new ArrayList<>();
            this.f158vS = new ArrayList<>();
            this.mContext = context;
            this.zzajn = context.getMainLooper();
            this.f144fo = context.getPackageName();
            this.f149vJ = context.getClass().getName();
        }

        public Builder(@NonNull Context context, @NonNull ConnectionCallbacks connectionCallbacks, @NonNull OnConnectionFailedListener onConnectionFailedListener) {
            this(context);
            zzac.zzb(connectionCallbacks, (Object) "Must provide a connected listener");
            this.f157vR.add(connectionCallbacks);
            zzac.zzb(onConnectionFailedListener, (Object) "Must provide a connection failed listener");
            this.f158vS.add(onConnectionFailedListener);
        }

        private static <C extends Api.zze, O> C zza(Api.zza<C, O> zza, Object obj, Context context, Looper looper, zzh zzh, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return zza.zza(context, looper, zzh, obj, connectionCallbacks, onConnectionFailedListener);
        }

        private Builder zza(@NonNull zzqz zzqz, int i, @Nullable OnConnectionFailedListener onConnectionFailedListener) {
            zzac.zzb(i >= 0, (Object) "clientId must be non-negative");
            this.f153vN = i;
            this.f154vO = onConnectionFailedListener;
            this.f152vM = zzqz;
            return this;
        }

        private static <C extends Api.zzg, O> zzai zza(Api.zzh<C, O> zzh, Object obj, Context context, Looper looper, zzh zzh2, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzai(context, looper, zzh.zzapt(), connectionCallbacks, onConnectionFailedListener, zzh2, zzh.zzr(obj));
        }

        private <O extends Api.ApiOptions> void zza(Api<O> api, O o, int i, Scope... scopeArr) {
            boolean z = true;
            if (i != 1) {
                if (i == 2) {
                    z = false;
                } else {
                    throw new IllegalArgumentException(new StringBuilder(90).append("Invalid resolution mode: '").append(i).append("', use a constant from GoogleApiClient.ResolutionMode").toString());
                }
            }
            HashSet hashSet = new HashSet(api.zzapm().zzp(o));
            for (Scope add : scopeArr) {
                hashSet.add(add);
            }
            this.f150vK.put(api, new zzh.zza(hashSet, z));
        }

        private GoogleApiClient zzaqe() {
            zzai zza;
            Api api;
            zzh zzaqd = zzaqd();
            Api api2 = null;
            Map<Api<?>, zzh.zza> zzaui = zzaqd.zzaui();
            ArrayMap arrayMap = new ArrayMap();
            ArrayMap arrayMap2 = new ArrayMap();
            ArrayList arrayList = new ArrayList();
            Api api3 = null;
            for (Api next : this.f151vL.keySet()) {
                Api.ApiOptions apiOptions = this.f151vL.get(next);
                int i = 0;
                if (zzaui.get(next) != null) {
                    i = zzaui.get(next).f352Cb ? 1 : 2;
                }
                arrayMap.put(next, Integer.valueOf(i));
                zzqf zzqf = new zzqf(next, i);
                arrayList.add(zzqf);
                if (next.zzapq()) {
                    Api.zzh zzapo = next.zzapo();
                    Api api4 = zzapo.getPriority() == 1 ? next : api3;
                    zza = zza(zzapo, (Object) apiOptions, this.mContext, this.zzajn, zzaqd, (ConnectionCallbacks) zzqf, (OnConnectionFailedListener) zzqf);
                    api = api4;
                } else {
                    Api.zza zzapn = next.zzapn();
                    Api api5 = zzapn.getPriority() == 1 ? next : api3;
                    zza = zza(zzapn, (Object) apiOptions, this.mContext, this.zzajn, zzaqd, (ConnectionCallbacks) zzqf, (OnConnectionFailedListener) zzqf);
                    api = api5;
                }
                arrayMap2.put(next.zzapp(), zza);
                if (!zza.zzahs()) {
                    next = api2;
                } else if (api2 != null) {
                    String valueOf = String.valueOf(next.getName());
                    String valueOf2 = String.valueOf(api2.getName());
                    throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 21 + String.valueOf(valueOf2).length()).append(valueOf).append(" cannot be used with ").append(valueOf2).toString());
                }
                api3 = api;
                api2 = next;
            }
            if (api2 != null) {
                if (api3 != null) {
                    String valueOf3 = String.valueOf(api2.getName());
                    String valueOf4 = String.valueOf(api3.getName());
                    throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf3).length() + 21 + String.valueOf(valueOf4).length()).append(valueOf3).append(" cannot be used with ").append(valueOf4).toString());
                }
                zzac.zza(this.f143ec == null, "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead", api2.getName());
                zzac.zza(this.f145vF.equals(this.f146vG), "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead.", api2.getName());
            }
            return new zzqp(this.mContext, new ReentrantLock(), this.zzajn, zzaqd, this.f155vP, this.f156vQ, arrayMap, this.f157vR, this.f158vS, arrayMap2, this.f153vN, zzqp.zza(arrayMap2.values(), true), arrayList);
        }

        private void zzf(GoogleApiClient googleApiClient) {
            zzqa.zza(this.f152vM).zza(this.f153vN, googleApiClient, this.f154vO);
        }

        public Builder addApi(@NonNull Api<? extends Api.ApiOptions.NotRequiredOptions> api) {
            zzac.zzb(api, (Object) "Api must not be null");
            this.f151vL.put(api, (Object) null);
            List<Scope> zzp = api.zzapm().zzp(null);
            this.f146vG.addAll(zzp);
            this.f145vF.addAll(zzp);
            return this;
        }

        public <O extends Api.ApiOptions.HasOptions> Builder addApi(@NonNull Api<O> api, @NonNull O o) {
            zzac.zzb(api, (Object) "Api must not be null");
            zzac.zzb(o, (Object) "Null options are not permitted for this Api");
            this.f151vL.put(api, o);
            List<Scope> zzp = api.zzapm().zzp(o);
            this.f146vG.addAll(zzp);
            this.f145vF.addAll(zzp);
            return this;
        }

        public <O extends Api.ApiOptions.HasOptions> Builder addApiIfAvailable(@NonNull Api<O> api, @NonNull O o, Scope... scopeArr) {
            zzac.zzb(api, (Object) "Api must not be null");
            zzac.zzb(o, (Object) "Null options are not permitted for this Api");
            this.f151vL.put(api, o);
            zza(api, o, 1, scopeArr);
            return this;
        }

        public Builder addApiIfAvailable(@NonNull Api<? extends Api.ApiOptions.NotRequiredOptions> api, Scope... scopeArr) {
            zzac.zzb(api, (Object) "Api must not be null");
            this.f151vL.put(api, (Object) null);
            zza(api, (Api.ApiOptions) null, 1, scopeArr);
            return this;
        }

        public Builder addConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks) {
            zzac.zzb(connectionCallbacks, (Object) "Listener must not be null");
            this.f157vR.add(connectionCallbacks);
            return this;
        }

        public Builder addOnConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener) {
            zzac.zzb(onConnectionFailedListener, (Object) "Listener must not be null");
            this.f158vS.add(onConnectionFailedListener);
            return this;
        }

        public Builder addScope(@NonNull Scope scope) {
            zzac.zzb(scope, (Object) "Scope must not be null");
            this.f145vF.add(scope);
            return this;
        }

        public GoogleApiClient build() {
            zzac.zzb(!this.f151vL.isEmpty(), (Object) "must call addApi() to add at least one API");
            GoogleApiClient zzaqe = zzaqe();
            synchronized (GoogleApiClient.f142vE) {
                GoogleApiClient.f142vE.add(zzaqe);
            }
            if (this.f153vN >= 0) {
                zzf(zzaqe);
            }
            return zzaqe;
        }

        public Builder enableAutoManage(@NonNull FragmentActivity fragmentActivity, int i, @Nullable OnConnectionFailedListener onConnectionFailedListener) {
            return zza(new zzqz(fragmentActivity), i, onConnectionFailedListener);
        }

        public Builder enableAutoManage(@NonNull FragmentActivity fragmentActivity, @Nullable OnConnectionFailedListener onConnectionFailedListener) {
            return enableAutoManage(fragmentActivity, 0, onConnectionFailedListener);
        }

        public Builder setAccountName(String str) {
            this.f143ec = str == null ? null : new Account(str, "com.google");
            return this;
        }

        public Builder setGravityForPopups(int i) {
            this.f147vH = i;
            return this;
        }

        public Builder setHandler(@NonNull Handler handler) {
            zzac.zzb(handler, (Object) "Handler must not be null");
            this.zzajn = handler.getLooper();
            return this;
        }

        public Builder setViewForPopups(@NonNull View view) {
            zzac.zzb(view, (Object) "View must not be null");
            this.f148vI = view;
            return this;
        }

        public Builder useDefaultAccount() {
            return setAccountName("<<default account>>");
        }

        public zzh zzaqd() {
            zzxa zzxa = zzxa.aAa;
            if (this.f151vL.containsKey(zzwy.API)) {
                zzxa = (zzxa) this.f151vL.get(zzwy.API);
            }
            return new zzh(this.f143ec, this.f145vF, this.f150vK, this.f147vH, this.f148vI, this.f144fo, this.f149vJ, zzxa);
        }
    }

    public interface ConnectionCallbacks {
        public static final int CAUSE_NETWORK_LOST = 2;
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;

        void onConnected(@Nullable Bundle bundle);

        void onConnectionSuspended(int i);
    }

    public interface OnConnectionFailedListener {
        void onConnectionFailed(@NonNull ConnectionResult connectionResult);
    }

    public static void dumpAll(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        synchronized (f142vE) {
            String concat = String.valueOf(str).concat("  ");
            int i = 0;
            for (GoogleApiClient dump : f142vE) {
                printWriter.append(str).append("GoogleApiClient#").println(i);
                dump.dump(concat, fileDescriptor, printWriter, strArr);
                i++;
            }
        }
    }

    public static Set<GoogleApiClient> zzaqa() {
        Set<GoogleApiClient> set;
        synchronized (f142vE) {
            set = f142vE;
        }
        return set;
    }

    public abstract ConnectionResult blockingConnect();

    public abstract ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit);

    public abstract PendingResult<Status> clearDefaultAccountAndReconnect();

    public abstract void connect();

    public void connect(int i) {
        throw new UnsupportedOperationException();
    }

    public abstract void disconnect();

    public abstract void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    @NonNull
    public abstract ConnectionResult getConnectionResult(@NonNull Api<?> api);

    public Context getContext() {
        throw new UnsupportedOperationException();
    }

    public Looper getLooper() {
        throw new UnsupportedOperationException();
    }

    public abstract boolean hasConnectedApi(@NonNull Api<?> api);

    public abstract boolean isConnected();

    public abstract boolean isConnecting();

    public abstract boolean isConnectionCallbacksRegistered(@NonNull ConnectionCallbacks connectionCallbacks);

    public abstract boolean isConnectionFailedListenerRegistered(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    public abstract void reconnect();

    public abstract void registerConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks);

    public abstract void registerConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    public abstract void stopAutoManage(@NonNull FragmentActivity fragmentActivity);

    public abstract void unregisterConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks);

    public abstract void unregisterConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    @NonNull
    public <C extends Api.zze> C zza(@NonNull Api.zzc<C> zzc) {
        throw new UnsupportedOperationException();
    }

    public void zza(zzrp zzrp) {
        throw new UnsupportedOperationException();
    }

    public boolean zza(@NonNull Api<?> api) {
        throw new UnsupportedOperationException();
    }

    public boolean zza(zzrl zzrl) {
        throw new UnsupportedOperationException();
    }

    public void zzaqb() {
        throw new UnsupportedOperationException();
    }

    public void zzb(zzrp zzrp) {
        throw new UnsupportedOperationException();
    }

    public <A extends Api.zzb, R extends Result, T extends zzqc.zza<R, A>> T zzc(@NonNull T t) {
        throw new UnsupportedOperationException();
    }

    public <A extends Api.zzb, T extends zzqc.zza<? extends Result, A>> T zzd(@NonNull T t) {
        throw new UnsupportedOperationException();
    }

    public <L> zzrd<L> zzs(@NonNull L l) {
        throw new UnsupportedOperationException();
    }
}
