package com.google.android.gms.common;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzt;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.util.zzm;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzsu;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class zzd {

    /* renamed from: uO */
    private static zzw f499uO;

    /* renamed from: uP */
    private static Context f500uP;

    /* renamed from: uQ */
    private static Set<zzt> f501uQ;

    /* renamed from: uR */
    private static Set<zzt> f502uR;

    static abstract class zza extends zzt.zza {

        /* renamed from: uS */
        private int f503uS;

        protected zza(byte[] bArr) {
            boolean z = false;
            if (bArr.length != 25) {
                int length = bArr.length;
                String valueOf = String.valueOf(zzm.zza(bArr, 0, bArr.length, false));
                Log.wtf("GoogleCertificates", new StringBuilder(String.valueOf(valueOf).length() + 51).append("Cert hash data has incorrect length (").append(length).append("):\n").append(valueOf).toString(), new Exception());
                bArr = Arrays.copyOfRange(bArr, 0, 25);
                zzac.zzb(bArr.length == 25 ? true : z, (Object) new StringBuilder(55).append("cert hash data has incorrect length. length=").append(bArr.length).toString());
            }
            this.f503uS = Arrays.hashCode(bArr);
        }

        protected static byte[] zzhd(String str) {
            try {
                return str.getBytes("ISO-8859-1");
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError(e);
            }
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof zzt)) {
                return false;
            }
            try {
                zzt zzt = (zzt) obj;
                if (zzt.zzapi() != hashCode()) {
                    return false;
                }
                com.google.android.gms.dynamic.zzd zzaph = zzt.zzaph();
                if (zzaph == null) {
                    return false;
                }
                return Arrays.equals(getBytes(), (byte[]) zze.zzae(zzaph));
            } catch (RemoteException e) {
                Log.e("GoogleCertificates", "iCertData failed to retrive data from remote");
                return false;
            }
        }

        /* access modifiers changed from: package-private */
        public abstract byte[] getBytes();

        public int hashCode() {
            return this.f503uS;
        }

        public com.google.android.gms.dynamic.zzd zzaph() {
            return zze.zzac(getBytes());
        }

        public int zzapi() {
            return hashCode();
        }
    }

    static class zzb extends zza {

        /* renamed from: uT */
        private final byte[] f504uT;

        zzb(byte[] bArr) {
            super(Arrays.copyOfRange(bArr, 0, 25));
            this.f504uT = bArr;
        }

        /* access modifiers changed from: package-private */
        public byte[] getBytes() {
            return this.f504uT;
        }
    }

    static abstract class zzc extends zza {

        /* renamed from: uV */
        private static final WeakReference<byte[]> f505uV = new WeakReference<>((Object) null);

        /* renamed from: uU */
        private WeakReference<byte[]> f506uU = f505uV;

        zzc(byte[] bArr) {
            super(bArr);
        }

        /* access modifiers changed from: package-private */
        public byte[] getBytes() {
            byte[] bArr;
            synchronized (this) {
                bArr = (byte[]) this.f506uU.get();
                if (bArr == null) {
                    bArr = zzapj();
                    this.f506uU = new WeakReference<>(bArr);
                }
            }
            return bArr;
        }

        /* access modifiers changed from: protected */
        public abstract byte[] zzapj();
    }

    /* renamed from: com.google.android.gms.common.zzd$zzd  reason: collision with other inner class name */
    static final class C1106zzd {

        /* renamed from: uW */
        static final zza[] f507uW = {new zzc(zza.zzhd("0??\u0004C0??\u0003+??\u0003\u0002\u0001\u0002\u0002\t\u0000??????FdJ0??0")) {
            /* access modifiers changed from: protected */
            public byte[] zzapj() {
                return zza.zzhd("0??\u0004C0??\u0003+??\u0003\u0002\u0001\u0002\u0002\t\u0000??????FdJ0??0\r\u0006\t*??H????\r\u0001\u0001\u0004\u0005\u00000t1\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\u0013\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00140\u0012\u0006\u0003U\u0004\n\u0013\u000bGoogle Inc.1\u00100\u000e\u0006\u0003U\u0004\u000b\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u0003\u0013\u0007Android0\u001e\u0017\r080821231334Z\u0017\r360107231334Z0t1\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\u0013\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00140\u0012\u0006\u0003U\u0004\n\u0013\u000bGoogle Inc.1\u00100\u000e\u0006\u0003U\u0004\u000b\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u0003\u0013\u0007Android0??\u0001 0\r\u0006\t*??H????\r\u0001\u0001\u0001\u0005\u0000\u0003??\u0001\r\u00000??\u0001\b\u0002??\u0001\u0001\u0000??V.\u0000??;??\b??\n??o\u0012N)??\u0011????V????X??????\u0013\u0003????T??r??@??\u001b\u001d??\u0013\tgbNFV??wj??\u0019=????????$??\u001ew\u0018??\u000ejG??;3??`??w\u00181E????{.Xft????V[\u001fLjYU????Q??=??????\\'\"\"R??u????\u0015Jd_??qh????????\u0012????xWi??4??y????~.??vL????\u0007????qT????_d??\u001aD??\u0002??I\u0005AW??\u0002??_\\\u000eU??????\u0019????'????Q\u0016??????o\u0019???????????????????h??)y??\u000e\u0018??????k;????????U*\u000e;L????X??????????????5??\u0003??????\r??D????$????38r??R!??^??????\r\u000b\u0014[j????????y\u0002\u0001\u0003??????0????0\u001d\u0006\u0003U\u001d\u000e\u0004\u0016\u0004\u0014??}????!\u0017V%????????k????????????0????\u0006\u0003U\u001d#\u0004????0??????\u0014??}????!\u0017V%????????k??????????????x??v0t1\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\u0013\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00140\u0012\u0006\u0003U\u0004\n\u0013\u000bGoogle Inc.1\u00100\u000e\u0006\u0003U\u0004\u000b\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u0003\u0013\u0007Android??\t\u0000??????FdJ0??0\f\u0006\u0003U\u001d\u0013\u0004\u00050\u0003\u0001\u0001??0\r\u0006\t*??H????\r\u0001\u0001\u0004\u0005\u0000\u0003??\u0001\u0001\u0000m??R??????0,6\n????????????????\u0004??]z\u0016a????F????B\u0004????Jh????\u001aS\u001e??YZb<??\u0007c??g)zz??W\u0012??\u0007??\b????\u0010??)\u0012M{\u0010b\u0019??????>??????_??q????&??????????mD????????l????\u0005?????????D~????s\u0010v??E???`\t??\u0019??a??&A????'\u001d??R(????????]??E'X??a????\f????5.BL??6\\R52??2Q7Y<J??A????A????\r\u000b\u0010q????@????????\u001c??'??gCi??????/??\u0011??\u0006????,??\u0010??\u000f??:??Wb????H??????LqD\u0017??B??\u0005??????W:??[9\r??????A??1??]_u??0\u0011&????b\u0014\u0010??i0??");
            }
        }, new zzc(zza.zzhd("0??\u0004??0??\u0003????\u0003\u0002\u0001\u0002\u0002\t\u0000??????l}??N??0")) {
            /* access modifiers changed from: protected */
            public byte[] zzapj() {
                return zza.zzhd("0??\u0004??0??\u0003????\u0003\u0002\u0001\u0002\u0002\t\u0000??????l}??N??0\r\u0006\t*??H????\r\u0001\u0001\u0004\u0005\u00000????1\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\u0013\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00100\u000e\u0006\u0003U\u0004\n\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u000b\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u0003\u0013\u0007Android1\"0 \u0006\t*??H????\r\u0001\t\u0001\u0016\u0013android@android.com0\u001e\u0017\r080415233656Z\u0017\r350901233656Z0????1\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\u0013\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00100\u000e\u0006\u0003U\u0004\n\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u000b\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u0003\u0013\u0007Android1\"0 \u0006\t*??H????\r\u0001\t\u0001\u0016\u0013android@android.com0??\u0001 0\r\u0006\t*??H????\r\u0001\u0001\u0001\u0005\u0000\u0003??\u0001\r\u00000??\u0001\b\u0002??\u0001\u0001\u0000????.\b\n????1M??????????\u0018\\??=3??\ft????????????\u0013??,\\9??V??F??=e????????Bk\u0007??????Z9????g??k??????'??K??\u000b\"\u0000\u0019????)\u0015??r??m*0\u001b??o????\u0011:??????t5??m#??}??????e????\u001f\n??????\n????QlN??\u0005\u0011????|\fU\u0017[??u??H??j????\b????O??????????}??,\n5\"????\u0006????\u0018^??Uy????m\b\u000b\u001da??????????????????\u0007??E????h????????^T????lS??\u000b\u0012\u001d??????\u000eb\f\u0018??????a????????<d_/U??????u??@p???qQ??6p??j??\u001a??^????\u0018??????????)????f????l??D??????m\u001c\u001b\u000f\u0002\u0001\u0003??????0????0\u001d\u0006\u0003U\u001d\u000e\u0004\u0016\u0004\u0014??\u001c??????LC<a??:\u0015??L??\u0003??O????0????\u0006\u0003U\u001d#\u0004????0??????\u0014??\u001c??????LC<a??:\u0015??L??\u0003??O????????????????0????1\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\u0013\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00100\u000e\u0006\u0003U\u0004\n\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u000b\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u0003\u0013\u0007Android1\"0 \u0006\t*??H????\r\u0001\t\u0001\u0016\u0013android@android.com??\t\u0000??????l}??N??0\f\u0006\u0003U\u001d\u0013\u0004\u00050\u0003\u0001\u0001??0\r\u0006\t*??H????\r\u0001\u0001\u0004\u0005\u0000\u0003??\u0001\u0001\u0000\u0019??\f??\u0005??x???L\r}??##=@??z????\u0000\b\u001d[?????????? k\u000e\u0011 ??\u0006Al??D????\u0013??kJ??????$??????\\nL??\u0001j\u0015??n????]??Z^:\u0001\u00006????H??\u0010????.\u001ea????g:;??m??\u000bw????)????U????L??]#??????\t????; +NZ\"??2cHJ#????)????\u00199u??3??????\u0016\u000fB??????\u0016>????????fC??????/??????33[????????k\"??????DB)??9??N????????e????K>Q????{fx{??\u0012????????????#??O????IL\u0002????\u0005\u0016\u0012??e)9>??F??????!??w??Q??_*??'????????\n??\u00035i??;????????|????>\u0012C??\u000b");
            }
        }};
    }

    private static Set<zzt> zza(IBinder[] iBinderArr) throws RemoteException {
        HashSet hashSet = new HashSet(r1);
        for (IBinder zzdt : iBinderArr) {
            zzt zzdt2 = zzt.zza.zzdt(zzdt);
            if (zzdt2 == null) {
                Log.e("GoogleCertificates", "iCertData is null, skipping");
            } else {
                hashSet.add(zzdt2);
            }
        }
        return hashSet;
    }

    private static boolean zzape() {
        zzac.zzy(f500uP);
        if (f499uO == null) {
            try {
                zzsu zza2 = zzsu.zza(f500uP, zzsu.f868OC, "com.google.android.gms.googlecertificates");
                Log.d("GoogleCertificates", "com.google.android.gms.googlecertificates module is loaded");
                f499uO = zzw.zza.zzdw(zza2.zzjd("com.google.android.gms.common.GoogleCertificatesImpl"));
            } catch (zzsu.zza e) {
                String valueOf = String.valueOf("Failed to load com.google.android.gms.googlecertificates: ");
                String valueOf2 = String.valueOf(e.getMessage());
                Log.e("GoogleCertificates", valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0054, code lost:
        android.util.Log.e("GoogleCertificates", "Failed to retrieve google certificates");
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static synchronized java.util.Set<com.google.android.gms.common.internal.zzt> zzapf() {
        /*
            java.lang.Class<com.google.android.gms.common.zzd> r1 = com.google.android.gms.common.zzd.class
            monitor-enter(r1)
            java.util.Set<com.google.android.gms.common.internal.zzt> r0 = f501uQ     // Catch:{ all -> 0x005c }
            if (r0 == 0) goto L_0x000b
            java.util.Set<com.google.android.gms.common.internal.zzt> r0 = f501uQ     // Catch:{ all -> 0x005c }
        L_0x0009:
            monitor-exit(r1)
            return r0
        L_0x000b:
            com.google.android.gms.common.internal.zzw r0 = f499uO     // Catch:{ all -> 0x005c }
            if (r0 != 0) goto L_0x0018
            boolean r0 = zzape()     // Catch:{ all -> 0x005c }
            if (r0 != 0) goto L_0x0018
            java.util.Set r0 = java.util.Collections.EMPTY_SET     // Catch:{ all -> 0x005c }
            goto L_0x0009
        L_0x0018:
            com.google.android.gms.common.internal.zzw r0 = f499uO     // Catch:{ RemoteException -> 0x0053 }
            com.google.android.gms.dynamic.zzd r0 = r0.zzauz()     // Catch:{ RemoteException -> 0x0053 }
            if (r0 != 0) goto L_0x002a
            java.lang.String r0 = "GoogleCertificates"
            java.lang.String r2 = "Failed to get google certificates from remote"
            android.util.Log.e(r0, r2)     // Catch:{ RemoteException -> 0x0053 }
            java.util.Set r0 = java.util.Collections.EMPTY_SET     // Catch:{ RemoteException -> 0x0053 }
            goto L_0x0009
        L_0x002a:
            java.lang.Object r0 = com.google.android.gms.dynamic.zze.zzae(r0)     // Catch:{ RemoteException -> 0x0053 }
            android.os.IBinder[] r0 = (android.os.IBinder[]) r0     // Catch:{ RemoteException -> 0x0053 }
            java.util.Set r0 = zza(r0)     // Catch:{ RemoteException -> 0x0053 }
            f501uQ = r0     // Catch:{ RemoteException -> 0x0053 }
            r0 = 0
        L_0x0037:
            com.google.android.gms.common.zzd$zza[] r2 = com.google.android.gms.common.zzd.C1106zzd.f507uW     // Catch:{ RemoteException -> 0x0053 }
            int r2 = r2.length     // Catch:{ RemoteException -> 0x0053 }
            if (r0 >= r2) goto L_0x0048
            java.util.Set<com.google.android.gms.common.internal.zzt> r2 = f501uQ     // Catch:{ RemoteException -> 0x0053 }
            com.google.android.gms.common.zzd$zza[] r3 = com.google.android.gms.common.zzd.C1106zzd.f507uW     // Catch:{ RemoteException -> 0x0053 }
            r3 = r3[r0]     // Catch:{ RemoteException -> 0x0053 }
            r2.add(r3)     // Catch:{ RemoteException -> 0x0053 }
            int r0 = r0 + 1
            goto L_0x0037
        L_0x0048:
            java.util.Set<com.google.android.gms.common.internal.zzt> r0 = f501uQ     // Catch:{ RemoteException -> 0x0053 }
            java.util.Set r0 = java.util.Collections.unmodifiableSet(r0)     // Catch:{ RemoteException -> 0x0053 }
            f501uQ = r0     // Catch:{ RemoteException -> 0x0053 }
        L_0x0050:
            java.util.Set<com.google.android.gms.common.internal.zzt> r0 = f501uQ     // Catch:{ all -> 0x005c }
            goto L_0x0009
        L_0x0053:
            r0 = move-exception
            java.lang.String r0 = "GoogleCertificates"
            java.lang.String r2 = "Failed to retrieve google certificates"
            android.util.Log.e(r0, r2)     // Catch:{ all -> 0x005c }
            goto L_0x0050
        L_0x005c:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.zzd.zzapf():java.util.Set");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004c, code lost:
        android.util.Log.e("GoogleCertificates", "Failed to retrieve google release certificates");
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static synchronized java.util.Set<com.google.android.gms.common.internal.zzt> zzapg() {
        /*
            java.lang.Class<com.google.android.gms.common.zzd> r1 = com.google.android.gms.common.zzd.class
            monitor-enter(r1)
            java.util.Set<com.google.android.gms.common.internal.zzt> r0 = f502uR     // Catch:{ all -> 0x0054 }
            if (r0 == 0) goto L_0x000b
            java.util.Set<com.google.android.gms.common.internal.zzt> r0 = f502uR     // Catch:{ all -> 0x0054 }
        L_0x0009:
            monitor-exit(r1)
            return r0
        L_0x000b:
            com.google.android.gms.common.internal.zzw r0 = f499uO     // Catch:{ all -> 0x0054 }
            if (r0 != 0) goto L_0x0018
            boolean r0 = zzape()     // Catch:{ all -> 0x0054 }
            if (r0 != 0) goto L_0x0018
            java.util.Set r0 = java.util.Collections.EMPTY_SET     // Catch:{ all -> 0x0054 }
            goto L_0x0009
        L_0x0018:
            com.google.android.gms.common.internal.zzw r0 = f499uO     // Catch:{ RemoteException -> 0x004b }
            com.google.android.gms.dynamic.zzd r0 = r0.zzava()     // Catch:{ RemoteException -> 0x004b }
            if (r0 != 0) goto L_0x002a
            java.lang.String r0 = "GoogleCertificates"
            java.lang.String r2 = "Failed to get google release certificates from remote"
            android.util.Log.d(r0, r2)     // Catch:{ RemoteException -> 0x004b }
            java.util.Set r0 = java.util.Collections.EMPTY_SET     // Catch:{ RemoteException -> 0x004b }
            goto L_0x0009
        L_0x002a:
            java.lang.Object r0 = com.google.android.gms.dynamic.zze.zzae(r0)     // Catch:{ RemoteException -> 0x004b }
            android.os.IBinder[] r0 = (android.os.IBinder[]) r0     // Catch:{ RemoteException -> 0x004b }
            java.util.Set r0 = zza(r0)     // Catch:{ RemoteException -> 0x004b }
            f502uR = r0     // Catch:{ RemoteException -> 0x004b }
            java.util.Set<com.google.android.gms.common.internal.zzt> r0 = f502uR     // Catch:{ RemoteException -> 0x004b }
            com.google.android.gms.common.zzd$zza[] r2 = com.google.android.gms.common.zzd.C1106zzd.f507uW     // Catch:{ RemoteException -> 0x004b }
            r3 = 0
            r2 = r2[r3]     // Catch:{ RemoteException -> 0x004b }
            r0.add(r2)     // Catch:{ RemoteException -> 0x004b }
            java.util.Set<com.google.android.gms.common.internal.zzt> r0 = f502uR     // Catch:{ RemoteException -> 0x004b }
            java.util.Set r0 = java.util.Collections.unmodifiableSet(r0)     // Catch:{ RemoteException -> 0x004b }
            f502uR = r0     // Catch:{ RemoteException -> 0x004b }
        L_0x0048:
            java.util.Set<com.google.android.gms.common.internal.zzt> r0 = f502uR     // Catch:{ all -> 0x0054 }
            goto L_0x0009
        L_0x004b:
            r0 = move-exception
            java.lang.String r0 = "GoogleCertificates"
            java.lang.String r2 = "Failed to retrieve google release certificates"
            android.util.Log.e(r0, r2)     // Catch:{ all -> 0x0054 }
            goto L_0x0048
        L_0x0054:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.zzd.zzapg():java.util.Set");
    }

    static synchronized void zzbr(Context context) {
        synchronized (zzd.class) {
            if (f500uP != null) {
                Log.w("GoogleCertificates", "GoogleCertificates has been initialized already");
            } else if (context != null) {
                f500uP = context.getApplicationContext();
            }
        }
    }
}
