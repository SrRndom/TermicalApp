package com.google.android.gms.common.api.internal;

import android.content.Context;
import com.google.android.gms.common.GoogleApiAvailabilityLight;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class zaag implements Runnable {
    private final /* synthetic */ zaad zaa;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zaag(zaad zaadVar) {
        this.zaa = zaadVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        GoogleApiAvailabilityLight googleApiAvailabilityLight;
        Context context;
        googleApiAvailabilityLight = this.zaa.zad;
        context = this.zaa.zac;
        googleApiAvailabilityLight.cancelAvailabilityErrorNotifications(context);
    }
}
