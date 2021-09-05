package com.google.android.gms.common.data;

import com.google.android.gms.common.data.DataBufferObserver;
import java.util.HashSet;
import java.util.Iterator;

public final class DataBufferObserverSet implements DataBufferObserver, DataBufferObserver.Observable {

    /* renamed from: zJ */
    private HashSet<DataBufferObserver> f191zJ = new HashSet<>();

    public void addObserver(DataBufferObserver dataBufferObserver) {
        this.f191zJ.add(dataBufferObserver);
    }

    public void clear() {
        this.f191zJ.clear();
    }

    public boolean hasObservers() {
        return !this.f191zJ.isEmpty();
    }

    public void onDataChanged() {
        Iterator<DataBufferObserver> it = this.f191zJ.iterator();
        while (it.hasNext()) {
            it.next().onDataChanged();
        }
    }

    public void onDataRangeChanged(int i, int i2) {
        Iterator<DataBufferObserver> it = this.f191zJ.iterator();
        while (it.hasNext()) {
            it.next().onDataRangeChanged(i, i2);
        }
    }

    public void onDataRangeInserted(int i, int i2) {
        Iterator<DataBufferObserver> it = this.f191zJ.iterator();
        while (it.hasNext()) {
            it.next().onDataRangeInserted(i, i2);
        }
    }

    public void onDataRangeMoved(int i, int i2, int i3) {
        Iterator<DataBufferObserver> it = this.f191zJ.iterator();
        while (it.hasNext()) {
            it.next().onDataRangeMoved(i, i2, i3);
        }
    }

    public void onDataRangeRemoved(int i, int i2) {
        Iterator<DataBufferObserver> it = this.f191zJ.iterator();
        while (it.hasNext()) {
            it.next().onDataRangeRemoved(i, i2);
        }
    }

    public void removeObserver(DataBufferObserver dataBufferObserver) {
        this.f191zJ.remove(dataBufferObserver);
    }
}
