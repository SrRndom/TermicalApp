package com.google.android.gms.common.api.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/* compiled from: com.google.android.gms:play-services-base@@17.4.0 */
/* loaded from: classes.dex */
public final class zabj extends BroadcastReceiver {
    private Context zaa;
    private final zabl zab;

    public zabj(zabl zablVar) {
        this.zab = zablVar;
    }

    public final void zaa(Context context) {
        this.zaa = context;
    }

    public final synchronized void zaa() {
        Context context = this.zaa;
        if (context != null) {
            context.unregisterReceiver(this);
        }
        this.zaa = null;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Uri data = intent.getData();
        if ("com.google.android.gms".equals(data != null ? data.getSchemeSpecificPart() : null)) {
            this.zab.zaa();
            zaa();
        }
    }
}
