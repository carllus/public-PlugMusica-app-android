package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzac;

public class zzqc {

    public static abstract class zza<R extends Result, A extends Api.zzb> extends zzqe<R> implements zzb<R> {

        /* renamed from: tv */
        private final Api<?> f600tv;

        /* renamed from: wx */
        private final Api.zzc<A> f601wx;

        @Deprecated
        protected zza(Api.zzc<A> zzc, GoogleApiClient googleApiClient) {
            super((GoogleApiClient) zzac.zzb(googleApiClient, (Object) "GoogleApiClient must not be null"));
            this.f601wx = (Api.zzc) zzac.zzy(zzc);
            this.f600tv = null;
        }

        protected zza(Api<?> api, GoogleApiClient googleApiClient) {
            super((GoogleApiClient) zzac.zzb(googleApiClient, (Object) "GoogleApiClient must not be null"));
            this.f601wx = api.zzapp();
            this.f600tv = api;
        }

        private void zza(RemoteException remoteException) {
            zzz(new Status(8, remoteException.getLocalizedMessage(), (PendingIntent) null));
        }

        public /* synthetic */ void setResult(Object obj) {
            super.zzc((Result) obj);
        }

        /* access modifiers changed from: protected */
        public abstract void zza(A a) throws RemoteException;

        public final Api.zzc<A> zzapp() {
            return this.f601wx;
        }

        public final Api<?> zzaqn() {
            return this.f600tv;
        }

        public final void zzb(A a) throws DeadObjectException {
            try {
                zza(a);
            } catch (DeadObjectException e) {
                zza((RemoteException) e);
                throw e;
            } catch (RemoteException e2) {
                zza(e2);
            }
        }

        /* access modifiers changed from: protected */
        public void zzb(R r) {
        }

        public final void zzz(Status status) {
            zzac.zzb(!status.isSuccess(), (Object) "Failed result must not be success");
            Result zzc = zzc(status);
            zzc(zzc);
            zzb(zzc);
        }
    }

    public interface zzb<R> {
        void setResult(R r);

        void zzz(Status status);
    }
}
