package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class zaf<ResultT> extends zab {
    private final TaskApiCall<Api.AnyClient, ResultT> zab;
    private final TaskCompletionSource<ResultT> zac;
    private final StatusExceptionMapper zad;

    public zaf(int i, TaskApiCall<Api.AnyClient, ResultT> taskApiCall, TaskCompletionSource<ResultT> taskCompletionSource, StatusExceptionMapper statusExceptionMapper) {
        super(i);
        this.zac = taskCompletionSource;
        this.zab = taskApiCall;
        this.zad = statusExceptionMapper;
        if (i == 2 && taskApiCall.shouldAutoResolveMissingFeatures()) {
            throw new IllegalArgumentException("Best-effort write calls cannot pass methods that should auto-resolve missing features.");
        }
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final void zac(GoogleApiManager.zaa<?> zaaVar) throws DeadObjectException {
        Status zab;
        try {
            this.zab.doExecute(zaaVar.zab(), this.zac);
        } catch (DeadObjectException e) {
            throw e;
        } catch (RemoteException e2) {
            zab = zac.zab(e2);
            zaa(zab);
        } catch (RuntimeException e3) {
            zaa(e3);
        }
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final void zaa(Status status) {
        this.zac.trySetException(this.zad.getException(status));
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final void zaa(Exception exc) {
        this.zac.trySetException(exc);
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final void zaa(zaw zawVar, boolean z) {
        zawVar.zaa(this.zac, z);
    }

    @Override // com.google.android.gms.common.api.internal.zab
    public final Feature[] zaa(GoogleApiManager.zaa<?> zaaVar) {
        return this.zab.zaa();
    }

    @Override // com.google.android.gms.common.api.internal.zab
    public final boolean zab(GoogleApiManager.zaa<?> zaaVar) {
        return this.zab.shouldAutoResolveMissingFeatures();
    }
}
