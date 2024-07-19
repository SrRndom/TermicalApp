package com.google.android.gms.common.api.internal;

import android.app.Dialog;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
final class zan extends zabl {
    private final /* synthetic */ Dialog zaa;
    private final /* synthetic */ zal zab;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zan(zal zalVar, Dialog dialog) {
        this.zab = zalVar;
        this.zaa = dialog;
    }

    @Override // com.google.android.gms.common.api.internal.zabl
    public final void zaa() {
        this.zab.zaa.zab();
        if (this.zaa.isShowing()) {
            this.zaa.dismiss();
        }
    }
}
