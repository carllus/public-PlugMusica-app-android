package com.google.tagmanager;

import com.google.tagmanager.Logger;

class NoOpLogger implements Logger {
    NoOpLogger() {
    }

    /* renamed from: e */
    public void mo12341e(String message) {
    }

    /* renamed from: e */
    public void mo12342e(String message, Throwable t) {
    }

    /* renamed from: w */
    public void mo12349w(String message) {
    }

    /* renamed from: w */
    public void mo12350w(String message, Throwable t) {
    }

    /* renamed from: i */
    public void mo12344i(String message) {
    }

    /* renamed from: i */
    public void mo12345i(String message, Throwable t) {
    }

    /* renamed from: d */
    public void mo12339d(String message) {
    }

    /* renamed from: d */
    public void mo12340d(String message, Throwable t) {
    }

    /* renamed from: v */
    public void mo12347v(String message) {
    }

    /* renamed from: v */
    public void mo12348v(String message, Throwable t) {
    }

    public Logger.LogLevel getLogLevel() {
        return Logger.LogLevel.NONE;
    }

    public void setLogLevel(Logger.LogLevel logLevel) {
    }
}
