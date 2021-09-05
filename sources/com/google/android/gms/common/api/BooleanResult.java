package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zzac;

public class BooleanResult implements Result {

    /* renamed from: fp */
    private final Status f139fp;

    /* renamed from: vu */
    private final boolean f140vu;

    public BooleanResult(Status status, boolean z) {
        this.f139fp = (Status) zzac.zzb(status, (Object) "Status must not be null");
        this.f140vu = z;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BooleanResult)) {
            return false;
        }
        BooleanResult booleanResult = (BooleanResult) obj;
        return this.f139fp.equals(booleanResult.f139fp) && this.f140vu == booleanResult.f140vu;
    }

    public Status getStatus() {
        return this.f139fp;
    }

    public boolean getValue() {
        return this.f140vu;
    }

    public final int hashCode() {
        return (this.f140vu ? 1 : 0) + ((this.f139fp.hashCode() + 527) * 31);
    }
}
