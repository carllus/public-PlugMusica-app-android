package com.google.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.tagmanager.Logger;

final class Log {
    @VisibleForTesting
    static Logger sLogger = new DefaultLogger();

    Log() {
    }

    public static void setLogger(Logger logger) {
        if (logger == null) {
            sLogger = new NoOpLogger();
        } else {
            sLogger = logger;
        }
    }

    public static Logger getLogger() {
        if (sLogger.getClass() == NoOpLogger.class) {
            return null;
        }
        return sLogger;
    }

    /* renamed from: e */
    public static void m175e(String message) {
        sLogger.mo12341e(message);
    }

    /* renamed from: e */
    public static void m176e(String message, Throwable t) {
        sLogger.mo12342e(message, t);
    }

    /* renamed from: w */
    public static void m181w(String message) {
        sLogger.mo12349w(message);
    }

    /* renamed from: w */
    public static void m182w(String message, Throwable t) {
        sLogger.mo12350w(message, t);
    }

    /* renamed from: i */
    public static void m177i(String message) {
        sLogger.mo12344i(message);
    }

    /* renamed from: i */
    public static void m178i(String message, Throwable t) {
        sLogger.mo12345i(message, t);
    }

    /* renamed from: d */
    public static void m173d(String message) {
        sLogger.mo12339d(message);
    }

    /* renamed from: d */
    public static void m174d(String message, Throwable t) {
        sLogger.mo12340d(message, t);
    }

    /* renamed from: v */
    public static void m179v(String message) {
        sLogger.mo12347v(message);
    }

    /* renamed from: v */
    public static void m180v(String message, Throwable t) {
        sLogger.mo12348v(message, t);
    }

    public static Logger.LogLevel getLogLevel() {
        return sLogger.getLogLevel();
    }
}
