package com.google.android.gms.common.api.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class NonGmsServiceBrokerClient implements ServiceConnection, Api.Client {
    private static final String zaa = "NonGmsServiceBrokerClient";
    private final String zab;
    private final String zac;
    private final ComponentName zad;
    private final Context zae;
    private final ConnectionCallbacks zaf;
    private final Handler zag;
    private final OnConnectionFailedListener zah;
    private IBinder zai;
    private boolean zaj;
    private String zak;

    public NonGmsServiceBrokerClient(Context context, Looper looper, String str, String str2, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, str, str2, null, connectionCallbacks, onConnectionFailedListener);
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final Feature[] getAvailableFeatures() {
        return new Feature[0];
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return 0;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final void getRemoteService(IAccountAccessor iAccountAccessor, Set<Scope> set) {
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final Feature[] getRequiredFeatures() {
        return new Feature[0];
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final IBinder getServiceBrokerBinder() {
        return null;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final void onUserSignOut(BaseGmsClient.SignOutCallbacks signOutCallbacks) {
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final boolean providesSignIn() {
        return false;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final boolean requiresAccount() {
        return false;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final boolean requiresGooglePlayServices() {
        return false;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final boolean requiresSignIn() {
        return false;
    }

    public NonGmsServiceBrokerClient(Context context, Looper looper, ComponentName componentName, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, null, null, componentName, connectionCallbacks, onConnectionFailedListener);
    }

    private NonGmsServiceBrokerClient(Context context, Looper looper, String str, String str2, ComponentName componentName, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        this.zaj = false;
        this.zak = null;
        this.zae = context;
        this.zag = new com.google.android.gms.internal.base.zap(looper);
        this.zaf = connectionCallbacks;
        this.zah = onConnectionFailedListener;
        boolean z = (str == null || str2 == null) ? false : true;
        boolean z2 = componentName != null;
        if (!z ? !z2 : z2) {
            throw new AssertionError("Must specify either package or component, but not both");
        }
        this.zab = str;
        this.zac = str2;
        this.zad = componentName;
    }

    private final void zab() {
        if (Thread.currentThread() != this.zag.getLooper().getThread()) {
            throw new IllegalStateException("This method should only run on the NonGmsServiceBrokerClient's handler thread.");
        }
    }

    private final void zaa(String str) {
        new StringBuilder(String.valueOf(str).length() + 30 + String.valueOf(String.valueOf(this.zai)).length());
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, final IBinder iBinder) {
        this.zag.post(new Runnable(this, iBinder) { // from class: com.google.android.gms.common.api.internal.zabp
            private final NonGmsServiceBrokerClient zaa;
            private final IBinder zab;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zaa = this;
                this.zab = iBinder;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zaa.zaa(this.zab);
            }
        });
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        this.zag.post(new Runnable(this) { // from class: com.google.android.gms.common.api.internal.zabq
            private final NonGmsServiceBrokerClient zaa;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zaa = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zaa.zaa();
            }
        });
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final void connect(BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        zab();
        zaa("Connect started.");
        if (isConnected()) {
            try {
                disconnect("connect() called when already connected");
            } catch (Exception unused) {
            }
        }
        try {
            Intent intent = new Intent();
            ComponentName componentName = this.zad;
            if (componentName != null) {
                intent.setComponent(componentName);
            } else {
                intent.setPackage(this.zab).setAction(this.zac);
            }
            boolean bindService = this.zae.bindService(intent, this, GmsClientSupervisor.getDefaultBindFlags());
            this.zaj = bindService;
            if (!bindService) {
                this.zai = null;
                this.zah.onConnectionFailed(new ConnectionResult(16));
            }
            zaa("Finished connect.");
        } catch (SecurityException e) {
            this.zaj = false;
            this.zai = null;
            throw e;
        }
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final void disconnect(String str) {
        zab();
        this.zak = str;
        disconnect();
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final void disconnect() {
        zab();
        zaa("Disconnect called.");
        this.zae.unbindService(this);
        this.zaj = false;
        this.zai = null;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final boolean isConnected() {
        zab();
        return this.zai != null;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final boolean isConnecting() {
        zab();
        return this.zaj;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final Intent getSignInIntent() {
        return new Intent();
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final String getEndpointPackageName() {
        String str = this.zab;
        if (str != null) {
            return str;
        }
        Preconditions.checkNotNull(this.zad);
        return this.zad.getPackageName();
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final Set<Scope> getScopesForConnectionlessNonSignIn() {
        return Collections.emptySet();
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public final String getLastDisconnectMessage() {
        return this.zak;
    }

    public final IBinder getBinder() {
        zab();
        return this.zai;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zaa() {
        this.zaj = false;
        this.zai = null;
        zaa("Disconnected.");
        this.zaf.onConnectionSuspended(1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zaa(IBinder iBinder) {
        this.zaj = false;
        this.zai = iBinder;
        zaa("Connected.");
        this.zaf.onConnected(new Bundle());
    }
}
