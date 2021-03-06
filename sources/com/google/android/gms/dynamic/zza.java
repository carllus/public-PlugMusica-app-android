package com.google.android.gms.dynamic;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.zzi;
import com.google.android.gms.dynamic.LifecycleDelegate;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class zza<T extends LifecycleDelegate> {
    /* access modifiers changed from: private */

    /* renamed from: Oi */
    public T f517Oi;
    /* access modifiers changed from: private */

    /* renamed from: Oj */
    public Bundle f518Oj;
    /* access modifiers changed from: private */

    /* renamed from: Ok */
    public LinkedList<C1107zza> f519Ok;

    /* renamed from: Ol */
    private final zzf<T> f520Ol = new zzf<T>() {
        public void zza(T t) {
            LifecycleDelegate unused = zza.this.f517Oi = t;
            Iterator it = zza.this.f519Ok.iterator();
            while (it.hasNext()) {
                ((C1107zza) it.next()).zzb(zza.this.f517Oi);
            }
            zza.this.f519Ok.clear();
            Bundle unused2 = zza.this.f518Oj = null;
        }
    };

    /* renamed from: com.google.android.gms.dynamic.zza$zza  reason: collision with other inner class name */
    private interface C1107zza {
        int getState();

        void zzb(LifecycleDelegate lifecycleDelegate);
    }

    private void zza(Bundle bundle, C1107zza zza) {
        if (this.f517Oi != null) {
            zza.zzb(this.f517Oi);
            return;
        }
        if (this.f519Ok == null) {
            this.f519Ok = new LinkedList<>();
        }
        this.f519Ok.add(zza);
        if (bundle != null) {
            if (this.f518Oj == null) {
                this.f518Oj = (Bundle) bundle.clone();
            } else {
                this.f518Oj.putAll(bundle);
            }
        }
        zza(this.f520Ol);
    }

    public static void zzb(FrameLayout frameLayout) {
        final Context context = frameLayout.getContext();
        final int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        String zzi = zzi.zzi(context, isGooglePlayServicesAvailable);
        String zzk = zzi.zzk(context, isGooglePlayServicesAvailable);
        LinearLayout linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        frameLayout.addView(linearLayout);
        TextView textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        textView.setText(zzi);
        linearLayout.addView(textView);
        if (zzk != null) {
            Button button = new Button(context);
            button.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            button.setText(zzk);
            linearLayout.addView(button);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    context.startActivity(GooglePlayServicesUtil.zzfm(isGooglePlayServicesAvailable));
                }
            });
        }
    }

    private void zzno(int i) {
        while (!this.f519Ok.isEmpty() && this.f519Ok.getLast().getState() >= i) {
            this.f519Ok.removeLast();
        }
    }

    public void onCreate(final Bundle bundle) {
        zza(bundle, (C1107zza) new C1107zza() {
            public int getState() {
                return 1;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                zza.this.f517Oi.onCreate(bundle);
            }
        });
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        final FrameLayout frameLayout = new FrameLayout(layoutInflater.getContext());
        final LayoutInflater layoutInflater2 = layoutInflater;
        final ViewGroup viewGroup2 = viewGroup;
        final Bundle bundle2 = bundle;
        zza(bundle, (C1107zza) new C1107zza() {
            public int getState() {
                return 2;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                frameLayout.removeAllViews();
                frameLayout.addView(zza.this.f517Oi.onCreateView(layoutInflater2, viewGroup2, bundle2));
            }
        });
        if (this.f517Oi == null) {
            zza(frameLayout);
        }
        return frameLayout;
    }

    public void onDestroy() {
        if (this.f517Oi != null) {
            this.f517Oi.onDestroy();
        } else {
            zzno(1);
        }
    }

    public void onDestroyView() {
        if (this.f517Oi != null) {
            this.f517Oi.onDestroyView();
        } else {
            zzno(2);
        }
    }

    public void onInflate(final Activity activity, final Bundle bundle, final Bundle bundle2) {
        zza(bundle2, (C1107zza) new C1107zza() {
            public int getState() {
                return 0;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                zza.this.f517Oi.onInflate(activity, bundle, bundle2);
            }
        });
    }

    public void onLowMemory() {
        if (this.f517Oi != null) {
            this.f517Oi.onLowMemory();
        }
    }

    public void onPause() {
        if (this.f517Oi != null) {
            this.f517Oi.onPause();
        } else {
            zzno(5);
        }
    }

    public void onResume() {
        zza((Bundle) null, (C1107zza) new C1107zza() {
            public int getState() {
                return 5;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                zza.this.f517Oi.onResume();
            }
        });
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (this.f517Oi != null) {
            this.f517Oi.onSaveInstanceState(bundle);
        } else if (this.f518Oj != null) {
            bundle.putAll(this.f518Oj);
        }
    }

    public void onStart() {
        zza((Bundle) null, (C1107zza) new C1107zza() {
            public int getState() {
                return 4;
            }

            public void zzb(LifecycleDelegate lifecycleDelegate) {
                zza.this.f517Oi.onStart();
            }
        });
    }

    public void onStop() {
        if (this.f517Oi != null) {
            this.f517Oi.onStop();
        } else {
            zzno(4);
        }
    }

    /* access modifiers changed from: protected */
    public void zza(FrameLayout frameLayout) {
        zzb(frameLayout);
    }

    /* access modifiers changed from: protected */
    public abstract void zza(zzf<T> zzf);

    public T zzbdt() {
        return this.f517Oi;
    }
}
