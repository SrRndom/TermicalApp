package com.google.android.gms.common.api.internal;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
final class zabi implements Runnable {
    private final /* synthetic */ ConnectionResult zaa;
    private final /* synthetic */ GoogleApiManager.zac zab;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zabi(GoogleApiManager.zac zacVar, ConnectionResult connectionResult) {
        this.zab = zacVar;
        this.zaa = connectionResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ApiKey apiKey;
        Api.Client client;
        Api.Client client2;
        Api.Client client3;
        Api.Client client4;
        Map map = GoogleApiManager.this.zam;
        apiKey = this.zab.zac;
        GoogleApiManager.zaa zaaVar = (GoogleApiManager.zaa) map.get(apiKey);
        if (zaaVar == null) {
            return;
        }
        if (this.zaa.isSuccess()) {
            GoogleApiManager.zac.zaa(this.zab, true);
            client = this.zab.zab;
            if (!client.requiresSignIn()) {
                try {
                    client3 = this.zab.zab;
                    client4 = this.zab.zab;
                    client3.getRemoteService(null, client4.getScopesForConnectionlessNonSignIn());
                    return;
                } catch (SecurityException e) {
                    Log.e("GoogleApiManager", "Failed to get service from broker. ", e);
                    client2 = this.zab.zab;
                    client2.disconnect("Failed to get service from broker.");
                    zaaVar.onConnectionFailed(new ConnectionResult(10));
                    return;
                }
            }
            this.zab.zaa();
            return;
        }
        zaaVar.onConnectionFailed(this.zaa);
    }
}
