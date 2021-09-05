package com.google.android.gms.common;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.common.internal.zzac;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zza implements ServiceConnection {

    /* renamed from: uH */
    boolean f496uH = false;

    /* renamed from: uI */
    private final BlockingQueue<IBinder> f497uI = new LinkedBlockingQueue();

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f497uI.add(iBinder);
    }

    public void onServiceDisconnected(ComponentName componentName) {
    }

    public IBinder zza(long j, TimeUnit timeUnit) throws InterruptedException, TimeoutException {
        zzac.zzhr("BlockingServiceConnection.getServiceWithTimeout() called on main thread");
        if (this.f496uH) {
            throw new IllegalStateException("Cannot call get on this connection more than once");
        }
        this.f496uH = true;
        IBinder poll = this.f497uI.poll(j, timeUnit);
        if (poll != null) {
            return poll;
        }
        throw new TimeoutException("Timed out waiting for the service connection");
    }

    public IBinder zzapc() throws InterruptedException {
        zzac.zzhr("BlockingServiceConnection.getService() called on main thread");
        if (this.f496uH) {
            throw new IllegalStateException("Cannot call get on this connection more than once");
        }
        this.f496uH = true;
        return this.f497uI.take();
    }
}
