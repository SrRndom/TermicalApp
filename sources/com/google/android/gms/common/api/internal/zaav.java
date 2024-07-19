package com.google.android.gms.common.api.internal;

import java.lang.ref.WeakReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class zaav extends zabl {
    private WeakReference<zaap> zaa;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zaav(zaap zaapVar) {
        this.zaa = new WeakReference<>(zaapVar);
    }

    @Override // com.google.android.gms.common.api.internal.zabl
    public final void zaa() {
        zaap zaapVar = this.zaa.get();
        if (zaapVar == null) {
            return;
        }
        zaapVar.zae();
    }
}
