package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
final class zaak extends zaba {
    private final /* synthetic */ BaseGmsClient.ConnectionProgressReportCallbacks zaa;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zaak(zaai zaaiVar, zaay zaayVar, BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        super(zaayVar);
        this.zaa = connectionProgressReportCallbacks;
    }

    @Override // com.google.android.gms.common.api.internal.zaba
    public final void zaa() {
        this.zaa.onReportServiceBinding(new ConnectionResult(16, null));
    }
}
