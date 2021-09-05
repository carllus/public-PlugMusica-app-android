package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import com.google.android.gms.common.util.zzs;

public final class zzrt extends Drawable implements Drawable.Callback {

    /* renamed from: AD */
    private int f833AD;

    /* renamed from: AE */
    private int f834AE;

    /* renamed from: AF */
    private int f835AF;

    /* renamed from: AG */
    private int f836AG;

    /* renamed from: AH */
    private int f837AH;

    /* renamed from: AI */
    private boolean f838AI;

    /* renamed from: AJ */
    private zzb f839AJ;

    /* renamed from: AK */
    private Drawable f840AK;

    /* renamed from: AL */
    private Drawable f841AL;

    /* renamed from: AM */
    private boolean f842AM;

    /* renamed from: AN */
    private boolean f843AN;

    /* renamed from: AO */
    private boolean f844AO;

    /* renamed from: AP */
    private int f845AP;

    /* renamed from: Ax */
    private boolean f846Ax;

    /* renamed from: bZ */
    private long f847bZ;
    private int mFrom;

    private static final class zza extends Drawable {
        /* access modifiers changed from: private */

        /* renamed from: AQ */
        public static final zza f848AQ = new zza();

        /* renamed from: AR */
        private static final C1148zza f849AR = new C1148zza();

        /* renamed from: com.google.android.gms.internal.zzrt$zza$zza  reason: collision with other inner class name */
        private static final class C1148zza extends Drawable.ConstantState {
            private C1148zza() {
            }

            public int getChangingConfigurations() {
                return 0;
            }

            public Drawable newDrawable() {
                return zza.f848AQ;
            }
        }

        private zza() {
        }

        public void draw(Canvas canvas) {
        }

        public Drawable.ConstantState getConstantState() {
            return f849AR;
        }

        public int getOpacity() {
            return -2;
        }

        public void setAlpha(int i) {
        }

        public void setColorFilter(ColorFilter colorFilter) {
        }
    }

    static final class zzb extends Drawable.ConstantState {

        /* renamed from: AS */
        int f850AS;
        int mChangingConfigurations;

        zzb(zzb zzb) {
            if (zzb != null) {
                this.mChangingConfigurations = zzb.mChangingConfigurations;
                this.f850AS = zzb.f850AS;
            }
        }

        public int getChangingConfigurations() {
            return this.mChangingConfigurations;
        }

        public Drawable newDrawable() {
            return new zzrt(this);
        }
    }

    public zzrt(Drawable drawable, Drawable drawable2) {
        this((zzb) null);
        drawable = drawable == null ? zza.f848AQ : drawable;
        this.f840AK = drawable;
        drawable.setCallback(this);
        this.f839AJ.f850AS |= drawable.getChangingConfigurations();
        drawable2 = drawable2 == null ? zza.f848AQ : drawable2;
        this.f841AL = drawable2;
        drawable2.setCallback(this);
        this.f839AJ.f850AS |= drawable2.getChangingConfigurations();
    }

    zzrt(zzb zzb2) {
        this.f833AD = 0;
        this.f835AF = 255;
        this.f837AH = 0;
        this.f846Ax = true;
        this.f839AJ = new zzb(zzb2);
    }

    public boolean canConstantState() {
        if (!this.f842AM) {
            this.f843AN = (this.f840AK.getConstantState() == null || this.f841AL.getConstantState() == null) ? false : true;
            this.f842AM = true;
        }
        return this.f843AN;
    }

    public void draw(Canvas canvas) {
        boolean z = true;
        boolean z2 = false;
        switch (this.f833AD) {
            case 1:
                this.f847bZ = SystemClock.uptimeMillis();
                this.f833AD = 2;
                break;
            case 2:
                if (this.f847bZ >= 0) {
                    float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.f847bZ)) / ((float) this.f836AG);
                    if (uptimeMillis < 1.0f) {
                        z = false;
                    }
                    if (z) {
                        this.f833AD = 0;
                    }
                    this.f837AH = (int) ((Math.min(uptimeMillis, 1.0f) * ((float) (this.f834AE + 0))) + 0.0f);
                    break;
                }
                break;
        }
        z2 = z;
        int i = this.f837AH;
        boolean z3 = this.f846Ax;
        Drawable drawable = this.f840AK;
        Drawable drawable2 = this.f841AL;
        if (z2) {
            if (!z3 || i == 0) {
                drawable.draw(canvas);
            }
            if (i == this.f835AF) {
                drawable2.setAlpha(this.f835AF);
                drawable2.draw(canvas);
                return;
            }
            return;
        }
        if (z3) {
            drawable.setAlpha(this.f835AF - i);
        }
        drawable.draw(canvas);
        if (z3) {
            drawable.setAlpha(this.f835AF);
        }
        if (i > 0) {
            drawable2.setAlpha(i);
            drawable2.draw(canvas);
            drawable2.setAlpha(this.f835AF);
        }
        invalidateSelf();
    }

    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.f839AJ.mChangingConfigurations | this.f839AJ.f850AS;
    }

    public Drawable.ConstantState getConstantState() {
        if (!canConstantState()) {
            return null;
        }
        this.f839AJ.mChangingConfigurations = getChangingConfigurations();
        return this.f839AJ;
    }

    public int getIntrinsicHeight() {
        return Math.max(this.f840AK.getIntrinsicHeight(), this.f841AL.getIntrinsicHeight());
    }

    public int getIntrinsicWidth() {
        return Math.max(this.f840AK.getIntrinsicWidth(), this.f841AL.getIntrinsicWidth());
    }

    public int getOpacity() {
        if (!this.f844AO) {
            this.f845AP = Drawable.resolveOpacity(this.f840AK.getOpacity(), this.f841AL.getOpacity());
            this.f844AO = true;
        }
        return this.f845AP;
    }

    @TargetApi(11)
    public void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback;
        if (zzs.zzaxk() && (callback = getCallback()) != null) {
            callback.invalidateDrawable(this);
        }
    }

    public Drawable mutate() {
        if (!this.f838AI && super.mutate() == this) {
            if (!canConstantState()) {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
            this.f840AK.mutate();
            this.f841AL.mutate();
            this.f838AI = true;
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        this.f840AK.setBounds(rect);
        this.f841AL.setBounds(rect);
    }

    @TargetApi(11)
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable.Callback callback;
        if (zzs.zzaxk() && (callback = getCallback()) != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    public void setAlpha(int i) {
        if (this.f837AH == this.f835AF) {
            this.f837AH = i;
        }
        this.f835AF = i;
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.f840AK.setColorFilter(colorFilter);
        this.f841AL.setColorFilter(colorFilter);
    }

    public void startTransition(int i) {
        this.mFrom = 0;
        this.f834AE = this.f835AF;
        this.f837AH = 0;
        this.f836AG = i;
        this.f833AD = 1;
        invalidateSelf();
    }

    @TargetApi(11)
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback;
        if (zzs.zzaxk() && (callback = getCallback()) != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public Drawable zzatn() {
        return this.f841AL;
    }
}
