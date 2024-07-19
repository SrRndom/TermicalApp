package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.BackgroundDetector;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class zabc implements BackgroundDetector.BackgroundStateChangeListener {
    private final /* synthetic */ GoogleApiManager zaa;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zabc(GoogleApiManager googleApiManager) {
        this.zaa = googleApiManager;
    }

    @Override // com.google.android.gms.common.api.internal.BackgroundDetector.BackgroundStateChangeListener
    public final void onBackgroundStateChanged(boolean z) {
        this.zaa.zaq.sendMessage(this.zaa.zaq.obtainMessage(1, Boolean.valueOf(z)));
    }
}
