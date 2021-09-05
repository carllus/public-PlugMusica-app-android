package com.google.analytics.tracking.android;

import com.google.analytics.tracking.android.Logger;
import com.google.android.gms.common.util.VisibleForTesting;

public class Log {
    private static GoogleAnalytics sGaInstance;

    private Log() {
    }

    /* renamed from: e */
    public static void m21e(String msg) {
        Logger l = getLogger();
        if (l != null) {
            l.error(msg);
        }
    }

    /* renamed from: e */
    public static void m20e(Exception e) {
        Logger l = getLogger();
        if (l != null) {
            l.error(e);
        }
    }

    /* renamed from: i */
    public static void m22i(String msg) {
        Logger l = getLogger();
        if (l != null) {
            l.info(msg);
        }
    }

    /* renamed from: v */
    public static void m23v(String msg) {
        Logger l = getLogger();
        if (l != null) {
            l.verbose(msg);
        }
    }

    /* renamed from: w */
    public static void m24w(String msg) {
        Logger l = getLogger();
        if (l != null) {
            l.warn(msg);
        }
    }

    public static boolean isVerbose() {
        if (getLogger() != null) {
            return Logger.LogLevel.VERBOSE.equals(getLogger().getLogLevel());
        }
        return false;
    }

    private static Logger getLogger() {
        if (sGaInstance == null) {
            sGaInstance = GoogleAnalytics.getInstance();
        }
        if (sGaInstance != null) {
            return sGaInstance.getLogger();
        }
        return null;
    }

    @VisibleForTesting
    static void clearGaInstance() {
        sGaInstance = null;
    }
}
