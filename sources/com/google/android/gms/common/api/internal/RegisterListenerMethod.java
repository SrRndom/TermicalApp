package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public abstract class RegisterListenerMethod<A extends Api.AnyClient, L> {
    private final ListenerHolder<L> zaa;
    private final Feature[] zab;
    private final boolean zac;

    protected RegisterListenerMethod(ListenerHolder<L> listenerHolder) {
        this.zaa = listenerHolder;
        this.zab = null;
        this.zac = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void registerListener(A a, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException;

    /* JADX INFO: Access modifiers changed from: protected */
    public RegisterListenerMethod(ListenerHolder<L> listenerHolder, Feature[] featureArr, boolean z) {
        this.zaa = listenerHolder;
        this.zab = featureArr;
        this.zac = z;
    }

    public ListenerHolder.ListenerKey<L> getListenerKey() {
        return this.zaa.getListenerKey();
    }

    public void clearListener() {
        this.zaa.clear();
    }

    public Feature[] getRequiredFeatures() {
        return this.zab;
    }

    public final boolean zaa() {
        return this.zac;
    }
}
