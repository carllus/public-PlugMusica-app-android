package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.View;
import com.google.android.gms.dynamic.zzc;

public final class zzh extends zzc.zza {

    /* renamed from: Ov */
    private Fragment f537Ov;

    private zzh(Fragment fragment) {
        this.f537Ov = fragment;
    }

    public static zzh zza(Fragment fragment) {
        if (fragment != null) {
            return new zzh(fragment);
        }
        return null;
    }

    public Bundle getArguments() {
        return this.f537Ov.getArguments();
    }

    public int getId() {
        return this.f537Ov.getId();
    }

    public boolean getRetainInstance() {
        return this.f537Ov.getRetainInstance();
    }

    public String getTag() {
        return this.f537Ov.getTag();
    }

    public int getTargetRequestCode() {
        return this.f537Ov.getTargetRequestCode();
    }

    public boolean getUserVisibleHint() {
        return this.f537Ov.getUserVisibleHint();
    }

    public zzd getView() {
        return zze.zzac(this.f537Ov.getView());
    }

    public boolean isAdded() {
        return this.f537Ov.isAdded();
    }

    public boolean isDetached() {
        return this.f537Ov.isDetached();
    }

    public boolean isHidden() {
        return this.f537Ov.isHidden();
    }

    public boolean isInLayout() {
        return this.f537Ov.isInLayout();
    }

    public boolean isRemoving() {
        return this.f537Ov.isRemoving();
    }

    public boolean isResumed() {
        return this.f537Ov.isResumed();
    }

    public boolean isVisible() {
        return this.f537Ov.isVisible();
    }

    public void setHasOptionsMenu(boolean z) {
        this.f537Ov.setHasOptionsMenu(z);
    }

    public void setMenuVisibility(boolean z) {
        this.f537Ov.setMenuVisibility(z);
    }

    public void setRetainInstance(boolean z) {
        this.f537Ov.setRetainInstance(z);
    }

    public void setUserVisibleHint(boolean z) {
        this.f537Ov.setUserVisibleHint(z);
    }

    public void startActivity(Intent intent) {
        this.f537Ov.startActivity(intent);
    }

    public void startActivityForResult(Intent intent, int i) {
        this.f537Ov.startActivityForResult(intent, i);
    }

    public void zzac(zzd zzd) {
        this.f537Ov.registerForContextMenu((View) zze.zzae(zzd));
    }

    public void zzad(zzd zzd) {
        this.f537Ov.unregisterForContextMenu((View) zze.zzae(zzd));
    }

    public zzd zzbdu() {
        return zze.zzac(this.f537Ov.getActivity());
    }

    public zzc zzbdv() {
        return zza(this.f537Ov.getParentFragment());
    }

    public zzd zzbdw() {
        return zze.zzac(this.f537Ov.getResources());
    }

    public zzc zzbdx() {
        return zza(this.f537Ov.getTargetFragment());
    }
}
