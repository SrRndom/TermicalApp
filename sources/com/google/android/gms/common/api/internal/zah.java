package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class zah extends zae<Boolean> {
    private final ListenerHolder.ListenerKey<?> zac;

    public zah(ListenerHolder.ListenerKey<?> listenerKey, TaskCompletionSource<Boolean> taskCompletionSource) {
        super(4, taskCompletionSource);
        this.zac = listenerKey;
    }

    @Override // com.google.android.gms.common.api.internal.zae, com.google.android.gms.common.api.internal.zac
    public final /* bridge */ /* synthetic */ void zaa(zaw zawVar, boolean z) {
    }

    @Override // com.google.android.gms.common.api.internal.zae
    public final void zad(GoogleApiManager.zaa<?> zaaVar) throws RemoteException {
        zabs remove = zaaVar.zac().remove(this.zac);
        if (remove != null) {
            remove.zab.unregisterListener(zaaVar.zab(), this.zab);
            remove.zaa.clearListener();
        } else {
            this.zab.trySetResult(false);
        }
    }

    @Override // com.google.android.gms.common.api.internal.zab
    public final Feature[] zaa(GoogleApiManager.zaa<?> zaaVar) {
        zabs zabsVar = zaaVar.zac().get(this.zac);
        if (zabsVar == null) {
            return null;
        }
        return zabsVar.zaa.getRequiredFeatures();
    }

    @Override // com.google.android.gms.common.api.internal.zab
    public final boolean zab(GoogleApiManager.zaa<?> zaaVar) {
        zabs zabsVar = zaaVar.zac().get(this.zac);
        return zabsVar != null && zabsVar.zaa.zaa();
    }

    @Override // com.google.android.gms.common.api.internal.zae, com.google.android.gms.common.api.internal.zac
    public final /* bridge */ /* synthetic */ void zaa(Exception exc) {
        super.zaa(exc);
    }

    @Override // com.google.android.gms.common.api.internal.zae, com.google.android.gms.common.api.internal.zac
    public final /* bridge */ /* synthetic */ void zaa(Status status) {
        super.zaa(status);
    }
}
