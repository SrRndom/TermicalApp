package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.GoogleApiManager;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class zabd implements Runnable {
    private final /* synthetic */ int zaa;
    private final /* synthetic */ GoogleApiManager.zaa zab;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zabd(GoogleApiManager.zaa zaaVar, int i) {
        this.zab = zaaVar;
        this.zaa = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zab.zaa(this.zaa);
    }
}
