package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zzac;
import java.util.concurrent.TimeUnit;

public final class BatchResult implements Result {

    /* renamed from: fp */
    private final Status f137fp;

    /* renamed from: vr */
    private final PendingResult<?>[] f138vr;

    BatchResult(Status status, PendingResult<?>[] pendingResultArr) {
        this.f137fp = status;
        this.f138vr = pendingResultArr;
    }

    public Status getStatus() {
        return this.f137fp;
    }

    public <R extends Result> R take(BatchResultToken<R> batchResultToken) {
        zzac.zzb(batchResultToken.mId < this.f138vr.length, (Object) "The result token does not belong to this batch");
        return this.f138vr[batchResultToken.mId].await(0, TimeUnit.MILLISECONDS);
    }
}
