package com.google.android.gms.common.api;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.internal.zzqe;
import java.util.ArrayList;
import java.util.List;

public final class Batch extends zzqe<BatchResult> {
    /* access modifiers changed from: private */

    /* renamed from: vo */
    public int f130vo;
    /* access modifiers changed from: private */

    /* renamed from: vp */
    public boolean f131vp;
    /* access modifiers changed from: private */

    /* renamed from: vq */
    public boolean f132vq;
    /* access modifiers changed from: private */

    /* renamed from: vr */
    public final PendingResult<?>[] f133vr;
    /* access modifiers changed from: private */
    public final Object zzakd;

    public static final class Builder {

        /* renamed from: kv */
        private GoogleApiClient f135kv;

        /* renamed from: vt */
        private List<PendingResult<?>> f136vt = new ArrayList();

        public Builder(GoogleApiClient googleApiClient) {
            this.f135kv = googleApiClient;
        }

        public <R extends Result> BatchResultToken<R> add(PendingResult<R> pendingResult) {
            BatchResultToken<R> batchResultToken = new BatchResultToken<>(this.f136vt.size());
            this.f136vt.add(pendingResult);
            return batchResultToken;
        }

        public Batch build() {
            return new Batch(this.f136vt, this.f135kv);
        }
    }

    private Batch(List<PendingResult<?>> list, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zzakd = new Object();
        this.f130vo = list.size();
        this.f133vr = new PendingResult[this.f130vo];
        if (list.isEmpty()) {
            zzc(new BatchResult(Status.f163vY, this.f133vr));
            return;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < list.size()) {
                PendingResult<?> pendingResult = list.get(i2);
                this.f133vr[i2] = pendingResult;
                pendingResult.zza(new PendingResult.zza() {
                    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
                        return;
                     */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void zzv(com.google.android.gms.common.api.Status r6) {
                        /*
                            r5 = this;
                            com.google.android.gms.common.api.Batch r0 = com.google.android.gms.common.api.Batch.this
                            java.lang.Object r1 = r0.zzakd
                            monitor-enter(r1)
                            com.google.android.gms.common.api.Batch r0 = com.google.android.gms.common.api.Batch.this     // Catch:{ all -> 0x0039 }
                            boolean r0 = r0.isCanceled()     // Catch:{ all -> 0x0039 }
                            if (r0 == 0) goto L_0x0011
                            monitor-exit(r1)     // Catch:{ all -> 0x0039 }
                        L_0x0010:
                            return
                        L_0x0011:
                            boolean r0 = r6.isCanceled()     // Catch:{ all -> 0x0039 }
                            if (r0 == 0) goto L_0x003c
                            com.google.android.gms.common.api.Batch r0 = com.google.android.gms.common.api.Batch.this     // Catch:{ all -> 0x0039 }
                            r2 = 1
                            boolean unused = r0.f132vq = r2     // Catch:{ all -> 0x0039 }
                        L_0x001d:
                            com.google.android.gms.common.api.Batch r0 = com.google.android.gms.common.api.Batch.this     // Catch:{ all -> 0x0039 }
                            com.google.android.gms.common.api.Batch.zzb(r0)     // Catch:{ all -> 0x0039 }
                            com.google.android.gms.common.api.Batch r0 = com.google.android.gms.common.api.Batch.this     // Catch:{ all -> 0x0039 }
                            int r0 = r0.f130vo     // Catch:{ all -> 0x0039 }
                            if (r0 != 0) goto L_0x0037
                            com.google.android.gms.common.api.Batch r0 = com.google.android.gms.common.api.Batch.this     // Catch:{ all -> 0x0039 }
                            boolean r0 = r0.f132vq     // Catch:{ all -> 0x0039 }
                            if (r0 == 0) goto L_0x0049
                            com.google.android.gms.common.api.Batch r0 = com.google.android.gms.common.api.Batch.this     // Catch:{ all -> 0x0039 }
                            com.google.android.gms.common.api.Batch.super.cancel()     // Catch:{ all -> 0x0039 }
                        L_0x0037:
                            monitor-exit(r1)     // Catch:{ all -> 0x0039 }
                            goto L_0x0010
                        L_0x0039:
                            r0 = move-exception
                            monitor-exit(r1)     // Catch:{ all -> 0x0039 }
                            throw r0
                        L_0x003c:
                            boolean r0 = r6.isSuccess()     // Catch:{ all -> 0x0039 }
                            if (r0 != 0) goto L_0x001d
                            com.google.android.gms.common.api.Batch r0 = com.google.android.gms.common.api.Batch.this     // Catch:{ all -> 0x0039 }
                            r2 = 1
                            boolean unused = r0.f131vp = r2     // Catch:{ all -> 0x0039 }
                            goto L_0x001d
                        L_0x0049:
                            com.google.android.gms.common.api.Batch r0 = com.google.android.gms.common.api.Batch.this     // Catch:{ all -> 0x0039 }
                            boolean r0 = r0.f131vp     // Catch:{ all -> 0x0039 }
                            if (r0 == 0) goto L_0x0069
                            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x0039 }
                            r2 = 13
                            r0.<init>(r2)     // Catch:{ all -> 0x0039 }
                        L_0x0058:
                            com.google.android.gms.common.api.Batch r2 = com.google.android.gms.common.api.Batch.this     // Catch:{ all -> 0x0039 }
                            com.google.android.gms.common.api.BatchResult r3 = new com.google.android.gms.common.api.BatchResult     // Catch:{ all -> 0x0039 }
                            com.google.android.gms.common.api.Batch r4 = com.google.android.gms.common.api.Batch.this     // Catch:{ all -> 0x0039 }
                            com.google.android.gms.common.api.PendingResult[] r4 = r4.f133vr     // Catch:{ all -> 0x0039 }
                            r3.<init>(r0, r4)     // Catch:{ all -> 0x0039 }
                            r2.zzc(r3)     // Catch:{ all -> 0x0039 }
                            goto L_0x0037
                        L_0x0069:
                            com.google.android.gms.common.api.Status r0 = com.google.android.gms.common.api.Status.f163vY     // Catch:{ all -> 0x0039 }
                            goto L_0x0058
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.Batch.C05171.zzv(com.google.android.gms.common.api.Status):void");
                    }
                });
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    static /* synthetic */ int zzb(Batch batch) {
        int i = batch.f130vo;
        batch.f130vo = i - 1;
        return i;
    }

    public void cancel() {
        super.cancel();
        for (PendingResult<?> cancel : this.f133vr) {
            cancel.cancel();
        }
    }

    /* renamed from: createFailedResult */
    public BatchResult zzc(Status status) {
        return new BatchResult(status, this.f133vr);
    }
}
