package com.google.android.gms.common.api.internal;

import android.os.Bundle;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class zaas implements com.google.android.gms.common.internal.zak {
    private final /* synthetic */ zaap zaa;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zaas(zaap zaapVar) {
        this.zaa = zaapVar;
    }

    @Override // com.google.android.gms.common.internal.zak
    public final Bundle getConnectionHint() {
        return null;
    }

    @Override // com.google.android.gms.common.internal.zak
    public final boolean isConnected() {
        return this.zaa.isConnected();
    }
}
