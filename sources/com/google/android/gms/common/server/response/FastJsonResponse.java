package com.google.android.gms.common.server.response;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.server.converter.ConverterWrapper;
import com.google.android.gms.common.util.zzc;
import com.google.android.gms.common.util.zzp;
import com.google.android.gms.common.util.zzq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class FastJsonResponse {

    public static class Field<I, O> extends AbstractSafeParcelable {
        public static final zza CREATOR = new zza();

        /* renamed from: DA */
        protected final String f398DA;

        /* renamed from: DB */
        private FieldMappingDictionary f399DB;
        /* access modifiers changed from: private */

        /* renamed from: DC */
        public zza<I, O> f400DC;

        /* renamed from: Dt */
        protected final int f401Dt;

        /* renamed from: Du */
        protected final boolean f402Du;

        /* renamed from: Dv */
        protected final int f403Dv;

        /* renamed from: Dw */
        protected final boolean f404Dw;

        /* renamed from: Dx */
        protected final String f405Dx;

        /* renamed from: Dy */
        protected final int f406Dy;

        /* renamed from: Dz */
        protected final Class<? extends FastJsonResponse> f407Dz;
        private final int mVersionCode;

        Field(int i, int i2, boolean z, int i3, boolean z2, String str, int i4, String str2, ConverterWrapper converterWrapper) {
            this.mVersionCode = i;
            this.f401Dt = i2;
            this.f402Du = z;
            this.f403Dv = i3;
            this.f404Dw = z2;
            this.f405Dx = str;
            this.f406Dy = i4;
            if (str2 == null) {
                this.f407Dz = null;
                this.f398DA = null;
            } else {
                this.f407Dz = SafeParcelResponse.class;
                this.f398DA = str2;
            }
            if (converterWrapper == null) {
                this.f400DC = null;
            } else {
                this.f400DC = converterWrapper.zzavo();
            }
        }

        protected Field(int i, boolean z, int i2, boolean z2, String str, int i3, Class<? extends FastJsonResponse> cls, zza<I, O> zza) {
            this.mVersionCode = 1;
            this.f401Dt = i;
            this.f402Du = z;
            this.f403Dv = i2;
            this.f404Dw = z2;
            this.f405Dx = str;
            this.f406Dy = i3;
            this.f407Dz = cls;
            if (cls == null) {
                this.f398DA = null;
            } else {
                this.f398DA = cls.getCanonicalName();
            }
            this.f400DC = zza;
        }

        public static Field zza(String str, int i, zza<?, ?> zza, boolean z) {
            return new Field(zza.zzavq(), z, zza.zzavr(), false, str, i, (Class<? extends FastJsonResponse>) null, zza);
        }

        public static <T extends FastJsonResponse> Field<T, T> zza(String str, int i, Class<T> cls) {
            return new Field<>(11, false, 11, false, str, i, cls, (zza) null);
        }

        public static <T extends FastJsonResponse> Field<ArrayList<T>, ArrayList<T>> zzb(String str, int i, Class<T> cls) {
            return new Field<>(11, true, 11, true, str, i, cls, (zza) null);
        }

        public static Field<Integer, Integer> zzk(String str, int i) {
            return new Field<>(0, false, 0, false, str, i, (Class<? extends FastJsonResponse>) null, (zza) null);
        }

        public static Field<Boolean, Boolean> zzl(String str, int i) {
            return new Field<>(6, false, 6, false, str, i, (Class<? extends FastJsonResponse>) null, (zza) null);
        }

        public static Field<String, String> zzm(String str, int i) {
            return new Field<>(7, false, 7, false, str, i, (Class<? extends FastJsonResponse>) null, (zza) null);
        }

        public I convertBack(O o) {
            return this.f400DC.convertBack(o);
        }

        public int getVersionCode() {
            return this.mVersionCode;
        }

        public String toString() {
            zzab.zza zzg = zzab.zzx(this).zzg("versionCode", Integer.valueOf(this.mVersionCode)).zzg("typeIn", Integer.valueOf(this.f401Dt)).zzg("typeInArray", Boolean.valueOf(this.f402Du)).zzg("typeOut", Integer.valueOf(this.f403Dv)).zzg("typeOutArray", Boolean.valueOf(this.f404Dw)).zzg("outputFieldName", this.f405Dx).zzg("safeParcelFieldId", Integer.valueOf(this.f406Dy)).zzg("concreteTypeName", zzawa());
            Class<? extends FastJsonResponse> zzavz = zzavz();
            if (zzavz != null) {
                zzg.zzg("concreteType.class", zzavz.getCanonicalName());
            }
            if (this.f400DC != null) {
                zzg.zzg("converterName", this.f400DC.getClass().getCanonicalName());
            }
            return zzg.toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            zza zza = CREATOR;
            zza.zza(this, parcel, i);
        }

        public void zza(FieldMappingDictionary fieldMappingDictionary) {
            this.f399DB = fieldMappingDictionary;
        }

        public int zzavq() {
            return this.f401Dt;
        }

        public int zzavr() {
            return this.f403Dv;
        }

        public boolean zzavv() {
            return this.f402Du;
        }

        public boolean zzavw() {
            return this.f404Dw;
        }

        public String zzavx() {
            return this.f405Dx;
        }

        public int zzavy() {
            return this.f406Dy;
        }

        public Class<? extends FastJsonResponse> zzavz() {
            return this.f407Dz;
        }

        /* access modifiers changed from: package-private */
        public String zzawa() {
            if (this.f398DA == null) {
                return null;
            }
            return this.f398DA;
        }

        public boolean zzawb() {
            return this.f400DC != null;
        }

        /* access modifiers changed from: package-private */
        public ConverterWrapper zzawc() {
            if (this.f400DC == null) {
                return null;
            }
            return ConverterWrapper.zza(this.f400DC);
        }

        public Map<String, Field<?, ?>> zzawd() {
            zzac.zzy(this.f398DA);
            zzac.zzy(this.f399DB);
            return this.f399DB.zzie(this.f398DA);
        }
    }

    public interface zza<I, O> {
        I convertBack(O o);

        int zzavq();

        int zzavr();
    }

    private void zza(StringBuilder sb, Field field, Object obj) {
        if (field.zzavq() == 11) {
            sb.append(((FastJsonResponse) field.zzavz().cast(obj)).toString());
        } else if (field.zzavq() == 7) {
            sb.append("\"");
            sb.append(zzp.zzii((String) obj));
            sb.append("\"");
        } else {
            sb.append(obj);
        }
    }

    private void zza(StringBuilder sb, Field field, ArrayList<Object> arrayList) {
        sb.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(",");
            }
            Object obj = arrayList.get(i);
            if (obj != null) {
                zza(sb, field, obj);
            }
        }
        sb.append("]");
    }

    public String toString() {
        Map<String, Field<?, ?>> zzavs = zzavs();
        StringBuilder sb = new StringBuilder(100);
        for (String next : zzavs.keySet()) {
            Field field = zzavs.get(next);
            if (zza(field)) {
                Object zza2 = zza(field, zzb(field));
                if (sb.length() == 0) {
                    sb.append("{");
                } else {
                    sb.append(",");
                }
                sb.append("\"").append(next).append("\":");
                if (zza2 != null) {
                    switch (field.zzavr()) {
                        case 8:
                            sb.append("\"").append(zzc.zzp((byte[]) zza2)).append("\"");
                            break;
                        case 9:
                            sb.append("\"").append(zzc.zzq((byte[]) zza2)).append("\"");
                            break;
                        case 10:
                            zzq.zza(sb, (HashMap) zza2);
                            break;
                        default:
                            if (!field.zzavv()) {
                                zza(sb, field, zza2);
                                break;
                            } else {
                                zza(sb, field, (ArrayList<Object>) (ArrayList) zza2);
                                break;
                            }
                    }
                } else {
                    sb.append("null");
                }
            }
        }
        if (sb.length() > 0) {
            sb.append("}");
        } else {
            sb.append("{}");
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public <O, I> I zza(Field<I, O> field, Object obj) {
        return field.f400DC != null ? field.convertBack(obj) : obj;
    }

    /* access modifiers changed from: protected */
    public boolean zza(Field field) {
        return field.zzavr() == 11 ? field.zzavw() ? zzid(field.zzavx()) : zzic(field.zzavx()) : zzib(field.zzavx());
    }

    public abstract Map<String, Field<?, ?>> zzavs();

    public HashMap<String, Object> zzavt() {
        return null;
    }

    public HashMap<String, Object> zzavu() {
        return null;
    }

    /* access modifiers changed from: protected */
    public Object zzb(Field field) {
        String zzavx = field.zzavx();
        if (field.zzavz() == null) {
            return zzia(field.zzavx());
        }
        zzac.zza(zzia(field.zzavx()) == null, "Concrete field shouldn't be value object: %s", field.zzavx());
        HashMap<String, Object> zzavu = field.zzavw() ? zzavu() : zzavt();
        if (zzavu != null) {
            return zzavu.get(zzavx);
        }
        try {
            char upperCase = Character.toUpperCase(zzavx.charAt(0));
            String valueOf = String.valueOf(zzavx.substring(1));
            return getClass().getMethod(new StringBuilder(String.valueOf(valueOf).length() + 4).append("get").append(upperCase).append(valueOf).toString(), new Class[0]).invoke(this, new Object[0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: protected */
    public abstract Object zzia(String str);

    /* access modifiers changed from: protected */
    public abstract boolean zzib(String str);

    /* access modifiers changed from: protected */
    public boolean zzic(String str) {
        throw new UnsupportedOperationException("Concrete types not supported");
    }

    /* access modifiers changed from: protected */
    public boolean zzid(String str) {
        throw new UnsupportedOperationException("Concrete type arrays not supported");
    }
}
