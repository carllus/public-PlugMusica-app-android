package com.google.android.gms.common.data;

import java.util.ArrayList;

public abstract class zzf<T> extends AbstractDataBuffer<T> {

    /* renamed from: Ab */
    private boolean f214Ab = false;

    /* renamed from: Ac */
    private ArrayList<Integer> f215Ac;

    protected zzf(DataHolder dataHolder) {
        super(dataHolder);
    }

    private void zzati() {
        synchronized (this) {
            if (!this.f214Ab) {
                int count = this.f186xi.getCount();
                this.f215Ac = new ArrayList<>();
                if (count > 0) {
                    this.f215Ac.add(0);
                    String zzath = zzath();
                    String zzd = this.f186xi.zzd(zzath, 0, this.f186xi.zzgb(0));
                    int i = 1;
                    while (i < count) {
                        int zzgb = this.f186xi.zzgb(i);
                        String zzd2 = this.f186xi.zzd(zzath, i, zzgb);
                        if (zzd2 == null) {
                            throw new NullPointerException(new StringBuilder(String.valueOf(zzath).length() + 78).append("Missing value for markerColumn: ").append(zzath).append(", at row: ").append(i).append(", for window: ").append(zzgb).toString());
                        }
                        if (!zzd2.equals(zzd)) {
                            this.f215Ac.add(Integer.valueOf(i));
                        } else {
                            zzd2 = zzd;
                        }
                        i++;
                        zzd = zzd2;
                    }
                }
                this.f214Ab = true;
            }
        }
    }

    public final T get(int i) {
        zzati();
        return zzl(zzgf(i), zzgg(i));
    }

    public int getCount() {
        zzati();
        return this.f215Ac.size();
    }

    /* access modifiers changed from: protected */
    public abstract String zzath();

    /* access modifiers changed from: protected */
    public String zzatj() {
        return null;
    }

    /* access modifiers changed from: package-private */
    public int zzgf(int i) {
        if (i >= 0 && i < this.f215Ac.size()) {
            return this.f215Ac.get(i).intValue();
        }
        throw new IllegalArgumentException(new StringBuilder(53).append("Position ").append(i).append(" is out of bounds for this buffer").toString());
    }

    /* access modifiers changed from: protected */
    public int zzgg(int i) {
        if (i < 0 || i == this.f215Ac.size()) {
            return 0;
        }
        int count = i == this.f215Ac.size() + -1 ? this.f186xi.getCount() - this.f215Ac.get(i).intValue() : this.f215Ac.get(i + 1).intValue() - this.f215Ac.get(i).intValue();
        if (count != 1) {
            return count;
        }
        int zzgf = zzgf(i);
        int zzgb = this.f186xi.zzgb(zzgf);
        String zzatj = zzatj();
        if (zzatj == null || this.f186xi.zzd(zzatj, zzgf, zzgb) != null) {
            return count;
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public abstract T zzl(int i, int i2);
}
