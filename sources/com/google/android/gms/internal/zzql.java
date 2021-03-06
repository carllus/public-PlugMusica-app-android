package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import android.support.p000v4.app.FragmentActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class zzql extends GoogleApiClient {

    /* renamed from: xj */
    private final UnsupportedOperationException f646xj;

    public zzql(String str) {
        this.f646xj = new UnsupportedOperationException(str);
    }

    public ConnectionResult blockingConnect() {
        throw this.f646xj;
    }

    public ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit) {
        throw this.f646xj;
    }

    public PendingResult<Status> clearDefaultAccountAndReconnect() {
        throw this.f646xj;
    }

    public void connect() {
        throw this.f646xj;
    }

    public void disconnect() {
        throw this.f646xj;
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        throw this.f646xj;
    }

    @NonNull
    public ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        throw this.f646xj;
    }

    public boolean hasConnectedApi(@NonNull Api<?> api) {
        throw this.f646xj;
    }

    public boolean isConnected() {
        throw this.f646xj;
    }

    public boolean isConnecting() {
        throw this.f646xj;
    }

    public boolean isConnectionCallbacksRegistered(@NonNull GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        throw this.f646xj;
    }

    public boolean isConnectionFailedListenerRegistered(@NonNull GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        throw this.f646xj;
    }

    public void reconnect() {
        throw this.f646xj;
    }

    public void registerConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        throw this.f646xj;
    }

    public void registerConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        throw this.f646xj;
    }

    public void stopAutoManage(@NonNull FragmentActivity fragmentActivity) {
        throw this.f646xj;
    }

    public void unregisterConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        throw this.f646xj;
    }

    public void unregisterConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        throw this.f646xj;
    }
}
