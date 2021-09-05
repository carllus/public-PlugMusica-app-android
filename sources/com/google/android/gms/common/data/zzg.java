package com.google.android.gms.common.data;

import java.util.NoSuchElementException;

public class zzg<T> extends zzb<T> {

    /* renamed from: Ad */
    private T f216Ad;

    public zzg(DataBuffer<T> dataBuffer) {
        super(dataBuffer);
    }

    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException(new StringBuilder(46).append("Cannot advance the iterator beyond ").append(this.f208zI).toString());
        }
        this.f208zI++;
        if (this.f208zI == 0) {
            this.f216Ad = this.f207zH.get(0);
            if (!(this.f216Ad instanceof zzc)) {
                String valueOf = String.valueOf(this.f216Ad.getClass());
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 44).append("DataBuffer reference of type ").append(valueOf).append(" is not movable").toString());
            }
        } else {
            ((zzc) this.f216Ad).zzfz(this.f208zI);
        }
        return this.f216Ad;
    }
}
