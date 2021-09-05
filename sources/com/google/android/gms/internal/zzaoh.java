package com.google.android.gms.internal;

import java.io.IOException;
import java.io.StringWriter;

public abstract class zzaoh {
    /* renamed from: aQ */
    public Number mo10069aQ() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: aR */
    public String mo10070aR() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: aS */
    public boolean mo10081aS() {
        return this instanceof zzaoe;
    }

    /* renamed from: aT */
    public boolean mo10082aT() {
        return this instanceof zzaok;
    }

    /* renamed from: aU */
    public boolean mo10083aU() {
        return this instanceof zzaon;
    }

    /* renamed from: aV */
    public boolean mo10084aV() {
        return this instanceof zzaoj;
    }

    /* renamed from: aW */
    public zzaok mo10085aW() {
        if (mo10082aT()) {
            return (zzaok) this;
        }
        String valueOf = String.valueOf(this);
        throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 19).append("Not a JSON Object: ").append(valueOf).toString());
    }

    /* renamed from: aX */
    public zzaoe mo10086aX() {
        if (mo10081aS()) {
            return (zzaoe) this;
        }
        throw new IllegalStateException("This is not a JSON Array.");
    }

    /* renamed from: aY */
    public zzaon mo10087aY() {
        if (mo10083aU()) {
            return (zzaon) this;
        }
        throw new IllegalStateException("This is not a JSON Primitive.");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: aZ */
    public Boolean mo10088aZ() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public boolean getAsBoolean() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public double getAsDouble() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public int getAsInt() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public long getAsLong() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            zzaqa zzaqa = new zzaqa(stringWriter);
            zzaqa.setLenient(true);
            zzapi.zzb(this, zzaqa);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
