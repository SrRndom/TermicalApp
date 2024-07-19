package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public abstract class zae<T> extends zab {
    protected final TaskCompletionSource<T> zab;

    public zae(int i, TaskCompletionSource<T> taskCompletionSource) {
        super(i);
        this.zab = taskCompletionSource;
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public void zaa(zaw zawVar, boolean z) {
    }

    protected abstract void zad(GoogleApiManager.zaa<?> zaaVar) throws RemoteException;

    @Override // com.google.android.gms.common.api.internal.zac
    public void zaa(Status status) {
        this.zab.trySetException(new ApiException(status));
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public void zaa(Exception exc) {
        this.zab.trySetException(exc);
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final void zac(GoogleApiManager.zaa<?> zaaVar) throws DeadObjectException {
        Status zab;
        Status zab2;
        try {
            zad(zaaVar);
        } catch (DeadObjectException e) {
            zab2 = zac.zab(e);
            zaa(zab2);
            throw e;
        } catch (RemoteException e2) {
            zab = zac.zab(e2);
            zaa(zab);
        } catch (RuntimeException e3) {
            zaa(e3);
        }
    }
}
