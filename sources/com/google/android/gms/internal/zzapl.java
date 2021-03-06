package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

public final class zzapl implements zzaou {
    private final zzapb bkM;

    private static final class zza<E> extends zzaot<Collection<E>> {
        private final zzaot<E> bms;
        private final zzapg<? extends Collection<E>> bmt;

        public zza(zzaob zzaob, Type type, zzaot<E> zzaot, zzapg<? extends Collection<E>> zzapg) {
            this.bms = new zzapv(zzaob, zzaot, type);
            this.bmt = zzapg;
        }

        public void zza(zzaqa zzaqa, Collection<E> collection) throws IOException {
            if (collection == null) {
                zzaqa.mo10223bx();
                return;
            }
            zzaqa.mo10219bt();
            for (E zza : collection) {
                this.bms.zza(zzaqa, zza);
            }
            zzaqa.mo10220bu();
        }

        /* renamed from: zzj */
        public Collection<E> zzb(zzapy zzapy) throws IOException {
            if (zzapy.mo10201bn() == zzapz.NULL) {
                zzapy.nextNull();
                return null;
            }
            Collection<E> collection = (Collection) this.bmt.mo10134bg();
            zzapy.beginArray();
            while (zzapy.hasNext()) {
                collection.add(this.bms.zzb(zzapy));
            }
            zzapy.endArray();
            return collection;
        }
    }

    public zzapl(zzapb zzapb) {
        this.bkM = zzapb;
    }

    public <T> zzaot<T> zza(zzaob zzaob, zzapx<T> zzapx) {
        Type bz = zzapx.mo10301bz();
        Class<? super T> by = zzapx.mo10300by();
        if (!Collection.class.isAssignableFrom(by)) {
            return null;
        }
        Type zza2 = zzapa.zza(bz, (Class<?>) by);
        return new zza(zzaob, zza2, zzaob.zza(zzapx.zzl(zza2)), this.bkM.zzb(zzapx));
    }
}
