package com.google.tagmanager;

public interface Logger {

    public enum LogLevel {
        VERBOSE,
        DEBUG,
        INFO,
        WARNING,
        ERROR,
        NONE
    }

    /* renamed from: d */
    void mo12339d(String str);

    /* renamed from: d */
    void mo12340d(String str, Throwable th);

    /* renamed from: e */
    void mo12341e(String str);

    /* renamed from: e */
    void mo12342e(String str, Throwable th);

    LogLevel getLogLevel();

    /* renamed from: i */
    void mo12344i(String str);

    /* renamed from: i */
    void mo12345i(String str, Throwable th);

    void setLogLevel(LogLevel logLevel);

    /* renamed from: v */
    void mo12347v(String str);

    /* renamed from: v */
    void mo12348v(String str, Throwable th);

    /* renamed from: w */
    void mo12349w(String str);

    /* renamed from: w */
    void mo12350w(String str, Throwable th);
}
