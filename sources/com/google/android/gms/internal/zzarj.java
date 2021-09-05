package com.google.android.gms.internal;

import java.io.IOException;

public class zzarj extends IOException {
    public zzarj(String str) {
        super(str);
    }

    /* renamed from: cT */
    static zzarj m127cT() {
        return new zzarj("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
    }

    /* renamed from: cU */
    static zzarj m128cU() {
        return new zzarj("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    /* renamed from: cV */
    static zzarj m129cV() {
        return new zzarj("CodedInputStream encountered a malformed varint.");
    }

    /* renamed from: cW */
    static zzarj m130cW() {
        return new zzarj("Protocol message contained an invalid tag (zero).");
    }

    /* renamed from: cX */
    static zzarj m131cX() {
        return new zzarj("Protocol message end-group tag did not match expected tag.");
    }

    /* renamed from: cY */
    static zzarj m132cY() {
        return new zzarj("Protocol message tag had invalid wire type.");
    }

    /* renamed from: cZ */
    static zzarj m133cZ() {
        return new zzarj("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }
}
