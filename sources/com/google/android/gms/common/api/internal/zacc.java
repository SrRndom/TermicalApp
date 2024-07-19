package com.google.android.gms.common.api.internal;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
final class zacc implements Runnable {
    private final /* synthetic */ com.google.android.gms.signin.internal.zam zaa;
    private final /* synthetic */ zacb zab;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zacc(zacb zacbVar, com.google.android.gms.signin.internal.zam zamVar) {
        this.zab = zacbVar;
        this.zaa = zamVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zab.zab(this.zaa);
    }
}
