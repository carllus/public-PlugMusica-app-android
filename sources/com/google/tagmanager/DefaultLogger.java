package com.google.tagmanager;

import android.util.Log;
import com.google.tagmanager.Logger;

class DefaultLogger implements Logger {
    private static final String LOG_TAG = "GoogleTagManager";
    private Logger.LogLevel mLogLevel = Logger.LogLevel.WARNING;

    DefaultLogger() {
    }

    /* renamed from: e */
    public void mo12341e(String message) {
        if (this.mLogLevel.ordinal() <= Logger.LogLevel.ERROR.ordinal()) {
            Log.e(LOG_TAG, message);
        }
    }

    /* renamed from: e */
    public void mo12342e(String message, Throwable t) {
        if (this.mLogLevel.ordinal() <= Logger.LogLevel.ERROR.ordinal()) {
            Log.e(LOG_TAG, message, t);
        }
    }

    /* renamed from: w */
    public void mo12349w(String message) {
        if (this.mLogLevel.ordinal() <= Logger.LogLevel.WARNING.ordinal()) {
            Log.w(LOG_TAG, message);
        }
    }

    /* renamed from: w */
    public void mo12350w(String message, Throwable t) {
        if (this.mLogLevel.ordinal() <= Logger.LogLevel.WARNING.ordinal()) {
            Log.w(LOG_TAG, message, t);
        }
    }

    /* renamed from: i */
    public void mo12344i(String message) {
        if (this.mLogLevel.ordinal() <= Logger.LogLevel.INFO.ordinal()) {
            Log.i(LOG_TAG, message);
        }
    }

    /* renamed from: i */
    public void mo12345i(String message, Throwable t) {
        if (this.mLogLevel.ordinal() <= Logger.LogLevel.INFO.ordinal()) {
            Log.i(LOG_TAG, message, t);
        }
    }

    /* renamed from: d */
    public void mo12339d(String message) {
        if (this.mLogLevel.ordinal() <= Logger.LogLevel.DEBUG.ordinal()) {
            Log.d(LOG_TAG, message);
        }
    }

    /* renamed from: d */
    public void mo12340d(String message, Throwable t) {
        if (this.mLogLevel.ordinal() <= Logger.LogLevel.DEBUG.ordinal()) {
            Log.d(LOG_TAG, message, t);
        }
    }

    /* renamed from: v */
    public void mo12347v(String message) {
        if (this.mLogLevel.ordinal() <= Logger.LogLevel.VERBOSE.ordinal()) {
            Log.v(LOG_TAG, message);
        }
    }

    /* renamed from: v */
    public void mo12348v(String message, Throwable t) {
        if (this.mLogLevel.ordinal() <= Logger.LogLevel.VERBOSE.ordinal()) {
            Log.v(LOG_TAG, message, t);
        }
    }

    public Logger.LogLevel getLogLevel() {
        return this.mLogLevel;
    }

    public void setLogLevel(Logger.LogLevel logLevel) {
        this.mLogLevel = logLevel;
    }
}
