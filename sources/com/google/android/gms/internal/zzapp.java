package com.google.android.gms.internal;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public final class zzapp extends zzaqa {
    private static final Writer bmx = new Writer() {
        public void close() throws IOException {
            throw new AssertionError();
        }

        public void flush() throws IOException {
            throw new AssertionError();
        }

        public void write(char[] cArr, int i, int i2) {
            throw new AssertionError();
        }
    };
    private static final zzaon bmy = new zzaon("closed");
    private zzaoh bmA = zzaoj.bld;
    private final List<zzaoh> bmw = new ArrayList();
    private String bmz;

    public zzapp() {
        super(bmx);
    }

    /* renamed from: bs */
    private zzaoh m74bs() {
        return this.bmw.get(this.bmw.size() - 1);
    }

    private void zzd(zzaoh zzaoh) {
        if (this.bmz != null) {
            if (!zzaoh.mo10084aV() || mo10314bK()) {
                ((zzaok) m74bs()).zza(this.bmz, zzaoh);
            }
            this.bmz = null;
        } else if (this.bmw.isEmpty()) {
            this.bmA = zzaoh;
        } else {
            zzaoh bs = m74bs();
            if (bs instanceof zzaoe) {
                ((zzaoe) bs).zzc(zzaoh);
                return;
            }
            throw new IllegalStateException();
        }
    }

    /* renamed from: br */
    public zzaoh mo10218br() {
        if (this.bmw.isEmpty()) {
            return this.bmA;
        }
        String valueOf = String.valueOf(this.bmw);
        throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 34).append("Expected one JSON element but was ").append(valueOf).toString());
    }

    /* renamed from: bt */
    public zzaqa mo10219bt() throws IOException {
        zzaoe zzaoe = new zzaoe();
        zzd(zzaoe);
        this.bmw.add(zzaoe);
        return this;
    }

    /* renamed from: bu */
    public zzaqa mo10220bu() throws IOException {
        if (this.bmw.isEmpty() || this.bmz != null) {
            throw new IllegalStateException();
        } else if (m74bs() instanceof zzaoe) {
            this.bmw.remove(this.bmw.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    /* renamed from: bv */
    public zzaqa mo10221bv() throws IOException {
        zzaok zzaok = new zzaok();
        zzd(zzaok);
        this.bmw.add(zzaok);
        return this;
    }

    /* renamed from: bw */
    public zzaqa mo10222bw() throws IOException {
        if (this.bmw.isEmpty() || this.bmz != null) {
            throw new IllegalStateException();
        } else if (m74bs() instanceof zzaok) {
            this.bmw.remove(this.bmw.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    /* renamed from: bx */
    public zzaqa mo10223bx() throws IOException {
        zzd(zzaoj.bld);
        return this;
    }

    public void close() throws IOException {
        if (!this.bmw.isEmpty()) {
            throw new IOException("Incomplete document");
        }
        this.bmw.add(bmy);
    }

    public void flush() throws IOException {
    }

    public zzaqa zza(Number number) throws IOException {
        if (number == null) {
            return mo10223bx();
        }
        if (!isLenient()) {
            double doubleValue = number.doubleValue();
            if (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue)) {
                String valueOf = String.valueOf(number);
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 33).append("JSON forbids NaN and infinities: ").append(valueOf).toString());
            }
        }
        zzd(new zzaon(number));
        return this;
    }

    public zzaqa zzcu(long j) throws IOException {
        zzd(new zzaon((Number) Long.valueOf(j)));
        return this;
    }

    public zzaqa zzdf(boolean z) throws IOException {
        zzd(new zzaon(Boolean.valueOf(z)));
        return this;
    }

    public zzaqa zzus(String str) throws IOException {
        if (this.bmw.isEmpty() || this.bmz != null) {
            throw new IllegalStateException();
        } else if (m74bs() instanceof zzaok) {
            this.bmz = str;
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public zzaqa zzut(String str) throws IOException {
        if (str == null) {
            return mo10223bx();
        }
        zzd(new zzaon(str));
        return this;
    }
}
