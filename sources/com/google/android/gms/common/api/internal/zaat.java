package com.google.android.gms.common.api.internal;

import android.content.Context;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class zaat implements ResultCallback<Status> {
    private final /* synthetic */ StatusPendingResult zaa;
    private final /* synthetic */ boolean zab;
    private final /* synthetic */ GoogleApiClient zac;
    private final /* synthetic */ zaap zad;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zaat(zaap zaapVar, StatusPendingResult statusPendingResult, boolean z, GoogleApiClient googleApiClient) {
        this.zad = zaapVar;
        this.zaa = statusPendingResult;
        this.zab = z;
        this.zac = googleApiClient;
    }

    @Override // com.google.android.gms.common.api.ResultCallback
    public final /* synthetic */ void onResult(Status status) {
        Context context;
        Status status2 = status;
        context = this.zad.zaj;
        Storage.getInstance(context).zaa();
        if (status2.isSuccess() && this.zad.isConnected()) {
            this.zad.reconnect();
        }
        this.zaa.setResult(status2);
        if (this.zab) {
            this.zac.disconnect();
        }
    }
}
