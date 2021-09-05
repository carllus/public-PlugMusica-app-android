package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzc;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@KeepName
public final class DataHolder extends AbstractSafeParcelable implements Closeable {
    public static final Parcelable.Creator<DataHolder> CREATOR = new zze();

    /* renamed from: zV */
    private static final zza f192zV = new zza(new String[0], (String) null) {
        public zza zza(ContentValues contentValues) {
            throw new UnsupportedOperationException("Cannot add data to empty builder");
        }

        public zza zzb(HashMap<String, Object> hashMap) {
            throw new UnsupportedOperationException("Cannot add data to empty builder");
        }
    };
    boolean mClosed;
    private final int mVersionCode;

    /* renamed from: rR */
    private final int f193rR;

    /* renamed from: zO */
    private final String[] f194zO;

    /* renamed from: zP */
    Bundle f195zP;

    /* renamed from: zQ */
    private final CursorWindow[] f196zQ;

    /* renamed from: zR */
    private final Bundle f197zR;

    /* renamed from: zS */
    int[] f198zS;

    /* renamed from: zT */
    int f199zT;

    /* renamed from: zU */
    private boolean f200zU;

    public static class zza {

        /* renamed from: Aa */
        private String f201Aa;
        /* access modifiers changed from: private */

        /* renamed from: zO */
        public final String[] f202zO;
        /* access modifiers changed from: private */

        /* renamed from: zW */
        public final ArrayList<HashMap<String, Object>> f203zW;

        /* renamed from: zX */
        private final String f204zX;

        /* renamed from: zY */
        private final HashMap<Object, Integer> f205zY;

        /* renamed from: zZ */
        private boolean f206zZ;

        private zza(String[] strArr, String str) {
            this.f202zO = (String[]) zzac.zzy(strArr);
            this.f203zW = new ArrayList<>();
            this.f204zX = str;
            this.f205zY = new HashMap<>();
            this.f206zZ = false;
            this.f201Aa = null;
        }

        private int zzc(HashMap<String, Object> hashMap) {
            if (this.f204zX == null) {
                return -1;
            }
            Object obj = hashMap.get(this.f204zX);
            if (obj == null) {
                return -1;
            }
            Integer num = this.f205zY.get(obj);
            if (num != null) {
                return num.intValue();
            }
            this.f205zY.put(obj, Integer.valueOf(this.f203zW.size()));
            return -1;
        }

        public zza zza(ContentValues contentValues) {
            zzc.zzu(contentValues);
            HashMap hashMap = new HashMap(contentValues.size());
            for (Map.Entry next : contentValues.valueSet()) {
                hashMap.put((String) next.getKey(), next.getValue());
            }
            return zzb((HashMap<String, Object>) hashMap);
        }

        public zza zzb(HashMap<String, Object> hashMap) {
            zzc.zzu(hashMap);
            int zzc = zzc(hashMap);
            if (zzc == -1) {
                this.f203zW.add(hashMap);
            } else {
                this.f203zW.remove(zzc);
                this.f203zW.add(zzc, hashMap);
            }
            this.f206zZ = false;
            return this;
        }

        public DataHolder zzgd(int i) {
            return new DataHolder(this, i, (Bundle) null);
        }
    }

    public static class zzb extends RuntimeException {
        public zzb(String str) {
            super(str);
        }
    }

    DataHolder(int i, String[] strArr, CursorWindow[] cursorWindowArr, int i2, Bundle bundle) {
        this.mClosed = false;
        this.f200zU = true;
        this.mVersionCode = i;
        this.f194zO = strArr;
        this.f196zQ = cursorWindowArr;
        this.f193rR = i2;
        this.f197zR = bundle;
    }

    private DataHolder(zza zza2, int i, Bundle bundle) {
        this(zza2.f202zO, zza(zza2, -1), i, bundle);
    }

    public DataHolder(String[] strArr, CursorWindow[] cursorWindowArr, int i, Bundle bundle) {
        this.mClosed = false;
        this.f200zU = true;
        this.mVersionCode = 1;
        this.f194zO = (String[]) zzac.zzy(strArr);
        this.f196zQ = (CursorWindow[]) zzac.zzy(cursorWindowArr);
        this.f193rR = i;
        this.f197zR = bundle;
        zzate();
    }

    public static DataHolder zza(int i, Bundle bundle) {
        return new DataHolder(f192zV, i, bundle);
    }

