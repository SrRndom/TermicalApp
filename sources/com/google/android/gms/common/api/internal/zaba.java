package com.google.android.gms.common.api.internal;

import java.util.concurrent.locks.Lock;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
abstract class zaba {
    private final zaay zaa;

    /* JADX INFO: Access modifiers changed from: protected */
    public zaba(zaay zaayVar) {
        this.zaa = zaayVar;
    }

    protected abstract void zaa();

    public final void zaa(zaax zaaxVar) {
        Lock lock;
        Lock lock2;
        zaay zaayVar;
        lock = zaaxVar.zaf;
        lock.lock();
        try {
            zaayVar = zaaxVar.zan;
            if (zaayVar != this.zaa) {
                return;
            }
            zaa();
        } finally {
            lock2 = zaaxVar.zaf;
            lock2.unlock();
        }
    }
}
