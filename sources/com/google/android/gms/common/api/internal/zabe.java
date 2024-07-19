package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.GoogleApiManager;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
final class zabe implements Runnable {
    private final /* synthetic */ GoogleApiManager.zaa zaa;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zabe(GoogleApiManager.zaa zaaVar) {
        this.zaa = zaaVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zaa.zam();
    }
}
