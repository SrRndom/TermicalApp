package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;
import java.util.concurrent.locks.Lock;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
final class zaaf implements BaseGmsClient.ConnectionProgressReportCallbacks {
    private final WeakReference<zaad> zaa;
    private final Api<?> zab;
    private final boolean zac;

    public zaaf(zaad zaadVar, Api<?> api, boolean z) {
        this.zaa = new WeakReference<>(zaadVar);
        this.zab = api;
        this.zac = z;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks
    public final void onReportServiceBinding(ConnectionResult connectionResult) {
        zaax zaaxVar;
        Lock lock;
        Lock lock2;
        boolean zab;
        boolean zad;
        zaad zaadVar = this.zaa.get();
        if (zaadVar == null) {
            return;
        }
        Looper myLooper = Looper.myLooper();
        zaaxVar = zaadVar.zaa;
        Preconditions.checkState(myLooper == zaaxVar.zad.getLooper(), "onReportServiceBinding must be called on the GoogleApiClient handler thread");
        lock = zaadVar.zab;
        lock.lock();
        try {
            zab = zaadVar.zab(0);
            if (zab) {
                if (!connectionResult.isSuccess()) {
                    zaadVar.zab(connectionResult, this.zab, this.zac);
                }
                zad = zaadVar.zad();
                if (zad) {
                    zaadVar.zae();
                }
            }
        } finally {
            lock2 = zaadVar.zab;
            lock2.unlock();
        }
    }
}
