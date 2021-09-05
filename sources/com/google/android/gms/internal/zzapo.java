package com.google.android.gms.internal;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class zzapo extends zzapy {
    private static final Reader bmu = new Reader() {
        public void close() throws IOException {
            throw new AssertionError();
        }

        public int read(char[] cArr, int i, int i2) throws IOException {
            throw new AssertionError();
        }
    };
    private static final Object bmv = new Object();
    private final List<Object> bmw = new ArrayList();

    public zzapo(zzaoh zzaoh) {
        super(bmu);
        this.bmw.add(zzaoh);
    }

    /* renamed from: bo */
    private Object m70bo() {
        return this.bmw.get(this.bmw.size() - 1);
    }

    /* renamed from: bp */
    private Object m71bp() {
        return this.bmw.remove(this.bmw.size() - 1);
    }

    private void zza(zzapz zzapz) throws IOException {
        if (mo10201bn() != zzapz) {
            String valueOf = String.valueOf(zzapz);
            String valueOf2 = String.valueOf(mo10201bn());
            throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 18 + String.valueOf(valueOf2).length()).append("Expected ").append(valueOf).append(" but was ").append(valueOf2).toString());
        }
    }

    public void beginArray() throws IOException {
        zza(zzapz.BEGIN_ARRAY);
        this.bmw.add(((zzaoe) m70bo()).iterator());
    }

    public void beginObject() throws IOException {
        zza(zzapz.BEGIN_OBJECT);
        this.bmw.add(((zzaok) m70bo()).entrySet().iterator());
    }

    /* renamed from: bn */
    public zzapz mo10201bn() throws IOException {
        if (this.bmw.isEmpty()) {
            return zzapz.END_DOCUMENT;
        }
        Object bo = m70bo();
        if (bo instanceof Iterator) {
            boolean z = this.bmw.get(this.bmw.size() - 2) instanceof zzaok;
            Iterator it = (Iterator) bo;
            if (!it.hasNext()) {
                return z ? zzapz.END_OBJECT : zzapz.END_ARRAY;
            }
            if (z) {
                return zzapz.NAME;
            }
            this.bmw.add(it.next());
            return mo10201bn();
        } else if (bo instanceof zzaok) {
            return zzapz.BEGIN_OBJECT;
        } else {
            if (bo instanceof zzaoe) {
                return zzapz.BEGIN_ARRAY;
            }
            if (bo instanceof zzaon) {
                zzaon zzaon = (zzaon) bo;
                if (zzaon.mo10106bc()) {
                    return zzapz.STRING;
                }
                if (zzaon.mo10104ba()) {
                    return zzapz.BOOLEAN;
                }
                if (zzaon.mo10105bb()) {
                    return zzapz.NUMBER;
                }
                throw new AssertionError();
            } else if (bo instanceof zzaoj) {
                return zzapz.NULL;
            } else {
                if (bo == bmv) {
                    throw new IllegalStateException("JsonReader is closed");
                }
                throw new AssertionError();
            }
        }
    }

    /* renamed from: bq */
    public void mo10202bq() throws IOException {
        zza(zzapz.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) m70bo()).next();
        this.bmw.add(entry.getValue());
        this.bmw.add(new zzaon((String) entry.getKey()));
    }

    public void close() throws IOException {
        this.bmw.clear();
        this.bmw.add(bmv);
    }

    public void endArray() throws IOException {
        zza(zzapz.END_ARRAY);
        m71bp();
        m71bp();
    }

    public void endObject() throws IOException {
        zza(zzapz.END_OBJECT);
        m71bp();
        m71bp();
    }

    public boolean hasNext() throws IOException {
        zzapz bn = mo10201bn();
        return (bn == zzapz.END_OBJECT || bn == zzapz.END_ARRAY) ? false : true;
    }

    public boolean nextBoolean() throws IOException {
        zza(zzapz.BOOLEAN);
        return ((zzaon) m71bp()).getAsBoolean();
    }

    public double nextDouble() throws IOException {
        zzapz bn = mo10201bn();
        if (bn == zzapz.NUMBER || bn == zzapz.STRING) {
            double asDouble = ((zzaon) m70bo()).getAsDouble();
            if (isLenient() || (!Double.isNaN(asDouble) && !Double.isInfinite(asDouble))) {
                m71bp();
                return asDouble;
            }
            throw new NumberFormatException(new StringBuilder(57).append("JSON forbids NaN and infinities: ").append(asDouble).toString());
        }
        String valueOf = String.valueOf(zzapz.NUMBER);
        String valueOf2 = String.valueOf(bn);
        throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 18 + String.valueOf(valueOf2).length()).append("Expected ").append(valueOf).append(" but was ").append(valueOf2).toString());
    }

    public int nextInt() throws IOException {
        zzapz bn = mo10201bn();
        if (bn == zzapz.NUMBER || bn == zzapz.STRING) {
            int asInt = ((zzaon) m70bo()).getAsInt();
            m71bp();
            return asInt;
        }
        String valueOf = String.valueOf(zzapz.NUMBER);
        String valueOf2 = String.valueOf(bn);
        throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 18 + String.valueOf(valueOf2).length()).append("Expected ").append(valueOf).append(" but was ").append(valueOf2).toString());
    }

    public long nextLong() throws IOException {
        zzapz bn = mo10201bn();
        if (bn == zzapz.NUMBER || bn == zzapz.STRING) {
            long asLong = ((zzaon) m70bo()).getAsLong();
            m71bp();
            return asLong;
        }
        String valueOf = String.valueOf(zzapz.NUMBER);
        String valueOf2 = String.valueOf(bn);
        throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 18 + String.valueOf(valueOf2).length()).append("Expected ").append(valueOf).append(" but was ").append(valueOf2).toString());
    }

    public String nextName() throws IOException {
        zza(zzapz.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) m70bo()).next();
        this.bmw.add(entry.getValue());
        return (String) entry.getKey();
    }

    public void nextNull() throws IOException {
        zza(zzapz.NULL);
        m71bp();
    }

    public String nextString() throws IOException {
        zzapz bn = mo10201bn();
        if (bn == zzapz.STRING || bn == zzapz.NUMBER) {
            return ((zzaon) m71bp()).mo10070aR();
        }
        String valueOf = String.valueOf(zzapz.STRING);
        String valueOf2 = String.valueOf(bn);
        throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 18 + String.valueOf(valueOf2).length()).append("Expected ").append(valueOf).append(" but was ").append(valueOf2).toString());
    }

    public void skipValue() throws IOException {
        if (mo10201bn() == zzapz.NAME) {
            nextName();
        } else {
            m71bp();
        }
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
