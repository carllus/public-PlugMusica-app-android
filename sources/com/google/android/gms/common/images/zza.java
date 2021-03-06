package com.google.android.gms.common.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.p003v7.widget.helper.ItemTouchHelper;
import android.widget.ImageView;
import com.google.android.gms.common.images.ImageManager;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzrt;
import com.google.android.gms.internal.zzru;
import com.google.android.gms.internal.zzrv;
import java.lang.ref.WeakReference;

public abstract class zza {

    /* renamed from: At */
    final C1095zza f237At;

    /* renamed from: Au */
    protected int f238Au = 0;

    /* renamed from: Av */
    protected int f239Av = 0;

    /* renamed from: Aw */
    protected boolean f240Aw = false;

    /* renamed from: Ax */
    private boolean f241Ax = true;

    /* renamed from: Ay */
    private boolean f242Ay = false;

    /* renamed from: Az */
    private boolean f243Az = true;

    /* renamed from: com.google.android.gms.common.images.zza$zza  reason: collision with other inner class name */
    static final class C1095zza {
        public final Uri uri;

        public C1095zza(Uri uri2) {
            this.uri = uri2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof C1095zza)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            return zzab.equal(((C1095zza) obj).uri, this.uri);
        }

        public int hashCode() {
            return zzab.hashCode(this.uri);
        }
    }

    public static final class zzb extends zza {

        /* renamed from: AA */
        private WeakReference<ImageView> f244AA;

        public zzb(ImageView imageView, int i) {
            super((Uri) null, i);
            com.google.android.gms.common.internal.zzc.zzu(imageView);
            this.f244AA = new WeakReference<>(imageView);
        }

        public zzb(ImageView imageView, Uri uri) {
            super(uri, 0);
            com.google.android.gms.common.internal.zzc.zzu(imageView);
            this.f244AA = new WeakReference<>(imageView);
        }

        private void zza(ImageView imageView, Drawable drawable, boolean z, boolean z2, boolean z3) {
            boolean z4 = !z2 && !z3;
            if (z4 && (imageView instanceof zzru)) {
                int zzatp = ((zzru) imageView).zzatp();
                if (this.f239Av != 0 && zzatp == this.f239Av) {
                    return;
                }
            }
            boolean zzc = zzc(z, z2);
            Drawable zza = zzc ? zza(imageView.getDrawable(), drawable) : drawable;
            imageView.setImageDrawable(zza);
            if (imageView instanceof zzru) {
                zzru zzru = (zzru) imageView;
                zzru.zzq(z3 ? this.f237At.uri : null);
                zzru.zzgj(z4 ? this.f239Av : 0);
            }
            if (zzc) {
                ((zzrt) zza).startTransition(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof zzb)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            ImageView imageView = (ImageView) this.f244AA.get();
            ImageView imageView2 = (ImageView) ((zzb) obj).f244AA.get();
            return (imageView2 == null || imageView == null || !zzab.equal(imageView2, imageView)) ? false : true;
        }

        public int hashCode() {
            return 0;
        }

        /* access modifiers changed from: protected */
        public void zza(Drawable drawable, boolean z, boolean z2, boolean z3) {
            ImageView imageView = (ImageView) this.f244AA.get();
            if (imageView != null) {
                zza(imageView, drawable, z, z2, z3);
            }
        }
    }

    public static final class zzc extends zza {

        /* renamed from: AB */
        private WeakReference<ImageManager.OnImageLoadedListener> f245AB;

        public zzc(ImageManager.OnImageLoadedListener onImageLoadedListener, Uri uri) {
            super(uri, 0);
            com.google.android.gms.common.internal.zzc.zzu(onImageLoadedListener);
            this.f245AB = new WeakReference<>(onImageLoadedListener);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof zzc)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            zzc zzc = (zzc) obj;
            ImageManager.OnImageLoadedListener onImageLoadedListener = (ImageManager.OnImageLoadedListener) this.f245AB.get();
            ImageManager.OnImageLoadedListener onImageLoadedListener2 = (ImageManager.OnImageLoadedListener) zzc.f245AB.get();
            return onImageLoadedListener2 != null && onImageLoadedListener != null && zzab.equal(onImageLoadedListener2, onImageLoadedListener) && zzab.equal(zzc.f237At, this.f237At);
        }

        public int hashCode() {
            return zzab.hashCode(this.f237At);
        }

        /* access modifiers changed from: protected */
        public void zza(Drawable drawable, boolean z, boolean z2, boolean z3) {
            ImageManager.OnImageLoadedListener onImageLoadedListener;
            if (!z2 && (onImageLoadedListener = (ImageManager.OnImageLoadedListener) this.f245AB.get()) != null) {
                onImageLoadedListener.onImageLoaded(this.f237At.uri, drawable, z3);
            }
        }
    }

    public zza(Uri uri, int i) {
        this.f237At = new C1095zza(uri);
        this.f239Av = i;
    }

    private Drawable zza(Context context, zzrv zzrv, int i) {
        return context.getResources().getDrawable(i);
    }

    /* access modifiers changed from: protected */
    public zzrt zza(Drawable drawable, Drawable drawable2) {
        if (drawable == null) {
            drawable = null;
        } else if (drawable instanceof zzrt) {
            drawable = ((zzrt) drawable).zzatn();
        }
        return new zzrt(drawable, drawable2);
    }

    /* access modifiers changed from: package-private */
    public void zza(Context context, Bitmap bitmap, boolean z) {
        com.google.android.gms.common.internal.zzc.zzu(bitmap);
        zza(new BitmapDrawable(context.getResources(), bitmap), z, false, true);
    }

    /* access modifiers changed from: package-private */
    public void zza(Context context, zzrv zzrv) {
        if (this.f243Az) {
            zza((Drawable) null, false, true, false);
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(Context context, zzrv zzrv, boolean z) {
        Drawable drawable = null;
        if (this.f239Av != 0) {
            drawable = zza(context, zzrv, this.f239Av);
        }
        zza(drawable, z, false, false);
    }

    /* access modifiers changed from: protected */
    public abstract void zza(Drawable drawable, boolean z, boolean z2, boolean z3);

    /* access modifiers changed from: protected */
    public boolean zzc(boolean z, boolean z2) {
        return this.f241Ax && !z2 && !z;
    }

    public void zzgh(int i) {
        this.f239Av = i;
    }
}
