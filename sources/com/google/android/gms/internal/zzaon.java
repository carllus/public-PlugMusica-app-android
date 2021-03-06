package com.google.android.gms.internal;

import java.math.BigInteger;

public final class zzaon extends zzaoh {
    private static final Class<?>[] blf = {Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};
    private Object value;

    public zzaon(Boolean bool) {
        setValue(bool);
    }

    public zzaon(Number number) {
        setValue(number);
    }

    zzaon(Object obj) {
        setValue(obj);
    }

    public zzaon(String str) {
        setValue(str);
    }

    private static boolean zza(zzaon zzaon) {
        if (!(zzaon.value instanceof Number)) {
            return false;
        }
        Number number = (Number) zzaon.value;
        return (number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte);
    }

    private static boolean zzcn(Object obj) {
        if (obj instanceof String) {
            return true;
        }
        Class<?> cls = obj.getClass();
        for (Class<?> isAssignableFrom : blf) {
            if (isAssignableFrom.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: aQ */
    public Number mo10069aQ() {
        return this.value instanceof String ? new zzape((String) this.value) : (Number) this.value;
    }

    /* renamed from: aR */
    public String mo10070aR() {
        return mo10105bb() ? mo10069aQ().toString() : mo10104ba() ? mo10088aZ().toString() : (String) this.value;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: aZ */
    public Boolean mo10088aZ() {
        return (Boolean) this.value;
    }

    /* renamed from: ba */
    public boolean mo10104ba() {
        return this.value instanceof Boolean;
    }

    /* renamed from: bb */
    public boolean mo10105bb() {
        return this.value instanceof Number;
    }

    /* renamed from: bc */
    public boolean mo10106bc() {
        return this.value instanceof String;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzaon zzaon = (zzaon) obj;
        if (this.value == null) {
            return zzaon.value == null;
        }
        if (zza(this) && zza(zzaon)) {
            return mo10069aQ().longValue() == zzaon.mo10069aQ().longValue();
        }
        if (!(this.value instanceof Number) || !(zzaon.value instanceof Number)) {
            return this.value.equals(zzaon.value);
        }
        double doubleValue = mo10069aQ().doubleValue();
        double doubleValue2 = zzaon.mo10069aQ().doubleValue();
        if (doubleValue == doubleValue2 || (Double.isNaN(doubleValue) && Double.isNaN(doubleValue2))) {
            z = true;
        }
        return z;
    }

    public boolean getAsBoolean() {
        return mo10104ba() ? mo10088aZ().booleanValue() : Boolean.parseBoolean(mo10070aR());
    }

    public double getAsDouble() {
        return mo10105bb() ? mo10069aQ().doubleValue() : Double.parseDouble(mo10070aR());
    }

    public int getAsInt() {
        return mo10105bb() ? mo10069aQ().intValue() : Integer.parseInt(mo10070aR());
    }

    public long getAsLong() {
        return mo10105bb() ? mo10069aQ().longValue() : Long.parseLong(mo10070aR());
    }

    public int hashCode() {
        if (this.value == null) {
            return 31;
        }
        if (zza(this)) {
            long longValue = mo10069aQ().longValue();
            return (int) (longValue ^ (longValue >>> 32));
        } else if (!(this.value instanceof Number)) {
            return this.value.hashCode();
        } else {
            long doubleToLongBits = Double.doubleToLongBits(mo10069aQ().doubleValue());
            return (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
        }
    }

    /* access modifiers changed from: package-private */
    public void setValue(Object obj) {
        if (obj instanceof Character) {
            this.value = String.valueOf(((Character) obj).charValue());
            return;
        }
        zzaoz.zzbs((obj instanceof Number) || zzcn(obj));
        this.value = obj;
    }
}
