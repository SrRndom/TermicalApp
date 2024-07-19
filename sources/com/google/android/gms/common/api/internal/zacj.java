package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
final class zacj implements Runnable {
    private final /* synthetic */ Result zaa;
    private final /* synthetic */ zack zab;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zacj(zack zackVar, Result result) {
        this.zab = zackVar;
        this.zaa = result;
    }

    @Override // java.lang.Runnable
    public final void run() {
        WeakReference weakReference;
        zacm zacmVar;
        zacm zacmVar2;
        WeakReference weakReference2;
        ResultTransform resultTransform;
        zacm zacmVar3;
        zacm zacmVar4;
        WeakReference weakReference3;
        try {
            try {
                BasePendingResult.zaa.set(true);
                resultTransform = this.zab.zaa;
                PendingResult onSuccess = ((ResultTransform) Preconditions.checkNotNull(resultTransform)).onSuccess(this.zaa);
                zacmVar3 = this.zab.zah;
                zacmVar4 = this.zab.zah;
                zacmVar3.sendMessage(zacmVar4.obtainMessage(0, onSuccess));
                BasePendingResult.zaa.set(false);
                zack zackVar = this.zab;
                zack.zaa(this.zaa);
                weakReference3 = this.zab.zag;
                GoogleApiClient googleApiClient = (GoogleApiClient) weakReference3.get();
                if (googleApiClient != null) {
                    googleApiClient.zab(this.zab);
                }
            } catch (RuntimeException e) {
                zacmVar = this.zab.zah;
                zacmVar2 = this.zab.zah;
                zacmVar.sendMessage(zacmVar2.obtainMessage(1, e));
                BasePendingResult.zaa.set(false);
                zack zackVar2 = this.zab;
                zack.zaa(this.zaa);
                weakReference2 = this.zab.zag;
                GoogleApiClient googleApiClient2 = (GoogleApiClient) weakReference2.get();
                if (googleApiClient2 != null) {
                    googleApiClient2.zab(this.zab);
                }
            }
        } catch (Throwable th) {
            BasePendingResult.zaa.set(false);
            zack zackVar3 = this.zab;
            zack.zaa(this.zaa);
            weakReference = this.zab.zag;
            GoogleApiClient googleApiClient3 = (GoogleApiClient) weakReference.get();
            if (googleApiClient3 != null) {
                googleApiClient3.zab(this.zab);
            }
            throw th;
        }
    }
}
