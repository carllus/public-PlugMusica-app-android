package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.zzac;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class zzb<T> implements Iterator<T> {

    /* renamed from: zH */
    protected final DataBuffer<T> f207zH;

    /* renamed from: zI */
    protected int f208zI = -1;

    public zzb(DataBuffer<T> dataBuffer) {
        this.f207zH = (DataBuffer) zzac.zzy(dataBuffer);
    }

    public boolean hasNext() {
        return this.f208zI < this.f207zH.getCount() + -1;
    }

    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException(new StringBuilder(46).append("Cannot advance the iterator beyond ").append(this.f208zI).toString());
        }
        DataBuffer<T> dataBuffer = this.f207zH;
        int i = this.f208zI + 1;
        this.f208zI = i;
        return dataBuffer.get(i);
    }

    public void remove() {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}
