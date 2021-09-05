package com.google.android.gms.internal;

import android.support.p003v7.widget.ActivityChooserView;
import java.io.IOException;

public final class zzarc {
    private int bql;
    private int bqm;
    private int bqn;
    private int bqo;
    private int bqp;
    private int bqq = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
    private int bqr;
    private int bqs = 64;
    private int bqt = 67108864;
    private final byte[] buffer;

    private zzarc(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.bql = i;
        this.bqm = i + i2;
        this.bqo = i;
    }

    /* renamed from: cJ */
    private void m104cJ() {
        this.bqm += this.bqn;
        int i = this.bqm;
        if (i > this.bqq) {
            this.bqn = i - this.bqq;
            this.bqm -= this.bqn;
            return;
        }
        this.bqn = 0;
    }

    public static int zzahb(int i) {
        return (i >>> 1) ^ (-(i & 1));
    }

    public static zzarc zzb(byte[] bArr, int i, int i2) {
        return new zzarc(bArr, i, i2);
    }

    public static zzarc zzbd(byte[] bArr) {
        return zzb(bArr, 0, bArr.length);
    }

    public static long zzcv(long j) {
        return (j >>> 1) ^ (-(1 & j));
    }

    /* renamed from: cA */
    public int mo10325cA() throws IOException {
        return mo10330cF();
    }

    /* renamed from: cB */
    public long mo10326cB() throws IOException {
        return mo10333cI();
    }

    /* renamed from: cC */
    public boolean mo10327cC() throws IOException {
        return mo10330cF() != 0;
    }

    /* renamed from: cD */
    public int mo10328cD() throws IOException {
        return zzahb(mo10330cF());
    }

    /* renamed from: cE */
    public long mo10329cE() throws IOException {
        return zzcv(mo10331cG());
    }

    /* renamed from: cF */
    public int mo10330cF() throws IOException {
        byte cM = mo10336cM();
        if (cM >= 0) {
            return cM;
        }
        byte b = cM & Byte.MAX_VALUE;
        byte cM2 = mo10336cM();
        if (cM2 >= 0) {
            return b | (cM2 << 7);
        }
        byte b2 = b | ((cM2 & Byte.MAX_VALUE) << 7);
        byte cM3 = mo10336cM();
        if (cM3 >= 0) {
            return b2 | (cM3 << 14);
        }
        byte b3 = b2 | ((cM3 & Byte.MAX_VALUE) << 14);
        byte cM4 = mo10336cM();
        if (cM4 >= 0) {
            return b3 | (cM4 << 21);
        }
        byte b4 = b3 | ((cM4 & Byte.MAX_VALUE) << 21);
        byte cM5 = mo10336cM();
        byte b5 = b4 | (cM5 << 28);
        if (cM5 >= 0) {
            return b5;
        }
        for (int i = 0; i < 5; i++) {
            if (mo10336cM() >= 0) {
                return b5;
            }
        }
        throw zzarj.m129cV();
    }

