package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class zacd implements Runnable {
    private final /* synthetic */ zacb zaa;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zacd(zacb zacbVar) {
        this.zaa = zacbVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zace zaceVar;
        zaceVar = this.zaa.zah;
        zaceVar.zaa(new ConnectionResult(4));
    }
}
