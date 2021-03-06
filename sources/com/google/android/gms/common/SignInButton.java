package com.google.android.gms.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import com.google.android.gms.C0417R;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzag;
import com.google.android.gms.common.internal.zzah;
import com.google.android.gms.dynamic.zzg;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class SignInButton extends FrameLayout implements View.OnClickListener {
    public static final int COLOR_AUTO = 2;
    public static final int COLOR_DARK = 0;
    public static final int COLOR_LIGHT = 1;
    public static final int SIZE_ICON_ONLY = 2;
    public static final int SIZE_STANDARD = 0;
    public static final int SIZE_WIDE = 1;
    private int mColor;
    private int mSize;

    /* renamed from: vg */
    private View f123vg;

    /* renamed from: vh */
    private View.OnClickListener f124vh;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ButtonSize {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ColorScheme {
    }

    public SignInButton(Context context) {
        this(context, (AttributeSet) null);
    }

    public SignInButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SignInButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f124vh = null;
        zzb(context, attributeSet);
        setStyle(this.mSize, this.mColor);
    }

    private static Button zza(Context context, int i, int i2) {
        zzah zzah = new zzah(context);
        zzah.zza(context.getResources(), i, i2);
        return zzah;
    }

    private void zzb(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C0417R.styleable.SignInButton, 0, 0);
        try {
            this.mSize = obtainStyledAttributes.getInt(C0417R.styleable.SignInButton_buttonSize, 0);
            this.mColor = obtainStyledAttributes.getInt(C0417R.styleable.SignInButton_colorScheme, 2);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private void zzca(Context context) {
        if (this.f123vg != null) {
            removeView(this.f123vg);
        }
        try {
            this.f123vg = zzag.zzb(context, this.mSize, this.mColor);
        } catch (zzg.zza e) {
            Log.w("SignInButton", "Sign in button not found, using placeholder instead");
            this.f123vg = zza(context, this.mSize, this.mColor);
        }
        addView(this.f123vg);
        this.f123vg.setEnabled(isEnabled());
        this.f123vg.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (this.f124vh != null && view == this.f123vg) {
            this.f124vh.onClick(this);
        }
    }

    public void setColorScheme(int i) {
        setStyle(this.mSize, i);
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.f123vg.setEnabled(z);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.f124vh = onClickListener;
        if (this.f123vg != null) {
            this.f123vg.setOnClickListener(this);
        }
    }

    @Deprecated
    public void setScopes(Scope[] scopeArr) {
        setStyle(this.mSize, this.mColor);
    }

    public void setSize(int i) {
        setStyle(i, this.mColor);
    }

    public void setStyle(int i, int i2) {
        this.mSize = i;
        this.mColor = i2;
        zzca(getContext());
    }

    @Deprecated
    public void setStyle(int i, int i2, Scope[] scopeArr) {
        setStyle(i, i2);
    }
}
