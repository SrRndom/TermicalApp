package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.internal.BaseGmsClient;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class zabf implements BaseGmsClient.SignOutCallbacks {
    final /* synthetic */ GoogleApiManager.zaa zaa;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zabf(GoogleApiManager.zaa zaaVar) {
        this.zaa = zaaVar;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.SignOutCallbacks
    public final void onSignOutComplete() {
        GoogleApiManager.this.zaq.post(new zabh(this));
    }
}
