package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
final class zaah extends zaba {
    private final /* synthetic */ ConnectionResult zaa;
    private final /* synthetic */ zaai zab;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zaah(zaai zaaiVar, zaay zaayVar, ConnectionResult connectionResult) {
        super(zaayVar);
        this.zab = zaaiVar;
        this.zaa = connectionResult;
    }

    @Override // com.google.android.gms.common.api.internal.zaba
    public final void zaa() {
        this.zab.zaa.zab(this.zaa);
    }
}
