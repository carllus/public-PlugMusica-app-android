package com.google.android.gms.ads.internal.util.client;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.p000v4.internal.view.SupportMenu;
import android.support.p000v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.search.SearchAdView;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.common.zzc;
import com.google.android.gms.internal.zziy;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.StringTokenizer;

@zziy
public class zza {
    public static final Handler zzctj = new Handler(Looper.getMainLooper());
    private static final String zzctk = AdView.class.getName();
    private static final String zzctl = InterstitialAd.class.getName();
    private static final String zzctm = PublisherAdView.class.getName();
    private static final String zzctn = PublisherInterstitialAd.class.getName();
    private static final String zzcto = SearchAdView.class.getName();
    private static final String zzctp = AdLoader.class.getName();

    /* renamed from: com.google.android.gms.ads.internal.util.client.zza$zza  reason: collision with other inner class name */
    public interface C1093zza {
        void zzcy(String str);
    }

    private void zza(ViewGroup viewGroup, AdSizeParcel adSizeParcel, String str, int i, int i2) {
        if (viewGroup.getChildCount() == 0) {
            Context context = viewGroup.getContext();
            TextView textView = new TextView(context);
            textView.setGravity(17);
            textView.setText(str);
            textView.setTextColor(i);
            textView.setBackgroundColor(i2);
            FrameLayout frameLayout = new FrameLayout(context);
            frameLayout.setBackgroundColor(i);
            int zzb = zzb(context, 3);
            frameLayout.addView(textView, new FrameLayout.LayoutParams(adSizeParcel.widthPixels - zzb, adSizeParcel.heightPixels - zzb, 17));
            viewGroup.addView(frameLayout, adSizeParcel.widthPixels, adSizeParcel.heightPixels);
        }
    }

    public int zza(DisplayMetrics displayMetrics, int i) {
        return (int) TypedValue.applyDimension(1, (float) i, displayMetrics);
    }

    @Nullable
    public String zza(StackTraceElement[] stackTraceElementArr, String str) {
        String str2;
        int i = 0;
        while (true) {
            if (i + 1 >= stackTraceElementArr.length) {
                str2 = null;
                break;
            }
            StackTraceElement stackTraceElement = stackTraceElementArr[i];
            String className = stackTraceElement.getClassName();
            if (!"loadAd".equalsIgnoreCase(stackTraceElement.getMethodName()) || (!zzctk.equalsIgnoreCase(className) && !zzctl.equalsIgnoreCase(className) && !zzctm.equalsIgnoreCase(className) && !zzctn.equalsIgnoreCase(className) && !zzcto.equalsIgnoreCase(className) && !zzctp.equalsIgnoreCase(className))) {
                i++;
            }
        }
        str2 = stackTraceElementArr[i + 1].getClassName();
        if (str != null) {
            String zzb = zzb(str, ".", 3);
            if (str2 != null && !str2.contains(zzb)) {
                return str2;
            }
        }
        return null;
    }

    public void zza(Context context, @Nullable String str, String str2, Bundle bundle, boolean z) {
        zza(context, str, str2, bundle, z, new C1093zza() {
            public void zzcy(final String str) {
                new Thread() {
                    public void run() {
                        new zzc().zzcy(str);
                    }
                }.start();
            }
        });
    }

    public void zza(Context context, @Nullable String str, String str2, Bundle bundle, boolean z, C1093zza zza) {
        if (z) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                applicationContext = context;
            }
            bundle.putString("os", Build.VERSION.RELEASE);
            bundle.putString("api", String.valueOf(Build.VERSION.SDK_INT));
            bundle.putString("appid", applicationContext.getPackageName());
            if (str == null) {
                str = new StringBuilder(23).append(zzc.zzapd().zzbo(context)).append(".").append(zzf.f319BA).toString();
            }
            bundle.putString("js", str);
        }
        Uri.Builder appendQueryParameter = new Uri.Builder().scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("id", str2);
        for (String str3 : bundle.keySet()) {
            appendQueryParameter.appendQueryParameter(str3, bundle.getString(str3));
        }
        zza.zzcy(appendQueryParameter.toString());
    }

    public void zza(ViewGroup viewGroup, AdSizeParcel adSizeParcel, String str) {
        zza(viewGroup, adSizeParcel, str, (int) ViewCompat.MEASURED_STATE_MASK, -1);
    }

    public void zza(ViewGroup viewGroup, AdSizeParcel adSizeParcel, String str, String str2) {
        zzb.zzdf(str2);
        zza(viewGroup, adSizeParcel, str, (int) SupportMenu.CATEGORY_MASK, (int) ViewCompat.MEASURED_STATE_MASK);
    }

    public void zza(boolean z, HttpURLConnection httpURLConnection, @Nullable String str) {
        httpURLConnection.setConnectTimeout(60000);
        httpURLConnection.setInstanceFollowRedirects(z);
        httpURLConnection.setReadTimeout(60000);
        if (str != null) {
            httpURLConnection.setRequestProperty("User-Agent", str);
        }
        httpURLConnection.setUseCaches(false);
    }

    public String zzar(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        String string = contentResolver == null ? null : Settings.Secure.getString(contentResolver, "android_id");
        if (string == null || zzve()) {
            string = "emulator";
        }
        return zzdc(string);
    }

    public boolean zzas(Context context) {
        return zzc.zzapd().isGooglePlayServicesAvailable(context) == 0;
    }

    public boolean zzat(Context context) {
        if (context.getResources().getConfiguration().orientation != 2) {
            return false;
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return ((int) (((float) displayMetrics.heightPixels) / displayMetrics.density)) < 600;
    }

    @TargetApi(17)
    public boolean zzau(Context context) {
        int intValue;
        int intValue2;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (zzs.zzaxp()) {
            defaultDisplay.getRealMetrics(displayMetrics);
            intValue = displayMetrics.heightPixels;
            intValue2 = displayMetrics.widthPixels;
        } else {
            try {
                intValue = ((Integer) Display.class.getMethod("getRawHeight", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
                intValue2 = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
            } catch (Exception e) {
                return false;
            }
        }
        defaultDisplay.getMetrics(displayMetrics);
        return displayMetrics.heightPixels == intValue && displayMetrics.widthPixels == intValue2;
    }

    public int zzav(Context context) {
        int identifier = context.getResources().getIdentifier("navigation_bar_width", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public int zzb(Context context, int i) {
        return zza(context.getResources().getDisplayMetrics(), i);
    }

    public int zzb(DisplayMetrics displayMetrics, int i) {
        return Math.round(((float) i) / displayMetrics.density);
    }

    /* access modifiers changed from: package-private */
    public String zzb(String str, String str2, int i) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, str2);
        StringBuilder sb = new StringBuilder();
        int i2 = i - 1;
        if (i <= 0 || !stringTokenizer.hasMoreElements()) {
            return str;
        }
        sb.append(stringTokenizer.nextToken());
        while (true) {
            int i3 = i2 - 1;
            if (i2 > 0 && stringTokenizer.hasMoreElements()) {
                sb.append(".").append(stringTokenizer.nextToken());
                i2 = i3;
            }
        }
        return sb.toString();
    }

    public int zzc(Context context, int i) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return zzb(displayMetrics, i);
    }

    public String zzdc(String str) {
        int i = 0;
        while (i < 2) {
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                instance.update(str.getBytes());
                return String.format(Locale.US, "%032X", new Object[]{new BigInteger(1, instance.digest())});
            } catch (NoSuchAlgorithmException e) {
                i++;
            }
        }
        return null;
    }

    public boolean zzve() {
        return Build.DEVICE.startsWith("generic");
    }

    public boolean zzvf() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
