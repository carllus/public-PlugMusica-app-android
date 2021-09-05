package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public abstract class DowngradeableSafeParcel extends AbstractSafeParcelable implements ReflectedParcelable {

    /* renamed from: Ce */
    private static final Object f251Ce = new Object();

    /* renamed from: Cf */
    private static ClassLoader f252Cf = null;

    /* renamed from: Cg */
    private static Integer f253Cg = null;

    /* renamed from: Ch */
    private boolean f254Ch = false;

    protected static ClassLoader zzaup() {
        synchronized (f251Ce) {
        }
        return null;
    }

    protected static Integer zzauq() {
        synchronized (f251Ce) {
        }
        return null;
    }

    private static boolean zzd(Class<?> cls) {
        try {
            return SafeParcelable.NULL.equals(cls.getField("NULL").get((Object) null));
        } catch (IllegalAccessException | NoSuchFieldException e) {
            return false;
        }
    }

    protected static boolean zzhs(String str) {
        ClassLoader zzaup = zzaup();
        if (zzaup == null) {
            return true;
        }
        try {
            return zzd(zzaup.loadClass(str));
        } catch (Exception e) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public boolean zzaur() {
        return false;
    }
}
