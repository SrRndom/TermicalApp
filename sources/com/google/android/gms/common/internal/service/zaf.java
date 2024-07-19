package com.google.android.gms.common.internal.service;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
final class zaf extends zag {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zaf(zac zacVar, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(zaj zajVar) throws RemoteException {
        ((zak) zajVar.getService()).zaa(new zae(this));
    }
}
