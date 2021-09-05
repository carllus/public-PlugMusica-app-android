package com.google.android.gms.dynamic;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.dynamic.zzc;

@SuppressLint({"NewApi"})
public final class zzb extends zzc.zza {

    /* renamed from: Os */
    private Fragment f534Os;

    private zzb(Fragment fragment) {
        this.f534Os = fragment;
    }

    public static zzb zza(Fragment fragment) {
        if (fragment != null) {
            return new zzb(fragment);
        }
        return null;
    }

    public Bundle getArguments() {
        return this.f534Os.getArguments();
    }

    public int getId() {
        return this.f534Os.getId();
    }

    public boolean getRetainInstance() {
        return this.f534Os.getRetainInstance();
    }

    public String getTag() {
        return this.f534Os.getTag();
    }

    public int getTargetRequestCode() {
        return this.f534Os.getTargetRequestCode();
    }

    public boolean getUserVisibleHint() {
        return this.f534Os.getUserVisibleHint();
    }

    public zzd getView() {
        return zze.zzac(this.f534Os.getView());
    }

    public boolean isAdded() {
        return this.f534Os.isAdded();
    }

    public boolean isDetached() {
        return this.f534Os.isDetached();
    }

    public boolean isHidden() {
        return this.f534Os.isHidden();
    }

    public boolean isInLayout() {
        return this.f534Os.isInLayout();
    }

    public boolean isRemoving() {
        return this.f534Os.isRemoving();
    }

    public boolean isResumed() {
        return this.f534Os.isResumed();
    }

    public boolean isVisible() {
        return this.f534Os.isVisible();
    }

    public void setHasOptionsMenu(boolean z) {
        this.f534Os.setHasOptionsMenu(z);
    }

    public void setMenuVisibility(boolean z) {
        this.f534Os.setMenuVisibility(z);
    }

    public void setRetainInstance(boolean z) {
        this.f534Os.setRetainInstance(z);
    }

    public void setUserVisibleHint(boolean z) {
        this.f534Os.setUserVisibleHint(z);
    }

    public void startActivity(Intent intent) {
        this.f534Os.startActivity(intent);
    }

    public void startActivityForResult(Intent intent, int i) {
        this.f534Os.startActivityForResult(intent, i);
    }

    public void zzac(zzd zzd) {
        this.f534Os.registerForContextMenu((View) zze.zzae(zzd));
    }

    public void zzad(zzd zzd) {
        this.f534Os.unregisterForContextMenu((View) zze.zzae(zzd));
    }

    public zzd zzbdu() {
        return zze.zzac(this.f534Os.getActivity());
    }

    public zzc zzbdv() {
        return zza(this.f534Os.getParentFragment());
    }

    public zzd zzbdw() {
        return zze.zzac(this.f534Os.getResources());
    }

    public zzc zzbdx() {
        return zza(this.f534Os.getTargetFragment());
    }
}