    private static CursorWindow[] zza(zza zza2, int i) {
        int i2;
        boolean z;
        CursorWindow cursorWindow;
        if (zza2.f202zO.length == 0) {
            return new CursorWindow[0];
        }
        ArrayList zzb2 = (i < 0 || i >= zza2.f203zW.size()) ? zza2.f203zW : zza2.f203zW.subList(0, i);
        int size = zzb2.size();
        CursorWindow cursorWindow2 = new CursorWindow(false);
        ArrayList arrayList = new ArrayList();
        arrayList.add(cursorWindow2);
        cursorWindow2.setNumColumns(zza2.f202zO.length);
        int i3 = 0;
        boolean z2 = false;
        while (i3 < size) {
            try {
                if (!cursorWindow2.allocRow()) {
                    Log.d("DataHolder", new StringBuilder(72).append("Allocating additional cursor window for large data set (row ").append(i3).append(")").toString());
                    cursorWindow2 = new CursorWindow(false);
                    cursorWindow2.setStartPosition(i3);
                    cursorWindow2.setNumColumns(zza2.f202zO.length);
                    arrayList.add(cursorWindow2);
                    if (!cursorWindow2.allocRow()) {
                        Log.e("DataHolder", "Unable to allocate row to hold data.");
                        arrayList.remove(cursorWindow2);
                        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
                    }
                }
                Map map = (Map) zzb2.get(i3);
                boolean z3 = true;
                for (int i4 = 0; i4 < zza2.f202zO.length && z3; i4++) {
                    String str = zza2.f202zO[i4];
                    Object obj = map.get(str);
                    if (obj == null) {
                        z3 = cursorWindow2.putNull(i3, i4);
                    } else if (obj instanceof String) {
                        z3 = cursorWindow2.putString((String) obj, i3, i4);
                    } else if (obj instanceof Long) {
                        z3 = cursorWindow2.putLong(((Long) obj).longValue(), i3, i4);
                    } else if (obj instanceof Integer) {
                        z3 = cursorWindow2.putLong((long) ((Integer) obj).intValue(), i3, i4);
                    } else if (obj instanceof Boolean) {
                        z3 = cursorWindow2.putLong(((Boolean) obj).booleanValue() ? 1 : 0, i3, i4);
                    } else if (obj instanceof byte[]) {
                        z3 = cursorWindow2.putBlob((byte[]) obj, i3, i4);
                    } else if (obj instanceof Double) {
                        z3 = cursorWindow2.putDouble(((Double) obj).doubleValue(), i3, i4);
                    } else if (obj instanceof Float) {
                        z3 = cursorWindow2.putDouble((double) ((Float) obj).floatValue(), i3, i4);
                    } else {
                        String valueOf = String.valueOf(obj);
                        throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 32 + String.valueOf(valueOf).length()).append("Unsupported object for column ").append(str).append(": ").append(valueOf).toString());
                    }
                }
                if (z3) {
                    i2 = i3;
                    z = false;
                    cursorWindow = cursorWindow2;
                } else if (z2) {
                    throw new zzb("Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.");
                } else {
                    Log.d("DataHolder", new StringBuilder(74).append("Couldn't populate window data for row ").append(i3).append(" - allocating new window.").toString());
                    cursorWindow2.freeLastRow();
                    CursorWindow cursorWindow3 = new CursorWindow(false);
                    cursorWindow3.setStartPosition(i3);
                    cursorWindow3.setNumColumns(zza2.f202zO.length);
                    arrayList.add(cursorWindow3);
                    i2 = i3 - 1;
                    cursorWindow = cursorWindow3;
                    z = true;
                }
                z2 = z;
                cursorWindow2 = cursorWindow;
                i3 = i2 + 1;
            } catch (RuntimeException e) {
                RuntimeException runtimeException = e;
                int size2 = arrayList.size();
                for (int i5 = 0; i5 < size2; i5++) {
                    ((CursorWindow) arrayList.get(i5)).close();
                }
                throw runtimeException;
            }
        }
        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
    }

    public static zza zzc(String[] strArr) {
        return new zza(strArr, (String) null);
    }

    public static DataHolder zzgc(int i) {
        return zza(i, (Bundle) null);
    }

    private void zzi(String str, int i) {
        if (this.f195zP == null || !this.f195zP.containsKey(str)) {
            String valueOf = String.valueOf(str);
            throw new IllegalArgumentException(valueOf.length() != 0 ? "No such column: ".concat(valueOf) : new String("No such column: "));
        } else if (isClosed()) {
            throw new IllegalArgumentException("Buffer is closed.");
        } else if (i < 0 || i >= this.f199zT) {
            throw new CursorIndexOutOfBoundsException(i, this.f199zT);
        }
    }

    public void close() {
        synchronized (this) {
            if (!this.mClosed) {
                this.mClosed = true;
                for (CursorWindow close : this.f196zQ) {
                    close.close();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            if (this.f200zU && this.f196zQ.length > 0 && !isClosed()) {
                close();
                String valueOf = String.valueOf(toString());
                Log.e("DataBuffer", new StringBuilder(String.valueOf(valueOf).length() + 178).append("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: ").append(valueOf).append(")").toString());
            }
        } finally {
            super.finalize();
        }
    }

    public int getCount() {
        return this.f199zT;
    }

    public int getStatusCode() {
        return this.f193rR;
    }

    /* access modifiers changed from: package-private */
    public int getVersionCode() {
        return this.mVersionCode;
    }

    public boolean isClosed() {
        boolean z;
        synchronized (this) {
            z = this.mClosed;
        }
        return z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zze.zza(this, parcel, i);
    }

    public void zza(String str, int i, int i2, CharArrayBuffer charArrayBuffer) {
        zzi(str, i);
        this.f196zQ[i2].copyStringToBuffer(i, this.f195zP.getInt(str), charArrayBuffer);
    }

    public Bundle zzasz() {
        return this.f197zR;
    }

    public void zzate() {
        this.f195zP = new Bundle();
        for (int i = 0; i < this.f194zO.length; i++) {
            this.f195zP.putInt(this.f194zO[i], i);
        }
        this.f198zS = new int[this.f196zQ.length];
        int i2 = 0;
        for (int i3 = 0; i3 < this.f196zQ.length; i3++) {
            this.f198zS[i3] = i2;
            i2 += this.f196zQ[i3].getNumRows() - (i2 - this.f196zQ[i3].getStartPosition());
        }
        this.f199zT = i2;
    }

    /* access modifiers changed from: package-private */
    public String[] zzatf() {
        return this.f194zO;
    }

    /* access modifiers changed from: package-private */
    public CursorWindow[] zzatg() {
        return this.f196zQ;
    }

    public long zzb(String str, int i, int i2) {
        zzi(str, i);
        return this.f196zQ[i2].getLong(i, this.f195zP.getInt(str));
    }

    public int zzc(String str, int i, int i2) {
        zzi(str, i);
        return this.f196zQ[i2].getInt(i, this.f195zP.getInt(str));
    }

    public String zzd(String str, int i, int i2) {
        zzi(str, i);
        return this.f196zQ[i2].getString(i, this.f195zP.getInt(str));
    }

    public boolean zze(String str, int i, int i2) {
        zzi(str, i);
        return Long.valueOf(this.f196zQ[i2].getLong(i, this.f195zP.getInt(str))).longValue() == 1;
    }

    public float zzf(String str, int i, int i2) {
        zzi(str, i);
        return this.f196zQ[i2].getFloat(i, this.f195zP.getInt(str));
    }

    public byte[] zzg(String str, int i, int i2) {
        zzi(str, i);
        return this.f196zQ[i2].getBlob(i, this.f195zP.getInt(str));
    }

    public int zzgb(int i) {
        int i2 = 0;
        zzac.zzbr(i >= 0 && i < this.f199zT);
        while (true) {
            if (i2 >= this.f198zS.length) {
                break;
            } else if (i < this.f198zS[i2]) {
                i2--;
                break;
            } else {
                i2++;
            }
        }
        return i2 == this.f198zS.length ? i2 - 1 : i2;
    }

    public Uri zzh(String str, int i, int i2) {
        String zzd = zzd(str, i, i2);
        if (zzd == null) {
            return null;
        }
        return Uri.parse(zzd);
    }

    public boolean zzhm(String str) {
        return this.f195zP.containsKey(str);
    }

    public boolean zzi(String str, int i, int i2) {
        zzi(str, i);
        return this.f196zQ[i2].isNull(i, this.f195zP.getInt(str));
    }
}
