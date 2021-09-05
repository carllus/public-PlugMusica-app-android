package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzac;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class zzsf implements ThreadFactory {

    /* renamed from: Ff */
    private final String f861Ff;

    /* renamed from: Fg */
    private final AtomicInteger f862Fg;

    /* renamed from: Fh */
    private final ThreadFactory f863Fh;
    private final int mPriority;

    public zzsf(String str) {
        this(str, 0);
    }

    public zzsf(String str, int i) {
        this.f862Fg = new AtomicInteger();
        this.f863Fh = Executors.defaultThreadFactory();
        this.f861Ff = (String) zzac.zzb(str, (Object) "Name must not be null");
        this.mPriority = i;
    }

    public Thread newThread(Runnable runnable) {
        Thread newThread = this.f863Fh.newThread(new zzsg(runnable, this.mPriority));
        String str = this.f861Ff;
        newThread.setName(new StringBuilder(String.valueOf(str).length() + 13).append(str).append("[").append(this.f862Fg.getAndIncrement()).append("]").toString());
        return newThread;
    }
}
