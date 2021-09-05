package com.google.android.gms.common.util;

import android.support.p000v4.util.ArrayMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;

public class zza<E> extends AbstractSet<E> {

    /* renamed from: EJ */
    private final ArrayMap<E, E> f474EJ;

    public zza() {
        this.f474EJ = new ArrayMap<>();
    }

    public zza(int i) {
        this.f474EJ = new ArrayMap<>(i);
    }

    public zza(Collection<E> collection) {
        this(collection.size());
        addAll(collection);
    }

    public boolean add(E e) {
        if (this.f474EJ.containsKey(e)) {
            return false;
        }
        this.f474EJ.put(e, e);
        return true;
    }

    public boolean addAll(Collection<? extends E> collection) {
        return collection instanceof zza ? zza((zza) collection) : super.addAll(collection);
    }

    public void clear() {
        this.f474EJ.clear();
    }

    public boolean contains(Object obj) {
        return this.f474EJ.containsKey(obj);
    }

    public Iterator<E> iterator() {
        return this.f474EJ.keySet().iterator();
    }

    public boolean remove(Object obj) {
        if (!this.f474EJ.containsKey(obj)) {
            return false;
        }
        this.f474EJ.remove(obj);
        return true;
    }

    public int size() {
        return this.f474EJ.size();
    }

    public boolean zza(zza<? extends E> zza) {
        int size = size();
        this.f474EJ.putAll(zza.f474EJ);
        return size() > size;
    }
}
