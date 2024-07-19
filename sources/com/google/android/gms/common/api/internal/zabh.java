package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
final class zabh implements Runnable {
    private final /* synthetic */ zabf zaa;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zabh(zabf zabfVar) {
        this.zaa = zabfVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Api.Client client;
        Api.Client client2;
        client = this.zaa.zaa.zac;
        client2 = this.zaa.zaa.zac;
        client.disconnect(String.valueOf(client2.getClass().getName()).concat(" disconnecting because it was signed out."));
    }
}