    /* renamed from: cG */
    public long mo10331cG() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte cM = mo10336cM();
            j |= ((long) (cM & Byte.MAX_VALUE)) << i;
            if ((cM & 128) == 0) {
                return j;
            }
        }
        throw zzarj.m129cV();
    }

    /* renamed from: cH */
    public int mo10332cH() throws IOException {
        return (mo10336cM() & 255) | ((mo10336cM() & 255) << 8) | ((mo10336cM() & 255) << 16) | ((mo10336cM() & 255) << 24);
    }

    /* renamed from: cI */
    public long mo10333cI() throws IOException {
        byte cM = mo10336cM();
        byte cM2 = mo10336cM();
        return ((((long) cM2) & 255) << 8) | (((long) cM) & 255) | ((((long) mo10336cM()) & 255) << 16) | ((((long) mo10336cM()) & 255) << 24) | ((((long) mo10336cM()) & 255) << 32) | ((((long) mo10336cM()) & 255) << 40) | ((((long) mo10336cM()) & 255) << 48) | ((((long) mo10336cM()) & 255) << 56);
    }

    /* renamed from: cK */
    public int mo10334cK() {
        if (this.bqq == Integer.MAX_VALUE) {
            return -1;
        }
        return this.bqq - this.bqo;
    }

    /* renamed from: cL */
    public boolean mo10335cL() {
        return this.bqo == this.bqm;
    }

    /* renamed from: cM */
    public byte mo10336cM() throws IOException {
        if (this.bqo == this.bqm) {
            throw zzarj.m127cT();
        }
        byte[] bArr = this.buffer;
        int i = this.bqo;
        this.bqo = i + 1;
        return bArr[i];
    }

    /* renamed from: cw */
    public int mo10337cw() throws IOException {
        if (mo10335cL()) {
            this.bqp = 0;
            return 0;
        }
        this.bqp = mo10330cF();
        if (this.bqp != 0) {
            return this.bqp;
        }
        throw zzarj.m130cW();
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* renamed from: cx */
    public void mo10338cx() throws java.io.IOException {
        /*
            r1 = this;
        L_0x0000:
            int r0 = r1.mo10337cw()
            if (r0 == 0) goto L_0x000c
            boolean r0 = r1.zzaha(r0)
            if (r0 != 0) goto L_0x0000
        L_0x000c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzarc.mo10338cx():void");
    }

    /* renamed from: cy */
    public long mo10339cy() throws IOException {
        return mo10331cG();
    }

    /* renamed from: cz */
    public long mo10340cz() throws IOException {
        return mo10331cG();
    }

    public int getPosition() {
        return this.bqo - this.bql;
    }

    public byte[] readBytes() throws IOException {
        int cF = mo10330cF();
        if (cF < 0) {
            throw zzarj.m128cU();
        } else if (cF == 0) {
            return zzarn.bqM;
        } else {
            if (cF > this.bqm - this.bqo) {
                throw zzarj.m127cT();
            }
            byte[] bArr = new byte[cF];
            System.arraycopy(this.buffer, this.bqo, bArr, 0, cF);
            this.bqo = cF + this.bqo;
            return bArr;
        }
    }

    public double readDouble() throws IOException {
        return Double.longBitsToDouble(mo10333cI());
    }

    public float readFloat() throws IOException {
        return Float.intBitsToFloat(mo10332cH());
    }

    public String readString() throws IOException {
        int cF = mo10330cF();
        if (cF < 0) {
            throw zzarj.m128cU();
        } else if (cF > this.bqm - this.bqo) {
            throw zzarj.m127cT();
        } else {
            String str = new String(this.buffer, this.bqo, cF, zzari.UTF_8);
            this.bqo = cF + this.bqo;
            return str;
        }
    }

    public void zza(zzark zzark) throws IOException {
        int cF = mo10330cF();
        if (this.bqr >= this.bqs) {
            throw zzarj.m133cZ();
        }
        int zzahc = zzahc(cF);
        this.bqr++;
        zzark.zzb(this);
        zzagz(0);
        this.bqr--;
        zzahd(zzahc);
    }

    public void zza(zzark zzark, int i) throws IOException {
        if (this.bqr >= this.bqs) {
            throw zzarj.m133cZ();
        }
        this.bqr++;
        zzark.zzb(this);
        zzagz(zzarn.zzaj(i, 4));
        this.bqr--;
    }

    public byte[] zzad(int i, int i2) {
        if (i2 == 0) {
            return zzarn.bqM;
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(this.buffer, this.bql + i, bArr, 0, i2);
        return bArr;
    }

    public void zzagz(int i) throws zzarj {
        if (this.bqp != i) {
            throw zzarj.m131cX();
        }
    }

    public boolean zzaha(int i) throws IOException {
        switch (zzarn.zzaht(i)) {
            case 0:
                mo10325cA();
                return true;
            case 1:
                mo10333cI();
                return true;
            case 2:
                zzahf(mo10330cF());
                return true;
            case 3:
                mo10338cx();
                zzagz(zzarn.zzaj(zzarn.zzahu(i), 4));
                return true;
            case 4:
                return false;
            case 5:
                mo10332cH();
                return true;
            default:
                throw zzarj.m132cY();
        }
    }

    public int zzahc(int i) throws zzarj {
        if (i < 0) {
            throw zzarj.m128cU();
        }
        int i2 = this.bqo + i;
        int i3 = this.bqq;
        if (i2 > i3) {
            throw zzarj.m127cT();
        }
        this.bqq = i2;
        m104cJ();
        return i3;
    }

    public void zzahd(int i) {
        this.bqq = i;
        m104cJ();
    }

    public void zzahe(int i) {
        if (i > this.bqo - this.bql) {
            throw new IllegalArgumentException(new StringBuilder(50).append("Position ").append(i).append(" is beyond current ").append(this.bqo - this.bql).toString());
        } else if (i < 0) {
            throw new IllegalArgumentException(new StringBuilder(24).append("Bad position ").append(i).toString());
        } else {
            this.bqo = this.bql + i;
        }
    }

    public void zzahf(int i) throws IOException {
        if (i < 0) {
            throw zzarj.m128cU();
        } else if (this.bqo + i > this.bqq) {
            zzahf(this.bqq - this.bqo);
            throw zzarj.m127cT();
        } else if (i <= this.bqm - this.bqo) {
            this.bqo += i;
        } else {
            throw zzarj.m127cT();
        }
    }
}
