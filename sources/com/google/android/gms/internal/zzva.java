package com.google.android.gms.internal;

public final class zzva {

    /* renamed from: Uw */
    private static zzva f879Uw;

    /* renamed from: Ux */
    private final zzux f880Ux = new zzux();

    /* renamed from: Uy */
    private final zzuy f881Uy = new zzuy();

    static {
        zza(new zzva());
    }

    private zzva() {
    }

    protected static void zza(zzva zzva) {
        synchronized (zzva.class) {
            f879Uw = zzva;
        }
    }

    private static zzva zzbhl() {
        zzva zzva;
        synchronized (zzva.class) {
            zzva = f879Uw;
        }
        return zzva;
    }

    public static zzux zzbhm() {
        return zzbhl().f880Ux;
    }

    public static zzuy zzbhn() {
        return zzbhl().f881Uy;
    }
}
